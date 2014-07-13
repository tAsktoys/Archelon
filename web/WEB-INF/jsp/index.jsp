<%-- 
    Document   : index
    Author     : mikan
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
                    <form action="" method="post" class="category">
                        <spring:message code="category.category1" />
                        <select name="category1">
                            <option value="category1-all">
                                <spring:message code="category.category1.all" />
                            </option>
                            <option value="category1-a">Category 1-A</option>
                            <option value="category1-b">Category 1-B</option>
                            <option value="category1-c">Category 1-C</option>
                        </select>
                        <spring:message code="category.category2" />
                        <select name="category2">
                            <option value="category2-all">
                                <spring:message code="category.category2.all" />
                            </option>
                            <option value="category2-a">Category 2-A</option>
                            <option value="category2-b">Category 2-B</option>
                            <option value="category2-c">Category 2-C</option>
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
                        <tr><td>3</td><td><a href="discussion/3">私はだれでしょう？</a></td><td><a href="user/mikan">mikan</a></td><td>12</td><td>2014/05/16 23:59</td></tr>
                        <tr><td>2</td><td><a href="discussion/2">Who am I?</a></td><td><a href="user/mikan">mikan</a></td><td>7</td><td>2014/05/16 0:00</td></tr>
                        <tr><td>1</td><td><a href="discussion/1">foo baa</a></td><td><a href="user/bearing">bearing</a></td><td>9</td><td>2014/05/16 0:00</td></tr>
                    </table>
                    Page: [1]<br />
                </div>
                <!-- Create a discussion -->
                <div id="create">
                    <h2><spring:message code="discussion.new" /></h2>
                    <form action="" method="post">
                        <table>
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
                </div>
                <div id="activities">
                    <h2><spring:message code="activity.names" /></h2>
                    <table border="1">
                        <tr><td>たった今</td><td><a href="user/mikan">mikan</a> が <a href="discussion/3">私はだれでしょう？</a> という話題を作りました！</td></tr>
                        <tr><td>1分前</td><td><a href="user/marishi">まりし</a>が <a href="discussion/2">who I am?</a> で発言しました！</td></tr>
                        <tr><td>10分前</td><td>ほげほげという話題がランキングにのりました！</td></tr>
                        <tr><td>3時間前</td><td>ほげほげ！</td></tr>
                        <tr><td>3時間前</td><td>ほげほげ！</td></tr>
                    </table>
                </div>
            </div>
        </div>
        <%@include file="../jspf/footer.jspf" %>
    </body>
</html>
