<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<head>
    <title>${user.fullName}</title>

    <link rel="icon" href="<spring:url value="/resources/images/agri_logo.png" />">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />


    <link href="<spring:url value="/resources/css/jquery.rating.css" />" rel="stylesheet" />

    <script type="text/javascript" src="<spring:url value="/resources/js/jquery.js" />" ></script>
    <script src="<spring:url value="/resources/js/jquery.rating.js" />"></script>
    <script src="<spring:url value="/resources/js/bootstrap.min.js" />"></script>

    <script type="text/javascript" >
        $(document).ready(function () {
            $('#submit-review').click(function () {
                var selectedVal = "";
                var selected = $("input[type='radio'][name='rating']:checked");
                selectedVal = selected.val();
                var reviewText = jQuery("textarea#review-text").val();
//                console.log(reviewText);
                $.ajax({
                    url: "${pageContext.request.contextPath}/web/addRate.htm",
                    type: "GET",
                    data: {rating: selectedVal, uID:${user.id}, reviewText: reviewText},
                    success: function (data) {
                        console.log('success' + data);
                        if (data === 'no_errors')
                            location.href = "http://${pageContext.request.getServerName()}:${pageContext.request.getServerPort()}${pageContext.request.contextPath}/web/getUser?id=${param['id']}";
                    },
                    error: function (e) {
                        console.log('error:' + e.data);
                    }
                });
            });
        });
    </script>

