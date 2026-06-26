package com.sug.aicodehelper.ai;

import com.sug.aicodehelper.tools.InterviewQuestionTool;
import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiCodeHelperServiceFactory
{
    @Resource
    ChatModel qwenChatModel;
    @Resource
    ContentRetriever contentRetriever;
    @Resource
    McpToolProvider toolProvider;

    @Bean
    public AiCodeHelperService aiCodeHelperService()
    {
        ChatMemory chatMemory= MessageWindowChatMemory.withMaxMessages(10);
        AiCodeHelperService aiCodeHelperService = AiServices.builder(AiCodeHelperService.class)
                .chatModel(qwenChatModel)
                .chatMemory(chatMemory)//会话记忆
                .contentRetriever(contentRetriever)//RAG检索增强
                .tools(new InterviewQuestionTool()) // 工具调用
                .toolProvider(toolProvider)//mcp工具调用
                .build();
        return aiCodeHelperService;
    }


}
