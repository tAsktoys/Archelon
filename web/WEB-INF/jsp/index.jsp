<%-- 
    Document   : index
    Author     : mikan,ysato
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
                <!-- Discussions -->
                <div id="discussions">
                    <h2><spring:message code="discussion.names" /></h2>
                    <!-- Category selection -->
                    <form action="<spring:eval expression="@properties.getProperty('contextpath')" />category_selection" method="post" class="category" onchange="submit(this.form)">
                        <spring:message code="category.category1" />
                        <select name="main_category_id">
                            <option value="">
                                <spring:message code="category.category1.all" />
                            </option>
                            <c:forEach var="item" items="${main_category_list}">
                                <option value="${item.id}" ${item.selected}>${item.name}</option>
                            </c:forEach>
                        </select>
                        <spring:message code="category.category2" />
                        <select name="sub_category_id">
                            <option value="">
                                <spring:message code="category.category2.all" />
                            </option>
                            <c:forEach var="item" items="${sub_category_list}">
                                <option value="${item.id}" ${item.selected}>${item.name}</option>
                            </c:forEach>
                        </select>
                    </form>
                    <!-- Table of discussions -->
                    <table>
                        <tr>
                            <th><spring:message code="discussion.no" /></th>
                            <th><spring:message code="discussion.title" /></th>
                            <th><spring:message code="discussion.owner" /></th>
                            <th><spring:message code="discussion.participants" /></th>
                            <th><spring:message code="discussion.createdate" /></th>
                        </tr>
                        <c:forEach var="row" items="${discussion_list}">
                            <tr><td>${row.id}</td><td><a href="discussion/${row.id}">${row.subject}</a></td><td><a href="user/${row.author_id}">${row.author_id}</a></td><td>${row.participants}</td><td>${row.create_time}</td></tr>
                                </c:forEach>
                    </table>
                    <a href="prev/${last_discussion_id}">Prev</a>|[1][2][3]...[10]<a href="next/${last_discussion_id}">Next</a>
                    <br />
                </div>
                <!-- Create a discussion -->
                <div id="create">
                    <h2><spring:message code="discussion.new" /></h2>
                    <c:if test="${userSession.getName() != null}">
                        <form action="<spring:eval expression="@properties.getProperty('contextpath')" />create_discussion" method="post">
                            <table>
                                <tr>
                                    <th><spring:message code="discussion.title" /></th>
                                    <td><input type="text" name="subject" /></td></tr>
                                <tr>
                                    <th><spring:message code="category.name" /></th>
                                    <td>
                                        <div>
                                            <spring:message code="category.category1" />
                                            <select name="main_category_id" onchange="submit(this.form)">
                                                <option value="">
                                                    <spring:message code="category.category1.all" />
                                                </option>
                                                <c:forEach var="item" items="${main_category_list}">
                                                    <option value="${item.id}" ${item.selected}>${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div>
                                            <spring:message code="category.category2" />
                                            <select name="sub_category_id" onchange="submit(this.form)">
                                                <option value="">
                                                    <spring:message code="category.category2.all" />
                                                </option>
                                                <c:forEach var="item" items="${sub_category_list}">
                                                    <option value="${item.id}" ${item.selected}>${item.name}</option>
                                                </c:forEach>
                                        </div>
                                    </td>
                                </tr>
                                <tr><th><spring:message code="discussion.description" /></th>
                                    <td>
                                        <table>
                                            <tr><td>
                                                    <input type="submit" value="Draw" />
                                                    <input type="submit" value="Math" />
                                                </td>
                                            </tr>
                                            <tr><td>
                                                    <textarea name="description"></textarea>                       
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                            <input type="submit" value="Confirm" />
                        </form>
                    </c:if>
                    <c:if test="${userSession.getName() == null}">
                        <c:out value="Please log in to make new discussion"/>
                    </c:if>
                    <div id="activities">
                        <h2><spring:message code="activity.names" /></h2>
                        <table border="1">
                            <c:forEach var="activity" items="${activity_list}">
                                <tr><td>${activity.time}</td><td>${activity.act}</td></tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
            <%@include file="../jspf/footer.jspf" %>
    </body>
</html>
