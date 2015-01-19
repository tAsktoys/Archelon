<%-- 
    Document   : complete
    Created on : 2014/07/29, 19:32:12
    Author     : mikan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../jspf/style.jspf" %>
        <title><spring:message code="app.name" /></title>
        <meta http-equiv="refresh" content="1;URL=./" />
    </head>
    <body>
        <%@include file="../jspf/header.jspf" %>
        <div id="main">
            <%@include file="../jspf/menu.jspf" %>
            <div id="content">
                <h2><spring:message code="auth.login.complete" /></h2>
                <p><spring:message code="auth.login.complete.desc" /></p>
                <p><a href="./"><spring:message code="auth.login.complete.open" /></a></p>
            </div>
        </div>
        <%@include file="../jspf/footer.jspf" %>
    </body>
</html>