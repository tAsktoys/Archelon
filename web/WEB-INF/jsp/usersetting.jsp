<%-- 
    Document   : userInfo
    Created on : 2014/07/09, 22:33:34
    Author     : Yuichiro
--%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Style fragment -->
        <%@include file="/WEB-INF/jspf/style.jspf" %>
        <title><spring:message code="app.name" /> ${id}</title>
    </head>
    <body>
        <!-- Header fragment -->
        <%@include file="/WEB-INF/jspf/header.jspf" %>

        <div id="main">
            <div id="content">
                <h2><spring:message code="usersetting.title" /></h2>
                <form action="<spring:eval expression="@properties.getProperty('contextpath')" />usersetting/usersetting" method="post">
                    <h3><spring:message code="auth.register.mandatory.field" /></h3>
                    <table class="required">
                        <tr>
                            <th><spring:message code="auth.register.userid" /></th>
                            <td><input type="text" name="user_name" value="${current_name}"maxlength="64" required /></td>
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
                            <th><spring:message code="auth.email" /></th>
                            <td><input type="text" name="email" value="${current_email}" maxlength="64" required /></td>
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
                            <td><textarea wrap="off" type="text" name="description">${current_description}</textarea></td>
                            <td class="note"><spring:message code="auth.register.public" /></td>
                        </tr>
                        <tr>
                            <th><spring:message code="auth.birthdate" /></th>
                            <td><input type="date" name="birthdate" value="${current_birthdate}"/></td>
                            <td class="note">
                                <c:if test="${birthdate_future}">
                                    <spring:message code="auth.register.birthdate.future" />
                                </c:if>
                                <c:if test="${birthdate_invalid}">
                                    <spring:message code="auth.register.birthdate.invalid" />
                                </c:if>
                                <spring:message code="auth.register.public" />
                            </td>
                        </tr>
                        <tr>
                            <th><spring:message code="auth.location" /></th>
                            <td><input type="text" name="location" value="${current_location}" maxlength="64" /></td>
                            <td class="note"><spring:message code="auth.register.public" /></td>
                        </tr>
                        <tr>
                            <th><spring:message code="auth.affiliate" /></th>
                            <td><input type="text" name="affiliate" value="${current_affiliate}" maxlength="64" /></td>
                            <td class="note"><spring:message code="auth.register.public" /></td>
                        </tr>
                        <tr>
                            <th><spring:message code="auth.url" /></th>
                            <td><input type="text" name="url" value="${current_url}" maxlength="64" /></td>
                            <td class="note"><spring:message code="auth.register.public" /></td>
                        </tr>
                        <tr>
                            <th><spring:message code="auth.twitterid" /></th>
                            <td><input type="text" name="twitterid" value="${current_twitterID}" maxlength="20" /></td>
                            <td class="note"><spring:message code="auth.register.public" /></td>
                        </tr>
                        <tr>
                            <th><spring:message code="auth.facebookid" /></th>
                            <td><input type="text" name="facebookid" value="${current_facebookID}" maxlength="64" /></td>
                            <td class="note"><spring:message code="auth.register.public" /></td>
                        </tr>
                    </table>
                    <p>
                        <spring:message code="auth.register.userpassword" />
                        <input type="password" name="user_password" maxlength="64" required />
                        <c:if test="${usersetting_password_missmatch}">
                            <spring:message code="usersetting.password.mismatch" />
                        </c:if>
                        <input type="submit" value="<spring:message code="usersetting.submit" />" />
                    </p>
                </form>

                <h3><spring:message code="usersetting.password.reset" /></h3>
                <form action="<spring:eval expression="@properties.getProperty('contextpath')" />usersetting/passwordreset" method="post">
                    <table>
                        <th><spring:message code="auth.register.userpassword" /></th>
                        <td><input type="password" name="new_user_password" maxlength="64" required /></td>
                        <td class="note">
                            <c:if test="${password_miss}">
                                <spring:message code="auth.register.password.miss" />
                            </c:if>
                            <spring:message code="auth.register.private" />
                        </td>
                        </tr>
                        <tr>
                            <th><spring:message code="auth.register.userpassword_r" /></th>
                            <td><input type="password" name="new_user_password_r" maxlength="64" required /></td>
                            <td class="note">
                                <c:if test="${password_missmatch}">
                                    <spring:message code="auth.register.password.missmatch" />
                                </c:if>
                                <spring:message code="auth.register.private" />
                            </td>
                        </tr>
                    </table>
                    <p>
                        <spring:message code="auth.register.userpassword" />
                        <input type="password" name="user_password" maxlength="64" required />
                        <c:if test="${passwordreset_password_missmatch}">
                            <spring:message code="usersetting.password.mismatch" />
                        </c:if>
                        <input type="submit" value="<spring:message code="usersetting.submit" />" />
                    </p>
                </form>

                <h3><spring:message code="usersetting.withdraw" /></h3>
                <form action="<spring:eval expression="@properties.getProperty('contextpath')" />usersetting/withdraw" method="post">
                    <spring:message code="auth.register.userpassword" />
                    <input type="password" name="user_password" maxlength="64" required />
                    <c:if test="${withdraw_password_missmatch}">
                        <spring:message code="usersetting.password.mismatch" />
                    </c:if>
                    <input type="submit" value="<spring:message code="usersetting.submit" />" />
                </form>
            </div>
        </div>

        <!-- Footer fragment -->
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
