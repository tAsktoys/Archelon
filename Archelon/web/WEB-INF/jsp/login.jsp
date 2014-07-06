<%-- 
    Document   : login
    Created on : 2014/06/22, 0:15:00
    Author     : mikan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Style fragment -->
        <%@include file="/WEB-INF/jspf/style.jspf" %>

        <title><spring:message code="auth.login" /></title>
    </head>
    <body>
        <!-- Header fragment -->
        <%@include file="/WEB-INF/jspf/header.jspf" %>

        <div id="main">
            <div id="content">
                <h2><spring:message code="auth.login" /></h2>
                <p>User ID: ${userId}</p>
                <p>Password: ${userPassword}</p>
            </div>
        </div>

        <!-- Footer fragment -->
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
