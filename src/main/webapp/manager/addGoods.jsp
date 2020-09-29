<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加商品</title>
</head>
<body>
<form action="<%=application.getContextPath()%>/goods?op=add" method="post" enctype="multipart/form-data">
    商品名称：<input type="text" name="name" placeholder="请输入商品名称"><br>
    商品图片：<input type="file" name="image" multiple><br>
    商品价格：<input type="number" name="price" placeholder="请输入商品价格"><br>
    <input type="submit" value="添加">
</form>
</body>
</html>
