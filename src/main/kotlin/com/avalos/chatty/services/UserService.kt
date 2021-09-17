package com.avalos.chatty.services

import com.avalos.chatty.entities.User
import com.avalos.chatty.generated.types.SetUserData
import com.avalos.chatty.repositories.UserRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional


interface UserService {
    fun getUsers(first: Int, after: Int): List<User>
    fun create(input: SetUserData): User
}

@Service
@Transactional
class DefaultUserService(
    private val userRepository: UserRepository,
): UserService {
    override fun getUsers(first: Int, after: Int): List<User> {
        // TODO: add pagination logic
        return userRepository.findAll()
    }

    override fun create(input: SetUserData): User {
        // check if we have a user with the same name
        var user = userRepository.findOneByName(input.name)
        if (user == null) {
            user = User(
                name = input.name
            )
        }
        return userRepository.save(user)
    }
}
