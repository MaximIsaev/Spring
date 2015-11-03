package com.xml_parser;

import com.get_news_feed_file.DownloadNewsFeedFile;
import com.json_news_item.JSONContainer;

import org.json.simple.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class XMLParser {

    final String IMG_FOLDER_HOME_PATH = System.getenv("CATALINA_HOME") + "\\webapps\\NewsData\\images";
    final String IMG_FILE_HOME_PATH = System.getenv("CATALINA_HOME") + "\\webapps\\NewsData\\images\\imgLinks.txt";

    ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
    JSONObject bufferObject;
    JSONContainer jsonContainer;
    DownloadNewsFeedFile newsFeedFile;
    String allImgLinks = "";
    File imgLinksFile = new File(IMG_FILE_HOME_PATH);
    File folder = new File(IMG_FOLDER_HOME_PATH);


    public void parse() {

        newsFeedFile = context.getBean(DownloadNewsFeedFile.class);

        try {

            DocumentBuilder xml = DocumentBuilderFactory.
                    newInstance().newDocumentBuilder();

            newsFeedFile.download(newsFeedFile.getNewsFeedsUrl());

            Document doc = xml.parse(newsFeedFile.getOutputFeedFile());


            Element rootElement = doc.getDocumentElement();
            NodeList lst = rootElement.getChildNodes();
            NodeList itemList;


            for (int i = 0; i < lst.getLength(); i++) {
                NodeList channelNodes = lst.item(i).getChildNodes();
                int itemCount = xmlItemCounter(channelNodes);
                for (int j = channelNodes.getLength() - itemCount; j < channelNodes.getLength(); j++) {
                    itemList = channelNodes.item(j).getChildNodes();
                    displayItemChild(itemList);
                }
            }

            write(allImgLinks);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void displayItemChild(NodeList list) {

        JSONObject jsonObject = (JSONObject) context.getBean("jsonObject");
        jsonContainer = context.getBean(JSONContainer.class);

        bufferObject = jsonObject;

        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getNodeName().equals("description")) {
                checkDescription(list.item(i));
            } else {
                jsonObject.put("" + list.item(i).getNodeName() + "", list.item(i).getTextContent());
            }
        }
        jsonContainer.addNewJsonRecord(jsonObject);
    }

    public int xmlItemCounter(NodeList list) {

        int counterItem = 0;
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getNodeName().equals("item")) {
                counterItem++;
            }
        }
        return counterItem;
    }

    public void checkDescription(Node descriptionNode) {

        NodeList descriptionNodeChildNodes = descriptionNode.getChildNodes();
        char[] subStringImg;
        String ImgLink = "";

        bufferObject.put("" + descriptionNode.getNodeName() + "", descriptionNode.getTextContent());

        for (int i = 0; i < descriptionNodeChildNodes.getLength(); i++) {
            String imgString = descriptionNodeChildNodes.item(i).getNodeValue();
            String[] imgStringArray = imgString.split(" ");

            for (int j = 0; j < imgStringArray.length; j++) {
                if (imgStringArray[j].contains("src=")) {
                    subStringImg = imgStringArray[j].toCharArray();
                    for (int k = 5; k < subStringImg.length - 1; k++) {
                        ImgLink = ImgLink + subStringImg[k];
                    }
                    allImgLinks = allImgLinks + ImgLink + "\n";
                }
            }

        }

    }

    public void write(String text) {

        try {
            if (createImgFolder()) {
                if (!imgLinksFile.exists()) {
                    imgLinksFile.createNewFile();
                }

                PrintWriter out = new PrintWriter(imgLinksFile.getAbsoluteFile());

                try {
                    out.print(text + "\n");
                } finally {
                    out.close();
                }
            } else {
                if (!imgLinksFile.exists()) {
                    imgLinksFile.createNewFile();
                }

                PrintWriter out = new PrintWriter(imgLinksFile.getAbsoluteFile());

                try {
                    out.print(text + "\n");
                } finally {
                    out.close();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean createImgFolder() {

        try {
            if (!folder.exists()) {
                folder.mkdirs();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}


