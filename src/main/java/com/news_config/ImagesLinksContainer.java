package com.news_config;

import java.util.ArrayList;

//Required for collecting images links;
public class ImagesLinksContainer {

    private ArrayList<String> imgListLink = new ArrayList<String>();

    public void setImgList(String link) {

        imgListLink.add(link);
    }

    public ArrayList getImgList() {
        return imgListLink;
    }

}
