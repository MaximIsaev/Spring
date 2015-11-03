package com.json_news_item;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.PrintWriter;

public class JSONContainer {

    ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

    private JSONArray jsonNewsList = context.getBean(JSONArray.class);

    public void addNewJsonRecord(JSONObject rec) {
        jsonNewsList.add(rec);
    }

    public JSONArray getJsonNewsList() {
        return jsonNewsList;
    }

    public void displayJsonObjects(PrintWriter out) {
        out.print(jsonNewsList.toJSONString());
    }

    public String encodeHtmlTag(String tag) {
        int length = tag.length();
        StringBuffer encodedTag = new StringBuffer(2 * length);
        for (int i = 0; i < length; i++) {
            char c = tag.charAt(i);
            if (c == '<') {
                encodedTag.append("&lt;");
            } else if (c == '>') {
                encodedTag.append("&gt;");
            } else if (c == '&') {
                encodedTag.append("&amp;");
            } else encodedTag.append(c);
        }
        return encodedTag.toString();
    }
}
