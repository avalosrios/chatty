package com.avalos.chatty.datafetchers

import com.avalos.chatty.generated.client.AddMessageGraphQLQuery
import com.avalos.chatty.generated.client.AddMessageProjectionRoot
import com.avalos.chatty.generated.types.AddMessageData
import com.avalos.chatty.generated.types.Message
import com.avalos.chatty.services.MessageService
import com.netflix.graphql.dgs.DgsQueryExecutor
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration
import com.netflix.graphql.dgs.client.GraphQLResponse
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [DgsAutoConfiguration::class, MessageMutation::class])
internal class MessageMutationTest {

    @Autowired
    lateinit var dgsQueryExecutor: DgsQueryExecutor

    @MockkBean
    lateinit var messageService: MessageService

    @BeforeEach
    fun beforeEach() {
        val mockMessage = Message(id = "1", text = "test")
        every { messageService.create(any()) } returns mockMessage
    }

    @Test
    fun addMessage() {
        val input = AddMessageData(
            text = "test"
        )
        val graphQLQueryRequest =
            GraphQLQueryRequest(
                AddMessageGraphQLQuery.Builder()
                    .input(input)
                    .build(),
                AddMessageProjectionRoot()
                    .text()
            )

        val context = dgsQueryExecutor.executeAndGetDocumentContext(graphQLQueryRequest.serialize())
        val response = GraphQLResponse(context.jsonString())
        val text = response.extractValue<String>("data.addMessage.text")
        Assertions.assertThat(text).contains("test")

        verify { messageService.create(any()) }
    }
}
