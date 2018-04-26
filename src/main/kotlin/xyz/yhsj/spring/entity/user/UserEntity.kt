package xyz.yhsj.spring.entity.user

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serializable
import org.springframework.security.core.authority.AuthorityUtils


@Document(collection = "User")
data class UserEntity(
        @Id
        var id: String? = null,
        var userName: String? = null,
        var passWord: String? = null,
        @DBRef
        var rolers: ArrayList<RoleEntity> = ArrayList()
) : Serializable, UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        if (rolers.isEmpty()) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList("")
        }
        val commaBuilder = StringBuilder()
        for (role in rolers) {
            commaBuilder.append(role.name).append(",")
        }
        val authorities = commaBuilder.substring(0, commaBuilder.length - 1)
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities)

    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String? {
        return this.userName
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String? {
        return this.passWord
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }
}