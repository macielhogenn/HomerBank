<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="templates/imports.jsp" %>
    </head>
    <body>
        
        <%@include file="templates/header.jsp" %>
        
        <center>

            <h1><fmt:message key="app.transfer.title" /></h1>

            <form method="post" action="${pageContext.servletContext.contextPath}/Transfer">
                <input id="agency" name="agency" type="text" class="field" placeholder="<fmt:message key="app.view.agency" />"/><br/>
                <input id="account" name="account" type="text" class="field" placeholder="<fmt:message key="app.view.account" />" /><br/>
                <input id="amount" name="amount" type="text" class="field" placeholder="<fmt:message key="app.view.amount" />" /><br/>

                <%@include file="templates/captcha.jsp" %>
                
                <input id="submit" name="submit" type="submit" class="button" value="<fmt:message key="app.view.confirm" />" /><br/>
            </form>
            <%@include file="templates/messages.jsp" %>
        </center>
    </body>
</html>
