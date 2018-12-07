package com.example.amqp.pojo;

public class Book {
    private String bookname;
    private String auther;

    public Book(String bookname, String auther) {
        this.bookname = bookname;
        this.auther = auther;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }
}
