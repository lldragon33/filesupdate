package com.lldragon.service;

import com.lldragon.entiy.UserFile;

import java.util.List;

public interface UserFileService {
    List<UserFile> findByUserId(Integer id);

    void save(UserFile userFile);

    UserFile finById(Integer id);

    void update(UserFile userFile);

    void delete(Integer id);
}
