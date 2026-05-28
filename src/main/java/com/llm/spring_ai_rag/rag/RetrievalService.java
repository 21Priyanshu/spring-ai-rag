package com.llm.spring_ai_rag.rag;

import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;

import com.llm.spring_ai_rag.embedding.EmbeddingService;
import com.llm.spring_ai_rag.embedding.InMemoryVectorStore;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;

@Service
public class RetrievalService {
    private final EmbeddingService embeddingService;

    private final InMemoryVectorStore vectorStore;

    public RetrievalService(EmbeddingService embeddingService, InMemoryVectorStore vectorStore) {
        this.embeddingService = embeddingService;
        this.vectorStore = vectorStore;
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
    public List<RetrievedChunk> retrieve(String question, int topK){

        // get the embeddings from question
        Embedding questionEmbedding = embeddingService.generEmbedding(TextSegment.from(question));

        // streams through the stored context chunk
        return vectorStore.getAllChunks().stream()
                    .map(chunk -> {

                        // calculate cosine similarity between question and content embedding
                        double similarity = SimilarityUtils
                                .cosSineSimilarity(questionEmbedding, chunk.getEmbedding());
                        
                        System.out.println("Retrieved Chunks:" + chunk.getText());
                        System.out.println("Retrieved score:" + similarity);
                        
                        return new RetrievedChunk(chunk.getText(), similarity);
                    })
                    .sorted(
                        // compare against highest similarity score
                        Comparator.comparingDouble(
                            RetrievedChunk::getScore
                        ).reversed()
                    )
                    // limit response to topK elements
                    .limit(topK)
                    .toList();
    }
}
