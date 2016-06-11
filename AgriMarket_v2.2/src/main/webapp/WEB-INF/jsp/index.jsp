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

    <script type="text/javascript" src="<spring:url value="/resources/js/jquery-1.7.2.min.js" />"></script>
    <script type="text/javascript" src="<spring:url value="/resources/js/jquery.cookie.js" />"></script>
    <script type="text/javascript">
        console.log($.cookie("myAppLocaleCookie"));
        var lang = $.cookie("myAppLocaleCookie");
        if (lang === 'ar_EG') {
            $('li:first').appendTo('ul');

            $(document).ready(function () {
                $.fn.exchangePositionWith = function (selector) {
                    var other = $(selector);
                    this.after(other.clone());
                    other.after(this).remove();
                };
                $(".menu ul li:eq(0)").exchangePositionWith(".menu ul li:eq(3)");
                $(".menu ul li:eq(1)").exchangePositionWith(".menu ul li:eq(2)");

            });

        }
    </script>
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
                    <div class="heading" style="float: <spring:message code="index.css.heading.float" />;">
                        <h3><spring:message code="text.index.newproduct" /></h3>
                    </div>
                    <div class="see" style="float: <spring:message code="index.css.see.float" />;">

                        <p><a href="web/getoffers.htm"><spring:message code="link.all.Products" /></a></p>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="section group">
                    <c:forEach items="${latestOffers}" var="offer">
                        <div class="grid_1_of_4 images_1_of_4" style="margin-left: <spring:message code="offer-page.css.padding.product.margin-left" />;float: <spring:message code="offer_page.css.heading.float" />;">
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
    <a href="#" id="toTop"><span id="toTopHover"> </span></a>
</body>
</html>


