<%-- 
    Document   : chat
    Created on : 2014/05/30, 21:15:44
    Author     : Yuichiro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Style fragment -->
        <%@include file="/WEB-INF/jspf/style.jspf" %>

        <title>Discussion</title>
    </head>
    <body>
        <!-- Header fragment -->
        <%@include file="/WEB-INF/jspf/header.jspf" %>

        <div id="main">

            <div id="discussion_theme">
                ${theme}
            </div>
            
            <div id="discussion_log">
                <ul id="discussion_log_list">
                    <c:forEach var="item" items="${discussion_log}">
                        <li class="${item.type}">
                            <img src="${item.icon}" alt="" width="50" heigth="50" class="${item.type}">
                            <div class="${item.type}">
                                <p><a href="${item.userpage}">${item.username}</a></p>
                                <p>${item.message}</p>
                            </div>
                        </li>
                    </c:forEach>
                        <!--
                    <il class="others">
                        <img src="" alt="" width=50 height=50 class="others"/>
                        <p><a href="">Sato</a>:</p>
                        <p>やっぱ目玉焼きにはソースだよな！</p>
                    </il>
                    <il class="others">
                        <img src="" alt="" width=50 height=50 class="others"/>
                        <p><a href="">anonymous</a>:</p>
                        <p>ないわー</p>
                    </il>
                    <il class="me">
                        <img src="" alt="" width=50 height=50 class="me"/>
                        <p><a href="">みかん</a>:</p>
                        <p>俺はコショウ！</p>
                    </il>
                    <il class="others">
                        <img src="" alt="" width=50 height=50 class="others"/>
                        <p><a href="">nobuko</a>:</p>
                        <p>ほげほげほげ！ほげほげほげ！ほげほげほげ！ほげほげほげ！ほげほげほげ！ほげほげほげ！ほげほげほげ！ほげほげほげ！ほげほげほげ！ほげほげほげ！ほげほげほげ！ほげほげほげ！ほげほげほげ！ほげほげほげ！ほげほげほげ！ほげほげほげ！ほげほげほげ！ほげほげほげ！ほげほげほげ！ほげほげほげ！ほげほげほげ！</p>
                    </il> -->
                </ul>
            </div>
            
            <form action="/archelon/discussion" method="post" id="discussion_form">
                <div id="discussion_selectors">
                    <input type="checkbox" name="q1" value="text" checked>text
                    <input type="checkbox" name="q1" value="figure">fig
                    <input type="checkbox" name="q1" value="equation">math
                </div>
                <textarea wrap="off" type="text" name="postedMessage" id="discussion_field"></textarea>
                <div id="discussion_bottons">
                    <input type="submit" value="submit" name="submit">
                    <input type="submit" value="clear" name="clear">
                </div>
            </form>

        </div>

        <!-- Footer fragment -->
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
