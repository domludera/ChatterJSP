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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <style><%@include file="/ressources/style/index.css"%></style>

</head>
<body class="text-center">


<form id="getin" class="form-signin" action="BasicServlet" method="post">

    <h1 class="h3 mb-3 font-weight-normal">Chatter JSP</h1>
    <input type="text" id="username" name="name" class="form-control" placeholder="@username" autofocus>
    <textarea id="message" name="message" class="form-control" rows="3" placeholder="Post your first message!" required></textarea>
    <button id="enter" class="btn btn-lg btn-primary btn-block" type="submit" name="postmessage" >Post</button>

</form>

<script>
    //submit on enter press
    var input = document.getElementById("getin");
    input.addEventListener("keyup", function(event) {
        if (event.keyCode === 13) {
            event.preventDefault();
            document.getElementById("enter").click();
        }
    });
</script>


</body>
</html>
