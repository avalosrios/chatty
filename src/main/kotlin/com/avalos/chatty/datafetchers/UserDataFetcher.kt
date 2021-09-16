package com.avalos.chatty.datafetchers

import com.avalos.chatty.generated.DgsConstants
import com.avalos.chatty.generated.types.User
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import graphql.relay.*
import graphql.schema.DataFetchingEnvironment

@DgsComponent
class UserDataFetcher {
    @DgsQuery(field = DgsConstants.QUERY.Users)
    fun users(dfe: DataFetchingEnvironment): Connection<User> {
        val first = dfe.arguments["first"] as Int? ?: 10
        val after = dfe.arguments["after"] as Int? ?: 0

        val users = listOf(
            User(id = "1", name = "test")
        )
        val edges = users.mapIndexed { idx, msg -> DefaultEdge(msg, DefaultConnectionCursor(idx.toString())) }
        val hasNextPage = true // TODO: Make sure we calculate this one
        return DefaultConnection(
            edges,
            DefaultPageInfo(edges[0].cursor, edges[users.size - 1].cursor, after > 0, hasNextPage)
        )
    }
}