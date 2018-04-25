package xyz.yhsj.spring.controller


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import xyz.yhsj.spring.dao.user.RoleRepository
import xyz.yhsj.spring.dao.user.UserRepository
import xyz.yhsj.spring.entity.user.SysRole
import xyz.yhsj.spring.entity.user.SysUser
import javax.persistence.EntityManager
import javax.servlet.http.HttpServletRequest
import javax.swing.text.html.parser.Entity

@RestController
@RequestMapping(value = ["/user"], produces = ["application/json;charset=UTF-8"])
class UserController {
    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var roleRepository: RoleRepository

    /**
     *
     * @param userParam
     * @param request
     * @return
     */
    @RequestMapping(value = ["/addUser"], method = [(RequestMethod.GET)], name = "登录")
    fun addUser(request: HttpServletRequest): Any {

        val roles = (1..10).map {
            val role = SysRole()
            role.name = "角色$it"
            role
        }

        roleRepository.saveAll(roles)


        val users = (0..100).map {
            val user = SysUser()
            user.username = "用户$it"
            user.role = SysRole((Math.random() * 10 + 1).toLong(), name = "角色ddd")
            user
        }
        userRepository.saveAll(users)


        return "成功"
    }

    /**
     *
     * @param userParam
     * @param request
     * @return
     */
    @RequestMapping(value = ["/user"], method = [(RequestMethod.GET)], name = "登录")
    fun user(request: HttpServletRequest): Any {

        return userRepository.findAll()
                .map {
                    println(it.role?.name)
                    it
                }
    }

    /**
     *
     * @param userParam
     * @param request
     * @return
     */
    @RequestMapping(value = ["/user2"], method = [(RequestMethod.GET)], name = "登录")
    fun user2(request: HttpServletRequest): Any {

        return roleRepository.findAll()
    }
}
