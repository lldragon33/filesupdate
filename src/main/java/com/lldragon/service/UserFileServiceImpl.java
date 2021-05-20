package com.lldragon.service;

import com.lldragon.dao.UserFileDao;
import com.lldragon.entiy.UserFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service//工厂管理
@Transactional
public class UserFileServiceImpl implements UserFileService{
    @Autowired
    private UserFileDao userFileDao;

    @Override
    public List<UserFile> findByUserId(Integer id) {
        return userFileDao.findByUserId(id);
    }

    @Override
    public void save(UserFile userFile) {
        String isImg = userFile.getType().startsWith("image") ? "yes" : "no";
        userFile.setIsImg(isImg);
        userFile.setDocuments(0);
        userFile.setUploadTime(new Date());
        userFileDao.save(userFile);
    }

    @Override
    public UserFile finById(Integer id) {
        return userFileDao.findById(id);
    }

    @Override
    public void update(UserFile userFile) {
        userFileDao.update(userFile);
    }

    @Override
    public void delete(Integer id) {
        userFileDao.delete(id);
    }
}
