<%@ page import="com.jsp_handler.JspLogic" %>
<%@ page import="com.news_session.NewsSession" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page contentType="text/html; charset=utf-8" language="java" %>

<html>
<head>
    <title>Choose Folder</title>
</head>
<body>

<%
    ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
    NewsSession newsSession = context.getBean(NewsSession.class);
    JspLogic jspLogic = context.getBean(JspLogic.class);

    newsSession.checkSession(request, response, session);
    if (newsSession.getSessionFlag()) {
%>

<h1><%=jspLogic.welcomeTitle%>
    <%
        } else {
            out.println(jspLogic.serverTime);
        }
        jspLogic.serverTime(out);
    %>
    <br>

</h1>
<br>

<%
    if (!jspLogic.getParameters(request)) { // Нет полей
%>

<form method="POST" action="SimplePage.jsp">
    <h2>Please input folder name with images</h2>

    Folder name: <input type="text" size="20" name="Folder_name">

    <INPUT TYPE=submit name=submit value="Check folder">
</form>


<%
} else {
    jspLogic.checkFolder(request, out);
%>
<%=jspLogic.getExistExpression()%>

<br>

<p1><b>Content of folder:<%="\"" + jspLogic.getImgFolderHomePath() + "\""%>:</b><br><br>
</p1>

<%
    jspLogic.displayImgFolderContent(out);
%>
<br>
<%
    }
%>
</body>
</html>