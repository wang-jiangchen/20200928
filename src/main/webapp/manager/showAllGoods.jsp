<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>显示所有商品</title>
</head>
<body>
<script type="text/javascript" src="<%=application.getContextPath()%>/js/jquery-2.0.0.js"></script>
<style>
    table{
        margin: auto;
        text-align: center;
        border-collapse: collapse;
    }
    table,th,td{
        border: 1px solid black;
    }
</style>
<script>
    $(function () {
        $(".delClass").click(function (event) {
            event.preventDefault();
            if (confirm("是否确认删除？")){
                window.location.href=$(this).attr("href");
            } else {
                alert("删除操作取消！");
            }
        });
    })
</script>
</body>
<%@include file="header.jsp"%>
<table>
    <tr>
        <th>编号</th>
        <th>名称</th>
        <th>图片</th>
        <th>价格</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${goodss}" var="g">
        <tr>
            <td>${g.id}</td>
            <td>${g.name}</td>
            <td><img src="<%=application.getContextPath()%>${g.image}" width="50"></td>
            <td>${g.price}</td>
            <td>
                <a href="<%=application.getContextPath()%>/goods?op=findById&id=${g.id}"><button>编辑</button></a>
                <a class="delClass" href="<%=application.getContextPath()%>/goods?op=remove&id=${g.id}"><button>删除</button></a>
            </td>
        </tr>
    </c:forEach>
</table>
</html>
