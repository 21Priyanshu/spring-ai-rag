package com.llm.spring_ai_rag.service;

import org.springframework.stereotype.Service;

import dev.langchain4j.model.chat.ChatLanguageModel;

@Service
public class ChatService {
    private final ChatLanguageModel chatLanguageModel;

    public ChatService(ChatLanguageModel chatLanguageModel){
        this.chatLanguageModel = chatLanguageModel;
    }

    public String chat(String query){
        return chatLanguageModel.generate(query);
    }
}
