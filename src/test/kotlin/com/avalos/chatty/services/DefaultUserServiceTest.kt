package com.avalos.chatty.services

import com.avalos.chatty.entities.User
import com.avalos.chatty.generated.types.SetUserData
import com.avalos.chatty.repositories.UserRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest(UserService::class)
@AutoConfigureMockMvc(addFilters = false)
internal class DefaultUserServiceTest {

    @Autowired
    lateinit var service: UserService

    @MockkBean
    private lateinit var userRepository: UserRepository

    @BeforeEach
    fun beforeEach() {
        val mockUser = User(
            id = "1",
            name = "test",
        )
        every { userRepository.findAll() } returns listOf(mockUser)
        every { userRepository.save(any()) } returns mockUser
        every { userRepository.findOneByName(any()) } returns null
    }

    @Test
    fun getUsers() {
        val result = service.getUsers(1, 0)
        assertThat(result).isNotEmpty
    }

    @Test
    fun create() {
        val input = SetUserData(
            name = "test"
        )

        val result = service.create(input)
        assertThat(result).isNotNull
    }
}
