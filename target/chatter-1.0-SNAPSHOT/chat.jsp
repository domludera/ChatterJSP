<%--
  Created by IntelliJ IDEA.
  User: melon
  Date: 2020-10-10
  Time: 2:33 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>ChatApp</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <style>
        <%@include file="/ressources/style/chat.css"%>
        <%
        if(request.getParameter("theme") != null){
            session.setAttribute("theme", request.getParameter("theme"));
        }
        %>

        <%
        if(session.getAttribute("theme") != null){
            if(session.getAttribute("theme").equals("light")){
        %>
        <%@include file="/ressources/style/firstStyle.css"%>
        <%
            } else {
        %>

        <%@include file="/ressources/style/secondStyle.css"%>
        <%
            }
        }
        %>


    </style>
</head>
<body>
<%
    response.setIntHeader("Refresh", 5);
%>
<div class="container">
    <div class="header">
        <h1>Chatter App</h1>
    </div>
    <div class="content-large" id="message-content">
    <%
        if (session.getAttribute("n") == null) {
            session.setAttribute("n", request.getParameter("name"));
        }
    %>
        <p>${s}</p>

    </div>
    <div class="footer">
        <form action="BasicServlet" method="post">
            <input type="text" name="name" value="<%=session.getAttribute("n")%>" hidden/>
            <input type="text" name="message"/>
            <input type="submit" name="postmessage" value="Post"/>
        </form>
    </div>
    <div class = "content-small">
        <form action="BasicServlet" method="get">
            <div class = "filterClear">
                <input type="date" id="from" name="from">
                <input type="submit" name="getmessage" value="Filter"/>
                <br/>
                <input type="date" id="to" name="to">
                <input type="submit" name="clear" value="Clear">
            </div>
            <div class = "downloading">
                <select name="format">
                    <option value="plain">plain/text</option>
                    <option value="xml">xml</option>
                </select>
                <input type="submit" name="download" value="Download">
            </div>

        </form>

        <form action="BasicServlet" method="get">
            <div class = "themeSwitch">
                <select name="theme">
                    <option value="light">light theme</option>
                    <option value="dark">dark theme</option>
                </select>
                <input type="submit" name="changetheme" value="Change Theme">
            </div>
        </form>
    </div>
</div>
<script>
    /*
    var objDiv = document.getElementById("message-content");
    objDiv.scrollTop = objDiv.scrollHeight;

     */
</script>
</body>
</html>
