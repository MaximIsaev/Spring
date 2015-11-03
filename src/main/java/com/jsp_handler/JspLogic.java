package com.jsp_handler;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Enumeration;
import javax.servlet.jsp.*;

public class JspLogic {

    final String IMG_FOLDER_HOME_PATH = System.getenv("CATALINA_HOME") + "\\webapps\\NewsData\\images\\";
    public String welcomeTitle = "Welcome! The server time is now";
    public String serverTime = "Server time: ";
    Enumeration fields;
    String fullPath;
    File rootImgFolder = new File(System.getenv("CATALINA_HOME") + "\\webapps\\NewsData\\images\\");
    private String existExpression;

    public void serverTime(JspWriter out) throws IOException {

        java.util.Calendar now = java.util.Calendar.getInstance();
        int hour = now.get(java.util.Calendar.HOUR_OF_DAY);
        int minute = now.get(java.util.Calendar.MINUTE);
        if (hour < 10) {
            out.println("0" + hour);
        } else {
            out.println(hour);
        }
        out.println(":");
        if (minute < 10) {
            out.println("0" + minute);
        } else {
            out.println(minute);
        }
    }


    public boolean getParameters(HttpServletRequest request) {

        fields = request.getParameterNames();

        return fields.hasMoreElements();

    }

    public void checkFolder(HttpServletRequest request, JspWriter out) throws IOException {


        while (fields.hasMoreElements()) {
            String field = (String) fields.nextElement();
            if (!field.equals("submit")) {
                String folderFullPath = IMG_FOLDER_HOME_PATH + request.getParameter(field);
                fullPath = IMG_FOLDER_HOME_PATH;
                File folder = new File(folderFullPath);
                if (folder.exists() && folder.isDirectory()) {
                    existExpression = "Folder " + "\"" + request.getParameter(field) + "\"" + " with this path: " + "\"" + folderFullPath + "\"" + " is EXIST!<br>";
                } else {
                    existExpression = "Folder " + "\"" + request.getParameter(field) + "\"" + " with this path: " + "\"" + folderFullPath + "\"" + " is NOT EXIST!<br>";
                }

            }
        }
    }

    public String getExistExpression() {
        return existExpression;
    }


    public String getImgFolderHomePath() {
        return IMG_FOLDER_HOME_PATH;
    }


    public void displayImgFolderContent(JspWriter out) throws IOException {
        if (!rootImgFolder.exists()) {
            rootImgFolder.mkdir();
        }

        File folderExport[] = rootImgFolder.listFiles();
        BasicFileAttributes attr;
        Path filePath;

        String[] foldersNames = new String[folderExport.length];
        if (foldersNames.length == 0) {
            out.println("Folder \"img\" is empty");
        } else {
            for (int i = 0; i < foldersNames.length; i++) {
                filePath = folderExport[i].toPath();
                attr = Files.readAttributes(filePath, BasicFileAttributes.class);
                out.println("<li>" + folderExport[i].getName());
                if (attr.creationTime() != null) {
                    out.println("(Create date: " + attr.creationTime() + ")</li><br>");
                }

            }
        }
    }

}
