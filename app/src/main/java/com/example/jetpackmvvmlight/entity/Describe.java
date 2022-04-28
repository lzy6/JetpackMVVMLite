package com.example.jetpackmvvmlight.entity;

public class Describe {

    public String title;
    public String content;
    public boolean isMarkdown;

    public Describe(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Describe(String title, String content, boolean isMarkdown) {
        this.title = title;
        this.content = content;
        this.isMarkdown = isMarkdown;
    }
}
