<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="templates/imports.jsp" %>
    </head>
    <body style="padding-top: 100px;">
        <center>
            <h1><fmt:message key="app.view.authenticate.welcome" /></h1>
            <br/>
            
            <div>
                <form method="post" action="${pageContext.servletContext.contextPath}/AuthLogin" >
                    <input id="agency" name="agency" type="text" class="field" placeholder="<fmt:message key="app.view.agency" />"/><br/>
                    <input id="account" name="account" type="text" class="field" placeholder="<fmt:message key="app.view.account" />"/><br/>
                    <input id="password" name="password" type="password" class="field" placeholder="<fmt:message key="app.view.password" />"/><br/>
                
                    <input id="submit" name="submit" type="submit" class="button" value="<fmt:message key="app.view.authenticate.loginButton" />" /><br/>
                </form>
                <%@include file="templates/messages.jsp" %>
            </div>
        </center>
    </body>
</html>
