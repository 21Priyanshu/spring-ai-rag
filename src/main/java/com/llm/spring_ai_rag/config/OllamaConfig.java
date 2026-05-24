package com.llm.spring_ai_rag.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

@Configuration
public class OllamaConfig {

    @Value("${ollama.base-url}")
    String baseUrl;

    @Value("${ollama.model}")
    String model;

    @Bean
    public ChatLanguageModel ChatLanguageModel(){
        return OllamaChatModel.builder()
                    .baseUrl(baseUrl)
                    .modelName(model)
                    .temperature(0.7)
                    .build();
    }
}
