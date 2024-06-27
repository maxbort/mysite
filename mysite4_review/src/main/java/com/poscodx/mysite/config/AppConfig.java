package com.poscodx.mysite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DBConfig.class, MyBatisConfig.class})
public class AppConfig {

}
