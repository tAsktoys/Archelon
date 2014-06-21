<%-- 
    Document   : sandbox.jsp
    Created on : 2014/06/14, 12:15:42
    Author     : bearing
--%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Style fragment -->
        <%@include file="/WEB-INF/jspf/style.jspf" %>
        <title><spring:message code="app.name" /> boo</title>
    </head>
    <body>
        <!-- Header fragment -->
        <%@include file="/WEB-INF/jspf/header.jspf" %>

        <div id="main">

            <h2>${id}<spring:message code="user.title.suffix" /></h2>
            <p>test is ${test} </p>


        </div>

        <!-- Footer fragment -->
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
