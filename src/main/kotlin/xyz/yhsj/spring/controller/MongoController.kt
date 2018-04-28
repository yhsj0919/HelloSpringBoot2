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

//        val roles = (1..2).map {
//            val user = RoleEntity()
//            user.id = it.toString()
//            user.name = "角色$it"
//            user
//        }

//        roleDao.saveRole(RoleEntity(name = "ROLE_admin"))
//        roleDao.saveRole(RoleEntity(name = "ROLE_user"))

        userDao.saveUser(UserEntity(userName = "root", passWord = "123456",roles = arrayListOf(RoleEntity(id = "5ae188d9ea5cd72bdcb456f8"))))
        userDao.saveUser(UserEntity(userName = "yhsj", passWord = "123456", roles = arrayListOf(RoleEntity(id = "5ae188d9ea5cd72bdcb456f9"))))

//        userDao.updateUser(UserEntity(id = "5ae188d9ea5cd72bdcb456fa", roles = arrayListOf(RoleEntity(id = "5ae188d9ea5cd72bdcb456f8"))))
//        userDao.updateUser(UserEntity(id = "5ae188d9ea5cd72bdcb456fb", roles = arrayListOf(RoleEntity(id = "5ae188d9ea5cd72bdcb456f9"))))


//        val users = (1..10)
//                .map { roleIndex ->
//                    val role = UserEntity()
//                    role.userName = "用户名称$roleIndex"
//
//                    role.rolers = arrayListOf(RoleEntity(id = ((Math.random() * 2).toInt()).toString()))
//                    role
//                }
//        userDao.saveUsers(users)
        return "成功"
    }

    /**
     * 查询TODO列表
     */
    @Cacheable(value = ["users"], key = "#userName")
    @RequestMapping(value = ["/role"], method = [RequestMethod.GET])
    fun findTodo(request: HttpServletRequest, userName: String): List<RoleEntity> {

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


    @RequestMapping(value = ["/login"], method = [RequestMethod.POST])
    fun login(userName: String?, error: String?): UserEntity? {
        return userDao.findUserByUserName("yhsj"?:"sss")
    }

    @RequestMapping(value = ["/success"], method = [RequestMethod.GET])
    fun success(userName: String?): String? {
        return "成功页面"
    }

    @RequestMapping(value = ["/failure"], method = [RequestMethod.GET])
    fun failure(): String? {
        return "失败页面"
    }


}
