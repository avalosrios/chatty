package com.avalos.chatty.services

import com.avalos.chatty.generated.types.AddMessageData
import com.avalos.chatty.generated.types.Message
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

    @BeforeEach
    fun beforeEach() {
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
