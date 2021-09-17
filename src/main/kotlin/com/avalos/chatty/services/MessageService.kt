package com.avalos.chatty.services

import com.avalos.chatty.entities.Message
import com.avalos.chatty.generated.types.AddMessageData
import com.avalos.chatty.repositories.MessageRepository
import com.avalos.chatty.repositories.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import javax.transaction.Transactional
import com.avalos.chatty.generated.types.Message as TypesMessage

interface MessageService {
    fun getMessages(first: Int, after: Int): List<Message>
    fun create(input: AddMessageData): Message
    fun getMessageById(id: String): Message
    fun getMessageFlux(): Flux<TypesMessage>
}

@Service
@Transactional
class DefaultMessageService(
    private val messageRepository: MessageRepository,
    private val userRepository: UserRepository,
): MessageService {
    private val sink: Sinks.Many<TypesMessage> = Sinks.many().replay().limit(1)

    override fun getMessages(first: Int, after: Int): List<Message> {
        // TODO: implement pagination
        return messageRepository.findAll()
    }

    override fun create(input: AddMessageData): Message {
        // check that the user exists
        // TODO: make this a pretty exception
        val user = userRepository.findById(input.userID).orElseThrow()
        var message = Message(
            text = input.text,
            user = user,
        )
        message = messageRepository.save(message)
        sink.emitNext(message.toGraphQL(), Sinks.EmitFailureHandler.FAIL_FAST)
        return message
    }

    override fun getMessageById(id: String): Message {
        return messageRepository.findById(id).orElseThrow()
    }

    override fun getMessageFlux(): Flux<TypesMessage> {
        return sink.asFlux()
    }
}
