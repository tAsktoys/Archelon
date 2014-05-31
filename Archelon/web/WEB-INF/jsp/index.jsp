<%-- 
    Document   : index
    Author     : mikan
--%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Style fragment -->
        <%@include file="/WEB-INF/jspf/style.jspf" %>
        
        <title><spring:message code="app.name" /></title>
    </head>
    <body>
        <!-- Header fragment -->
        <%@include file="/WEB-INF/jspf/header.jspf" %>

        

        <!-- Category selection -->
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
        </form>

        <hr />

        <!-- Table of discussions -->
        <h2><spring:message code="discussion.names" /></h2>
        <table border="1" style="width: 80%">
            <tr>
                <th><spring:message code="discussion.no" /></th>
                <th><spring:message code="discussion.title" /></th>
                <th><spring:message code="discussion.owner" /></th>
                <th><spring:message code="discussion.participants" /></th>
                <th><spring:message code="discussion.createdate" /></th>
            </tr>
            <tr><td>3</td><td><a href="discussion.htm?id=3">私はだれでしょう？</a></td><td>mikan</td><td>12</td><td>2014/05/16 23:59</td></tr>
            <tr><td>2</td><td><a href="discussion.htm?id=2">Who am I?</a></td><td>mikan</td><td>7</td><td>2014/05/16 0:00</td></tr>
            <tr><td>1</td><td><a href="discussion.htm?id=1">foo baa</a></td><td>bearing</td><td>9</td><td>2014/05/16 0:00</td></tr>
        </table>
        Page: [1]<br />
        <hr />

        <!-- Create a discussion -->
        <h2><spring:message code="discussion.new" /></h2>
        <form action="" method="post">
            <table border="1">
                <tr>
                    <th><spring:message code="discussion.title" /></th>
                    <td><input type="text" name="title" /></td></tr>
                <tr>
                    <th><spring:message code="category.name" /></th>
                    <td>
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

        <hr />

        <h2><spring:message code="activity.names" /></h2>
        <table border="1">
            <tr><td>たった今</td><td>mikan が <a href="discussion.htm?id=3">私はだれでしょう？</a> という話題を作りました！</td></tr>
            <tr><td>1分前</td><td>まりしが <a href="discussion.htm?id=2">who I am?</a> で発言しました！</td></tr>
            <tr><td>10分前</td><td>ほげほげという話題がランキングにのりました！</td></tr>
            <tr><td>3時間前</td><td>ほげほげ！</td></tr>
            <tr><td>3時間前</td><td>ほげほげ！</td></tr>
        </table>

        <!-- Footer fragment -->
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
