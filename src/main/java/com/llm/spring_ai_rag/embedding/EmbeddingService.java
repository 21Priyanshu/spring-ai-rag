package com.llm.spring_ai_rag.embedding;

import org.springframework.stereotype.Service;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;

@Service
public class EmbeddingService {

    private final EmbeddingModel embeddingModel;

    public EmbeddingService(EmbeddingModel embeddingModel){
        this.embeddingModel = embeddingModel;
    }

    public Embedding generEmbedding(TextSegment testSegment){
        return embeddingModel
                .embed(testSegment)
                .content();
    }
}
