package imooc.security.browser.dao;

import imooc.security.browser.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 浦希成
 * 2018/10/18
 */
public interface UserDao extends JpaRepository<MyUser, String> {
}
