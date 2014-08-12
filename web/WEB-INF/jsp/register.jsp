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
                <form action="/archelon/register" method="post">
                    <h3><spring:message code="auth.register.mandatory.field" /></h3>
                    <table class="required">
                        <tr>
                            <th><spring:message code="auth.register.userid" /></th>
                            <td><input type="text" name="user_name" value="${input_user_name}"maxlength="64" required /></td>
                            <td class="note">
                                <c:if test="${user_name_miss}">
                                    <spring:message code="auth.register.name.miss" />
                                </c:if>
                                <c:if test="${user_name_duplicate}">
                                    <spring:message code="auth.register.name.duplicate" />
                                </c:if>
                                <spring:message code="auth.register.public" />
                            </td>
                        </tr>
                        <tr>
                            <th><spring:message code="auth.register.userpassword" /></th>
                            <td><input type="password" name="user_password" maxlength="64" required /></td>
                            <td class="note">
                                <c:if test="${password_miss}">
                                    <spring:message code="auth.register.password.miss" />
                                </c:if>
                                <spring:message code="auth.register.private" />
                            </td>
                        </tr>
                        <tr>
                            <th><spring:message code="auth.register.userpassword_r" /></th>
                            <td><input type="password" name="user_password_r" maxlength="64" required /></td>
                            <td class="note">
                                <c:if test="${password_missmatch}">
                                    <spring:message code="auth.register.password.missmatch" />
                                </c:if>
                                <spring:message code="auth.register.private" />
                            </td>
                        </tr>
                        <tr>
                            <th><spring:message code="auth.email" /></th>
                            <td><input type="text" name="email" value="${input_email}" maxlength="64" required /></td>
                            <td class="note">
                                <c:if test="${email_miss}">
                                    <spring:message code="auth.register.email.miss" />
                                </c:if>
                                <c:if test="${email_invalid}">
                                    <spring:message code="auth.register.email.invalid" />
                                </c:if>
                                <spring:message code="auth.register.private" />
                            </td>
                        </tr>
                    </table>
                    <h3><spring:message code="auth.register.optional.field" /></h3>
                    <table class="option">
                        <tr>
                            <th><spring:message code="auth.description" /></th>
                            <td><textarea wrap="off" type="text" name="description" value="${input_description}"></textarea></td>
                            <td class="note"><spring:message code="auth.register.public" /></td>
                        </tr>
                        <tr>
                            <th><spring:message code="auth.birthdate" /></th>
                            <td><input type="date" name="birthdate" value="${input_birthdate}"/></td>
                            <td class="note">
                                <c:if test="${birthdate_future}">
                                    <spring:message code="auth.register.birthdate.future" />
                                </c:if>
                                <c:if test="${birthdate_invalid}">
                                    <spring:message code="auth.register.date.invalid" />
                                </c:if>
                                <spring:message code="auth.register.public" />
                            </td>
                        </tr>
                        <tr>
                            <th><spring:message code="auth.location" /></th>
                            <td><input type="text" name="location" value="${input_location}" maxlength="64" /></td>
                            <td class="note"><spring:message code="auth.register.public" /></td>
                        </tr>
                        <tr>
                            <th><spring:message code="auth.affiliate" /></th>
                            <td><input type="text" name="affiliate" value="${input_affiliate}" maxlength="64" /></td>
                            <td class="note"><spring:message code="auth.register.public" /></td>
                        </tr>
                        <tr>
                            <th><spring:message code="auth.url" /></th>
                            <td><input type="text" name="url" value="${input_url}" maxlength="64" /></td>
                            <td class="note"><spring:message code="auth.register.public" /></td>
                        </tr>
                        <tr>
                            <th><spring:message code="auth.twitterid" /></th>
                            <td><input type="text" name="twitterid" value="${input_twitterid}" maxlength="20" /></td>
                            <td class="note"><spring:message code="auth.register.public" /></td>
                        </tr>
                        <tr>
                            <th><spring:message code="auth.facebookid" /></th>
                            <td><input type="text" name="facebookid" value="${input_facebookid}" maxlength="64" /></td>
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
