package com.llm.spring_ai_rag.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.ollama.OllamaEmbeddingModel;
import dev.langchain4j.model.ollama.OllamaModel;

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

    @Bean
    public EmbeddingModel embeddingModel(){
        return OllamaEmbeddingModel.builder()
                .baseUrl(baseUrl)
                .modelName("nomic-embed-text")
                .build();
    }
}
