<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" />

<c:set var="defaultImagesPath" value="${contextPath}/resources/default/images" />
<c:set var="defaultCssPath" value="${contextPath}/resources/default/css" />
<c:set var="defaultJsPath" value="${contextPath}/resources/default/js" />
    
    <title><fmt:message key="app.title" /></title>

    <link rel="shortcut icon" href="${defaultImagesPath}/homerbank-icon.ico"/>
    <link rel="stylesheet" href="${defaultCssPath}/homerbank.css" />
    <link rel="stylesheet" href="${defaultCssPath}/theme.css" />
    
    <script type="text/javascript" src="${defaultJsPath}/jquery.min.js"></script>