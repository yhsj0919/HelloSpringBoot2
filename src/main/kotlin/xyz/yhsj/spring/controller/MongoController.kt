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
@RequestMapping(value = ["/api"], produces = ["application/json;charset=UTF-8"])
class TodoController {
    @Autowired
    lateinit var userDao: UserDao
    @Autowired
    lateinit var roleDao: RoleDao

    @Autowired
    lateinit var redisSuper: RedisSuper

    /**
     * 查询TODO列表
     */
    @RequestMapping(value = ["/addRole"], method = [RequestMethod.GET])
    fun getAuthorList(request: HttpServletRequest): String {

//        val users = (0..10).map {
//            val user = UserEntity()
//            user.id = it.toString()
//            user.userName = "测试用户$it"
//            user
//        }

//        userDao.saveUsers(users)


        val roles = (0..100)
                .map { roleIndex ->
                    val role = RoleEntity()
                    role.name = "角色$roleIndex"

                    (0..10).map {
                        val user = UserEntity()
                        user.id = it.toString()
                        user
                    }

                    role.users = UserEntity(id = ((Math.random() * 10).toInt()).toString())
                    role
                }


        roleDao.saveRoles(roles)
        return "成功"
    }

    /**
     * 查询TODO列表
     */
    @RequestMapping(value = ["/role"], method = [RequestMethod.GET])
    fun findTodo(request: HttpServletRequest): List<RoleEntity> {

        return roleDao.getRoles()

    }

    /**
     * 查询TODO列表
     */
    @Cacheable(value = ["users"], key = "#userName")
    @RequestMapping(value = ["/user"], method = [RequestMethod.GET])
    fun findUser(userName: String): List<UserEntity> {
        println(userName)

        return userDao.getUsers()

    }


}
