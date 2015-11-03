package com.news_item;


import java.util.ArrayList;

public class NewsContainer {
    private ArrayList<News> newsList = new ArrayList<News>();

    public void setNewsList(News record) {
        newsList.add(record);
    }

    public ArrayList<News> getNewsList() {
        return newsList;
    }


}
