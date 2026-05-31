package com.llm.spring_ai_rag.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.llm.spring_ai_rag.document.DocumentStore;
import com.llm.spring_ai_rag.dto.DocumentUploadResponse;
import com.llm.spring_ai_rag.embedding.DocumentChunk;
import com.llm.spring_ai_rag.embedding.DocumentChunkRepository;
import com.llm.spring_ai_rag.embedding.EmbeddingService;
import com.llm.spring_ai_rag.embedding.InMemoryVectorStore;
import com.llm.spring_ai_rag.embedding.VectorUtils;
import com.llm.spring_ai_rag.rag.TextChunkingService;
import com.llm.spring_ai_rag.service.PdfService;

import dev.langchain4j.data.segment.TextSegment;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin("*")
public class DocumentController {
    private final PdfService pdfService;

    private final DocumentStore documentStore;

    private final TextChunkingService textChunkingService;

    private final EmbeddingService embeddingService;

    private final InMemoryVectorStore vectorStore;

    private final DocumentChunkRepository documentChunkRepository;

    public DocumentController(PdfService pdfService, DocumentStore documentStore,
        TextChunkingService textChunkingService, EmbeddingService embeddingService,
        InMemoryVectorStore vectorStore, DocumentChunkRepository documentChunkRepository){

        this.pdfService = pdfService;
        this.documentStore = documentStore;
        this.textChunkingService = textChunkingService;
        this.embeddingService = embeddingService;
        this.vectorStore = vectorStore;
        this.documentChunkRepository = documentChunkRepository;
    }

    @PostMapping(value="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public DocumentUploadResponse uploadPdf(@RequestParam("file") MultipartFile file) throws IOException{
        String extractedText = pdfService.extractContent(file);

        documentStore.setDocumentText(extractedText);

        int pageCount = pdfService.getPageCount(file);

        vectorStore.clear();

        List<TextSegment> segments = textChunkingService.chunkText(extractedText);

        System.out.println(
                "Generated chunks: " + segments.size()
            );

        for(TextSegment segment : segments){
            System.out.println(segment.text());
            
            DocumentChunk chunkEntity = new DocumentChunk();

            chunkEntity.setContent(segment.text());

            chunkEntity.setEmbedding(VectorUtils.toVector(embeddingService.generEmbedding(segment)));

            documentChunkRepository.save(chunkEntity);
        }

        return new DocumentUploadResponse(file.getOriginalFilename(),pageCount,extractedText.substring(
                    0,
                    Math.min(extractedText.length(), 8000)
                ));
    }
}
