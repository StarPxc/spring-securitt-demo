package imooc.security.browser.service;

import imooc.security.browser.dao.UserDao;
import imooc.security.browser.entity.MyUser;
import imooc.security.browser.entity.UserDetailsImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import org.springframework.stereotype.Component;


import java.util.Optional;

/**
 * @author 浦希成
 * 2018/10/18
 */
@Component
public class MyUserDetailService implements UserDetailsService {
    //可以传dao对象然后从数据库查询信息

    @Autowired
    private UserDao userDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional temp = userDao.findById(username);
        if (!temp.isPresent()) {
            System.out.println("用户不存在");
            throw new UsernameNotFoundException("用户不存在");

        }
        MyUser user = (MyUser) temp.get();

        //根据查找的用户信息判断用户是否被冻结等
        UserDetailsImpl userDetailsImpl = new UserDetailsImpl();
        BeanUtils.copyProperties(user, userDetailsImpl);

        return userDetailsImpl;
    }
}