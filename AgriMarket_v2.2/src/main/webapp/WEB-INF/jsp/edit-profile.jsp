<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
    <head>
        <title>${user.fullName}</title>
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
        <c:if test="${empty sessionScope.user}">
            <c:redirect url="/web/signup.htm"/>
        </c:if>
        
        <div class="wrap">
            <div class="fb-like" data-href="https://www.facebook.com/ChatAppJits/" data-width="200" data-layout="button_count" data-action="like" data-show-faces="true" data-share="true"></div>
            <!---include header description -->
            <jsp:include page="header/headertop_desc.jsp" />
            <div class="call" style="    margin-top: -34px;">
                <p style="direction: <spring:message code="addoffer.css.contactform.dir" />;"> <spring:message code="text.lang" /> : <a href="?lang=en"><spring:message code="text.lang.english" /></a>|<a href="?lang=ar_EG"><spring:message code="text.lang.arbic" /></a></p>
            </div>
            <jsp:include page="header/header_top.jsp" />
            <!---include nav bar -->
            <jsp:include page="header/header_bottom_nav.jsp" />
            <form class="form-horizontal" method="post" enctype="multipart/form-data" action="uprofile.htm" role="form" >
            <div class="container" style="padding-top: 60px;    margin-top: -72px;
    margin-left: -25px;
    margin-bottom: 215px;    width: 104%;">
                <h1 class="page-header"><spring:message code="edit-profile.text.editprofile" /></h1>
                
                <div class="row" style="direction:  <spring:message code="edit-profile.css.dir" />;">
                    <!-- left column -->
                    <div class="col-md-4 col-sm-6 col-xs-12" style="direction:  <spring:message code="edit-profile.css.dir" />;"></h1>

                        <div class="text-center">
                            <img style="height: 117px;margin-right: 85px;" alt="avatar" title="<spring:message code="img.title.text.view_user.profileimage" />"
                                 class="avatar img-circle img-thumbnail" src="${pageContext.request.contextPath}${sessionScope.user.imageUrl}">
                            <h6><spring:message code="edit-profile.text.uploadimage" /></h6>
                           
                            <!--<input type="file" class="text-center center-block well well-sm" />-->
                            <input type="file" name="file"  class="form-control" />
                        </div>
                    </div>
                    <!-- edit form column -->
                    <div class="col-md-8 col-sm-6 col-xs-12 personal-info">
                        <div class="alert alert-info alert-dismissable">
                            <a class="panel-close close" data-dismiss="alert">×</a> 
                            <i class="fa fa-coffee"></i>
                            <spring:message code="edit-profile.text.note" />
                        </div>
                            <h3 style="padding-bottom: 10px;"><spring:message code="edit-profile.text.userinfo" /></h3>
                        
                        <div class="form-group" style="padding-bottom: 13px;">
                                <label class="col-lg-3 control-label"><spring:message code="edit-profile.fullname" /></label>
                                <div class="col-lg-8">
                                    <input class="form-control" value="${sessionScope.user.fullName}" maxlength="55" required="" name="fullName" min="11" type="text">
                                </div>
                            </div>
                            <div class="form-group" style="padding-bottom: 13px;">
                                <label class="col-lg-3 control-label"><spring:message code="text.user.mobile" /> </label>
                                <div class="col-lg-8">
                                    <input class="form-control" value="${sessionScope.user.mobile}" name="mobile" required="" title="at least 11 number" maxlength="11" min="11" type="tel">
                                </div>
                            </div>
                            <div class="form-group" style="padding-bottom: 13px;">
                                <label class="col-lg-3 control-label"><spring:message code="text.user.email"  /></label>
                                <div class="col-lg-8">
                                    <input class="form-control" value="${sessionScope.user.mail}" name="mail" maxlength="55" required="" min="10" type="email">
                                </div>
                            </div>
                            <div class="form-group" style="padding-bottom: 13px;">
                                <label class="col-lg-3 control-label"><spring:message code="text.addoffer.governerate" /></label>
                                <div class="col-lg-8">
                                    <input class="form-control" value="${sessionScope.user.governerate}"    maxlength="55" min="22" required="" name="governerate" type="text">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-md-3 control-label"></label>
                                <div class="col-md-8">
                                    <input class="btn btn-primary" value="<spring:message code="edit-profile.button.savechange" />" type="submit">
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
