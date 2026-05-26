package com.llm.spring_ai_rag.document;

import org.springframework.stereotype.Component;

@Component
public class DocumentStore {
    private String documentText;

    public String getDocumentText() {
        return documentText;
    }

    public void setDocumentText(String documentText) {
        this.documentText = documentText;
    }

    public boolean hasDocument() {
        return documentText != null && !documentText.isBlank();
    }
}
