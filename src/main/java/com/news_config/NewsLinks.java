package com.news_config;

import java.util.ArrayList;

//Required for collecting news links;
public class NewsLinks {

    private ArrayList<String> newsListLink = new ArrayList<String>();

    public void setNewsList(String link) {

        newsListLink.add(link);
    }

    public ArrayList getNewsList() {
        return newsListLink;
    }
}
