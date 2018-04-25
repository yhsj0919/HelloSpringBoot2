package xyz.yhsj.spring.dao.user.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Component
import xyz.yhsj.spring.dao.user.RoleDao
import xyz.yhsj.spring.entity.user.RoleEntity


@Component
class RoleDaoImpl : RoleDao {

    @Autowired
    lateinit var mongoTemplate: MongoTemplate


    override fun saveRole(user: RoleEntity) {
        mongoTemplate.save(user)
    }

    override fun saveRoles(users: List<RoleEntity>) {
        mongoTemplate.insertAll(users)
    }

    override fun findRoleByName(userName: String): RoleEntity? {


        val query = Query(Criteria.where("name").`is`(userName))
        return mongoTemplate.findOne(query, RoleEntity::class.java)
    }

    override fun getRoles(): List<RoleEntity> {

//        val query = Query.query(Criteria.where("users.\$id").`is`("0"))
//
//
////        val query = Query(Criteria.where("userName").`is`(userName))
//        return mongoTemplate.find(query, RoleEntity::class.java)

        return mongoTemplate.findAll(RoleEntity::class.java)
    }

    override fun updateRole(user: RoleEntity): Long {
        val query = Query(Criteria.where("id").`is`(user.id!!))
        val update = Update().set("name", user.name!!)
        //更新查询返回结果集的第一条
        val result = mongoTemplate.updateFirst(query, update, RoleEntity::class.java)
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
        return result.matchedCount
    }

    override fun deleteRoleById(id: Long?) {
        val query = Query(Criteria.where("id").`is`(id!!))
        mongoTemplate.remove(query, RoleEntity::class.java)
    }
}