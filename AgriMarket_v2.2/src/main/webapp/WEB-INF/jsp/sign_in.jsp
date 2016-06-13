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
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <c:if test="${not empty msg}">
            <div class="msg">${msg}</div>
        </c:if>
        <!--<div class="header">-->
        <div class="wrap">
            <!---include header top -->
            <jsp:include page="header/header_top.jsp" />
            <!---include nav bar -->
            <jsp:include page="admin/header/header_bottom_nav.jsp" />
        </div>


        <section class="mbr-section mbr-section--relative mbr-section--fixed-size mbr-parallax-background mbr-after-navbar" id="form1-19">
            <div class="mbr-section__container mbr-section__container--std-padding container">
                <div class="row">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2" >

                        </div>
                        <div class="col-sm-12">

                            <div class="col-sm-8 col-sm-offset-2">

                                <form name='loginForm'
                                      action="<c:url value='j_spring_security_check'/>" method='POST'>

                                    <div class="form-group">
                                        <td>User:</td>
                                        <td><input class="form-control"   type='text' name='username' value=''></td>
                                    </div>
                                    <div class="form-group">

                                        <td>Password:</td>
                                        <td><input class="form-control"   type='password' name='password' /></td>
                                    </div>
                                    <td colspan='2'>
                                        <div class="mbr-buttons mbr-buttons--right">
                                            <input  Class="mbr-buttons__btn btn btn-lg btn-danger"  name="submit" type="submit"
                                                    value="submit" /></td>
                            </div>

                            </form>

                        </div>

                    </div>
                </div>
            </div>
        </div>
    </section>

    <jsp:include  page="footer/footer.jsp"/>
</body>
</html>




