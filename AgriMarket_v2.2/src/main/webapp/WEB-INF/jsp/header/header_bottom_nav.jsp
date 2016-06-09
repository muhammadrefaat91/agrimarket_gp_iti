<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>

<link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet" />
    <link href="<spring:url value="/resources/css/slider.css" />" rel="stylesheet" />	
<div class="header_bottom">
	     	<div class="menu">
	     		<ul>
			    	<li class="active"><a href="${pageContext.request.contextPath}/index.htm">Home</a></li>
			    	
			    	<li><a href="offers.htm">Products</a></li>
			    	<!--<li><a href="news.htm">News</a></li>-->
			    	<li><a href="contact.htm">Contact</a></li>
                                <li><a href="about.htm">About</a></li>
			    	<div class="clear"></div>
     			</ul>
	     	</div>
	     	<div class="search_box">
                    <form action="${pageContext.request.contextPath}/web/getoffers" method="get">
                        <input type="text" value="${param.name}" name="name" placeholder="search for  products..">
                            <input type="submit" value="" >
	     		</form>
	     	</div>
	     	<div class="clear"></div>
	     </div>