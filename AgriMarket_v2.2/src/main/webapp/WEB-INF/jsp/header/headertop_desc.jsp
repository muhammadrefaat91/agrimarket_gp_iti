
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet" />
<link href="<spring:url value="/resources/css/slider.css" />" rel="stylesheet" />	
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<c:if test="${empty user}" >
    <div class="headertop_desc">

        <div class="account_desc">
            <ul>
                <li><a href="${pageContext.request.contextPath}/signup.htm"><spring:message code="link.signup" /></a></li>
                <li><a href="${pageContext.request.contextPath}/login.htm"><spring:message code="link.login" /></a></li>
<!--                <li><a href="#">Delivery</a></li>
                <li><a href="#">Checkout</a></li>
                <li><a href="#">My Account</a></li>-->
            </ul>
        </div>
        <div class="clear"></div>
    </div>
</c:if>