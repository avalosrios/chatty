package com.avalos.chatty.services

import com.avalos.chatty.generated.types.AddMessageData
import com.avalos.chatty.generated.types.Message
import com.avalos.chatty.repositories.MessageRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest(MessageService::class)
@AutoConfigureMockMvc(addFilters = false)
internal class DefaultMessageServiceTest {

    @Autowired
    private lateinit var service: MessageService

    @MockkBean
    private lateinit var messageRepository: MessageRepository

    @BeforeEach
    fun beforeEach() {
        val mockMessage = com.avalos.chatty.entities.Message(
            id = "1",
            text = "test"
        )
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
            text = "test"
        )

        val result = service.create(input)
        assertThat(result).isNotNull
    }
}
