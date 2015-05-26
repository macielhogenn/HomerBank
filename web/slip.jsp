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
            <h1><fmt:message key="app.slip.title" /></h1>
            <form method="post" action="${pageContext.servletContext.contextPath}/Slip">
                <input id="bar-code" name="bar-code" type="text" class="field" placeholder="<fmt:message key="app.slip.barCode" />" /><br/>
                <input id="date-of-payment" name="date-of-payment" type="text" class="field" placeholder="<fmt:message key="app.slip.dateOfPayment" />" /><br/>
                <input id="amount" name="amount" type="text" class="field" placeholder="<fmt:message key="app.slip.amount" />" /><br/>
                <input id="password" name="password" type="text" class="field" placeholder="<fmt:message key="app.view.password" />" /><br/> 

                <%@include file="templates/captcha.jsp" %>

                <input id="submit" name="submit" type="submit" class="button" value="<fmt:message key="app.slip.pay" />" />
            </form>

            <%@include file="templates/messages.jsp" %>
        </center>
    </body>
</html>
