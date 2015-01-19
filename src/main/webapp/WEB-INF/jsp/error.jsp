<%-- 
    Document   : error
    Author     : mikan
--%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../jspf/style.jspf" %>
        <title><spring:message code="app.name" /></title>
    </head>
    <body>
        <%@include file="../jspf/header.jspf" %>
        <div id="main">
            <%@include file="../jspf/menu.jspf" %>
            <div id="content">
                <h2 class="error"><spring:message code="error.title" /></h2>
                <p><spring:message code="${message}" /></p>
                <p>${exception}</p>
            </div>
        </div>
        <%@include file="../jspf/footer.jspf" %>
    </body>
</html>
