
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<head>
    <title>M2hsol</title>
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
                    url: "${pageContext.request.contextPath}/web/addRate",
                    type: "GET",
                    data: {rating: selectedVal, uID:${userHasOffer.id}, reviewText: reviewText},
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
    <style type="text/css">
        .rating {
            overflow: hidden;
            display: inline-block;
            font-size: 0;
            position: relative;
        }
        .rating-input {
            float: right;
            width: 16px;
            height: 16px;
            padding: 0;
            margin: 0 0 0 -16px;
            opacity: 0;
        }
        .rating:hover .rating-star:hover,
        .rating:hover .rating-star:hover ~ .rating-star,
        .rating-input:checked ~ .rating-star {
            background-position: 0 0;
        }
        .rating-star,
        .rating:hover .rating-star {
            position: relative;
            float: right;
            display: block;
            width: 16px;
            height: 16px;
            /*background-color: yellow;*/
            background: url('http://kubyshkin.ru/samples/star-rating/star.png') 0 -16px;
        }

        /* Just for the demo */
        body {
            margin: 20px;
        }

        body {
            padding-top: 70px;
        }
        .btn-grey{
            background-color:#D8D8D8;
            color:#FFF;
        }
        .rating-block{
            background-color:#FAFAFA;
            border:1px solid #EFEFEF;
            padding:15px 15px 20px 15px;
            border-radius:3px;
        }
        .bold{
            font-weight:700;
        }
        .padding-bottom-7{
            padding-bottom:7px;
        }

        .review-block{
            background-color:#FAFAFA;
            border:1px solid #EFEFEF;
            padding:15px;
            border-radius:3px;
            margin-bottom:15px;
        }
        .review-block-name{
            font-size:12px;
            margin:10px 0;
        }
        .review-block-date{
            font-size:12px;
        }
        .review-block-rate{
            font-size:13px;
            margin-bottom:15px;
        }
        .review-block-title{
            font-size:15px;
            font-weight:700;
            margin-bottom:10px;
        }
        .review-block-description{
            font-size:13px;
        }

    </style>
</head>
<body>
    <div class="wrap">
        <div class="header">
            <jsp:include page="header/headertop_desc.jsp" />
            <jsp:include page="header/header_bottom_nav.jsp" />
            <c:if test="${empty userHasOffer}" >
                <c:redirect url="/web/getUser?id=${param['id']}" />
            </c:if>
        </div>
        <!--profile-->

        <hr class="">
        <div class="container target">
            <div class="row">
                <div class="col-sm-2"><a href="/users" class="pull-right"><img style="height: 117px;margin-right: 85px;" title="profile image" class="img-circle img-responsive" src="${pageContext.request.contextPath}${userHasOffer.imageUrl}"></a>

                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-sm-3">
                    <!--left col-->
                    <ul class="list-group">
                        <li class="list-group-item text-muted" contenteditable="false">${userHasOffer.fullName}</li>
                        <!--<li class="list-group-item text-right"><span class="pull-left"><strong class="">Joined</strong></span> 2.13.2014</li>-->
                        <li class="list-group-item text-right"><span class="pull-left"><strong class="">Mobile</strong></span>${userHasOffer.mobile}</li>
                        <li class="list-group-item text-right"><span class="pull-left"><strong class="">E-Mail</strong></span> ${userHasOffer.mail}</li>
                        <!--                        <li class="list-group-item text-right"><span class="pull-left"><strong class="">Role: </strong></span> Pet Sitter
                                                </li>-->
                    </ul>
                        <div class="panel panel-default" style="height: 2px;">
                        <div class="panel-heading">Rate

                        </div>
                        <div class="panel-body" style="    width: 112%;
                             margin-left: -15px;
                             margin-top: -15px;">
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
                <div class="col-sm-9" contenteditable="false" style="margin-top: -109px;">

                    <div class="panel panel-default target" style="background: antiquewhite;">
                        <div class="see">
                            <p><a href="${pageContext.request.contextPath}/offers.htm">See all Products</a></p>
                        </div>
                        <div class="panel-heading" contenteditable="false">User Products</div>

                        <div class="panel-body" style="max-height: 345px;
                             border: 1px solid gray;
                             overflow: auto;">
                            <div class="row">
                                <c:forEach items="${userHasOffer.userOfferProductFixeds}" var="offer">
                                    <div class="col-md-4">
                                        <div class="thumbnail">
                                            <img alt="300x200" src="${pageContext.request.contextPath}${offer.imageUrl}" />
                                            <div style="margin-left: 14px;" class="caption">
                                                <h3>
                                                    ${offer.price}
                                                </h3>
                                                <p>
                                                    ${offer.description}
                                                </p>
                                                <p>

                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default" style="    width: 79%;">
                        <div class="panel-heading"><a style="cursor: pointer;" data-toggle="collapse" data-target="#demo">How Do You Rate This User?</a></div>


                        <div class="review collapse" id="demo"  >
                            <div class="your-review" style="    margin-left: 23px;">

                                <div class="panel-body">

                                    <input type="radio" name="rating" value="1" class="star">
                                    <input type="radio" name="rating" value="2" class="star">
                                    <input type="radio" name="rating" value="3" class="star">
                                    <input type="radio" name="rating" value="4" class="star">
                                    <input type="radio" name="rating" value="5" class="star">

                                </div>
                                <p>Write Your Own Review?</p>

                                <div>
                                    <span><textarea id="review-text" placeholder="Tell others what you think about this book. Would you recommend it, and why?"> </textarea></span>
                                </div>
                                <div>
                                    <span><input type="submit" id="submit-review" value="SUBMIT REVIEW"></span>
                                </div>
                            </div>	
                        </div>
                    </div>
                    <div class="panel panel-default" style="    width: 79.333333%;margin-left: 0px; margin-top: 2px;">
                        <div class="panel-heading">
                            <a style="cursor: pointer;     margin-left: -9px;" data-toggle="collapse" data-target="#demo2">Reviews</a></div>


                        <!--<div class="review collapse" id="demo2"  >-->
                        <!--<div class="container">-->
                        <div class="row" style="    margin-left: -14px;">
                            <div class="col-sm-7" style="width: 99.333333%; margin-top: 3px;">
                                <!--<hr/>-->
                                <c:forEach items="${userHasOffer.userRatesUsersForRatedId}"  var="rater">
                                    <div class="review-block">
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <img style="width: 50px;" src="${pageContext.request.contextPath}/image/${rater.userByRaterId.imageUrl}" class="img-rounded">
                                                <div class="review-block-name"><a href="#">${rater.userByRaterId.fullName}</a></div>
                                                <!--<div class="review-block-date">January 29, 2016<br/>1 day ago</div>-->
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
</div>
</div>

<jsp:include page="footer/footer.jsp" />
</body>
</html>

