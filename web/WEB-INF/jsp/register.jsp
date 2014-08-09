<%-- 
    Document   : register
    Created on : 2014/06/22, 0:29:23
    Author     : mikan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Style fragment -->
        <%@include file="/WEB-INF/jspf/style.jspf" %>

        <title><spring:message code="auth.register" /></title>
    </head>
    <body>
        <!-- Header fragment -->
        <%@include file="/WEB-INF/jspf/header.jspf" %>

        <div id="main">
            <div id="content">
                <h2><spring:message code="auth.register" /></h2>
                <form action="" method="post">
                    <h3><spring:message code="auth.register.mandatory.field" /></h3>
                    <table class="required">
                        <tr>
                            <th><spring:message code="auth.userid" /></th>
                            <td><input type="text" name="user_name" maxlength="64" required /></td>
                            <td class="note"><spring:message code="auth.register.public" /></td>
                        </tr>
                        <tr>
                            <th><spring:message code="auth.userpassword" /></th>
                            <td><input type="password" name="user_password" maxlength="64" required /></td>
                            <td class="note"><spring:message code="auth.register.private" /></td>
                        </tr>
                        <tr>
                            <th><spring:message code="auth.userpassword_r" /></th>
                            <td><input type="password" name="user_password_r" maxlength="64" required /></td>
                            <td class="note"><spring:message code="auth.register.private" /></td>
                        </tr>
                        <tr>
                            <th><spring:message code="auth.email" /></th>
                            <td><input type="text" name="email" maxlength="64" required /></td>
                            <td class="note"><spring:message code="auth.register.private" /></td>
                        </tr>
                    </table>
                    <h3><spring:message code="auth.register.optional.field" /></h3>
                    <table class="option">
                        <tr>
                            <th><spring:message code="auth.description" /></th>
                            <td><textarea wrap="off" type="text" name="description"></textarea></td>
                            <td class="note"><spring:message code="auth.register.public" /></td>
                        </tr>
                        <tr>
                            <th><spring:message code="auth.birthdate" /></th>
                            <td><input type="date" name="birthdate" /></td>
                            <td class="note"><spring:message code="auth.register.public" /></td>
                        </tr>
                        <tr>
                            <th><spring:message code="auth.location" /></th>
                            <td><input type="text" name="location" maxlength="64" /></td>
                            <td class="note"><spring:message code="auth.register.public" /></td>
                        </tr>
                        <tr>
                            <th><spring:message code="auth.affiliate" /></th>
                            <td><input type="text" name="affiliate" maxlength="64" /></td>
                            <td class="note"><spring:message code="auth.register.public" /></td>
                        </tr>
                        <tr>
                            <th><spring:message code="auth.url" /></th>
                            <td><input type="text" name="url" maxlength="64" /></td>
                            <td class="note"><spring:message code="auth.register.public" /></td>
                        </tr>
                        <tr>
                            <th><spring:message code="auth.twitterid" /></th>
                            <td><input type="text" name="twitterid" maxlength="20" /></td>
                            <td class="note"><spring:message code="auth.register.public" /></td>
                        </tr>
                        <tr>
                            <th><spring:message code="auth.facebookid" /></th>
                            <td><input type="text" name="facebookid" maxlength="64" /></td>
                            <td class="note"><spring:message code="auth.register.public" /></td>
                        </tr>
                    </table>
                    <h3><spring:message code="auth.register.submit" /></h3>
                    <input type="submit" value="<spring:message code="auth.register.submit" />" />
                    <p><spring:message code="auth.register.confirm.agreement" /></p>
                </form>
            </div>
        </div>

        <!-- Footer fragment -->
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
