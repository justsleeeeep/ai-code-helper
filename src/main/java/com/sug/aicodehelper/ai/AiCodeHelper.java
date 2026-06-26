package com.sug.aicodehelper.ai;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AiCodeHelper
{
    @Resource
    private ChatModel qwenChatModel;

    private static final String STSTEM_MESSAGE= "你是求职方面的专家";

    // 简单对话
    public String chat(String message)
    {
        SystemMessage systemMessage=SystemMessage.from(STSTEM_MESSAGE);
        UserMessage userMessage=UserMessage.from(message);
        ChatResponse chatResponse = qwenChatModel.chat(systemMessage,userMessage);
        AiMessage aiMessage = chatResponse.aiMessage();
        log.info("AI: "+aiMessage.toString());
        return aiMessage.text();
    }

    //简单对话-自定义用户消息
    public String chatWithMessage(UserMessage userMessage)
    {
        ChatResponse chatResponse = qwenChatModel.chat(userMessage);
        AiMessage aiMessage = chatResponse.aiMessage();
        log.info("AI: "+aiMessage.toString());
        return aiMessage.text();
    }

}
