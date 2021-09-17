package com.avalos.chatty.datafetchers

import com.avalos.chatty.generated.DgsConstants
import com.avalos.chatty.generated.types.Message
import com.avalos.chatty.generated.types.User
import com.avalos.chatty.services.MessageService
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsQuery
import graphql.relay.*
import graphql.schema.DataFetchingEnvironment

@DgsComponent
class MessageDataFetcher(private val messageService: MessageService) {
    @DgsQuery(field = DgsConstants.QUERY.Messages)
    fun messages(dfe: DataFetchingEnvironment): Connection<Message> {
        val first = dfe.arguments["first"] as Int? ?: 10
        val after = dfe.arguments["after"] as Int? ?: 0
        val messages = messageService.getMessages(first, after).map { message ->  message.toGraphQL() }
        val edges = messages.mapIndexed { idx, msg -> DefaultEdge(msg, DefaultConnectionCursor(idx.toString())) }
        val hasPreviousPage = after > 0 // TODO: this is not accurate we need to implement pagination
        val hasNextPage = edges.isNotEmpty() // TODO: Make sure we calculate this one
        val startCursor = if (edges.isNotEmpty()) edges[0].cursor else null
        val endCursor = if (edges.isNotEmpty()) edges[messages.size - 1].cursor else null
        val pageInfo = DefaultPageInfo(startCursor, endCursor, hasPreviousPage, hasNextPage)
        return DefaultConnection(
            edges,
            pageInfo,
        )
    }

    @DgsData(parentType = DgsConstants.MESSAGE.TYPE_NAME, field = DgsConstants.MESSAGE.User)
    fun user(dfe: DgsDataFetchingEnvironment): User {
        val contextMessage = dfe.getSource<Message>()
        val message = messageService.getMessageById(contextMessage.id)
        return message.user.toGraphQL()
    }
}
