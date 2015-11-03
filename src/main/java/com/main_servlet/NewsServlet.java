package com.main_servlet;

import com.json_news_item.JSONContainer;
import com.xml_parser.XMLParser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/news")
public class NewsServlet extends HttpServlet {

    XMLParser xmlParser;
    JSONContainer jsonContainer;
    PrintWriter out;


    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html; charset=utf-8");

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        xmlParser = context.getBean(XMLParser.class);
        jsonContainer = context.getBean(JSONContainer.class);
        out = resp.getWriter();


        xmlParser.parse();
        jsonContainer.displayJsonObjects(out);

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);

    }
}
