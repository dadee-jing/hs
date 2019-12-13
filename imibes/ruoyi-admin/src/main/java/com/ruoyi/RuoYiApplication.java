package com.ruoyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动程序
 *
 * @author ruoyi
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.ruoyi.*.mapper")
@ComponentScan(
        basePackages = {"com.ruoyi"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.ruoyi\\.duge\\.third\\.guangdong\\..*"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.ruoyi\\.duge\\.third\\.trafficPolice\\..*")})
//@EnableScheduling
public class RuoYiApplication {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("启动成功\n");
    }
}