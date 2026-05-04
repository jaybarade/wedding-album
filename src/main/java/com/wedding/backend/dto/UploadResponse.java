package com.wedding.backend.dto;

import java.util.List;

public class UploadResponse {
    private boolean status;
    private String message;
    private List<String> data;

    public UploadResponse() {}

    public UploadResponse(boolean status, String message, List<String> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public List<String> getData() { return data; }
    public void setData(List<String> data) { this.data = data; }

    public static UploadResponseBuilder builder() {
        return new UploadResponseBuilder();
    }

    public static class UploadResponseBuilder {
        private boolean status;
        private String message;
        private List<String> data;

        public UploadResponseBuilder status(boolean status) { this.status = status; return this; }
        public UploadResponseBuilder message(String message) { this.message = message; return this; }
        public UploadResponseBuilder data(List<String> data) { this.data = data; return this; }
        public UploadResponse build() {
            return new UploadResponse(status, message, data);
        }
    }
}
