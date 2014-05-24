<%-- 
    Document   : index
    Author     : mikan
--%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="app.name" /></title>
    </head>
    <body>
        <h1><spring:message code="app.name" /></h1>
        
        <hr />
        
        <!-- Seach form -->
        <form action="" method="get">
            <div style="text-align: right">
                <input type="text" name="search" />
                <input type="submit" value="Search" />
            </div>
        </form>
        
        <hr />

        <!-- Login form -->
        <form action="" method="post">
            <div style="text-align: right">
                <spring:message code="auth.userid" />
                <input type="text" name="userid" />
                &nbsp;
                <spring:message code="auth.userpassword" />
                <input type="text" name="userpassword" />
                <input type="submit" value="Login" />
            </div>
        </form>
                
        <hr />

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
        <table border="1">
            <tr>
                <th><spring:message code="discussion.no" /></th>
                <th><spring:message code="discussion.title" /></th>
                <th><spring:message code="discussion.owner" /></th>
                <th><spring:message code="discussion.createdate" /></th>
            </tr>
            <tr><td>2</td><td>私はだれでしょう？</td><td>mikan</td><td>2014/05/16 23:59</td></tr>
            <tr><td>1</td><td>Who am I?</td><td>mikan</td><td>2014/05/16 0:00</td></tr>
        </table>
            
            <hr />

        <!-- Create a discussion -->
        <h2><spring:message code="discussion.new" /></h2>
        <form action="" method="post">
            <table border="1">
                <tr>
                    <th><spring:message code="discussion.title" /></th>
                    <td><input type="text" name="title" /></td></tr>
                <tr><th><spring:message code="discussion.description" /></th>
                    <td>
                        <table>
                            <tr><td>
                                    <input type="submit" value="Draw" />
                                    <input type="submit" value="Math" />
                                </td>
                            </tr>
                            <tr><td>
                                    <textarea name="title"></textarea>                       
                                </td>    
                            </tr>
                        </table>
                    </td>
                </tr>
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
            </table>
            <input type="submit" value="Confirm" />
        </form>
                    
                    <hr />

        <p><spring:message code="app.copyright" /></p>
    </body>
</html>
