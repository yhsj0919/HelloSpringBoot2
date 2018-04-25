package xyz.yhsj.spring.entity.mysql

import javax.persistence.MappedSuperclass


//基类必须加入这个注解
@MappedSuperclass
open class BaseEntity(
        var createTime: Long? = null,
        var createUser: Long? = null,
        var deleteTime: Long? = null,
        var deleteUser: Long? = null,
        var updateTime: Long? = null,
        var updateUser: Long? = null)