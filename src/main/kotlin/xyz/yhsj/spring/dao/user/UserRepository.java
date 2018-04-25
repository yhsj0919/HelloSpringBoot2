package xyz.yhsj.spring.dao.user;


import org.springframework.data.jpa.repository.JpaRepository;
import xyz.yhsj.spring.entity.user.SysUser;

public interface UserRepository extends JpaRepository<SysUser, Long> {
}