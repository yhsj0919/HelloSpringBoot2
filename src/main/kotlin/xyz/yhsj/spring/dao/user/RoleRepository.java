package xyz.yhsj.spring.dao.user;


import org.springframework.data.jpa.repository.JpaRepository;
import xyz.yhsj.spring.entity.user.SysRole;


/**
 * 获取角色/包括权限
 */
public interface RoleRepository extends JpaRepository<SysRole, Long> {

}
