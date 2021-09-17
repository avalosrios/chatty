package com.avalos.chatty.services

import com.avalos.chatty.entities.User
import com.avalos.chatty.generated.types.AddMessageData
import com.avalos.chatty.generated.types.Message
import com.avalos.chatty.repositories.MessageRepository
import com.avalos.chatty.repositories.UserRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import java.util.*

@WebMvcTest(MessageService::class)
@AutoConfigureMockMvc(addFilters = false)
internal class DefaultMessageServiceTest {

    @Autowired
    private lateinit var service: MessageService

    @MockkBean
    private lateinit var messageRepository: MessageRepository

    @MockkBean
    private lateinit var userRepository: UserRepository

    @BeforeEach
    fun beforeEach() {
        val mockUser = User(
            id = "2",
            name = "test"
        )
        val mockMessage = com.avalos.chatty.entities.Message(
            id = "1",
            text = "test",
            user = mockUser,
        )
        every { userRepository.findById(any()) } returns Optional.of(mockUser)
        every { messageRepository.findAll() } returns listOf(mockMessage)
        every { messageRepository.save(any()) } returns mockMessage
    }

    @Test
    fun getMessages() {
        val result = service.getMessages(1, 0)
        assertThat(result).isNotEmpty
    }

    @Test
    fun create() {
        val input = AddMessageData(
            text = "test",
            userID = "2",
        )
        val result = service.create(input)
        assertThat(result).isNotNull
        verify { userRepository.findById("2") }
        verify { messageRepository.save(any()) }
    }
}
