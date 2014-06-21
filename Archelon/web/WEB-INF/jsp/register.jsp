<%-- 
    Document   : register
    Created on : 2014/06/22, 0:29:23
    Author     : mikan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Style fragment -->
        <%@include file="/WEB-INF/jspf/style.jspf" %>

        <title><spring:message code="auth.register" /></title>
    </head>
    <body>
        <!-- Header fragment -->
        <%@include file="/WEB-INF/jspf/header.jspf" %>

        <div id="main">
            <div id="content">
                <h2><spring:message code="auth.register" /></h2>
                <p>User ID: ${userId}</p>
                <p>Password: ${userPassword}</p>
            </div>
        </div>

        <!-- Footer fragment -->
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
