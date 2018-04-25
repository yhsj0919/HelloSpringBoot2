package xyz.yhsj.spring.entity.user

import org.hibernate.annotations.ColumnDefault
import javax.persistence.*


/**
 * 角色表
 */
@Entity
data class SysRole(
        @Id
        @GeneratedValue
        var id: Long? = null,
        //名称
        var name: String? = null,
        //是否删除
        @ColumnDefault(value = "0")
        var isDelete: Int = 0,

        //是否可以修改0,不可修改,1可修改
        @ColumnDefault(value = "1")
        var canChange: Int? = null,

        //角色
        //下面必须是接口类型List,Set
        //mappedBy,其值为主控方中引用的外键对象的名称。
        @OneToMany(mappedBy = "role")
        var users: List<SysUser>? = null


)