<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<head>
    <title>${product.nameEn}</title>

    <link rel="icon" href="<spring:url value="/resources/images/agri_logo.png" />">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />

    <script type="text/javascript" src="<spring:url value="/resources/js/jquery-1.7.2.min.js" />"></script>
    <script type="text/javascript" src="<spring:url value="/resources/js/jquery.cookie.js" />"></script>
    <link href="<spring:url value="/resources/css/jquery.rating.css" />" rel="stylesheet" />

    <script type="text/javascript" src="<spring:url value="/resources/js/jquery.js" />" ></script>
    <script src="<spring:url value="/resources/js/jquery.rating.js" />"></script>
    <script src="<spring:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
    <div class="wrap">
        <div class="header">
            <jsp:include page="header/headertop_desc.jsp" />
            <div class="call" style="    margin-top: -34px;">
                <p> <spring:message code="text.lang" /> : <a href="?id=${param['id']}&lang=en"><spring:message code="text.lang.english" /></a>|<a href="?id=${param['id']}&lang=ar_EG"><spring:message code="text.lang.arbic" /></a></p>
            </div>
            <!---include header top -->
            <jsp:include page="header/header_top.jsp" />
            <jsp:include page="header/header_bottom_nav.jsp" />



            <c:if test="${empty product}" >
                <c:redirect url="/admin/preview_product.htm?id=${param['id']}" />
            </c:if>
        </div>
        <!--profile-->

        <hr class="">
        <div class="container target">
            <div class="row">
                <div class="col-sm-2" style="float: <spring:message code="view_user.css.row.col-sm-3.float" />;">
                    <a href="/users" class="pull-right">
                        <img 
                                        style="border: 1.1px solid #2969b0;
                                        border-bottom: none; width: 220px; height: 150px;" title="<spring:message code="img.title.text.view_user.profileimage" />" class="img-circle img-responsive" src="${pageContext.request.contextPath}${product.imageUrl}">
                    </a>

                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-sm-3" style="float: <spring:message code="view_user.css.row.col-sm-3.float" />;">
                    <!--left col-->
                    <ul class="list-group">
                        <li style="text-align: <spring:message code="view-user.rate.text-align"/>;
                            direction:<spring:message code="view_user.css.rate.panel.dir"/>;"class="list-group-item text-right">
                            <span style="    margin-left: <spring:message code="view-user.rate.span.margin-left"/>px;" class="pull-left">
                                <strong class=""><spring:message code="text.name.en" /></strong></span>${product.nameEn}</li>
                        <li style="    text-align: <spring:message code="view-user.rate.text-align"/>;" class="list-group-item text-right">
                            <span style="margin-left: <spring:message code="view-user.rate.margin-left"/>px; direction: <spring:message code="view_user.css.rate.panel.dir"/>;" class="pull-left"><strong class=""><spring:message code="text.name.ar" /></strong></span> ${product.nameAr}</li>

                        <li style="    text-align: <spring:message code="view-user.rate.text-align"/>;" class="list-group-item text-right">
                            <span style="margin-left: <spring:message code="view-user.rate.margin-left"/>px; direction: <spring:message code="view_user.css.rate.panel.dir"/>;" class="pull-left"><strong class=""><spring:message code="text.parent.en" /></strong></span> ${product.category.nameEn}</li>
                        
                        <li style="    text-align: <spring:message code="view-user.rate.text-align"/>;" class="list-group-item text-right">
                            <span style="margin-left: <spring:message code="view-user.rate.margin-left"/>px; direction: <spring:message code="view_user.css.rate.panel.dir"/>;" class="pull-left"><strong class=""><spring:message code="text.parent.ar" /></strong></span> ${product.category.nameAr}</li>
                    </ul>
                    
                </div>
                <!--/col-3-->
                <div class="col-sm-9" contenteditable="false" style="margin-top: -109px;">

                    <div class="panel panel-default target" style="background: antiquewhite;">
                        
                        <div style="direction: <spring:message code="view_user.css.user-products.dir" />" class="panel-heading" contenteditable="false"><spring:message code="text.offers" /></div>

                        <div class="panel-body" style="max-height: 345px;
                             border: 1px solid gray;
                             overflow: auto;">
                            <c:forEach items="${product.userOfferProductFixeds}" var="offer">
                                <div class="grid_1_of_4 images_1_of_4" style="margin-left: <spring:message code="offer-page.css.padding.product.margin-left" />;float: <spring:message code="offer_page.css.heading.float" />;">
                                    <a href="${pageContext.request.contextPath}/admin/preview.htm?id=${offer.id}">
                                        <img  
                                            style="border: 1.1px solid #2969b0;
                                            border-bottom: none;"  src="${pageContext.request.contextPath}${offer.imageUrl}" /></a>
                                            <h2>${offer.startDate}</h2>
                                    <div class="price-details">
                                        
                                        <div class="add-cart">								
                                            <h4><a href="${pageContext.request.contextPath}/admin/preview.htm?id=${offer.id}"><spring:message code="link.More.details" /></a></h4>
                                        </div>
                                        <div class="clear"></div>
                                    </div>
                                </div>
                            </c:forEach>
                            <!--</div>-->
                        </div>
                    </div>
                    
                     
                </div>	
            </div>
        </div>
    </div>
</body>
</html>
