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
        <meta charset="UTF-8" >

        <title><spring:message code="title.addoffer.addproduct" /></title>
        <link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet" >
        <link href="<spring:url value="/resources/css/slider.css" />" rel="stylesheet" >
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/css/style.css">
        <link rel="stylesheet" href="resources/css/mbr-additional.css" type="text/css">



        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
        <link rel="stylesheet" href="style.css" />
        <title>jQuery Example</title>

    </head>
    <!--style="background-image: url(resources/images/istock-000016896298xlarge-4200x2833-56.jpg);"-->
    <body  >

        <div class="wrap">

        <div class="fb-like" data-href="https://www.facebook.com/ChatAppJits/" data-width="200" data-layout="button_count" data-action="like" data-show-faces="true" data-share="true"></div>

        <!---include header description -->


        <jsp:include page="header/headertop_desc.jsp" />
      <div class="call" style="    margin-top: -34px;">
            <p> <spring:message code="text.lang" /> : <a href="?lang=en"><spring:message code="text.lang.english" /></a>|<a href="?lang=ar_EG"><spring:message code="text.lang.arbic" /></a></p>
        </div>
        <!---include header top -->
        <jsp:include page="header/header_top.jsp" />

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
                                <p style="margin-bottom: 5px; color: red;"><spring:message code="text.addoffer.error" /> ${error}</p>
                            </c:if>
                        </div>
                        <div class="col-sm-12">

                            <div class="col-sm-8 col-sm-offset-2">
                                <form   method="post" enctype="multipart/form-data" action="addoffer" >
                                    <div align="center">
                                        <%--<spring:message code="name" />--%>

                                        <div class="form-group">


                                            <label><spring:message code="text.addoffer.description" /> </label> <input type="text"    name="description" class="form-control"  />


                                        </div>


                                        <div class="form-group">
                                            <label><spring:message code="text.preview.quantity" /> </label>
                                            <input   id="quantity" type="number" class="form-control"  name="quantity" required />

                                        </div>




                                        <!--                                        <div class="form-group">
                                                                                    <label>unit of Quantity: </label>
                                                                                    <input   id="quantity" type="number" class="form-control"  name="quantityunit" required />
                                        
                                                                                </div>-->



                                        <!--
                                                                                <div class="form-group">
                                                                                    <label>unit of price: </label>
                                                                                    <input   id="quantity" type="number" class="form-control"  name="unitprice" required />
                                        
                                                                                </div>-->

                                        <div class="form-group">
                                            <label><spring:message code="text.addoffer.quantityunit" /> </label> 

                                            <select name="quantityunit">
                                                <c:forEach var="item" items="${units}">
                                                    <option value="${item.id}">${item.nameAr}</option>
                                                </c:forEach>
                                            </select>

                                        </div>

                                        <div class="form-group">

                                            <label><spring:message code="text.addoffer.priceunit" /></label>  
                                            <select name="unitprice">
                                                <c:forEach var="item" items="${units}">
                                                    <option class="form-control" value="${item.id}">${item.nameEn}</option>
                                                </c:forEach>
                                            </select>

                                        </div>


                                        <div class="form-group">
                                            <label><spring:message code="text.addoffer.price" /> </label>
                                            <input   id="quantity" type="number" class="form-control"  name="price" required />

                                        </div>


                                        <div class="form-group">

                                            <label><spring:message code="text.addoffer.product" /> </label>
                                            
                                            <select name="product">
                                                <c:forEach var="item" items="${products}">
                                                    <option value="${item.id}" class="form-control">${item.nameEn}</option>
                                                </c:forEach>
                                            </select>

                                        </div>


                                        <div class="form-group">

                                            <label><spring:message code="text.addoffer.mobile" /> </label><input type="tel"   name="mobile"  class="form-control" />


                                        </div>




                                        <div class="form-group">

                                            <label><spring:message code="text.addoffer.governerate" /> </label><input type="text"   name="governerate" class="form-control"/>



                                        </div>


<!--
                                        <div class="form-group">
                                            <label>product id: </label>
                                            <input   id="quantity" type="number" class="form-control"  name="product" required />

                                        </div>-->



                                        <div class="form-group">
                                            <tr><td><spring:message code="text.addoffer.image" /></td><td><input type="file" name="file"  class="form-control" /></td></tr>
                                        </div>


                                        <tr><td><spring:message code="text.addoffer.name" /></td><td><input type="text" name="name" class="form-control" /></td></tr>





                                        <div class="form-group">

                                            <input type="submit" value="<spring:message code="button.addoffer.add" />" id="add" /><br/>
                                        </div>




                                    </div>

                                </form>



                            </div>

                            <!--
                                                        <form action='signupimage' method='post' ENCTYPE='MULTIPART/FORM-DATA'>
                                                            <table><tr><td>Upload:<input type='file' name='fileUpload' /></td><td>&nbsp;&nbsp;<input type='submit' value='Upload'/></td></tr>
                                                            </table> </form>-->









                        </div>
                    </div>
                </div>
            </div>
        </section>

                                        
                                        
                    <!DOCTYPE html>
<!--
<div>
  <form  method="post" enctype="multipart/form-data" action="addoffer" >
      
      
    <label for="fname">First Name</label>
     <label>Description :</label> <input type="text"    name="description"  />

                                  
                                            <label>Quantity: </label>
                                            <input   id="quantity" type="number"   name="quantity" required />




                                                                                <div class="form-group">
                                                                                    <label>unit of Quantity: </label>
                                                                                    <input   id="quantity" type="number" class="form-control"  name="quantityunit" required />
                                        
                                                                                </div>



                                        
                                                                                <div class="form-group">
                                                                                    <label>unit of price: </label>
                                                                                    <input   id="quantity" type="number" class="form-control"  name="unitprice" required />
                                        
                                                                                </div>

                                           <label>Quantity Unit :</label> 

                                            <select name="quantityunit">
                                                <c:forEach var="item" items="${units}">
                                                    <option value="${item.id}">${item.nameAr}</option>
                                                </c:forEach>
                                            </select>

                                           <label>price Unit :</label>  
                                            <select name="unitprice">
                                                <c:forEach var="item" items="${units}">
                                                    <option class="form-control" value="${item.id}">${item.nameEn}</option>
                                                </c:forEach>
                                            </select>

                                             <label>Price: </label>
                                            <input   id="quantity" type="number"   name="price" required />

                                     <label>Product : </label>
                                            
                                            <select name="product">
                                                <c:forEach var="item" items="${products}">
                                                    <option value="${item.id}" class="form-control">${item.nameEn}</option>
                                                </c:forEach>
                                            </select>

 

      
                                            <label>Telephone: </label><input type="tel"   name="mobile"   />





                                           <label>Governerate: </label><input type="text"   name="governerate" />



                                        <div class="form-group">
                                            <label>product id: </label>
                                            <input   id="quantity" type="number" class="form-control"  name="product" required />

                                        </div>



                                            <tr><td>Image :</td><td><input type="file" name="file"   /></td></tr>
 

                                        <tr><td>Name:</td><td><input type="text" name="name"  /></td></tr>




                                          <input type="submit" value="Submit" id="add" /><br/>
 

                                    </div>

  </form>
</div>-->
        </div>
</body>
</html>

        <jsp:include  page="footer/footer.jsp"/>
    </body>
