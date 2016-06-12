<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>

<link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet" />
<link href="<spring:url value="/resources/css/slider.css" />" rel="stylesheet" />	
<link rel="icon" href="<spring:url value="/resources/images/agri_logo_vs.png" />">

<div class="header">

    <div class="header_slide">
        <div class="header_bottom_left" style="float: <spring:message code="header.css.header_bottom_left.float"/>">				
            <div class="advertisements" style="text-align: <spring:message code="header.css.header_bottom_left.advertise.text-align"/>;">
                <ul>
                    <h3><spring:message code="text.header.categories" /></h3>
                    <!-- view advertisements images-->
                    <c:forEach items="${allcategories}" var="module"> 

                        <li><a href="${pageContext.request.contextPath}/web/getoffers.htm?category=${module.id}">${requestScope.lang eq 'en'?module.nameEn:module.nameAr}</a></li>
                        </c:forEach>

                </ul>
            </div>					
        </div>
                    <div class="header_bottom_right" style="margin-left: <spring:message code="header.css.header_bottom_right.margin-left"/>px;">					 
            <div class="slider">					     
                <div id="slider"><!-- view special  offers ads-->
                    <div id="mover">
                        <div id="slide" class="slide">			                    
                            <div class="slider-img">
                                <a href="#"><img src="resources/images/slider.jpg" alt="AgriMarket" /></a>									    
                            </div>
                            <div class="slider-text">
                                <h1><spring:message code="title.index.agrimarket"/><br>
                                    <!--<span>AgriMarket</span></h1>-->
                                <h2><spring:message code="title.index.sale"/></h2>
                                <div class="features_list">
                                    <h4><spring:message code="title.index.bestselling"/></h4>							               
                                </div>
                                <a href="addoffer" class="button"><spring:message code="button.index.sellnow"/></a>
                            </div>			               
                            <div class="clear"></div>				
                        </div>	
                        <div class="slide">
                            <div class="slider-text">
                                <h1>Clearance<br><span>SALE</span></h1>
                                <h2>UPTo 40% OFF</h2>
                                <div class="features_list">
                                    <h4>Get to Know More About Our Memorable Services</h4>							               
                                </div>
                                <a href="preview.html" class="button">Shop Now</a>
                            </div>		
                            <div class="slider-img">
                                <a href="preview.html"><img src="resources/images/slider.jpg" alt="learn more" /></a>
                            </div>						             					                 
                            <div class="clear"></div>				
                        </div>
                        <div class="slide">						             	
                            <div class="slider-img">
                                <a href="preview.html"><img src="resources/images/slider.jpg" alt="learn more" /></a>
                            </div>
                            <div class="slider-text">
                                <h1>Clearance<br><span>SALE</span></h1>
                                <h2>UPTo 10% OFF</h2>
                                <div class="features_list">
                                    <h4>Get to Know More About Our Memorable Services Lorem Ipsum is simply dummy text</h4>							               
                                </div>
                                <a href="preview.html" class="button">Shop Now</a>
                            </div>	
                            <div class="clear"></div>				
                        </div>												
                    </div>		
                </div>
                <div class="clear"></div>					       
            </div>
        </div>
        <div class="clear"></div>
    </div>
</div>
