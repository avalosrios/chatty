package com.avalos.chatty.services

import com.avalos.chatty.entities.Message
import com.avalos.chatty.generated.types.AddMessageData
import com.avalos.chatty.repositories.MessageRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional
import com.avalos.chatty.generated.types.Message as GraphQLMessage

interface MessageService {
    fun getMessages(first: Int, after: Int): List<GraphQLMessage>
    fun create(input: AddMessageData): GraphQLMessage
}

@Service
@Transactional
class DefaultMessageService(
    private val messageRepository: MessageRepository,
): MessageService {
    override fun getMessages(first: Int, after: Int): List<GraphQLMessage> {
        // TODO: implement pagination
        return messageRepository.findAll().map { message ->  message.toGraphQL() }
    }

    override fun create(input: AddMessageData): GraphQLMessage {
        val message = Message(
            text = input.text
        )
        return messageRepository.save(message).toGraphQL()
    }
}
