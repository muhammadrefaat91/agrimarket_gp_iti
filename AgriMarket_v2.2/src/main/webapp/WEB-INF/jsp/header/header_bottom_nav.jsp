<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>

<link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet" />
    <link href="<spring:url value="/resources/css/slider.css" />" rel="stylesheet" />	
<div class="header_bottom">
	     	<div class="menu">
	     		<ul>
			    	<li class="active"><a href="${pageContext.request.contextPath}/index.htm">Home</a></li>
			    	<li><a href="${pageContext.request.contextPath}/offers.htm">Products</a></li>
			    	<li><a href="${pageContext.request.contextPath}/about.htm">About</a></li>
			    	<li><a href="${pageContext.request.contextPath}/contact.htm">Contact</a></li>
			    	<div class="clear"></div>
     			</ul>
	     	</div>
<!--	     	<div class="search_box">
	     		<form>
	     			<input type="text" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}"><input type="submit" value="">
	     		</form>
	     	</div>-->
	     	<div class="clear"></div>
	     </div>