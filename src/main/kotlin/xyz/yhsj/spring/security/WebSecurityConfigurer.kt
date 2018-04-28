package xyz.yhsj.spring.security

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.stereotype.Component

/**
 * 权限控制，表明各个接口的权限
 * 参考自https://www.jianshu.com/p/f7609dee0c72
 */
@Component
class WebSecurityConfigurer : WebSecurityConfigurerAdapter() {
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
                .antMatchers("/resources/**", "/signup", "/security/about").permitAll()
                //"/admin/**" 路径为 ADMIN 角色可访问
                .antMatchers("/security/failure").hasRole("ADMIN")
                //"/db/**" 路径为 ADMIN 和 DBA 角色同时拥有时可访问
                .antMatchers("/security/success").access("hasRole('ADMIN') and hasRole('DBA')")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()

        super.configure(http)
    }
}
