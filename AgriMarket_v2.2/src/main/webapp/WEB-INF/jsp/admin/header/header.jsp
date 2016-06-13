<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>

<link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet" />
<link href="<spring:url value="/resources/css/slider.css" />" rel="stylesheet" />	
<link rel="icon" href="<spring:url value="/resources/images/agri_logo_vs.png" />">

<div class="header">

    <div class="header_slide">
<!--        <div class="header_bottom_left" style="float: <spring:message code="header.css.header_bottom_left.float"/>">				
            <div class="advertisements" style="text-align: <spring:message code="header.css.header_bottom_left.advertise.text-align"/>;">
                
            </div>					
        </div>-->
        <!--<div class="header_bottom_right" style="margin-left: <spring:message code="header.css.header_bottom_right.margin-left"/>px;">-->					 
            <div class="slider">					     
                <div id="slider"><!-- view special  offers ads-->
                    <div id="mover">
                        <div id="slide" class="slide">			                    
                            <div class="slider-img">
                                <a href="#"><img src="<spring:url value="/resources/images/slider.jpg"/>" alt="AgriMarket" /></a>									    
                            </div>
                            <div class="slider-text">
                                <h1><spring:message code="title.index.agrimarket"/><br>
                                    <!--<span>AgriMarket</span></h1>-->
                                    <h2>Administration</h2>
                                    
                            </div>			               
                            <div class="clear"></div>				
                        </div>	
                        											
                    </div>		
                </div>
                <div class="clear"></div>					       
            </div>
        <!--</div>-->
        <div class="clear"></div>
    </div>
</div>
