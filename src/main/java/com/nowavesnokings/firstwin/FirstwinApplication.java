package com.nowavesnokings.firstwin;

import com.nowavesnokings.firstwin.core.annotation.EnableExtendedBaseRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableExtendedBaseRepository
public class FirstwinApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstwinApplication.class, args);
    }

}
