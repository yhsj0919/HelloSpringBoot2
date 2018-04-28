package xyz.yhsj.spring.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
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
@RestController
@RequestMapping(value = ["/security"], produces = ["application/json;charset=UTF-8"])
class SecurityController {

    @RequestMapping(value = ["/success"], method = [RequestMethod.GET])
    fun success(userName: String?): String? {
        return "成功页面"
    }

    @RequestMapping(value = ["/failure"], method = [RequestMethod.GET])
    fun failure(): String? {
        return "失败页面"
    }

    @RequestMapping(value = ["/about"], method = [RequestMethod.GET])
    fun about(): String? {
        return "关于页面"
    }

}
