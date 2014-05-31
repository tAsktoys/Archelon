<%-- 
    Document   : ranking
    Created on : 2014/05/31, 11:36:41
    Author     : Yuichiro
--%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Style fragment -->
        <%@include file="/WEB-INF/jspf/style.jspf" %>
        
        <title><spring:message code="ranking.title" /></title>
    </head>
    <body>
        <!-- Header fragment -->
        <%@include file="/WEB-INF/jspf/header.jspf" %>

        <h1><spring:message code="ranking.title" /></h1>
                <form action="" method="post">
            <div>
                <spring:message code="category.category1" />:
                <select name="category1">
                    <option value="category1-all">
                        <spring:message code="category.category1.all" />
                    </option>
                    <option value="category1-a">Category 1-A</option>
                    <option value="category1-b">Category 1-B</option>
                    <option value="category1-c">Category 1-C</option>
                </select>
            </div>
            <div>
                <spring:message code="category.category2" />:
                <select name="category2">
                    <option value="category2-all">
                        <spring:message code="category.category2.all" />
                    </option>
                    <option value="category2-a">Category 2-A</option>
                    <option value="category2-b">Category 2-B</option>
                    <option value="category2-c">Category 2-C</option>
                </select>
            </div>
            <div>
                <spring:message code="ranking.sort-type" />:
                <select name="sort-type">
                    <option value="all-type">Sort by...</option>
                    <option value="type1"><spring:message code="discussion.participants" /></option>
                    <option value="type2"><spring:message code="ranking.hottest" /></option>
                </select>
            </div>
        </form>

        <table border="1" style="width: 80%">
            <tr>
                <th><spring:message code="ranking.rank" /></th>
                <th><spring:message code="discussion.title" /></th>
                <th><spring:message code="discussion.owner" /></th>
                <th><spring:message code="discussion.participants" /></th>
                <th><spring:message code="discussion.createdate" /></th>
            </tr>
            <tr><td>1</td><td><a href="discussion.htm?id=3">私はだれでしょう？</a></td><td>mikan</td><td>12</td><td>2014/05/16 23:59</td></tr>
            <tr><td>2</td><td><a href="discussion.htm?id=2">Who am I?</a></td><td>mikan</td><td>7</td><td>2014/05/16 0:00</td></tr>
            <tr><td>3</td><td><a href="discussion.htm?id=1">foo baa</a></td><td>bearing</td><td>4</td><td>2014/05/16 0:00</td></tr>
            <tr><td>4</td><td><a href="discussion.htm?id=3">hoggeeee!</a></td><td>mikan</td><td>3</td><td>2014/05/16 23:59</td></tr>
            <tr><td>5</td><td><a href="discussion.htm?id=2">What is best unko?</a></td><td>mikan</td><td>2</td><td>2014/05/16 0:00</td></tr>
            <tr><td>6</td><td><a href="discussion.htm?id=1">Spring4.0の変更点でよかったところとは</a></td><td>bearing</td><td>1</td><td>2014/05/16 0:00</td></tr>
        </table>
        
        <!-- Footer fragment -->
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
        
    </body>
</html>
