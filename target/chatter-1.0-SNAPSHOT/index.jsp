<%--
  Created by IntelliJ IDEA.
  User: melon
  Date: 2020-10-09
  Time: 4:37 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Welcome!</title>
</head>
<body>
<h1>${s}</h1>


<form action="BasicServlet" method="post">
    Username: <input type="text" name="name"/>
    Message: <input type="text" name="message"/>
    <input type="submit" name="postmessage" value="Post"/>
    <input type="submit" name="clear" value="Clear">
</form>


</body>
</html>
