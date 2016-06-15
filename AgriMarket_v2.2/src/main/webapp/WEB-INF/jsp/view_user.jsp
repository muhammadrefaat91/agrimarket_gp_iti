<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<head>
    <title>${userHasOffer.fullName}</title>

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

    <style type="text/css">
        #noresults{ 
            width: 670px;
            /*margin-right: 0px;*/
            /*margin-left: -14px <spring:message code="view-user.css.margin-left" />;*/
            text-align: left;
            padding: 0px 8px;
            background-color: #fff;
            border: 1px solid rgba(0, 0, 0, 0.2);
            -moz-border-radius: 8px;
            border-radius: 8px;
            -moz-box-shadow: 0 5px 10px rgba(0,0,0,.2);
            box-shadow: 0 5px 10px rgba(0,0,0,.2);
        }
        #noresults h4{
            
            color: #4CAF50;
              height: 73px;
            margin-top: 21px;
            direction: <spring:message code="view_user.css.user-products.dir" />;
        }
        #noresults h4 strong{
            margin-left: 213px;
            font-size: xx-large;

          
        }
    </style>

</head>
<body>
    <div class="wrap" style="
         margin-bottom: 133px;">
        <div class="header">
            <jsp:include page="header/headertop_desc.jsp" />
            <div class="call" style="    margin-top: -34px;">
                <p style="direction: <spring:message code="addoffer.css.contactform.dir" />;"> <spring:message code="text.lang" /> : <a href="?id=${param['id']}&lang=en"><spring:message code="text.lang.english" /></a>|<a href="?id=${param['id']}&lang=ar_EG"><spring:message code="text.lang.arbic" /></a></p>
            </div>
            <!---include header top -->
            <jsp:include page="header/header_top.jsp" />
            <jsp:include page="header/header_bottom_nav.jsp" />



            <c:if test="${empty userHasOffer}" >
                <c:redirect url="/web/getUser.htm?id=${param['id']}" />
            </c:if>
        </div>
        <!--profile-->

        <hr class="">
        <div class="container target">
            <div class="row">
                <div class="col-sm-2" style="float: <spring:message code="view_user.css.row.col-sm-3.float" />;"><a href="/users" class="pull-right"><img 
                            style="border: 1.1px solid #2969b0;
                            border-bottom: none; width: 220px; height: 150px;" title="<spring:message code="img.title.text.view_user.profileimage" />" class="img-circle img-responsive" src="${pageContext.request.contextPath}${userHasOffer.imageUrl}"></a>

                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-sm-3" style="float: <spring:message code="view_user.css.row.col-sm-3.float" />;">
                    <!--left col-->
                    <ul class="list-group">
                        <li class="list-group-item text-muted" contenteditable="false">${userHasOffer.fullName}</li>
                        <li style="text-align: <spring:message code="view-user.rate.text-align"/>;
                            direction:<spring:message code="view_user.css.rate.panel.dir"/>;"class="list-group-item text-right"><span style="    margin-left: <spring:message code="view-user.rate.span.margin-left"/>px;" class="pull-left"><strong class=""><spring:message code="text.user.mobile" /></strong></span>${userHasOffer.mobile}</li>
                        <li style="    text-align: <spring:message code="view-user.rate.text-align"/>;" class="list-group-item text-right"><span style="margin-left: <spring:message code="view-user.rate.margin-left"/>px;
                                                                                                                                                 direction: <spring:message code="view_user.css.rate.panel.dir"/>;" class="pull-left"><strong class=""><spring:message code="text.user.email" /></strong></span> <p style="font-size: 14px;">${userHasOffer.mail}</p></li>

                    </ul>

                    <div class="panel panel-default" style="height: 2px;">
                        <div class="panel-heading" style="text-align: <spring:message code="view-user.panel-heading.text-align"/>"><spring:message code="text.view_user.rate" />

                        </div>
                        <div class="panel-body" style="margin-left: <spring:message code="view_user.css.rate.panel.margin-left"/>px;

                             width:112%;

                             direction: <spring:message code="view_user.css.rate.panel.dir"/>;
                             margin-top:-15px;">
                            <div class="rating-block">
                                <!--<h4>Average user rating</h4>-->
                                <h2 class="bold padding-bottom-7">${userHasOffer.ratesAverage} <small>/ 5</small></h2>
                                <c:forEach begin="1" end="${userHasOffer.ratesAverage}">
                                    <button type="button" class="btn btn-warning btn-xs" aria-label="Left Align">
                                        <span class="glyphicon glyphicon-star" aria-hidden="true"></span>
                                    </button>
                                </c:forEach>
                                <c:forEach begin="1" end="${5-userHasOffer.ratesAverage}">

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
                <div class="col-sm-9" contenteditable="false" style="">

                    <div class="panel panel-default target" style="background: white;">
                        <div class="see">
                            <p><a style="margin-left: <spring:message code="view_user.css.all-products.m-left" />px;" href="${pageContext.request.contextPath}/offers.htm"><spring:message code="link.all.Products" /></a></p>
                        </div>
                        <div style="direction: <spring:message code="view_user.css.user-products.dir" />" class="panel-heading" contenteditable="false"><spring:message code="text.user.products" /></div>

                        <div class="panel-body" style="max-height: 345px;
                             border: 1px solid rgba(128, 128, 128, 0.13);
                             overflow: auto;">
                            <c:forEach items="${userHasOffer.userOfferProductFixeds}" var="offer">
                                <div class="grid_1_of_4 images_1_of_4" style="margin-left: <spring:message code="offer-page.css.padding.product.margin-left" />;float: <spring:message code="offer_page.css.heading.float" />;">
                                    <a href="preview.htm?id=${offer.id}">
                                        <img  
                                            style="border: 1.1px solid #2969b0;
                                            border-bottom: none; width: 220px; height: 150px;"  src="${pageContext.request.contextPath}${offer.imageUrl}" /></a>
                                    <h2>${requestScope.lang eq 'en'?offer.product.nameEn:offer.product.nameAr} </h2>
                                    <div class="price-details">
                                        <div class="price-number">
                                            <p><span class="rupees">${offer.price}  <spring:message code="preview.money"  />/${requestScope.lang eq 'en'?offer.unitByPricePerUnitId.nameEn:offer.unitByPricePerUnitId.nameAr}</span></p>
                                        </div>
                                        <div class="add-cart">								
                                            <h4><a  href="preview.htm?id=${offer.id}"><spring:message code="link.More.details" /></a></h4>
                                        </div>
                                        <div class="clear"></div>
                                    </div>
                                </div>
                            </c:forEach>
                            <!--</div>-->
                        </div>
                    </div>
                    <c:if test="${ empty sessionScope.user}">
                        <div class="alert alert-info alert-dismissable" style="margin-left: <spring:message code="view-user.css.note.marign" />px;direction: <spring:message code="view_user.css.user-products.dir" />;    width: 79%;
                             ;background-color: whitesmoke">
                            <!--<a class="panel-close close" data-dismiss="alert">×</a>--> 
                            <i class="fa fa-coffee"></i>
                            <a href="${pageContext.request.contextPath}/web/signup.htm"><spring:message code="view-user.text.login" /> </a>

                        </div>
                    </c:if>
                    <c:if test="${not empty sessionScope.user}">  
                        <c:if test="${sessionScope.user.mail != userHasOffer.mail}">
                            <div class="panel panel-default" style="    width: 79%;margin-left: <spring:message code="view-user.css.review.margin-left" />;">
                                <div class="panel-heading"><a style="cursor: pointer;margin-left: <spring:message code="view_user.css.panel-heading.margin-left" />px;" data-toggle="collapse" data-target="#demo"><spring:message code="text.user.ratetext" /></a></div>
                                <div class="review collapse" id="demo"  >
                                    <div class="your-review" style="    margin-left: 20px; direction: <spring:message code="view_user.css.panel-heading.panel-body.dir" />;   margin-right: <spring:message code="view_user.css.panel-heading.panel-body.margin-right" />px;">
                                        <div class="panel-body" style="    margin-top: 4px;margin-left: <spring:message code="view_user.css.panel-heading.panel-body.margin-left" />px;">
                                            <input type="radio" name="rating" value="1" class="star">
                                            <input type="radio" name="rating" value="2" class="star">
                                            <input type="radio" name="rating" value="3" class="star">
                                            <input type="radio" name="rating" value="4" class="star">
                                            <input type="radio" name="rating" value="5" class="star">
                                        </div>
                                        <p><spring:message code="text.user.review" /></p>
                                        <div>
                                            <span><textarea id="review-text" placeholder="<spring:message code="placeholder.user.review" />"
                                                            style="width: <spring:message code="view-user.css.textarea" />px;"> </textarea></span>
                                        </div>
                                        <div>
                                            <span><input type="submit" id="submit-review" value="<spring:message code="button.submit.review" />"></span>
                                        </div>
                                    </div>	
                                </div>
                            </div></c:if>
                    </c:if>
                    <div class="panel panel-default" style="margin-left: <spring:message code="view-user.css.review.margin-left" />; direction: <spring:message code="view_user.css.panel-default.dir"/>;    width: 79.333333%; margin-top: 2px;">
                        <div class="panel-heading">
                            <a style="cursor: pointer;     margin-left: -9px;" data-toggle="collapse" data-target="#demo2"><spring:message code="text.view_user.reviews" />
                            </a>
                        </div>
                        <c:if test="${empty userHasOffer.userRatesUsersForRatedId}">
                            <!--<div class="container">-->
                                <!--<div class="row">-->
                                    <div class="">            
                                        <div id="noresults" ><h4><strong> <spring:message code="view-user.text.noresults" /></strong></h4></div>
                                    </div>
                                <!--</div>-->
                            <!--</div>-->
                        </c:if>
                        <div class="row" style=" background-color: white;   margin-left: -14px;">
                            <div class="col-sm-7" style="    width: 100.333333%;
                                 margin-top: 0px;">
                                <!--<hr/>-->
                                <c:forEach items="${userHasOffer.userRatesUsersForRatedId}"  var="rater">
                                    <div class="review-block">
                                        <div class="row">
                                            <div class="col-sm-3" style="float: <spring:message code="offer_page.css.heading.float"/>;">
                                                <img style="width: 50px;" src="${pageContext.request.contextPath}${rater.userByRaterId.imageUrl}" class="img-rounded">
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
        </div>
    </div>
    <script type="text/javascript" >
        $(document).ready(function () {
            $('#submit-review').click(function () {

//if user doesn't login 
        <c:if test="${empty sessionScope.user}">
                var user = null;
        </c:if>

//                if (user == null)
//                    location.href = "http://${pageContext.request.getServerName()}:${pageContext.request.getServerPort()}${pageContext.request.contextPath}/web/signup.htm";

                var selectedVal = "";
                var selected = $("input[type='radio'][name='rating']:checked");
                selectedVal = selected.val();
                var reviewText = jQuery("textarea#review-text").val();
//                console.log(reviewText);
                $.ajax({
                    url: "${pageContext.request.contextPath}/web/addRate.htm",
                    type: "GET",
                    data: {rating: selectedVal, raterID:${sessionScope.user.id}, ratedID:${userHasOffer.id}, reviewText: reviewText},
                    success: function (data) {
                        console.log('success' + data);
                        if (data === 'no_errors')
                            location.href = "http://${pageContext.request.getServerName()}:${pageContext.request.getServerPort()}${pageContext.request.contextPath}/web/getUser.htm?id=${param['id']}";
                    },
                    error: function (e) {
                        console.log('error:' + e.data);
                    }
                });
            });
        });
    </script>
    <jsp:include page="footer/footer.jsp" />
</body>
</html>
