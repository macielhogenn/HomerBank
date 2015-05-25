<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="br.com.homerbank.web.captcha.CaptchasDotNet" %>

<%
CaptchasDotNet captchas = new CaptchasDotNet(request.getSession(true), "demo", "secret");
%>

    <div id="captcha-container">
        <div style="padding: 10px 0 10px 0">
            <%= captchas.image() %>
        </div>
        <a href="<%= captchas.audioUrl() %>"><img src="${pageContext.servletContext.contextPath}/resources/default/images/mic.gif" /></a>
        <br/>
        <input id="captcha" name="captcha" type="text" class="field" placeholder="<fmt:message key="app.captcha.input" />" size="16" />
    </div>
            
        
          
        