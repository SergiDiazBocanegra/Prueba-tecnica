package org.prueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "org.prueba")
@EnableAutoConfiguration
public class SuperHeroesApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(SuperHeroesApplication.class, args);
    }
}