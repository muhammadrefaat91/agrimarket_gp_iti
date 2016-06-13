<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<head>
    <title><spring:message code="title.index" /></title>

    <link rel="icon" href="<spring:url value="/resources/images/agri_logo.png" />">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="<spring:url value="/resources/css/slider.css" />" rel="stylesheet">

    <script type="text/javascript" src="<spring:url value="/resources/js/jquery-1.7.2.min.js" />"></script>
    <script type="text/javascript" src="<spring:url value="/resources/js/jquery.cookie.js" />"></script>

</head>
<body>

    <div class="wrap">

        <!-- header--->
        <jsp:include page="header/headertop_desc.jsp" />
        <div class="call" style="    margin-top: -34px;">
            <p> <spring:message code="text.lang" /> : <a href="?name=${param['name']}&lang=en"><spring:message code="text.lang.english" /></a>|<a href="?name=${param['name']}&lang=ar_EG"><spring:message code="text.lang.arbic" /></a></p>
        </div>
        <!---include header top -->
        <jsp:include page="header/header_top.jsp" />
        <jsp:include page="header/header_bottom_nav.jsp" />
        <jsp:include page="header/header.jsp" />
        <!-- content--->
        <div class="main">
            <div class="content">
<!--                <div class="content_top">
                    
                </div>
                <div class="section group">

                </div>-->

            </div>
        </div>
    </div>

    <a href="#" id="toTop"><span id="toTopHover"> </span></a>
</body>
</html>


