
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>

<!DOCTYPE HTML >
<html lang="ar_EG">
    <head>
        <title><spring:message code="title.admin.show.products" /></title>

        <link rel="icon" href="<spring:url value="/resources/images/agri_logo.png" />">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet">
        <link href="<spring:url value="/resources/css/slider.css" />" rel="stylesheet">
        <link href="<spring:url value="/resources/css/main.css" />" rel="stylesheet">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">

        <script type="text/javascript" src="resources/js/jquery-1.7.2.min.js"></script> 
        <script type="text/javascript" src="resources/js/move-top.js"></script>
        <script type="text/javascript" src="resources/js/easing.js"></script>
        <script type="text/javascript" src="resources/js/startstop-slider.js"></script>

    </head>
    <body>


        <c:if test="${empty products}" >
            <c:redirect url="/admin/products_page.htm" />
        </c:if>

        <div class="wrap">

            <!-- header--->
            <jsp:include page="header/headertop_desc.jsp" />
            <div class="call" style="    margin-top: -34px;">
                <p> <spring:message code="text.lang" /> : <a href="?name=${param['name']}&lang=en"><spring:message code="text.lang.english" /></a>|<a href="?name=${param['name']}&lang=ar_EG"><spring:message code="text.lang.arbic" /></a></p>
            </div>
            <!---include header top -->
            <jsp:include page="header/header_top.jsp" />
            <jsp:include page="header/header_bottom_nav.jsp" />
            <!-- content--->
            <div class="main">
                <div class="content">

                    <div class="content_top" dir="<spring:message code="offer_page.css.content_top.dir" />">
                        <!-- search!-->

                        <div class="heading" style="float: <spring:message code="offer_page.css.heading.float" />;">

                            <div class="heading" style="float: <spring:message code="index.css.heading.float" />;">
                                <h3><spring:message code="header.all.products" /></h3>
                            </div>
                        </div>

                        <div >

                            <div class="add-cart">								
                                <h4><a href="<spring:url value="/admin/addproduct.htm"/>"><spring:message code="button.add.product" /></a></h4>
                            </div>
                            <div class="clear"></div>
                        </div>
                        <%-- end list--%>
                        <div class="see">

                        </div>
                        <div class="clear"></div>
                    </div>



                    <div class="section group">
                        <c:forEach items="${products}" var="product">
                            <div class="grid_1_of_4 images_1_of_4" style="margin-left: <spring:message code="offer-page.css.padding.product.margin-left" />;float: <spring:message code="offer_page.css.heading.float" />;">
                                <a href="preview_product.htm?id=${product.id}">
                                    <img  
                                        style="border: 1.1px solid #2969b0;
                                        border-bottom: none; width: 220px; height: 150px;"  src="${pageContext.request.contextPath}${product.imageUrl}" />
                                </a>
                                <h2>${product.nameEn}</h2>

                                <div class="price-details">
                                    <div class="price-number">
                                    </div>
                                    <div class="add-cart">								
                                        <h4><a href="preview_product.htm?id=${product.id}"><spring:message code="link.More.details" /></a></h4>
                                    </div>
                                    <div class="clear"></div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                </div>
            </div>
        </div>

        <a href="#" id="toTop"><span id="toTopHover"> </span></a>
    </body>





</body>
</html>


