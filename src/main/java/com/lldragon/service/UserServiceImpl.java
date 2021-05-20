package com.lldragon.service;

import com.lldragon.dao.UserDao;
import com.lldragon.entiy.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User login(User user) {
        return userDao.login(user);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void register(User user) {
        userDao.register(user);
    }

    @Override
    public boolean existName(String username) {
        User user = userDao.checkName(username);
        return user!=null;

    }
}
