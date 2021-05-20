package com.lldragon.dao;

import com.lldragon.entiy.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    User login(User user);
    void register(User user);
    User checkName(String username);
}
