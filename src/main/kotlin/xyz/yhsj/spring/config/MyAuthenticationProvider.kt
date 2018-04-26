package xyz.yhsj.spring.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import xyz.yhsj.spring.dao.user.UserDao
import xyz.yhsj.spring.dao.user.impl.UserDaoImpl
import xyz.yhsj.spring.entity.user.UserEntity

@Component
class MyAuthenticationProvider : AuthenticationProvider {

    @Autowired
    lateinit var userService: UserDao

    /**
     * 自定义验证方式
     */
    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {

        println(">>>>>>>>>>>>>自定义验证方式>>>>>>>>>>>>>>>>>")


        val username = authentication.name
        val password = authentication.credentials as String
        val user = userService.findUserByUserName(username) ?: throw BadCredentialsException("Username not found.")

        //加密过程在这里体现
        if (password != user.password) {
            throw BadCredentialsException("Wrong password.")
        }

        val authorities = user.authorities
        return UsernamePasswordAuthenticationToken(user, password, authorities)
    }

    override fun supports(arg0: Class<*>): Boolean {
        return true
    }

}
