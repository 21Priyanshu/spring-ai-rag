package com.llm.spring_ai_rag.embedding;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class InMemoryVectorStore {
    private final List<EmbeddedChunk> embeddedChunks = new ArrayList<>();

    public void addChunk(EmbeddedChunk chunk){
        embeddedChunks.add(chunk);
    }

    public List<EmbeddedChunk> getAllChunks(){
        return embeddedChunks;
    }

    public void clear(){
        embeddedChunks.clear();
    }
}
