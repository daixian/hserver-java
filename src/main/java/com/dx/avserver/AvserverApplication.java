package com.dx.avserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;


@SpringBootApplication
@EntityScan(basePackageClasses = {AvserverApplication.class, Jsr310JpaConverters.class})
public class AvserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(AvserverApplication.class, args);
    }

}
