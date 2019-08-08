package com.ruoyi.duge.third.shunde.persistence;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.spring.boot.mapper.cluster",sqlSessionTemplateRef = "clusterSqlSessionTemplate")
public class ShundeConfig {
}
