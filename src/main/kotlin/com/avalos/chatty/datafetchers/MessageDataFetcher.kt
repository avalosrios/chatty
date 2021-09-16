package com.avalos.chatty.datafetchers

import com.avalos.chatty.generated.DgsConstants
import com.avalos.chatty.generated.types.Message
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import graphql.relay.*
import graphql.schema.DataFetchingEnvironment

@DgsComponent
class MessageDataFetcher {
    @DgsQuery(field = DgsConstants.QUERY.Messages)
    fun messages(dfe: DataFetchingEnvironment): Connection<Message> {
        val first = dfe.arguments["first"] as Int? ?: 10
        val after = dfe.arguments["after"] as Int? ?: 0
        val messages = listOf(
            Message(id = "1", text = "test"),
            Message(id = "2", text = "b"),
            Message(id = "3", text = "c")
        )
        val edges = messages.mapIndexed { idx, msg -> DefaultEdge(msg, DefaultConnectionCursor(idx.toString())) }
        val hasNextPage = true // TODO: Make sure we calculate this one
        return DefaultConnection(
            edges,
            DefaultPageInfo(edges[0].cursor, edges[messages.size - 1].cursor, after > 0, hasNextPage)
        )
    }
}