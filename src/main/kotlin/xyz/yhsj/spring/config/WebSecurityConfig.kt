package xyz.yhsj.spring.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Configurable
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService


@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var provider: MyAuthenticationProvider//自定义验证

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                //禁用CSRF保护
                .csrf().disable()
                .authorizeRequests()
                //任何访问都必须授权
//                .anyRequest()
                // .fullyAuthenticated()
                //配置那些路径可以不用权限访问
                .antMatchers("/api/success")
//                .permitAll()  .antMatchers("/api/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/api/login")
                .failureForwardUrl("/api/login?error")
                .permitAll()
                .and()
                .logout()
                .permitAll()
    }

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        //将验证过程交给自定义验证工具


        println(">>>>>>>>>>>>将验证过程交给自定义验证工具>>>>>>>>>>>>>>>>>>>")
        auth.authenticationProvider(provider)
    }

}
