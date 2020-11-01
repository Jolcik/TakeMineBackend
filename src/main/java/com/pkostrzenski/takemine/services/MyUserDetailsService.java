package com.pkostrzenski.takemine.services;

import com.pkostrzenski.takemine.models.MyUserDetails;
import com.pkostrzenski.takemine.repository.interfaces.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("myUserService")
public class MyUserDetailsService implements UserDetailsService {

    UserDao userDao;

    @Autowired
    public MyUserDetailsService(@Qualifier("UserJPA") UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // accepts both username and email
        return userDao.findByUsernameOrEmail(username)
                .map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
    }

}