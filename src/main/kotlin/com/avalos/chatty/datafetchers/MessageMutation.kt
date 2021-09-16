package com.avalos.chatty.datafetchers

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.avalos.chatty.generated.DgsConstants
import com.avalos.chatty.generated.types.AddMessageData
import com.avalos.chatty.generated.types.Message
import com.netflix.graphql.dgs.InputArgument

@DgsComponent
class MessageMutation {
    @DgsMutation(field = DgsConstants.MUTATION.AddMessage)
    fun addMessage(@InputArgument("input") input: AddMessageData): Message {
        return Message(id = "1", text = input.text)
    }
}