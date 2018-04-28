package xyz.yhsj.spring.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.util.Assert

import java.util.ArrayList

/**
 * 安全验证代理类
 */

@Component
class SpringAuthenticationProvider : AuthenticationProvider {

    @Autowired
    private val userDetailsService: SpringUserDetailsService? = null

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        Assert.isInstanceOf(UsernamePasswordAuthenticationToken::class.java, authentication, "非验证类型")
        //账号
        val username = authentication.name
        //密码
        val password = authentication.credentials.toString()

        println("加密前的密码： $password")
        val encoder = BCryptPasswordEncoder()
        println("加密后的密码： " + encoder.encode(password))

        println("密码对比" + encoder.matches(password, "\$2a\$10\$uvYdNLuhqR4iPjVBRxV3cujgmFa8hOqt3dhZ7xv/oncRLchhPLW1q"))


        val userDetails = userDetailsService!!.loadUserByUsername(username)
                ?: throw UsernameNotFoundException("用户名/密码无效")


        val roles = ArrayList(userDetails.authorities)
        val token = UsernamePasswordAuthenticationToken(username, password, roles)
        token.details = userDetails
        return token
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java
                .isAssignableFrom(authentication)
    }
}