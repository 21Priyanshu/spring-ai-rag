package com.llm.spring_ai_rag.rag;

import dev.langchain4j.data.embedding.Embedding;

public class SimilarityUtils {

    /**
     * Calculate how similar 2 vectors are
     * 1.00 -> very similar
     * 0.0 -> unrelated
     * -1.0 -> opposite
     * @param embedding1
     * @param embedding2
     * @return
     */
    public static double cosSineSimilarity(Embedding embedding1, Embedding embedding2){

        float vector1[] = embedding1.vector();

        float vector2[] = embedding2.vector();

        double dotProduct = 0.00;

        double norm1 = 0.0;

        double norm2 = 0.0;

        for(int i =0;i< vector1.length;i++){
            dotProduct += vector1[i] * vector2[i];

            norm1 += Math.pow(vector1[i], 2);

            norm2 += Math.pow(vector2[i], 2);
        }

        return dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
    }
}
