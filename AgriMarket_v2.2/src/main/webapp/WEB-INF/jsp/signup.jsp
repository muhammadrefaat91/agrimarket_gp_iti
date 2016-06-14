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
                                    location.href = "http://${pageContext.request.getServerName()}:${pageContext.request.getServerPort()}${pageContext.request.contextPath}/web/" + data + "";
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
        

<!--        <section class="mbr-section mbr-section--relative mbr-section--fixed-size mbr-parallax-background mbr-after-navbar" id="form1-19">
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

                                <div class="g-person" data-href="//plus.google.com/b/116305644917036746344" data-rel="author"></div>


                                <div class="g-plusone" data-annotation="inline" data-width="300"></div>

                                <div class="g-follow" data-annotation="bubble" data-height="20" data-href="//plus.google.com/u/0/114611953418832109500" data-rel="author"></div>

                                <table>
                                    <tr>
                                        <td>
                                            <div class="g-post" data-href="https://plus.google.com/116305644917036746344/posts/TWnChPfcmuV"></div>
                                        </td>
                                        <td>
                                    <g:page href="https://plus.google.com/116305644917036746344"></g:page>

                                    </td>
                                    </tr>  
                                </table>

                            </div>
                            <div id="name"></div>

                            <script>startApp();</script>

                        </div>
                    </div>
                </div>
            </div>
        </section>-->

        <div class="main">
            <div class="content">
                <div class="section group">
                    <div class="col span_2_of_3" style="direction: <spring:message code="contact.css.col span_2_of_3.dir" />;
                         float:<spring:message code="contact.css.col span_2_of_3.float" />; ">
                        <div class="contact-form">
                            <div align="center">
                                 
                                <br><br>

                    
                                <div id="gSignInWrapper">
<!--                                     <span class="label">Sign in with:</span>-->
<!--                                    <div id="customBtn" class="customGPlusSignIn">-->
                                        <span class="icon" id="gicon"></span>
<!--                                        <span class="buttonText">Google</span>-->
<!--                                    </div>        -->

                                </div>
                                <div id="name"></div>

                                <script>startApp();</script>
                                
                                          <div class="g-post" data-href="https://plus.google.com/116305644917036746344/posts/TWnChPfcmuV"></div>
                            
                                


                            </div>
                        </div>
                       
                        </div>
                        <div class="col span_1_of_3">
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