</head>
<body>
    <div class="wrap">
        <div class="header">
            <jsp:include page="header/headertop_desc.jsp" />
            <div class="call" style="    margin-top: -34px;">
                <p style="direction: <spring:message code="addoffer.css.contactform.dir" />;"> <spring:message code="text.lang" /> : <a href="?id=${param['id']}&lang=en"><spring:message code="text.lang.english" /></a>|<a href="?id=${param['id']}&lang=ar_EG"><spring:message code="text.lang.arbic" /></a></p>
            </div>
            <!---include header top -->
            <jsp:include page="header/header_top.jsp" />
            <jsp:include page="header/header_bottom_nav.jsp" />
            <c:if test="${empty user}" >
                <c:redirect url="/index.htm" />
            </c:if>
        </div>
        <!--profile-->

        <hr class="">
        <div class="container target" style=" 
    margin-bottom: 248px;  width: 105%;">
            <div class="row">
                <div class="col-sm-2" style="float: <spring:message code="view_user.css.row.col-sm-3.float" />;">
                    <a href="/users" class="pull-right">
                        <img style="height: 117px;margin-right: 85px;" title="<spring:message code="img.title.text.view_user.profileimage" />" class="img-circle img-responsive" src="${pageContext.request.contextPath}${user.imageUrl}">

                    </a>
                    <div class="add-cart">
                        <h4>
                            <a s href="${pageContext.request.contextPath}/web/edit-profile.htm" style="position: absolute;
                               margin-left: -31px;background-color: white;background-repeat: no-repeat;padding-bottom: 97px;background-image: url('${pageContext.request.contextPath}/resources/images/ic_mode_edit_black_24dp_1x.png')">
                            </a>
                        </h4>
                    </div>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-sm-3" style="float: <spring:message code="view_user.css.row.col-sm-3.float" />;">
                    <!--left col-->
                    <ul class="list-group">
                        <li class="list-group-item text-muted" contenteditable="false">${user.fullName}</li>
                        <!--<li class="list-group-item text-right"><span class="pull-left"><strong class="">Joined</strong></span> 2.13.2014</li>-->
                        <li style="text-align: <spring:message code="view-user.rate.text-align"/>;
                            direction:<spring:message code="view_user.css.rate.panel.dir"/>;"
                            class="list-group-item text-right">
                            <span style="    margin-left: <spring:message code="view-user.rate.span.margin-left"/>px;"
                                  class="pull-left"><strong class="">
                                    <spring:message code="text.user.mobile" /></strong>
                            </span>${user.mobile}</li>
                        <li style="    text-align: <spring:message code="view-user.rate.text-align"/>;" 
                            class="list-group-item text-right">
                            <span style="margin-left: <spring:message code="view-user.rate.margin-left"/>px; direction: <spring:message code="view_user.css.rate.panel.dir"/>;" class="pull-left">
                                <strong class="">
                                    <spring:message code="text.user.email" /></strong>
                            </span> ${user.mail}</li>
                        <li style="    text-align: <spring:message code="view-user.rate.text-align"/>;" 
                            class="list-group-item text-right">
                            <span style="margin-left: <spring:message code="view-user.rate.margin-left"/>px; direction: <spring:message code="view_user.css.rate.panel.dir"/>;" class="pull-left">
                                <strong class="">
                                    <spring:message code="text.addoffer.governerate" /></strong>
                            </span> ${user.governerate}</li>
                    </ul>
                    <div class="panel panel-default" style="height: 2px;">
                        <div class="panel-heading" style="text-align: <spring:message code="view-user.panel-heading.text-align"/>">
                            <spring:message code="text.view_user.rate" />
                        </div>
                        <div class="panel-body" style="margin-left: <spring:message code="view_user.css.rate.panel.margin-left"/>px;
                             width:112%;
                             direction: <spring:message code="view_user.css.rate.panel.dir"/>;
                             margin-top:-15px;">
                            <div class="rating-block">
                                <!--<h4>Average user rating</h4>-->
                                <h2 class="bold padding-bottom-7">${user.ratesAverage} <small>/ 5</small></h2>
                                <c:forEach begin="1" end="${user.ratesAverage}">
                                    <button type="button" class="btn btn-warning btn-xs" aria-label="Left Align">
                                        <span class="glyphicon glyphicon-star" aria-hidden="true"></span>
                                    </button>
                                </c:forEach>
                                <c:forEach begin="1" end="${5-user.ratesAverage}">

                                    <button type="button" class="btn btn-default btn-grey btn-xs" aria-label="Left Align">
                                        <span class="glyphicon glyphicon-star" aria-hidden="true"></span>
                                    </button>
                                </c:forEach>
                            </div>
                            <span class="rating">

                            </span>
                        </div>
                    </div>
                </div>
                <!--/col-3-->
                <div class="col-sm-9" contenteditable="false" style="margin-top: -109px;">

                    <div class="panel panel-default target" style="background: white;">
                        <div class="see">
                            <p><a style="margin-left: <spring:message code="view_user.css.all-products.m-left" />px;" href="${pageContext.request.contextPath}/web/getoffers.htm"><spring:message code="link.all.Products" /></a></p>
                        </div>
                        <div style="direction: <spring:message code="view_user.css.user-products.dir" />" class="panel-heading" contenteditable="false"><spring:message code="text.user.products" /></div>

                        <div class="panel-body" style="max-height: 345px;
                             border: 1px solid rgba(128, 128, 128, 0.13);
                             overflow: auto;">
                            <!--<div class="row">-->
                            <c:forEach items="${user.userOfferProductFixeds}" var="offer">

                                <div class="grid_1_of_4 images_1_of_4" style="margin-left: <spring:message code="offer-page.css.padding.product.margin-left" />;
                                     float: <spring:message code="offer_page.css.heading.float" />;">
                                    <div class="add-cart">
                                        <h4> <a s href="${pageContext.request.contextPath}/removeoffer.htm?offerid=${offer.id}" style="position: absolute;
                                                margin-left: -31px;background-image: url('${pageContext.request.contextPath}/resources/images/ic_delete_black_24dp_1x.png')"></a></h4>
                                    </div>
                                    <!--<div class="thumbnail grid_1_of_4">-->
                                    <a href="preview.htm?id=${offer.id}">
                                        <img style="border: 1.1px solid #2969b0;
                                             border-bottom: none;" alt="300x200" src="${pageContext.request.contextPath}${offer.imageUrl}" /></a>
                                    <h2 align="center">${requestScope.lang eq 'en'?offer.product.nameEn:offer.product.nameAr}</h2>

                                    <div  class="caption">
                                        <div class="price-details">
                                            <div class="price-number">
                                                <p><span class="rupees">$${offer.price}L.E</span></p> 
                                            </div>
                                            <div class="add-cart">								
                                                <h4><a href="preview.htm?id=${offer.id}"><spring:message code="link.More.details" /></a></h4>
                                            </div>
                                            <div class="clear"></div>
                                        </div>
                                        <!--<p>-->

                                        <!--</p>-->
                                    </div>
                                    <!--</div>-->
                                </div>
                            </c:forEach>
                            <!--</div>-->
                        </div>
                    </div>

                    <div class="panel panel-default" style="margin-left: <spring:message code="view-user.css.review.margin-left" />; direction: <spring:message code="view_user.css.panel-default.dir"/>;    width: 79.333333%; margin-top: 2px;">
                        <div class="panel-heading">
                            <a style="cursor: pointer;     margin-left: -9px;" data-toggle="collapse" data-target="#demo2"><spring:message code="text.view_user.reviews" /></a></div>
                        <div class="row" style="    margin-left: -14px;">
                            <div class="col-sm-7" style="width: 99.333333%; margin-top: 3px;">
                                <!--<hr/>-->
                                <c:forEach items="${user.userRatesUsersForRatedId}"  var="rater">
                                    <div class="review-block">
                                        <div class="row">
                                            <div class="col-sm-3" style="float: <spring:message code="offer_page.css.heading.float"/>;">
                                                <img style="width: 50px;" src="${pageContext.request.contextPath}/image/${rater.userByRaterId.imageUrl}" class="img-rounded">
                                                <div class="review-block-name"><a href="#">${rater.userByRaterId.fullName}</a></div>
                                            </div>
                                            <div class="col-sm-9">
                                                <h2 class="bold padding-bottom-7">${rater.rate} <small>/ 5</small></h2>
                                                <div class="review-block-rate">
                                                    <c:forEach begin="1" end="${rater.rate}">
                                                        <button type="button" class="btn btn-warning btn-xs" aria-label="Left Align">
                                                            <span class="glyphicon glyphicon-star" aria-hidden="true"></span>
                                                        </button>
                                                    </c:forEach>
                                                    <c:forEach begin="1" end="${5-rater.rate}">

                                                        <button type="button" class="btn btn-default btn-grey btn-xs" aria-label="Left Align">
                                                            <span class="glyphicon glyphicon-star" aria-hidden="true"></span>
                                                        </button>
                                                    </c:forEach>

                                                </div>
                                                <div class="review-block-description">${rater.review}</div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>                 

                    </div> 
                </div>	
            </div>
            <!--    </div>-->
        </div>
    </div>
    <jsp:include page="footer/footer.jsp" />
</body>
</html>

