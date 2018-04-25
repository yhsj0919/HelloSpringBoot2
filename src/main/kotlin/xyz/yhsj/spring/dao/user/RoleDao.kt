package xyz.yhsj.spring.dao.user

import xyz.yhsj.spring.entity.user.RoleEntity

interface RoleDao {

    fun saveRole(user: RoleEntity)

    fun saveRoles(users: List<RoleEntity>)

    fun findRoleByName(userName: String): RoleEntity?

    fun getRoles(): List<RoleEntity>

    fun updateRole(user: RoleEntity): Long

    fun deleteRoleById(id: Long?)

}