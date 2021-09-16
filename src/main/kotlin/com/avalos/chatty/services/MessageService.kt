package com.avalos.chatty.services

import com.avalos.chatty.generated.types.AddMessageData
import com.avalos.chatty.generated.types.Message
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

interface MessageService {
    fun getMessages(first: Int, after: Int): List<Message>
    fun create(input: AddMessageData): Message
}

@Service
@Transactional
class DefaultMessageService(): MessageService {
    val allMessages = mutableListOf(
        Message(id = "1", text = "test"),
    )

    override fun getMessages(first: Int, after: Int): List<Message> {
        // TODO: add pagination logic
        return allMessages
    }

    override fun create(input: AddMessageData): Message {
        val message = Message(
            id = UUID.randomUUID().toString(),
            text = input.text
        )
        allMessages.add(message)
        return message
    }

}
