package com.logging;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class LoggerListener implements HttpSessionListener, ServletContextListener, ServletContextAttributeListener {

    private static int totalActiveSessions;

    ConsoleEventLogger eventLogger;
    ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

    public LoggerListener() {
        eventLogger = (ConsoleEventLogger) context.getBean("consoleEventLogger");
    }

    public static int getTotalActiveSession() {
        return totalActiveSessions;
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        totalActiveSessions++;
        eventLogger.logEvent("sessionCreated - add one session into counter");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        totalActiveSessions--;
        eventLogger.logEvent("sessionDestroyed - deduct one session from counter");
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        eventLogger.logEvent("ServletContextListener destroyed");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        eventLogger.logEvent("ServletContextListener started");
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        eventLogger.logEvent("An attribute was added to the ServletContext object");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
        eventLogger.logEvent("An attribute was removed from the ServletContext object");
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        eventLogger.logEvent("An attribute was replaced in the ServletContext object");
    }
}
