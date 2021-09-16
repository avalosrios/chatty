package com.avalos.chatty.datafetchers

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.avalos.chatty.generated.DgsConstants
import com.avalos.chatty.generated.types.AddMessageData
import com.avalos.chatty.generated.types.Message
import com.avalos.chatty.services.MessageService
import com.netflix.graphql.dgs.InputArgument

@DgsComponent
class MessageMutation(private val messageService: MessageService) {
    @DgsMutation(field = DgsConstants.MUTATION.AddMessage)
    fun addMessage(@InputArgument("input") input: AddMessageData): Message {
        return messageService.create(input)
    }
}
