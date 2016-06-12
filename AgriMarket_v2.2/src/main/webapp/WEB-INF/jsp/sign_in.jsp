<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">

        <link rel="icon" href="<spring:url value="/resources/images/agri_logo.png" />">
        <title><spring:message code="title.sign_in" /></title>
        <link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet" >
        <link href="<spring:url value="/resources/css/slider.css" />" rel="stylesheet" >
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/css/style.css">
        <link rel="stylesheet" href="resources/css/mbr-additional.css" type="text/css">

    </head>
    <body  >
        <!--<div class="header">-->
        <div class="wrap">
            <!---include header description -->

            <jsp:include page="header/headertop_desc.jsp" />
            <div class="call" style="    margin-top: -34px;">
                <p> <spring:message code="text.lang" /> : <a href="?id=${param['id']}&lang=en"><spring:message code="text.lang.english" /></a>|<a href="?id=${param['id']}&lang=ar_EG"><spring:message code="text.lang.arbic" /></a></p>
            </div>
            <!---include header top -->
            <jsp:include page="header/header_top.jsp" />
            <!---include nav bar -->
            <jsp:include page="header/header_bottom_nav.jsp" />
        </div>


        <section class="mbr-section mbr-section--relative mbr-section--fixed-size mbr-parallax-background mbr-after-navbar" id="form1-19">
            <div class="mbr-section__container mbr-section__container--std-padding container">
                <div class="row">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2" >
                            <c:if test="${not empty error}">
                                <p style="margin-bottom: 5px; color: red;"><spring:message code="text.addoffer.error" /> ${error}</p>
                            </c:if>
                        </div>
                        <div class="col-sm-12">

                            <div class="col-sm-8 col-sm-offset-2">
                                <c:if test="${not empty user}">
                                    <c:redirect url="index.htm" />
                                </c:if>
                                <spring:message code="offer_page.css.content_top.dir"  var="margin"/>
                                <form:form action="userlogin" method="post" dir="${margin}" >

                                    <div class="form-group">
                                        <label style="margin-bottom: 8px;"><spring:message code="text.user.email" />  </label>
                                        <input type="email"  id="mail"  class="form-control"  name="mail"  required />
                                    </div>
                                    <div class="form-group">
                                        <label style="margin-bottom: 8px;"><spring:message code="text.signin.password" /> </label>
                                        <input   id="fullName" type="password" class="form-control"  name="password" required />
                                        <form:errors path="fullName"/>
                                    </div>
                                    <div class="mbr-article mbr-article--auto-align mbr-article--wysiwyg"><h4 id="error"></h4></div>

                                    <div class="mbr-buttons mbr-buttons--right">
                                        <input  type="submit"   Class="mbr-buttons__btn btn btn-lg btn-danger" value="<spring:message code="button.signin.signin" />" /></div>
                                    </form:form>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </section>

        <jsp:include  page="footer/footer.jsp"/>
    </body>
</html>