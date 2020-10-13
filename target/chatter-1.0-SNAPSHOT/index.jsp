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
</head>
<body class="text-center">
<h1>${s}</h1>


<form class="form-signin" action="BasicServlet" method="post">
    <!--
    Username: <input type="text" name="name"/>
    Message: <input type="text" name="message"/>
    <input type="submit" name="postmessage" value="Post"/>
    <input type="submit" name="clear" value="Clear">
    -->


    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    <label for="username" class="sr-only">Email address</label>
    <input type="text" id="username" name="name" class="form-control" placeholder="name" required autofocus>
    <label for="message" class="sr-only">Password</label>
    <input type="text" id="message" name="message" class="form-control" placeholder="Password" required>
    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me"> Remember me
        </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit" name="postmessage" >Post</button>
    <button class="btn btn-lg btn-primary btn-block" type="submit" name="clear" >Clear</button>


</form>


</body>
</html>
