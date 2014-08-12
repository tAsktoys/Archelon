<%-- 
    Document   : complete
    Created on : 2014/08/13, 4:30:58
    Author     : YuichiroSato
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Style fragment -->
        <%@include file="/WEB-INF/jspf/style.jspf" %>

        <title><spring:message code="auth.complete" /></title>
    </head>
    <body>
        <!-- Header fragment -->
        <%@include file="/WEB-INF/jspf/header.jspf" %>

        <div id="main">
            <div id="content">
                <h2><spring:message code="auth.complete" /></h2>
                <p><spring:message code="auth.complete.message" /></p>
            </div>
        </div>
        <%@include file="../jspf/footer.jspf" %>
    </body>
</html>
