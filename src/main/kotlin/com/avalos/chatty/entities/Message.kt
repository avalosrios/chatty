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
class Message(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    var id: String? = null,

    @Column(name = "text", nullable = false, columnDefinition="TEXT")
    var text: String,

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime? = LocalDateTime.now(),
) {
    fun toGraphQL(): Message {
        return Message(
            id = id.toString(),
            text = text,
            createdAt = OffsetDateTime.of(createdAt, ZoneOffset.UTC).format(DateTimeFormatter.ISO_DATE),
        )
    }
}
