package com.avalos.chatty.services

import com.avalos.chatty.entities.User
import com.avalos.chatty.generated.types.SetUserData
import com.avalos.chatty.repositories.UserRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional
import com.avalos.chatty.generated.types.User as GraphQLUser

interface UserService {
    fun getUsers(first: Int, after: Int): List<GraphQLUser>
    fun create(input: SetUserData): GraphQLUser
}

@Service
@Transactional
class DefaultUserService(
    private val userRepository: UserRepository,
): UserService {
    override fun getUsers(first: Int, after: Int): List<GraphQLUser> {
        // TODO: add pagination logic
        return userRepository.findAll().map { user -> user.toGraphQL() }
    }

    override fun create(input: SetUserData): GraphQLUser {
        // check if we have a user with the same name
        var user = userRepository.findOneByName(input.name)
        if (user == null) {
            user = User(
                name = input.name
            )
        }
        return userRepository.save(user).toGraphQL()
    }
}
