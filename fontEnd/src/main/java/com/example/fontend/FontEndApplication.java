package com.example.fontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class FontEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(FontEndApplication.class, args);
    }

}
