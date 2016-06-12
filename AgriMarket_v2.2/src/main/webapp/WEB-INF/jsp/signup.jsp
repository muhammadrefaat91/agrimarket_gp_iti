<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
    <head>
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

              $.ajax({
                    url: "${pageContext.request.contextPath}/signupgplus",
                    type: "POST",
                    data: {name: googleUser.getBasicProfile().getName(), email:googleUser.getBasicProfile().getEmail()},
                    success: function (data) {
                        console.log('success' + data);
//                        if (data === 'no_errors')
                            location.href = "http://${pageContext.request.getServerName()}:${pageContext.request.getServerPort()}${pageContext.request.contextPath}/"+data+"";
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
                background: url('/identity/sign-in/g-normal.png') transparent 5px 50% no-repeat;
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
    </head>
    <!--style="background-image: url(resources/images/istock-000016896298xlarge-4200x2833-56.jpg);"-->
    <body  >

        <div class="fb-like" data-href="https://www.facebook.com/ChatAppJits/" data-width="200" data-layout="button_count" data-action="like" data-show-faces="true" data-share="true"></div>

        <jsp:include page="header/headertop_desc.jsp" />
        <div class="call" style="    margin-top: -34px;">
            <p> Language : <a href="?id=${param['id']}&lang=en">English</a>|<a href="?id=${param['id']}&lang=ar_EG">عربي</a></p>
        </div>

        <jsp:include page="header/header_bottom_nav.jsp" />
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
          <jsp:include  page="footer/footer.jsp"/>
    </body>
</html>