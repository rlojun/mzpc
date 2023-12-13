package com.fivemin.mzpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EntityScan("com.fivemin.mzpc.data.entity")
@EnableJpaAuditing
@EnableRedisHttpSession
public class MzpcApplication {

    public static void main(String[] args) {
        SpringApplication.run(MzpcApplication.class, args);
    }

}
