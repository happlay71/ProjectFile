package com.happlay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j  // 日志输出
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement  // 开起事务注解的支持
public class ReggieApplication {

    public static void main(String[] args) {

        SpringApplication.run(ReggieApplication.class, args);
        log.info("项目启动成功...");
    }

}
