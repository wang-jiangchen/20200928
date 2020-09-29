<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="margin: auto;text-align: center">
    <a href="<%=application.getContextPath()%>/manager/addGoods.jsp">添加商品</a>
    <a href="<%=application.getContextPath()%>/goods?op=findAll">显示所有商品</a>

    <c:if test="${empty sessionScope.admin}">
        <a href="<%=application.getContextPath()%>/login.jsp"><button>登录</button></a>
    </c:if>
    <c:if test="${!empty sessionScope.admin}">
        ${admin.name}&nbsp;
        <a href="<%=application.getContextPath()%>/admin?op=exit">退出</a>
    </c:if>
    当前已访问人数:${applicationScope.num}
</div>