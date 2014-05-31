<%-- 
    Document   : chat
    Created on : 2014/05/30, 21:15:44
    Author     : Yuichiro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Style fragment -->
        <%@include file="/WEB-INF/jspf/style.jspf" %>
        
        <title>Chat</title>
    </head>
    <body>
        <!-- Header fragment -->
        <%@include file="/WEB-INF/jspf/header.jspf" %>
        
        <div style="text-align: center">
            質問
        </div>
        <div style="text-align: center">目玉焼きに一番合う調味料は？</div>
        <div style="margin-left: auto; margin-right : auto">
            <div style="margin-left: auto">
                <table>
                    <tr>
                        <td>
                            <img src="" alt="" width=50 height=50>
                        </td>
                        <td>
                            <a href="">Sato</a>:<br>
                            やっぱ目玉焼きにはソースだよな！
                        </td>
                    </tr>
                </table>
            </div><br>
            <div style="margin-left: auto">
                <table>
                    <tr>
                        <td>
                            <img src="" alt="" width=50 height=50>
                        </td>
                        <td>
                            anonymous:<br>
                            ないわー
                        </td>
                    </tr>
                </table>
            </div>
            <div aligin="right">
                <table>
                    <tr>
                        <td>
                            <a href="">みかん</a>:<br>
                            俺はコショウ！
                        </td>
                        <td>
                            <img src="" alt="" width=50 height=50>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <form action="" method="post">
            <input type="checkbox" name="q1" value="text" checked>text
            <input type="checkbox" name="q1" value="figure">fig
            <input type="checkbox" name="q1" value="equation">math<br>
            <textarea rows="5" cols="64" wrap="off" name="MESSAGE"></textarea><br>
            <input type="submit" value="submit" name="submit">
            <input type="submit" value="clear" name="clear">
        </form>
        
        <!-- Footer fragment -->
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
