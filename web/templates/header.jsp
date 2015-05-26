<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div class="header">
        
        <div class="left">
            <a href="${pageContext.servletContext.contextPath}/homer.jsp"><img style="max-height: 90px; float: left; padding: 5px 5px 5px 5px" src="<c:out value="${pageContext.servletContext.contextPath}" />/resources/default/images/homerbank-logo.png" /></a>
        </div>
        
        <div class="right">
            <div class="owner-info">
                <span><c:out value="${sessionScope.account.owner.name}" escapeXml="true" /></span><br/>
                <span style="font-size: 80%; float: right"><c:out value="${sessionScope.account.agency.number}" escapeXml="true" /></span><br/>
                <span style="font-size: 80%; float: right"><c:out value="${sessionScope.account.number}" escapeXml="true" /></span><br/>
                <span style="float: right"><fmt:formatNumber value="${sessionScope.account.balance}" type="currency"/></span><br/>
            </div>
        </div>
    </div>  
    
    <div class="menu">
        <div class="left">
            <ul>
                <li><a href="${pageContext.servletContext.contextPath}/transfer.jsp"><fmt:message key="app.transfer.title" /></a></li>
                <li><a href="${pageContext.servletContext.contextPath}/statement.jsp"><fmt:message key="app.statement.title" /></a></li>
                <li><a href="${pageContext.servletContext.contextPath}/slip.jsp"><fmt:message key="app.slip.title" /></a></li>
            </ul>
        </div>
        <div class="right">
            <ul style="float: right">
                <li><a href="${pageContext.servletContext.contextPath}/Logout?a=a"><fmt:message key="app.view.authenticate.logoutButton" /></a></li>
            </ul>
        </div>
        
    </div>
