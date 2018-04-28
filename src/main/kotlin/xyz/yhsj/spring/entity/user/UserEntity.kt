package xyz.yhsj.spring.entity.user

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serializable
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.authority.SimpleGrantedAuthority


@Document(collection = "User")
data class UserEntity(
        @Id
        var id: String? = null,
        var userName: String? = null,
        var passWord: String? = null,
        @DBRef
        var roles: ArrayList<RoleEntity> = ArrayList()
) : Serializable, UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = java.util.ArrayList<GrantedAuthority>()
        for (role in roles) {
            authorities.add(SimpleGrantedAuthority(role.name))
        }
        return authorities

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