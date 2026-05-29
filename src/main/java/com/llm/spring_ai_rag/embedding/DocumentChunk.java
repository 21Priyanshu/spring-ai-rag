package com.llm.spring_ai_rag.embedding;

import jakarta.persistence.*;

@Entity
@Table(name = "documen_chunks")
public class DocumentChunk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "vector(768)")
    private String embedding;
}
