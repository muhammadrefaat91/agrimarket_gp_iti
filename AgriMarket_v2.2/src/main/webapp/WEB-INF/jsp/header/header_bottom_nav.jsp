<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>

<link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet" />
<link href="<spring:url value="/resources/css/slider.css" />" rel="stylesheet" />

<script src="<spring:url value="/resources/js/jquery.min.js" />"></script>
<script>
    $(document).ready(function () {
        if (window.location.pathname == "${pageContext.request.contextPath}/contact.htm")
            $("#contact").attr("class", "active");
        else if (window.location.pathname == "${pageContext.request.contextPath}/about.htm")
            $("#about").attr("class", "active");
        else if (window.location.pathname == "${pageContext.request.contextPath}/index.htm")
            $("#home").attr("class", "active");
        else if (window.location.pathname == "${pageContext.request.contextPath}/web/getoffers.htm")
            $("#products").attr("class", "active");
    });
</script>
<div class="header_bottom">
    <div class="menu">
        <ul>
			    	<li id="home" name="home"><a href="${pageContext.request.contextPath}/index.htm"><spring:message code="link.home" /></a></li>
			    	<li id="products" name="products" ><a href="${pageContext.request.contextPath}/web/getoffers.htm"><spring:message code="link.nav.products" /></a></li>
			    	<li id="about" name="about" ><a href="${pageContext.request.contextPath}/about.htm"><spring:message code="link.nav.about" /></a></li>
			    	<li id="contact" name="contact" ><a href="${pageContext.request.contextPath}/contact.htm"><spring:message code="link.nav.contact" /></a></li>
            <div class="clear"></div>
        </ul>
    </div>
    <div class="search_box">
                    <form action="${pageContext.request.contextPath}/web/getoffers.htm" method="get">
                        <input type="text" value="${param.name}" name="name" placeholder="<spring:message code="input.header_bottom.searchtext" />">
            <input type="submit" value="" >
        </form>
    </div>
    <div class="clear"></div>
</div>
