package com.avalos.chatty.datafetchers

import com.avalos.chatty.generated.DgsConstants
import com.avalos.chatty.generated.types.SetUserData
import com.avalos.chatty.generated.types.User
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument

@DgsComponent
class UserMutation {
    @DgsMutation(field = DgsConstants.MUTATION.SetUser)
    fun setUser(@InputArgument("input") input: SetUserData): User {
        return User(id = "1", name = input.name)
    }
}
