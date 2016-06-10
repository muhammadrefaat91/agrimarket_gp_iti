<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<head>
    <title><spring:message code="title.index" /></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="<spring:url value="/resources/css/slider.css" />" rel="stylesheet">

    <!--<link href="resources/css/style.css" rel="stylesheet" type="text/css" media="all"/>-->
    <!--<link href="resources/css/slider.css" rel="stylesheet" type="text/css" media="all"/>-->
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script> 
    <script type="text/javascript" src="js/move-top.js"></script>
    <script type="text/javascript" src="js/easing.js"></script>
    <script type="text/javascript" src="js/startstop-slider.js"></script>
</head>
<body>
    <c:if test="${empty allcategories}" >
        <c:redirect url="/index.htm" />
    </c:if>
    
    <c:if test="${empty latestOffers}" >
        <c:redirect url="/index.htm" />
    </c:if>
    <div class="wrap">
        <!-- header--->
        <!---include header description -->

        <jsp:include page="header/headertop_desc.jsp" />
        <div class="call" style="    margin-top: -34px;">
            <p> <spring:message code="text.lang" /> : <a href="?lang=en"><spring:message code="text.lang.english" /></a>|<a href="?lang=ar_EG"><spring:message code="text.lang.arbic" /></a></p>
        </div>

        <!---include header top -->
        <jsp:include page="header/header_top.jsp" />

        <!---include nav bar -->
        <jsp:include page="header/header_bottom_nav.jsp" />
        <jsp:include page="header/header.jsp" />


        <!-- content--->
        <div class="main">
            <div class="content">
                <div class="content_top">
                    <div class="heading">
                        <h3><spring:message code="text.index.newproduct" /></h3>
                    </div>
                    <div class="see">

                        <p><a href="offers.htm"><spring:message code="link.all.Products" /></a></p>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="section group">
                    <c:forEach items="${latestOffers}" var="offer">
                        <div class="grid_1_of_4 images_1_of_4">
                            <a href="preview.html">
                                <img  
                                    style="border: 1.1px solid #2969b0;
                                    border-bottom: none;"  src="${pageContext.request.contextPath}${offer.imageUrl}" /></a>
                            <h2>${offer.product.nameEn}</h2>

                            <div class="price-details">
                                <div class="price-number">
                                    <p><span class="rupees">${offer.price} L.E</span></p>
                                </div>
                                <div class="add-cart">								
                                    <h4><a href="web/preview.htm?id=${offer.id}"><spring:message code="link.More.details" /></a></h4>
                                </div>
                                <div class="clear"></div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

            </div>
        </div>
    </div>

    <!--Footer--->
    <jsp:include  page="footer/footer.jsp" />
    <script type="text/javascript">
        $(document).ready(function () {
            $().UItoTop({easingType: 'easeOutQuart'});

        });
    </script>
    <a href="#" id="toTop"><span id="toTopHover"> </span></a>
</body>
</html>


