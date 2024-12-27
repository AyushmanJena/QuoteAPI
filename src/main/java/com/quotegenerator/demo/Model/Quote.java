package com.quotegenerator.demo.Model;

public class Quote {
    private String author;
    private String content;
    private String[] tags;

    public Quote(String author, String content, String[] tags) {
        this.author = author;
        this.content = content;
        this.tags = tags;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
}
