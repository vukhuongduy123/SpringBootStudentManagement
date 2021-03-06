package com.example.student.management.models;

public class FileUploadInfo {
    private String name;
    private String url;
    private long size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public FileUploadInfo(String name, String url, long size) {
        this.name = name;
        this.url = url;
        this.size = size;
    }
}
