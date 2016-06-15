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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />

        <script src="https://apis.google.com/js/platform.js" async defer>
            {
                lang: 'ar'
            }
        </script>

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
                    attachSignin(document.getElementById('gicon'));
                });
            };

            function attachSignin(element) {
                console.log(element.id);
                auth2.attachClickHandler(element, {},
                        function (googleUser) {
                            document.getElementById('name').innerText = "Signed in: " +
                                    googleUser.getBasicProfile().getName();

                            var profile = googleUser.getBasicProfile();



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
                background: url('/AgriMarket_v2.2/resources/images/google.png') transparent 5px 50% no-repeat;
                display: inline-block;
                vertical-align: middle;
                width: 250px;
                height: 60px;
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
            <div class="main">
                <div class="content">
                    <div class="section group">
                        <div class="col span_2_of_3" style="float:left; ">
                            <div class="contact-form">
                                <div align="center" style="text-align: start;">
                                    <div class="alert alert-info alert-dismissable" style="direction: ltr;
                                         width: 58%;background-color: #4CAF50;height: 46px;">
                                        <!--<a class="panel-close close" data-dismiss="alert">×</a>--> 
                                        <i class="fa fa-coffee"></i>
                                        <p style="color: white;margin-top: 7px;margin-left: 34px;direction: <spring:message code="signup.text.login.dir" />;margin-right: <spring:message code="signup.text.login.margin-right" />px;"><spring:message code="signup.text.login" /></p>
                                    </div>

                                    <div id="gSignInWrapper" style="    padding-bottom: 120px;">
                                        <span class="icon" id="gicon"></span>
                                    </div>

                                    <div id="name"></div>
                                    <!--<p>Posts from Google plus Page</p>-->
                                    <div class="alert alert-info alert-dismissable" style="direction: ltr;
                                         width: 73%;background-color: #4CAF50;height: 46px;">
                                        <!--<a class="panel-close close" data-dismiss="alert">×</a>--> 
                                        <i class="fa fa-coffee"></i>
                                        <p style="color: white;margin-top: 7px;margin-left: 34px;direction: <spring:message code="signup.text.login.dir" />;margin-right: <spring:message code="signup.text.login.margin-right" />px;"><spring:message code="signup.text.posts" /></p>
                                    </div>
                                    <script>startApp();</script>
                                    <div class="g-post" data-href="https://plus.google.com/116305644917036746344/posts/TWnChPfcmuV"></div>
                                </div>
                            </div>

                        </div>

                        <div class="col span_1_of_3">
                            <div class="alert alert-info alert-dismissable" style="    direction: ltr;
                                 width: 136%;
                                 background-color: #4CAF50;
                                 height: 47px;">
                                <!--<a class="panel-close close" data-dismiss="alert">×</a>--> 
                                <i class="fa fa-coffee"></i>
                                <p style="color: white;
    margin-top: -26px;
    margin-left: 34px;
    font-size: 23px;margin-right: <spring:message code="signup.subscribe.margin-right" />;direction:<spring:message code="signup.text.login.dir" />; "><spring:message code="signup.text.subscribe" /></p>
                            </div>

                            <div class="contact_info" style="text-align:<spring:message code="contact.css.contact_info.text-align" />; ">
                                <table>
                                    <tr>
                                        <td>
                                    <g:page href="https://plus.google.com/116305644917036746344"></g:page>
                                    </td>
                                    </tr>
                                    <tr>
                                        <td>
                                        </td>
                                    </tr>  
                                </table>
                            </div>
                        </div>
                    </div>		
                </div> 
            </div>
        </div>
        <jsp:include  page="footer/footer.jsp"/>
    </body>
</html>
