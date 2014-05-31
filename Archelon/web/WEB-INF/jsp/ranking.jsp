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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<spring:theme code="styleSheet"/>" type="text/css"/>
          <title><spring:message code="ranking.title" /></title>
    </head>
    <body>
        <h1><spring:message code="ranking.title" /></h1>
        <select name="sort-type">
            <option value="all-type">Sort by...</option>
            <option value="type1"><spring:message code="discussion.participants" /></option>
            <option value="type2"><spring:message code="ranking.hottest" /></option>
        </select>
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
    </body>
</html>
