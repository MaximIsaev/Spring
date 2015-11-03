package com.news_item;

public class News {

    private  String guid;
    private  String title;
    private  String description;
    private  String author;
    private  String pubdate;
    private  String link;
    private  int id;

    public  void setGuid(String guid) {
        this.guid = guid;
    }

    public  void setTitle(String title) {
        this.title = title;
    }

    public  void setDescription(String description) {
        this.description = description;
    }

    public  void setAuthor(String author) {
        this.author = author;
    }

    public  void setPubdatee(String pubdate) {
        this.pubdate = pubdate;
    }

    public  void setId(int id) {
        this.id = id;
    }

    public  void setLink(String link) {
        this.link = link;
    }

    public  String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getPubdate() {
        return pubdate;
    }

    public int getId() {
        return id;
    }


}
