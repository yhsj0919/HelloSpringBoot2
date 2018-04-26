package xyz.yhsj.spring.dao.user.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Component
import xyz.yhsj.spring.dao.user.UserDao

import xyz.yhsj.spring.entity.user.UserEntity


@Component
class UserDaoImpl : UserDao {


    @Autowired
    lateinit var mongoTemplate: MongoTemplate


    override fun getUsers(): List<UserEntity> {

        println(">>>>>>>>通过数据库获取>>>>>>>>>>>>>>>")

        return mongoTemplate.findAll(UserEntity::class.java)
    }

    override fun saveUsers(users: List<UserEntity>) {
        mongoTemplate.insertAll(users)
    }

    /**
     * 创建对象
     * @param user
     */
    override fun saveUser(user: UserEntity) {
        mongoTemplate.save(user)
    }

    /**
     * 根据用户名查询对象
     * @param userName
     * @return
     */
    override fun findUserByUserName(userName: String): UserEntity? {
        val query = Query(Criteria.where("userName").`is`(userName))
        return mongoTemplate.findOne(query, UserEntity::class.java)
    }

    /**
     * 更新对象
     * @param user
     */
    override fun updateUser(user: UserEntity): Long {
        val query = Query(Criteria.where("id").`is`(user.id!!))
        val update = Update().set("rolers", user.rolers)
        //更新查询返回结果集的第一条
        val result = mongoTemplate.updateFirst(query, update, UserEntity::class.java)
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
        return result.matchedCount
    }

    /**
     * 删除对象
     * @param id
     */
    override fun deleteUserById(id: Long?) {
        val query = Query(Criteria.where("id").`is`(id!!))
        mongoTemplate.remove(query, UserEntity::class.java)
    }
}