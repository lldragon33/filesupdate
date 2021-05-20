package com.lldragon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

@SpringBootApplication
@MapperScan("com.lldragon.dao")
public class FilesupdateApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilesupdateApplication.class, args);
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<String, Integer>();
    }

}
