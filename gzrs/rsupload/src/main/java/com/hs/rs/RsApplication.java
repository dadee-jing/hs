package com.hs.rs;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.hs.rs"})
@Configuration
@EnableScheduling
@EnableAsync
public class RsApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RsApplication.class, args);
    }
}
