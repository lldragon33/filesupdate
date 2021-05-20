package com.lldragon.controller;

import com.lldragon.entiy.User;
import com.lldragon.entiy.UserFile;
import com.lldragon.service.UserFileService;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("file")
public class FileController {

    @Autowired
    private UserFileService userFileService;

    /**
     * 返回当前用户的所有文件列表----json格式数据
     */
    @GetMapping("findAllJSON")
    @ResponseBody
    public List<UserFile> findAllJSON(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        List<UserFile> userFiles = userFileService.findByUserId(user.getId());
       return userFiles;
    }




        @GetMapping("showAll")
        public String findAll(HttpSession session, Model model){
            User user = (User) session.getAttribute("user");
            List<UserFile> userFiles = userFileService.findByUserId(user.getId());
            model.addAttribute("files",userFiles);
            return "UserFileList";
        }

        @RequestMapping("upload")
        public String upload(MultipartFile aaa,HttpSession session) throws IOException {
            User user =(User) session.getAttribute("user");
            Integer userId = user.getId();
            String oldFileName = aaa.getOriginalFilename();
            String extension = "."+FilenameUtils.getExtension(aaa.getOriginalFilename());
            //生成新文件名
            String newFileName =new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ UUID.randomUUID().toString().replace("-","")+extension;
            //文件大小
            long size = aaa.getSize();
            String type = aaa.getContentType();

            //根据日期生成目录
            String realPath = ResourceUtils.getURL("classpath:").getPath() + "/static/files";
            String dataPormat= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String dateDirPath = realPath + "/" + dataPormat;
            File dateDir = new File(dateDirPath);
            if(!dateDir.exists())dateDir.mkdir();
            //处理文件上传
            aaa.transferTo(new File(dateDir,newFileName));
            //将文件信息放入数据库中
            UserFile userFile = new UserFile();
            userFile.setOldFileName(oldFileName).setNewFileName(newFileName).setExt(extension).setSize(String.valueOf(size))
                    .setType(type).setPath("/files/"+dataPormat).setUserId(userId);
            userFileService.save(userFile);

            return "redirect:/file/showAll";
        }
        @RequestMapping("download")
        public void download(String openStyle,Integer id, HttpServletResponse response) throws IOException {
           openStyle = openStyle==null?"attachment":openStyle;
            UserFile userFile = userFileService.finById(id);
           //点击下载时跟新下载次数
            if("attachment".equals(openStyle)){
                userFile.setDocuments(userFile.getDocuments()+1);
                userFileService.update(userFile);
            }
           //根据文件信息中的文件名字，获取文件输入路径
            String realPath = ResourceUtils.getURL("classpath:").getPath() + "/static" + userFile.getPath();
            //获取文件输入流
            FileInputStream is = new FileInputStream(new File(realPath, userFile.getNewFileName()));
            //附件下载
            response.setHeader("content-disposition",openStyle+";fileName="+ URLEncoder.encode(userFile.getOldFileName(),"UTF-8") );
            //获取文件响应流
            ServletOutputStream os = response.getOutputStream();
            //文件拷贝
            IOUtils.copy(is,os);
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(os);


        }

    /**
     * 删除文件信息
     * @param id
     * @return
     */
    @GetMapping("delete")
        public String delete(Integer id) throws FileNotFoundException {
            //根据id查询信息
        UserFile userFile = userFileService.finById(id);
        //删除文件
        String realPath = ResourceUtils.getURL("classpath:").getPath() + "/static" + userFile.getPath();
        File file = new File(realPath, userFile.getNewFileName());
        if(file.exists())file.delete();//立即删除
        //删除数据库中的信息
        userFileService.delete(id);
        return "redirect:/file/showAll";
        }

}
