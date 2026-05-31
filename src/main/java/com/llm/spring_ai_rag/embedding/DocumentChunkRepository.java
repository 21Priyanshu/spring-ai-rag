package com.llm.spring_ai_rag.embedding;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface DocumentChunkRepository extends JpaRepository<DocumentChunk, Long>{

    @Query(value = """
            SELECT *
            FROM DOCUMENT_CHUNKS
            ORDER BY embedding <=> CAST(:embedding AS vector)
            LIMIT :limit
            """,
            nativeQuery = true)
    List<DocumentChunk> findSimilarChunks(String embedding, int limit);

}
