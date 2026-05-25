package com.llm.spring_ai_rag.service;

import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PdfService {
    public String extractContent(MultipartFile file) throws IOException{
        PDDocument document = Loader.loadPDF(file.getBytes());

        PDFTextStripper pdfTextStripper = new PDFTextStripper();

        String text = pdfTextStripper.getText(document);

        document.close();

        return text;
    }

    public int getPageCount(MultipartFile file) throws IOException{
        PDDocument document = Loader.loadPDF(file.getBytes());

        int pageCount= document.getNumberOfPages();

        document.close();

        return pageCount;
    }
}
