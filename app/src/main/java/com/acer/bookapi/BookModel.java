package com.acer.bookapi;

public class BookModel {
    String title,bookimage,description;

    public BookModel(String title, String bookimage, String description) {
        this.title = title;
        this.bookimage = bookimage;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBookimage() {
        return bookimage;
    }

    public void setBookimage(String bookimage) {
        this.bookimage = bookimage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
