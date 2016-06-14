<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
    <head>
        <c:if test="${not empty user}" >
            <c:redirect url="/index.htm" />
        </c:if>
        <meta charset="UTF-8">

        <link rel="icon" href="<spring:url value="/resources/images/agri_logo.png" />">
        <title><spring:message code="title.sign_in" /></title>
        <link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet" >
        <link href="<spring:url value="/resources/css/slider.css" />" rel="stylesheet" >
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/css/style.css">
        <link rel="stylesheet" href="resources/css/mbr-additional.css" type="text/css">

        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
        <script src="https://apis.google.com/js/api:client.js"></script>
        <script>
            var googleUser = {};
            var startApp = function () {
                gapi.load('auth2', function () {
                    // Retrieve the singleton for the GoogleAuth library and set up the client.
                    auth2 = gapi.auth2.init({
                        client_id: '316647912569-d30dt8m1kqgkp1rblrfbp70c0tk11hol.apps.googleusercontent.com',
                        cookiepolicy: 'single_host_origin',
                        // Request scopes in addition to 'profile' and 'email'
                        //scope: 'additional_scope'
                    });
                    attachSignin(document.getElementById('customBtn'));
                });
            };

            function attachSignin(element) {
                console.log(element.id);
                auth2.attachClickHandler(element, {},
                        function (googleUser) {
                            document.getElementById('name').innerText = "Signed in: " +
                                    googleUser.getBasicProfile().getName();

                            var profile = googleUser.getBasicProfile();
                            console.log("ID: " + profile.getId()); // Don't send this directly to your server!
                            console.log('Full Name: ' + profile.getName());
                            console.log('Given Name: ' + profile.getGivenName());
                            console.log('Family Name: ' + profile.getFamilyName());
                            console.log("Image URL: " + profile.getImageUrl());
                            console.log("Email: " + profile.getEmail());



                            $.ajax({
                                url: "${pageContext.request.contextPath}/web/signupgplus",
                                type: "POST",
                                data: {name: googleUser.getBasicProfile().getName(), email: googleUser.getBasicProfile().getEmail(), img: profile.getImageUrl()},
                                success: function (data) {
                                    console.log('success' + data);
//                        if (data === 'no_errors')
                                    location.href = "http://${pageContext.request.getServerName()}:${pageContext.request.getServerPort()}${pageContext.request.contextPath}/" + data + "";
                                },
                                error: function (e) {
                                    console.log('error:' + e.data);
                                }
                            });



                        }, function (error) {
                    alert(JSON.stringify(error, undefined, 2));
                });
            }
        </script>
        <style type="text/css">
            #customBtn {
                display: inline-block;
                background: white;
                color: #444;
                width: 190px;
                border-radius: 5px;
                border: thin solid #888;
                box-shadow: 1px 1px 1px grey;
                white-space: nowrap;
            }
            #customBtn:hover {
                cursor: pointer;
            }
            span.label {
                font-family: serif;
                font-weight: normal;
            }
            span.icon {
                background: url('resources/images/google.png') transparent 5px 50% no-repeat;
                display: inline-block;
                vertical-align: middle;
                width: 42px;
                height: 42px;
            }
            span.buttonText {
                display: inline-block;
                vertical-align: middle;
                padding-left: 42px;
                padding-right: 42px;
                font-size: 14px;
                font-weight: bold;
                /* Use the Roboto font that is loaded in the <head> */
                font-family: 'Roboto', sans-serif;
            }
        </style>


        <style type="text/css">
            .search_box form input[type="submit"]{
                margin-top: <spring:message code="search.icon.margin-top" />px;
            }
        </style>
    </head>
    <body  >
        <!--<div class="header">-->
        <div class="wrap">
            <!---include header description -->

            <jsp:include page="header/headertop_desc.jsp" />
            <div class="call" style="    margin-top: -34px;">
                <p style="direction: <spring:message code="addoffer.css.contactform.dir" />;"> <spring:message code="text.lang" /> : <a href="?id=${param['id']}&lang=en"><spring:message code="text.lang.english" /></a>|<a href="?id=${param['id']}&lang=ar_EG"><spring:message code="text.lang.arbic" /></a></p>
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
                        <div align="center">



                            <div id="gSignInWrapper">
                                <span class="label">Sign in with:</span>
                                <div id="customBtn" class="customGPlusSignIn">
                                    <span class="icon"></span>
                                    <span class="buttonText">Google</span>
                                </div>
                            </div>
                            <div id="name"></div>

                            <script>startApp();</script>

                        </div>
                    </div>
                </div>
            </div>
        </section>

        <jsp:include  page="footer/footer.jsp"/>
    </body>
</html>
