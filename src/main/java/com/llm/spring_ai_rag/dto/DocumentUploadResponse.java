package com.llm.spring_ai_rag.dto;

public class DocumentUploadResponse {
    private String fileName;
    private int pageCount;
    private String extractedText;
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public int getPageCount() {
        return pageCount;
    }
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    public String getExtractedText() {
        return extractedText;
    }
    public void setExtractedText(String extractedText) {
        this.extractedText = extractedText;
    }
    public DocumentUploadResponse(String fileName, int pageCount, String extractedText) {
        this.fileName = fileName;
        this.pageCount = pageCount;
        this.extractedText = extractedText;
    }
}
