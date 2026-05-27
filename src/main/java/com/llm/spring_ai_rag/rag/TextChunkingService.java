package com.llm.spring_ai_rag.rag;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;


@Service
public class TextChunkingService {
    public List<TextSegment> chunkText(String text){
        Document document = Document.from(text);

        return DocumentSplitters
                .recursive(500, 100)
                .split(document);
    }
}
