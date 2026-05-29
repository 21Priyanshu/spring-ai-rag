package com.llm.spring_ai_rag.embedding;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface DocumentChunkReporsitory extends JpaRepository<DocumentChunk, Long>{

    @Query(valu)
    List<DocumentChunk> findSimilarChunks(String embedding, int limit);

}
