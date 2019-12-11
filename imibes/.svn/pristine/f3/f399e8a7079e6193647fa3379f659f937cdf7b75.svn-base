package com.ruoyi.duge.third;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
//@Configuration
//@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages={"com.ruoyi.common.httphelper","com.ruoyi.duge.third"}, exclude = { DataSourceAutoConfiguration.class })
public class ThirdApplication {
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(ThirdApplication.class, args);
        System.out.println("third start");
    }
}