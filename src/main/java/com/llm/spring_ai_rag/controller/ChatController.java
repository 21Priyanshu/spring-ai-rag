package com.llm.spring_ai_rag.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.llm.spring_ai_rag.dto.ChatRequest;
import com.llm.spring_ai_rag.dto.ChatResponse;
import com.llm.spring_ai_rag.service.ChatService;


@RestController
@RequestMapping("/api/chat")
@CrossOrigin("*")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request){
        String response = chatService.chat(request.getQuery());

        return new ChatResponse(response);
    }
}
