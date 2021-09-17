package com.avalos.chatty.datafetchers

import com.avalos.chatty.generated.DgsConstants
import com.avalos.chatty.generated.types.User
import com.avalos.chatty.services.UserService
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import graphql.relay.*
import graphql.schema.DataFetchingEnvironment

@DgsComponent
class UserDataFetcher(private val userService: UserService) {

    @DgsQuery(field = DgsConstants.QUERY.Users)
    fun users(dfe: DataFetchingEnvironment): Connection<User> {
        val first = dfe.arguments["first"] as Int? ?: 10
        val after = dfe.arguments["after"] as Int? ?: 0

        val users = userService.getUsers(first, after).map { user -> user.toGraphQL() }
        val edges = users.mapIndexed { idx, msg -> DefaultEdge(msg, DefaultConnectionCursor(idx.toString())) }
        val startCursor = if (edges.isNotEmpty()) edges[0].cursor else null
        val endCursor = if (edges.isNotEmpty()) edges[users.size - 1].cursor else null
        val hasPreviousPage = after > 0 // TODO: this is not accurate we need to implement pagination
        val hasNextPage = true // TODO: Make sure we calculate this one
        val pageInfo = DefaultPageInfo(startCursor, endCursor, hasPreviousPage, hasNextPage)
        return DefaultConnection(
            edges,
            pageInfo,
        )
    }
}
