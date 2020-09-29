<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
</head>
<body>
${message}
<form action="<%=application.getContextPath()%>/admin?op=login" method="post">
    <input type="text" name="account" placeholder="请输入账号" />
    <input type="password" name="password" placeholder="请输入密码" />
    <input type="submit" value="登录" />
</form>
</body>
</html>
