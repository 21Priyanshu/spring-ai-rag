package com.llm.spring_ai_rag.embedding;

import dev.langchain4j.data.embedding.Embedding;

public class EmbeddedChunk {
    private String text;

    private Embedding embedding;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Embedding getEmbedding() {
        return embedding;
    }

    public void setEmbedding(Embedding embedding) {
        this.embedding = embedding;
    }

    public EmbeddedChunk(String text, Embedding embedding) {
        this.text = text;
        this.embedding = embedding;
    }
    
}
