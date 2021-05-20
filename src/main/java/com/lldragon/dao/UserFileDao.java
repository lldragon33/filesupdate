package com.lldragon.dao;

import com.lldragon.entiy.UserFile;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserFileDao {
    //根据登录用户id获取用户的文件列表信息
    List<UserFile> findByUserId(Integer id);
    //将上传后的文件信息录入数据库
    void save(UserFile userFile);
    //根据文件id查找文件信息
    UserFile findById(Integer id);
    //跟新文件信息
    void update(UserFile userFile);
    //删除数据库中文件信息
    void delete(Integer id);
}
