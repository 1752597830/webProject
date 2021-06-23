package com.qf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class PropertyServerManageMasterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PropertyServerManageMasterApplication.class, args);
    }
}
