package com.wedding.backend.dto;

import java.util.List;

public class UploadResponse {
    private boolean status;
    private String message;
    private List<String> data;

    private String musicUrl;

    public UploadResponse() {}

    public UploadResponse(boolean status, String message, List<String> data, String musicUrl) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.musicUrl = musicUrl;
    }


    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public List<String> getData() { return data; }
    public void setData(List<String> data) { this.data = data; }

    public String getMusicUrl() { return musicUrl; }
    public void setMusicUrl(String musicUrl) { this.musicUrl = musicUrl; }


    public static UploadResponseBuilder builder() {
        return new UploadResponseBuilder();
    }

    public static class UploadResponseBuilder {
        private boolean status;
        private String message;
        private List<String> data;
        private String musicUrl;

        public UploadResponseBuilder status(boolean status) { this.status = status; return this; }
        public UploadResponseBuilder message(String message) { this.message = message; return this; }
        public UploadResponseBuilder data(List<String> data) { this.data = data; return this; }
        public UploadResponseBuilder musicUrl(String musicUrl) { this.musicUrl = musicUrl; return this; }
        public UploadResponse build() {
            return new UploadResponse(status, message, data, musicUrl);
        }

    }
}
