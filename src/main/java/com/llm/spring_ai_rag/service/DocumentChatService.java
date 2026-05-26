package com.llm.spring_ai_rag.service;

import org.springframework.stereotype.Service;

import com.llm.spring_ai_rag.document.DocumentStore;

import dev.langchain4j.model.chat.ChatLanguageModel;

@Service
public class DocumentChatService {

    private final ChatLanguageModel chatLanguageModel;

    private final DocumentStore documentStore;

    public DocumentChatService(ChatLanguageModel chatLanguageModel, DocumentStore documentStore){
        this.chatLanguageModel = chatLanguageModel;
        this.documentStore = documentStore;
    }

    public String askQuestion(String question){
        if(!documentStore.hasDocument()){
            System.out.println("No document uploaded");
        }

        String documentText = documentStore.getDocumentText();

        String prompt = """
                You are an AI document assistant.

                Answer the question  ONLY using the provided document.

                If the answer  is not present in document,
                say:
                "I could not find that information in the document."

                DOCUMENT:
                %s

                QUESTION:
                %s.
                """.formatted(documentText, question);
        return chatLanguageModel.generate(prompt);
    }
}
