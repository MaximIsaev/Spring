package com.news_filters;


import com.json_news_item.JSONContainer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.UnsupportedEncodingException;

import java.io.PrintWriter;

public class FilteringByAuthor {

    static final String AUTHOR_NAME = "";

    ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");


    public void filter(JSONArray jsonArray, PrintWriter writer) throws UnsupportedEncodingException {
        JSONObject jsonObject;
        JSONContainer jsonContainer = context.getBean(JSONContainer.class);

        for (int i = 0; i < jsonArray.size(); i++) {
            jsonObject = (JSONObject) jsonArray.get(i);
            if (jsonObject.get("author").equals(AUTHOR_NAME) || AUTHOR_NAME.equals("")) {
                writer.write(jsonContainer.encodeHtmlTag(jsonObject.toString()) + "<br><br>");
            }
        }
    }
}
