package com.llm.spring_ai_rag.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.llm.spring_ai_rag.dto.AskRequest;
import com.llm.spring_ai_rag.service.DocumentChatService;

@RestController
@RequestMapping("/api/ask")
@CrossOrigin("*")
public class AskController {
    private final DocumentChatService documentChatService;

    public AskController(DocumentChatService documentChatService){
        this.documentChatService = documentChatService;
    }

    @PostMapping
    public String askQuestion(
        @RequestBody AskRequest request
    ){
        String answer = documentChatService.askQuestion(request.getQuestion());

        return answer;
    }
}
