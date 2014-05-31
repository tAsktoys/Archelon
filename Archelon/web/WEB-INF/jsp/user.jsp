<%-- 
    Document   : profile
    Created on : 2014/05/31, 13:02:42
    Author     : mikan
--%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Style fragment -->
        <%@include file="/WEB-INF/jspf/style.jspf" %>
        
        <title><spring:message code="app.name" /> mikan</title>
    </head>
    <body>
        <!-- Header fragment -->
        <%@include file="/WEB-INF/jspf/header.jspf" %>
        
        <h2>${id}<spring:message code="user.title.suffix" /></h2>
        
        
        
        <!-- Footer fragment -->
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
