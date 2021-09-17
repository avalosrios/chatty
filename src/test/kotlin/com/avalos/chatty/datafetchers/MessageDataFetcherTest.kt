package com.avalos.chatty.datafetchers

import com.avalos.chatty.generated.client.MessagesGraphQLQuery
import com.avalos.chatty.generated.client.MessagesProjectionRoot
import com.avalos.chatty.generated.types.Message
import com.avalos.chatty.services.MessageService
import com.netflix.graphql.dgs.DgsQueryExecutor
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration
import com.netflix.graphql.dgs.client.GraphQLResponse
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.aspectj.lang.annotation.Before
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import com.avalos.chatty.entities.Message as EntitiesMessage

@SpringBootTest(classes = [DgsAutoConfiguration::class, MessageDataFetcher::class])
internal class MessageDataFetcherTest {

    @Autowired
    lateinit var dgsQueryExecutor: DgsQueryExecutor

    @MockkBean
    lateinit var messageService: MessageService

    @BeforeEach
    fun beforeEach() {
        val mockkMessages = listOf(
            EntitiesMessage(id = "1", text = "test", user = mockk()),
        )
        every { messageService.getMessages(any(), any()) } returns mockkMessages
    }

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

        verify { messageService.getMessages(10, 0) }
    }
}
