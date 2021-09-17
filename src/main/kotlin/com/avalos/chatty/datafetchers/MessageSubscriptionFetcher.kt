package com.avalos.chatty.datafetchers

import com.avalos.chatty.generated.DgsConstants
import com.avalos.chatty.generated.types.Message
import com.avalos.chatty.services.MessageService
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsSubscription
import org.reactivestreams.Publisher

@DgsComponent
class MessageSubscriptionFetcher(private val messageService: MessageService) {
    @DgsSubscription(field = DgsConstants.SUBSCRIPTION.MessageAdded)
    fun messageAdded(): Publisher<Message> {
        return messageService.getMessageFlux()
    }
}
