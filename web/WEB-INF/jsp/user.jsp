<%-- 
    Document   : user
    Created on : 2014/05/31, 13:02:42
    Author     : mikan
    Author     : ysato
--%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Style fragment -->
        <%@include file="/WEB-INF/jspf/style.jspf" %>
        <title><spring:message code="app.name" /> ${name}</title>
    </head>
    <body>
        <!-- Header fragment -->
        <%@include file="/WEB-INF/jspf/header.jspf" %>

        <div id="main">

            <h2>${name}<spring:message code="user.title.suffix" /></h2>

            <div id="user_information">
                <h3><spring:message code="user.information.title" /></h3>
                <p>${profile}</p>
                <ul id="user_information_list">
                    <c:forEach var="info" items="${overview}">
                        <li class="user_information_item"><c:out value="${info}"/></li>
                    </c:forEach>
                </ul>
            </div>
            
            <div id="user_activities">
                <h3><spring:message code="user.activity.title" /></h3>
                <c:forEach var="activity" items="${user_activity}">
                    <div class="user_activity">${activity}</div>
                </c:forEach>
            </div>
            
        </div>

        <!-- Footer fragment -->
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
