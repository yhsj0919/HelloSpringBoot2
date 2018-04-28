package xyz.yhsj.spring.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import xyz.yhsj.spring.dao.user.RoleDao
import xyz.yhsj.spring.dao.user.UserDao
import xyz.yhsj.spring.entity.user.RoleEntity
import xyz.yhsj.spring.entity.user.UserEntity
import xyz.yhsj.spring.utils.RedisSuper
import javax.servlet.http.HttpServletRequest


/**
 * 控制器
 * Created by LOVE on 2018/02/28 028.
 */
@Controller
class ViewController {
    @RequestMapping("/login")
    fun login(): String {
        return "login"
    }
}
