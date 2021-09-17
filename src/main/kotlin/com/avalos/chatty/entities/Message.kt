package com.avalos.chatty.entities

import com.avalos.chatty.generated.types.Message
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import javax.persistence.*

@Table(name = "messages")
@Entity
open class Message(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    open var id: String? = null,

    @Column(name = "text", nullable = false, columnDefinition="TEXT")
    open var text: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    open var user: User,

    @CreatedDate
    @Column(nullable = false, updatable = false)
    open var createdAt: LocalDateTime? = LocalDateTime.now(),
) {
    fun toGraphQL(): Message {
        return Message(
            id = id.toString(),
            text = text,
            createdAt = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(createdAt),
        )
    }
}
