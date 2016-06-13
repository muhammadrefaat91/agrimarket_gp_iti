<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" http-equiv="Content-Type" content="text/html;">

        <link rel="icon" href="<spring:url value="/resources/images/agri_logo.png" />">
        <title><spring:message code="title.addoffer.addproduct" /></title>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
        <link href="<spring:url value="/resources/css/main.css" />" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="http://snipplicious.com/css/bootstrap-3.2.0.min.css">

        <link rel="stylesheet" type="text/css" href="http://snipplicious.com/css/font-awesome-4.1.0.min.css">


    </head>
    <body>
        <%--<c:if test="${empty sessionScope.user}">--%>
            <%--<c:redirect url=""  />--%>
        <%--</c:if>--%>
        <div class="wrap">
            <div class="fb-like" data-href="https://www.facebook.com/ChatAppJits/" data-width="200" data-layout="button_count" data-action="like" data-show-faces="true" data-share="true"></div>
            <!---include header description -->
            <jsp:include page="header/headertop_desc.jsp" />
            <div class="call" style="    margin-top: -34px;">
                <p> <spring:message code="text.lang" /> : <a href="?lang=en"><spring:message code="text.lang.english" /></a>|<a href="?lang=ar_EG"><spring:message code="text.lang.arbic" /></a></p>
            </div>
            <jsp:include page="header/header_top.jsp" />
            <!---include nav bar -->
            <jsp:include page="header/header_bottom_nav.jsp" />
            <form class="form-horizontal" method="post" enctype="multipart/form-data" action="uprofile.htm" role="form" >
            <div class="container" style="padding-top: 60px;">
                <h1 class="page-header">Edit Profile</h1>
                
                <div class="row">
                    <!-- left column -->
                    <div class="col-md-4 col-sm-6 col-xs-12">
                        <div class="text-center">
                            <img style="height: 117px;margin-right: 85px;" alt="avatar" title="<spring:message code="img.title.text.view_user.profileimage" />"
                                 class="avatar img-circle img-thumbnail" src="${pageContext.request.contextPath}${sessionScope.user.imageUrl}">
                            <!--<img src="http://lorempixel.com/200/200/people/9/" class="avatar img-circle img-thumbnail" alt="avatar">-->
                            <h6>Upload a different photo...</h6>
                           
                            <!--<input type="file" class="text-center center-block well well-sm" />-->
                            <input type="file" name="file"  class="form-control" />
                        </div>
                    </div>
                    <!-- edit form column -->
                    <div class="col-md-8 col-sm-6 col-xs-12 personal-info">
                        <div class="alert alert-info alert-dismissable">
                            <a class="panel-close close" data-dismiss="alert">×</a> 
                            <i class="fa fa-coffee"></i>
                            يرجي التأكد من كتابه البيانات بشكل صحيح
                        </div>
                        <h3>Personal info</h3>
                        
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Full name:</label>
                                <div class="col-lg-8">
                                    <input class="form-control" value="${sessionScope.user.fullName}" name="fullName" type="text">
                                </div>
                            </div>
                            <!--                                                        <div class="form-group">
                                                                                        <label class="col-lg-3 control-label">Last name:</label>
                                                                                        <div class="col-lg-8">
                                                                                            <input class="form-control" value="Bishop" type="text">
                                                                                        </div>
                                                                                    </div>-->
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Mobile: </label>
                                <div class="col-lg-8">
                                    <input class="form-control" value="${sessionScope.user.mobile}" name="mobile" type="mobile">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Email:</label>
                                <div class="col-lg-8">
                                    <input class="form-control" value="${sessionScope.user.mail}" name="mail" type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Governerate:</label>
                                <div class="col-lg-8">
                                    <input class="form-control" value="${sessionScope.user.governerate}" name="governerate" type="text">
                                </div>
                            </div>
                            
                            <!--                                                        <div class="form-group">
                                                                                        <label class="col-lg-3 control-label">Time Zone:</label>
                                                                                        <div class="col-lg-8">
                                                                                            <div class="ui-select">
                                                                                                <select id="user_time_zone" class="form-control">
                                                                                                    <option value="Hawaii">(GMT-10:00) Hawaii</option>
                                                                                                    <option value="Alaska">(GMT-09:00) Alaska</option>
                                                                                                    <option value="Pacific Time (US &amp; Canada)">(GMT-08:00) Pacific Time (US &amp; Canada)</option>
                                                                                                    <option value="Arizona">(GMT-07:00) Arizona</option>
                                                                                                    <option value="Mountain Time (US &amp; Canada)">(GMT-07:00) Mountain Time (US &amp; Canada)</option>
                                                                                                    <option value="Central Time (US &amp; Canada)" selected="selected">(GMT-06:00) Central Time (US &amp; Canada)</option>
                                                                                                    <option value="Eastern Time (US &amp; Canada)">(GMT-05:00) Eastern Time (US &amp; Canada)</option>
                                                                                                    <option value="Indiana (East)">(GMT-05:00) Indiana (East)</option>
                                                                                                </select>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>-->
                            <!--                                                        <div class="form-group">
                                                                                        <label class="col-md-3 control-label">Governerate</label>
                                                                                        <div class="col-md-8">
                                                                                            <input class="form-control" value="${userHasOffer.governerate}" type="text">
                                                                                        </div>
                                                                                    </div>-->
                            <!--                                                        <div class="form-group">
                                                                                        <label class="col-md-3 control-label">Password:</label>
                                                                                        <div class="col-md-8">
                                                                                            <input class="form-control" value="11111122333" type="password">
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="form-group">
                                                                                        <label class="col-md-3 control-label">Confirm password:</label>
                                                                                        <div class="col-md-8">
                                                                                            <input class="form-control" value="11111122333" type="password">
                                                                                        </div>
                                                                                    </div>-->
                            <div class="form-group">
                                <label class="col-md-3 control-label"></label>
                                <div class="col-md-8">
                                    <input class="btn btn-primary" value="Save Changes" type="submit">
                             
<!--                                    <input class="btn btn-default" value="Cancel" type="reset">-->
                                </div>
                            </div>
                        <!--</form>-->
                    </div>
                </div>
            </div>
            </form>
        </div>
    </div>

</div>		
</div> 
</div>
</div>

</div>
</div>
</body>
</html>

<jsp:include  page="footer/footer.jsp"/>
</body>
