<%-- 
    Document   : index
    Author     : mikan
--%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="app.name" /></title>
    </head>
    <body>
        <h1><spring:message code="app.name" /></h1>
        <form action="" method="post">
            <div>
            <spring:message code="home.category1" />:
            <select name="category1">
                <option value="category1-a">Category 1-A</option>
                <option value="category1-b">Category 1-B</option>
                <option value="category1-c">Category 1-C</option>
            </select>
            </div>
            <div>
            <spring:message code="home.category2" />:
            <select name="category2">
                <option value="category2-a">Category 2-A</option>
                <option value="category2-b">Category 2-B</option>
                <option value="category2-c">Category 2-C</option>
            </select>
            </div>
        </form>
        <p><spring:message code="app.hello" /></p>
    </body>
</html>
