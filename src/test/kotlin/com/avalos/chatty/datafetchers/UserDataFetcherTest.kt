package com.avalos.chatty.datafetchers

import com.avalos.chatty.generated.client.UsersGraphQLQuery
import com.avalos.chatty.generated.client.UsersProjectionRoot
import com.netflix.graphql.dgs.DgsQueryExecutor
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration
import com.netflix.graphql.dgs.client.GraphQLResponse
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [DgsAutoConfiguration::class, UserDataFetcher::class])
internal class UserDataFetcherTest {

    @Autowired
    lateinit var dgsQueryExecutor: DgsQueryExecutor

    // TODO: Add a service and mock responses

    @Test
    fun users() {
        val graphQLQueryRequest =
            GraphQLQueryRequest(
                UsersGraphQLQuery.Builder()
                    .build(),
                UsersProjectionRoot()
                    .edges()
                    .node()
                    .name()
            )

        val context = dgsQueryExecutor.executeAndGetDocumentContext(
            graphQLQueryRequest.serialize()
        )
        val response = GraphQLResponse(context.jsonString())
        val name = response.extractValue<String>("data.users.edges[0].node.name")
        Assertions.assertThat(name).contains("test")
    }
}
