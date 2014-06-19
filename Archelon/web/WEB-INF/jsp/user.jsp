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

        <div id="main">

            <h2>${id}<spring:message code="user.title.suffix" /></h2>

            <div id="user_information">
                <h3>User Information</h3>
                <p>one comment: I am a high school student in Isikawa. I have an interest in Computer Science. I have been challenging to invent a web service by Common Lisp :-)</p>
                <ul>
                    <li>name :Hoge</li>
                    <li>age : 17</li>
                    <li>school : High scool</li>
                    <li>contact information</li>
                    <ul>
                        <li>facebook :</li>
                        <li>twitter : twitterid</li>
                        <li>skype :</li>
                    </ul>
                    <li>activity: around-a-clock</li>
                    <li>hiralcy : master</li>
                </ul>
            </div>
            
            <div id="user_activities">
                <h3>user activities</h3>
                    <div class="user_activity">
                        hogeさんは「うぬうぬ」という話題で「ほげほげほげっっっっっっっっっほっっっっっっっっっっっっｈ」と言った
                    </div>
                    <div class="user_activity">
                        hogeさんは「うぬうぬ」という話題で「ほげほげほげほ！！！！！ほげほげほげっっっっっっっっっほっっっっっっっっっっっっｈほああああああああああああああああああああああああああああああああああああああああああ」と言った
                    </div>
                    <div class="user_activity">
                        hogeさんは「うぬうぬ」という話題で「」
                    </div>
                    <div class="user_activity">
                        hogeさんは「うぬうぬ」という話題で「」
                    </div>
                    <div class="user_activity">
                        hogeさんは「うぬうぬ」という話題で「」
                    </div>
            </div>
            
        </div>

        <!-- Footer fragment -->
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
