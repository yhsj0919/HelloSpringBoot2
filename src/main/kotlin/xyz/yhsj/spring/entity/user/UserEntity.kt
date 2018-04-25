package xyz.yhsj.spring.entity.user

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable

@Document(collection = "User")
data class UserEntity(
        @Id
        var id: String? = null,
        var userName: String? = null,
        var passWord: String? = null
) : Serializable