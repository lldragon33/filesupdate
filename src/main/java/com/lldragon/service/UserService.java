package com.lldragon.service;

import com.lldragon.entiy.User;

public interface UserService {
   User login(User user);
   void register(User user);

   boolean existName(String username);
}
