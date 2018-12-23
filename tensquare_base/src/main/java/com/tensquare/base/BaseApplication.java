package com.tensquare.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @author xsj
 */
@SpringBootApplication
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class,args);
    }

    /**
     * 以注解的方式把工具类注入到spring的容器中
     * @return
     */
    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
}
