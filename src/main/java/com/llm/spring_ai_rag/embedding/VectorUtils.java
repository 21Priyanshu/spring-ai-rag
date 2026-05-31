package com.llm.spring_ai_rag.embedding;

import dev.langchain4j.data.embedding.Embedding;

public class VectorUtils {
    public static float[] toVector(Embedding embedding){
        return embedding.vector();
    }

    public static String toVectorString(Embedding embedding) {
        float[] v = embedding.vector();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < v.length; i++) {
            if (i > 0) sb.append(',');
            sb.append(v[i]);
        }
        return "[" + sb.toString() + "]";
    }

}