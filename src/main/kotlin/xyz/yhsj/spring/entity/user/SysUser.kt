package xyz.yhsj.spring.entity.user

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

/**
 * 用户表,一个用户可能包含多种角色
 */
@Entity
data class SysUser(
        @Id
        @GeneratedValue
        var id: Long? = null,
        //用户名
        var username: String? = null,
        //密码
        var password: String? = null,

        //角色
        //@set:JsonBackReference是为了防止循环引用
        @ManyToOne(optional = false)
        @set:JsonBackReference
        var role: SysRole? = null


)