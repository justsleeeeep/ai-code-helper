package com.sug.aicodehelper.ai;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiCodeHelperServiceFactory
{
    @Resource
    ChatModel qwenChatModel;

    @Bean
    public AiCodeHelperService aiCodeHelperService()
    {
        ChatMemory chatMemory= MessageWindowChatMemory.withMaxMessages(10);
        AiCodeHelperService aiCodeHelperService = AiServices.builder(AiCodeHelperService.class)
                .chatModel(qwenChatModel)
                .chatMemory(chatMemory)//会话记忆
                .build();
        return aiCodeHelperService;
    }

}
