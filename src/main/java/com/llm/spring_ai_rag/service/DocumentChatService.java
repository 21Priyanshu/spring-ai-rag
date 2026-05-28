package com.llm.spring_ai_rag.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.llm.spring_ai_rag.document.DocumentStore;
import com.llm.spring_ai_rag.rag.RetrievalService;
import com.llm.spring_ai_rag.rag.RetrievedChunk;

import dev.langchain4j.model.chat.ChatLanguageModel;

@Service
public class DocumentChatService {

    private final ChatLanguageModel chatLanguageModel;

    private final RetrievalService retrievalService;

    public DocumentChatService(ChatLanguageModel chatLanguageModel, RetrievalService retrievalService) {
        this.chatLanguageModel = chatLanguageModel;
        this.retrievalService = retrievalService;
    }

    public String askQuestion(String question){
        // if(!documentStore.hasDocument()){
        //     System.out.println("No document uploaded");
        // }

        // String documentText = documentStore.getDocumentText();

        List<RetrievedChunk> relevaChunks = retrievalService.retrieve(question, 3);

        String context = relevaChunks.stream()
                                .map(RetrievedChunk::getText)
                                .reduce("", (a,b) -> a + "\n\n" + b);

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
                """.formatted(context, question);
        return chatLanguageModel.generate(prompt);
    }
}
