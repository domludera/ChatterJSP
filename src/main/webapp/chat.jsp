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
    <style><%@include file="/firstStyle.css"%></style>
</head>
<body>
<div class = "container">
    <div class="header">
        <h1>Chatter App</h1>
    </div>
    <div class = "content-large">
        <%
            if (session.getAttribute("n") == null) {
                session.setAttribute("n", request.getParameter("name"));
            }
        %>

        <p>${s}</p>
    </div>
    <div class = "footer">
        <form action="BasicServlet" method="post">
            <input type="text" name="name" value="<%=session.getAttribute("n")%>" hidden/>
            Message: <input type="text" name="message"/>
            <input type="submit" name="postmessage" value="Post"/>
        </form>
    </div>
    <div class = "content-small">
        <form action="BasicServlet" method="get">
            <input type="date" id="from" name="from">
            <input type="date" id="to" name="to">
            <input type="text" name="format" value="plain">
            <br/>
            <button type="submit" name="getmessage" value="Filter">Get Message</button>
            <button type="submit" name="clear" value="Clear">Clear</button>
            <br/>
            <button type="submit" name="download" value="Download">Download</button>
            <button type="submit" name="downloadXML" value="Download as XML"/>Download XML</button>

        </form>
    </div>
</div>
</body>
</html>
