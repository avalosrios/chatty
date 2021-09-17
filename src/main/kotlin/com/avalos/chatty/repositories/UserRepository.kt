package com.avalos.chatty.repositories;

import com.avalos.chatty.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, String> {
    fun findOneByName(name: String): User?
}
