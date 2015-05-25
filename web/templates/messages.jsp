<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:if test="${!empty requestScope.status && requestScope.status == 'error'}" >
    <div id="div_danger" class="alert alert-dismissable alert-danger" >
        <c:out value="${requestScope.message}" escapeXml="true" />
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true" onclick="$('div#div_danger').hide()">×</button>
    </div>
</c:if>

<c:if test="${!empty requestScope.status && requestScope.status == 'success'}" >
    <div id="div_sucesss" class="alert alert-success alert-dismissable" >
        <c:out value="${requestScope.message}" escapeXml="true" />
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true" onclick="$('div#div_sucesss').hide()">×</button>
    </div>
</c:if>
