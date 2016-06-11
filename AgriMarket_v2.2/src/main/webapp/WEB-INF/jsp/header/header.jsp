<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>

<link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet" />
<link href="<spring:url value="/resources/css/slider.css" />" rel="stylesheet" />	


<div class="header">

    <div class="header_slide">
        <div class="header_bottom_left">				
            <div class="advertisements">
                <ul>
                    <h3><spring:message code="text.header.categories" /></h3>
                    <!-- view advertisements images-->
                    <c:forEach items="${allcategories}" var="module"> 

                        <li><a href="${pageContext.request.contextPath}/web/getoffers.htm?category=${module.id}">${module.nameEn}</a></li>
                        </c:forEach>
                    <!--                                    <li><a href="#">Sports &amp; Fitness</a></li>
                                        <li><a href="#">Footwear</a></li>
                                        <li><a href="#">Jewellery</a></li>
                                        <li><a href="#">Clothing</a></li>
                                        <li><a href="#">Home Decor &amp; Kitchen</a></li>
                                        <li><a href="#">Beauty &amp; Healthcare</a></li>
                                        <li><a href="#">Toys, Kids &amp; Babies</a></li>-->
                </ul>
            </div>					
        </div>
        <div class="header_bottom_right">					 
            <div class="slider">					     
                <div id="slider"><!-- view special  offers ads-->
                    <div id="mover">
                        <div id="slide-1" class="slide">			                    
                            <div class="slider-img">
                                <a href="#"><img src="resources/images/slider.jpg" alt="AgriMarket" /></a>									    
                            </div>
                            <div class="slider-text">
                                <h1>AgriMarket<br>
                                    <!--<span>AgriMarket</span></h1>-->
                                <h2>UPTo 50% OFF</h2>
                                <div class="features_list">
                                    <h4>Best Selling Prices</h4>							               
                                </div>
                                <a href="addoffer" class="button">Sell Now</a>
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