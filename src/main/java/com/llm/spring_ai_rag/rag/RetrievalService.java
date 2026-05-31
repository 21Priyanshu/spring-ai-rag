package com.llm.spring_ai_rag.rag;

import java.util.List;
import org.springframework.stereotype.Service;

import com.llm.spring_ai_rag.embedding.DocumentChunk;
import com.llm.spring_ai_rag.embedding.DocumentChunkRepository;
import com.llm.spring_ai_rag.embedding.EmbeddingService;
import com.llm.spring_ai_rag.embedding.VectorUtils;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;

@Service
public class RetrievalService {
    private final EmbeddingService embeddingService;

    private final DocumentChunkRepository documentChunkRepository;

    public RetrievalService(EmbeddingService embeddingService,
        DocumentChunkRepository documentChunkRepository
    ) {
        this.embeddingService = embeddingService;
        this.documentChunkRepository = documentChunkRepository;
    }

    /**
     * Question -> embedding -> compare against all embedding
     * -> calculate similarity score
     * -> sort highest simialrity first
     * -> return tok-k chunks
     * 
     * @param question
     * @param topK
     * @return
     */
    public  List<DocumentChunk> retrieve(String question, int topK){

        // get the embeddings from question
        Embedding questionEmbedding = embeddingService.generEmbedding(TextSegment.from(question));

        String vectorLiteral = VectorUtils.toVectorString(questionEmbedding);

        return documentChunkRepository.findSimilarChunks(vectorLiteral, topK);
    }
}
