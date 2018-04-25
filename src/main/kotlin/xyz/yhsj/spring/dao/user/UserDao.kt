package xyz.yhsj.spring.dao.user

import xyz.yhsj.spring.entity.user.UserEntity


interface UserDao {

    fun saveUser(user: UserEntity)

    fun saveUsers(users: List<UserEntity>)

    fun findUserByUserName(userName: String): UserEntity?

    fun getUsers(): List<UserEntity>

    fun updateUser(user: UserEntity): Long

    fun deleteUserById(id: Long?)

}