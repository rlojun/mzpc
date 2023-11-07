package com.fivemin.mzpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.fivemin.mzpc.data.entity")
public class MzpcApplication {

    public static void main(String[] args) {
        SpringApplication.run(MzpcApplication.class, args);
    }

}
