package com.avalos.chatty.entities

import com.avalos.chatty.generated.types.User
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "users")
@Entity
open class User(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    var id: String? = null,

    @Column(name = "name", nullable = false, unique = true)
    var name: String,

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime? = LocalDateTime.now(),
) {
    fun toGraphQL(): User {
        return User(
            id = id.toString(),
            name = name,
        )
    }
}
