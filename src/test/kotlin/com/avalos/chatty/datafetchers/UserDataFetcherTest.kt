package com.avalos.chatty.datafetchers

import com.avalos.chatty.generated.client.UsersGraphQLQuery
import com.avalos.chatty.generated.client.UsersProjectionRoot
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

@SpringBootTest(classes = [DgsAutoConfiguration::class, UserDataFetcher::class])
internal class UserDataFetcherTest {

    @Autowired
    lateinit var dgsQueryExecutor: DgsQueryExecutor

    @MockkBean
    lateinit var userService: UserService

    @BeforeEach
    fun beforeEach() {
        val mockUsers = listOf(
            EntitiesUser(id = "1", name = "test")
        )
        every { userService.getUsers(any(), any()) } returns mockUsers
    }

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

        verify { userService.getUsers(10, 0) }
    }
}
