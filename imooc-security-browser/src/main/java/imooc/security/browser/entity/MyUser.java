package imooc.security.browser.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 浦希成
 * 2018/10/18
 */
@Data
@Entity
@Table(name = "user")
public class MyUser {
    @Id
    private String username;
    private String password;
    private String authors;
    private Long lastPasswordChange;
    private Integer enable;

}
