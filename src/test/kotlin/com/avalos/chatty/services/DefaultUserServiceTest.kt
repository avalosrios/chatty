package com.avalos.chatty.services

import com.avalos.chatty.generated.types.SetUserData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest(UserService::class)
@AutoConfigureMockMvc(addFilters = false)
internal class DefaultUserServiceTest {

    @Autowired
    lateinit var service: UserService

    @Test
    fun getUsers() {
        val result = service.getUsers(1, 0)
        assertThat(result).isNotEmpty
    }

    @Test
    fun create() {
        val input = SetUserData(
            name = "test"
        )

        val result = service.create(input)
        assertThat(result).isNotNull
    }
}
