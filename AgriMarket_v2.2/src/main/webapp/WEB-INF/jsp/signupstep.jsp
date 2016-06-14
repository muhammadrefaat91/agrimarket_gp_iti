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

        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>

        <link rel="icon" href="<spring:url value="/resources/images/agri_logo.png" />">
        <title><spring:message code="title.sign_in" /></title>
        <!--<link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet" >-->
        <!--<link href="<spring:url value="/resources/css/slider.css" />" rel="stylesheet" >-->
        <link rel="stylesheet" href="<spring:url value="/resources/css/bootstrap.min.css" />">
        <!--<link rel="stylesheet" href="<spring:url value="/resources/css/style.css"/>">-->
        <!--<link rel="stylesheet" href="resources/css/mbr-additional.css" type="text/css">-->
         <!--<link href="<spring:url value="/resources/css/main.css" />" rel="stylesheet">-->
        <style type="text/css">
            div label{
                float:<spring:message code="index.css.heading.float" /> ;
            }
        </style>
    </head>
    <body  >
        <!--<div class="header">-->
        <div class="wrap">
            <!---include header description -->

            <jsp:include page="header/headertop_desc.jsp" />
            <div class="call" style="    margin-top: -34px;">
                <p> <spring:message code="text.lang" /> : <a href="?lang=en"><spring:message code="text.lang.english" /></a>|<a href="?lang=ar_EG"><spring:message code="text.lang.arbic" /></a></p>
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



                                <div class="col-sm-8 col-sm-offset-2">
                                    <form   method="post" enctype="multipart/form-data" action="signupgplusstep2" >
                                        <div align="center">
                                            <%--<spring:message code="name" />--%>
                                            <div class="form-group">
                                                <span> <label><spring:message code="text.addoffer.governerate" />  </label></span>

                                                <span>
                                                    <select name="governerate" >
                                                        <c:forEach var="item" items="${requestScope.lang eq 'en'?states_us:states_ar}">
                                                            <option class="textbox" value="${item}">${item}</option>
                                                        </c:forEach>
                                                    </select>
                                                </span>

                                            </div>


                                            <div class="form-group">

                                                <label style="direction: <spring:message code="addoffer.css.contactform.dir" />"><spring:message code="text.addoffer.mobile" /></label><input type="tel" maxlength="11" min="11"  name="mobile" pattern="(01)\d{9}" title="<spring:message code="validate.mobile" />" required class="form-control" />


                                            </div>

                                            <div class="form-group">

                                                <input type="submit" value="<spring:message code="signupstep.button.signup" />" id="add" class="form-control" /><br/>
                                            </div>

                                        </div>
                                    </form>

                                </div>

                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </section>

        <jsp:include  page="footer/footer.jsp"/>
    </body>
</html>
