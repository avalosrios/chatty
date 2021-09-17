package com.avalos.chatty.services

import com.avalos.chatty.entities.Message
import com.avalos.chatty.generated.types.AddMessageData
import com.avalos.chatty.repositories.MessageRepository
import com.avalos.chatty.repositories.UserRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

interface MessageService {
    fun getMessages(first: Int, after: Int): List<Message>
    fun create(input: AddMessageData): Message
    fun getMessageById(id: String): Message
}

@Service
@Transactional
class DefaultMessageService(
    private val messageRepository: MessageRepository,
    private val userRepository: UserRepository,
): MessageService {
    override fun getMessages(first: Int, after: Int): List<Message> {
        // TODO: implement pagination
        return messageRepository.findAll()
    }

    override fun create(input: AddMessageData): Message {
        // check that the user exists
        // TODO: make this a pretty exception
        val user = userRepository.findById(input.userID).orElseThrow()
        val message = Message(
            text = input.text,
            user = user,
        )
        return messageRepository.save(message)
    }

    override fun getMessageById(id: String): Message {
        return messageRepository.findById(id).orElseThrow()
    }
}
