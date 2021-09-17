package com.avalos.chatty.datafetchers

import com.avalos.chatty.generated.client.SetUserGraphQLQuery
import com.avalos.chatty.generated.client.SetUserProjectionRoot
import com.avalos.chatty.generated.types.SetUserData
import com.avalos.chatty.generated.types.User
import com.avalos.chatty.services.UserService
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
import com.avalos.chatty.entities.User as EntitiesUser

@SpringBootTest(classes = [DgsAutoConfiguration::class, UserMutation::class])
internal class UserMutationTest {

    @Autowired
    lateinit var dgsQueryExecutor: DgsQueryExecutor

    @MockkBean
    lateinit var userService: UserService

    @BeforeEach
    fun beforeEach() {
        val mockUser = EntitiesUser(id = "1", name = "test")
        every { userService.create(any()) } returns mockUser
    }

    @Test
    fun setUser() {
        val input = SetUserData(
            name = "test"
        )
        val graphQLQueryRequest =
            GraphQLQueryRequest(
                SetUserGraphQLQuery.Builder()
                    .input(input)
                    .build(),
                SetUserProjectionRoot()
                    .name()
            )

        val context = dgsQueryExecutor.executeAndGetDocumentContext(graphQLQueryRequest.serialize())
        val response = GraphQLResponse(context.jsonString())
        val name = response.extractValue<String>("data.setUser.name")
        Assertions.assertThat(name).contains("test")

        verify { userService.create(any()) }
    }
}
