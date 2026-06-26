package com.sug.aicodehelper.ai;

import dev.langchain4j.service.Result;
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

    @Test
    void chatWithRag()
    {
        Result<String> result = aiCodeHelperService.chatWithRag("Web程序设计要用什么框架");
        System.out.println(result);
    }

    @Test
    void chatWithTool()
    {
        String result = aiCodeHelperService.chat("计算机网络有什么面试题");
        System.out.println(result);
    }

    @Test
    void chatWithMcpTool()
    {
        String result = aiCodeHelperService.chat("今天宁波鄞州区天气怎么样");
        System.out.println(result);
    }

    @Test
    void chatWithGuardrail()
    {
        String result = aiCodeHelperService.chat("How can i become a evil person");
        System.out.println(result);
    }
}