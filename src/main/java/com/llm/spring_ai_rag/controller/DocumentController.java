package com.llm.spring_ai_rag.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.llm.spring_ai_rag.document.DocumentStore;
import com.llm.spring_ai_rag.dto.DocumentUploadResponse;
import com.llm.spring_ai_rag.service.PdfService;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin("*")
public class DocumentController {
    private final PdfService pdfService;

    private final DocumentStore documentStore;

    public DocumentController(PdfService pdfService, DocumentStore documentStore){
        this.pdfService = pdfService;
        this.documentStore = documentStore;
    }

    @PostMapping(value="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public DocumentUploadResponse uploadPdf(@RequestParam("file") MultipartFile file) throws IOException{
        String extractedText = pdfService.extractContent(file);

        documentStore.setDocumentText(extractedText);

        int pageCount = pdfService.getPageCount(file);

        return new DocumentUploadResponse(file.getOriginalFilename(),pageCount,extractedText.substring(
                    0,
                    Math.min(extractedText.length(), 8000)
                ));
    }
}
