package org.prueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "org.prueba")
public class SuperHeroesApplication {
    public static void main(String[] args) {
        SpringApplication.run(SuperHeroesApplication.class, args);
    }
}