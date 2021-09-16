package com.avalos.chatty.services

import com.avalos.chatty.generated.types.SetUserData
import com.avalos.chatty.generated.types.User
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

interface UserService {
    fun getUsers(first: Int, after: Int): List<User>
    fun create(input: SetUserData): User
}

@Service
@Transactional
class DefaultUserService(): UserService {
    // TODO: remove this and use repository
    val allUsers = mutableListOf(
        User(id = "1", name = "test"),
    )

    override fun getUsers(first: Int, after: Int): List<User> {
        // TODO: add pagination logic
        return allUsers
    }

    override fun create(input: SetUserData): User {
        val user = User(
            id = UUID.randomUUID().toString(),
            name = input.name
        )
        allUsers.add(user)
        return user
    }

}
