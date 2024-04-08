package com.example.myapplication.docfragment;

public class News  {
    int id;
    String title;
    String content;
    public News(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }


}
