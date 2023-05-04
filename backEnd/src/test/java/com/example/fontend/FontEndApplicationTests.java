package com.example.fontend;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
@Slf4j
class FontEndApplicationTests {

    @Test
    void contextLoads() {
        String h="hellow";
        String w="hello";


        log.info("{}", h.equals(w));

    }

}
