package com.sug.aicodehelper.ai;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AiCodeHelperServiceFactoryTest {
    @Resource
    AiCodeHelperService aiCodeHelperService;

    @Test
    void chat() {
        String result = aiCodeHelperService.chat("hello");
        System.out.println(result);

    }
    @Test
    void chatWithMemory() {
        String result1 = aiCodeHelperService.chat("我是sugpepper");
        System.out.println(result1);
        String result2 = aiCodeHelperService.chat("我是谁");
        System.out.println(result2);
    }


    @Test
    void chatWithReport(){
        AiCodeHelperService.Report report= aiCodeHelperService.chatWithReport("我是sugpepper");
        System.out.println(report);
    }
}