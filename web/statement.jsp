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
            <h1><fmt:message key="app.statement.title" /></h1>

            <c:if test="${empty requestScope.transactions}">
                <form method="post" action="${pageContext.servletContext.contextPath}/Statement">
                    <input id="submit" name="submit" type="submit" class="button" value="<fmt:message key="app.statement.queryLastThirtyDays" />" /><br/>
                    
                    <input id="date-start" name="date-start" type="text" class="field" placeholder="<fmt:message key="app.statement.dateStart" />" /><br/>
                    <input id="date-end" name="date-end" type="text" class="field" placeholder="<fmt:message key="app.statement.dateEnd" />" /><br/>

                    <input id="submit" name="submit" type="submit" class="button" value="<fmt:message key="app.statement.query" />" /><br/>
                </form>
            </c:if>

            <c:if test="${!empty requestScope.transactions}">
                <h4><c:out value="AG ${sessionScope.account.agency.number}   -   AC ${sessionScope.account.number}   -   ${sessionScope.account.owner.name}" /></h4><br/>
                <table class="statement">
                    <tbody>
                        <c:set var="i" value="${1}" />
                        <c:forEach var="t" items="${requestScope.transactions}">
                            <c:set var="isDebit" value="${sessionScope.account.id eq t.acDebit.id}"/>
                            <tr style="<c:if test="${sessionScope.account.id eq t.acDebit.id}">color: red;</c:if>">
                                <td><c:out value="${i}" /></td>
                                <td><fmt:formatDate value="${t.date}" pattern="dd/MM/yyyy" /></td>
                                <c:if test="${sessionScope.account.id eq t.acDebit.id}">
                                    <td><c:out value="AG ${t.acCredit.agency.number}  -  AC ${t.acCredit.number}  -  ${t.acCredit.owner.name}" /></td>
                                    <td>D <fmt:formatNumber value="${t.amount}" type="currency" /></td>
                                </c:if>
                                <c:if test="${sessionScope.account.id eq t.acCredit.id}">
                                    <td><c:out value="AG ${t.acDebit.agency.number}  -  AC ${t.acDebit.number}  -  ${t.acDebit.owner.name}" /></td>
                                    <td>C <fmt:formatNumber value="${t.amount}" type="currency" /></td>
                                </c:if>
                            </tr>
                            <c:set var="i" value="${i + 1}" />
                        </c:forEach>
                    </tbody>
                </table><br/>
                <input type="button" class="button" value="<fmt:message key="app.view.print" />" onclick="window.print()" />
            </c:if>
            <%@include file="templates/messages.jsp" %>
        </center>
    </body>
</html>
