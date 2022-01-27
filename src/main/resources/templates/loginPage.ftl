<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body style="text-align: center">

<h1>SpringSecurity登陆demo</h1>
<form action="/login" method="post">
    <span>用户名</span> <input type="text" name="username"/> <br>

    <span >密码</span> <input type="password" name="password" style="margin-top: 20px"/> <br>
    <input type="submit" value="登陆" style="font-size: 20px;margin-top: 20px;">

</form>

<#if RequestParameters['error']??>
    用户名称或者密码错误
</#if>


</body>
</html>