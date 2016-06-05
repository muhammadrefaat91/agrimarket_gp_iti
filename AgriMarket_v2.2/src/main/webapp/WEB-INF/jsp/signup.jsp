<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
    <head>
        <!-- Site made with Mobirise Website Builder v2.9, https://mobirise.com -->
        <meta charset="UTF-8">

        <title>Sign In</title>
        <link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet" >
        <link href="<spring:url value="/resources/css/slider.css" />" rel="stylesheet" >
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/css/style.css">
        <link rel="stylesheet" href="resources/css/mbr-additional.css" type="text/css">
        <script>
            function signIn() {

            }

        </script>
        
        
          <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
  <link rel="stylesheet" href="style.css" />
  <title>jQuery Example</title>
  <script>
 $(document).ready(function() {
  $.ajaxSetup({ cache: true });
  $.getScript('//connect.facebook.net/en_US/sdk.js', function(){
    FB.init({
      appId: '600728280015510',
      version: 'v2.5' // or v2.0, v2.1, v2.2, v2.3
    });     
    $('#loginbutton,#feedbutton').removeAttr('disabled');
    FB.getLoginStatus(updateStatusCallback);
  });
});
  </script>
        
        
  
  
  
  
  
        
    </head>
    <!--style="background-image: url(resources/images/istock-000016896298xlarge-4200x2833-56.jpg);"-->
    <body  >

                             
         

<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.6&appId=600728280015510";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>

<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.6&appId=600728280015510";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>


<div class="fb-like" data-href="https://www.facebook.com/ChatAppJits/" data-width="200" data-layout="button_count" data-action="like" data-show-faces="true" data-share="true"></div>

        <!---include header description -->

        
        <jsp:include page="header/headertop_desc.jsp" />

        <!---include header top -->
        <%--<jsp:include page="header/header_top.jsp" />--%>

        <!---include nav bar -->
        <jsp:include page="header/header_bottom_nav.jsp" />
        <!--        <div class="mbr-overlay" style="opacity: 0.2; background-color: rgb(34, 34, 34);"></div>
       
               <section class="engine"><a rel="external" href="https://mobirise.com">Mobirise bootstrap layout builder
                   </a></section>-->
        <!--        <section class="mbr-navbar mbr-navbar--freeze mbr-navbar--absolute mbr-navbar--transparent mbr-navbar--sticky mbr-navbar--auto-collapse" id="menu-0">
                    <div class="mbr-navbar__section mbr-section">
                        <div class="mbr-section__container container">
                            <div class="mbr-navbar__container">
                                <div class="mbr-navbar__column mbr-navbar__column--s mbr-navbar__brand">
                                    <span class="mbr-navbar__brand-link mbr-brand mbr-brand--inline">
                                        <span class="mbr-brand__logo"><a href="index.jsp"><img class="mbr-navbar__brand-img mbr-brand__img" src="resources/images/untitled-382x276-98.png" alt="AgriMarket" title="AgriMarket"></a></span>
                                        <span class="mbr-brand__name"><a class="mbr-brand__name text-white" href="index.jsp">AGRIMARKET</a></span>
                                    </span>
                                </div>
                                <div class="mbr-navbar__hamburger mbr-hamburger text-white"><span class="mbr-hamburger__line"></span></div>
                                <div class="mbr-navbar__column mbr-navbar__menu">
                                    <nav class="mbr-navbar__menu-box mbr-navbar__menu-box--inline-right">
                                        <div class="mbr-navbar__column"><ul class="mbr-navbar__items mbr-navbar__items--right mbr-buttons mbr-buttons--freeze mbr-buttons--right btn-decorator mbr-buttons--active"><li class="mbr-navbar__item"><a class="mbr-buttons__link btn text-white" href="index.jsp">HOME</a></li></ul></div>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>-->

        <section class="mbr-section mbr-section--relative mbr-section--fixed-size mbr-parallax-background mbr-after-navbar" id="form1-19">
            <div class="mbr-section__container mbr-section__container--std-padding container">
                <div class="row">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2" >
                            <c:if test="${not empty error}">
                                <p style="margin-bottom: 5px; color: red;">Error: ${error}</p>
                            </c:if>
                        </div>
                        <div class="col-sm-12">

                            <div class="col-sm-8 col-sm-offset-2">
                                <form   method="post" enctype="multipart/form-data" action="saveuser" >
                                    <div align="center">
                                        <%--<spring:message code="name" />--%>

                                        <div class="form-group">


                                            <label>full name :</label> <input type="text"    name="fullName" class="form-control"  />


                                        </div>


                                        <div class="form-group">
                                            <label>Password: </label>
                                            <input   id="fullName" type="password" class="form-control"  name="password" required />

                                        </div>




                                        <div class="form-group">

                                            <label>Telephone: </label><input type="tel"   name="mobile"  class="form-control" />


                                        </div>




                                        <div class="form-group">

                                            <label>Governerate: </label><input type="text"   name="governerate"class="form-control"/>



                                        </div>


                                        <div class="form-group">

                                            <label>Email : </label>


                                            <input type="email"  name="mail"   class="form-control" />


                                        </div>

                                        <!--
                                                                                <div class="form-group">
                                        
                                                                                    <label>image :</label><input type="file"  name="image"  class="form-control"/>
                                                                                </div>-->
                                        <div class="form-group">
                                            <tr><td>File to upload:</td><td><input type="file" name="file" /></td></tr>
                                        </div>


                                        <tr><td>Name:</td><td><input type="text" name="name" /></td></tr>




                                        <div class="form-group">

                                            <input type="submit" value="Submit" id="add" /><br/>
                                        </div>




                                    </div>

                                </form>



                            </div>

                            <!--
                                                        <form action='signupimage' method='post' ENCTYPE='MULTIPART/FORM-DATA'>
                                                            <table><tr><td>Upload:<input type='file' name='fileUpload' /></td><td>&nbsp;&nbsp;<input type='submit' value='Upload'/></td></tr>
                                                            </table> </form>-->


                            <div>
                                <form method="POST" enctype="multipart/form-data" action="uploadimage.">
                                    <table>
                                        <tr><td>File to upload:</td><td><input type="file" name="file" /></td></tr>
                                        <tr><td>Name:</td><td><input type="text" name="name" /></td></tr>
                                        <tr><td></td><td><input type="submit" value="Upload" /></td></tr>
     
                                    
                                    
                                    
                                    </table>
                                    
                 
<div class="fb-like" data-href="https://www.facebook.com/ChatAppJits/" data-width="200" data-layout="button_count" data-action="like" data-show-faces="true" data-share="true"></div>

                    <div class="fb-login-button" data-max-rows="3" data-size="large" data-show-faces="true" data-auto-logout-link="true" ></div>              
                                        
                   
                                    
                                    
                                    
                                </form>
                            </div>




                        </div>
                    </div>
                </div>
            </div>
        </section>
                                        
           
        <jsp:include  page="footer/footer.jsp"/>
    </body>
