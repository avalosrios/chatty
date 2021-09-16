package com.avalos.chatty.datafetchers

import com.avalos.chatty.generated.client.MessagesGraphQLQuery
import com.avalos.chatty.generated.client.MessagesProjectionRoot
import com.netflix.graphql.dgs.DgsQueryExecutor
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration
import com.netflix.graphql.dgs.client.GraphQLResponse
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [DgsAutoConfiguration::class, MessageDataFetcher::class])
internal class MessageDataFetcherTest {

    @Autowired
    lateinit var dgsQueryExecutor: DgsQueryExecutor

    // TODO: Add a service and mock responses

    @Test
    fun messages() {
        val graphQLQueryRequest =
            GraphQLQueryRequest(
                MessagesGraphQLQuery.Builder()
                    .build(),
                MessagesProjectionRoot()
                    .edges()
                    .node()
                    .text()
            )

        val context = dgsQueryExecutor.executeAndGetDocumentContext(
            graphQLQueryRequest.serialize()
        )
        val response = GraphQLResponse(context.jsonString())
        val text = response.extractValue<String>("data.messages.edges[0].node.text")
        Assertions.assertThat(text).contains("test")
    }
}
