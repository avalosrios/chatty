package com.avalos.chatty.datafetchers

import com.avalos.chatty.generated.DgsConstants
import com.avalos.chatty.generated.types.Message
import com.avalos.chatty.services.MessageService
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import graphql.relay.*
import graphql.schema.DataFetchingEnvironment

@DgsComponent
class MessageDataFetcher(private val messageService: MessageService) {
    @DgsQuery(field = DgsConstants.QUERY.Messages)
    fun messages(dfe: DataFetchingEnvironment): Connection<Message> {
        val first = dfe.arguments["first"] as Int? ?: 10
        val after = dfe.arguments["after"] as Int? ?: 0
        val messages = messageService.getMessages(first, after)
        val edges = messages.mapIndexed { idx, msg -> DefaultEdge(msg, DefaultConnectionCursor(idx.toString())) }
        val hasNextPage = true // TODO: Make sure we calculate this one
        return DefaultConnection(
            edges,
            DefaultPageInfo(edges[0].cursor, edges[messages.size - 1].cursor, after > 0, hasNextPage)
        )
    }
}