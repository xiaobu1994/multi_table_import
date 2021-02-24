package com.xiaobu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MultiTableImportApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MultiTableImportApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println(" 服务启动成功....." );
    }
}
