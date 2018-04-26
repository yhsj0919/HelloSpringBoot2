package xyz.yhsj.spring.entity.user

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable

@Document(collection = "Role")
data class RoleEntity(
        @Id
        var id: String? = null,
        var name: String? = null,
        var password: String? = null
) : Serializable