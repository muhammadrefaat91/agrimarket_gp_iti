<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
    <head>

        <link rel="icon" href="<spring:url value="/resources/images/agri_logo.png" />">
        <!-- Site made with Mobirise Website Builder v2.9, https://mobirise.com -->
        <meta charset="UTF-8" http-equiv="Content-Type" content="text/html">

        <link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet" >
        <link href="<spring:url value="/resources/css/slider.css" />" rel="stylesheet" >
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/css/style.css">
        <link rel="stylesheet" href="resources/css/mbr-additional.css" type="text/css">


        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <link href="<spring:url value="/resources/css/main.css" />" rel="stylesheet">

        <link rel="stylesheet" href="style.css" />
        <title>Update Product</title>
        <style type="text/css">
/*            .search_box form input[type="submit"]{
                margin-top: <spring:message code="search.icon.margin-top" />px;
            }*/
        </style>
    </head>
    <!--style="background-image: url(resources/images/istock-000016896298xlarge-4200x2833-56.jpg);"-->
    <body  >

        <div class="fb-like" data-href="https://www.facebook.com/ChatAppJits/" data-width="200" data-layout="button_count" data-action="like" data-show-faces="true" data-share="true"></div>

        <!---include header description -->
        <div class="wrap">
            <jsp:include page="header/headertop_desc.jsp" />
            <div class="call" style="    margin-top: -34px;">
                <p> <spring:message code="text.lang" /> : <a class="lang" href="?offerId=${param['offerId']}&lang=en"><spring:message code="text.lang.english" /></a>|<a class="lang" href="?offerId=${param['offerId']}&lang=ar_EG"><spring:message code="text.lang.arbic" /></a></p>
            </div>
            <!---include header top -->
            <%--<jsp:include page="header/header_top.jsp" />--%>

            <!---include nav bar -->
            <jsp:include page="header/header_bottom_nav.jsp" />
            <!--<div class="wrap">-->
            <div class="main">
                <div class="content">
                    <div class="section group">
                        <div class="col span_2_of_3" style="margin-left: 243px;">
                            <div class="contact-form" style="direction:<spring:message code="addoffer.css.contactform.dir" /> ">
                                <h2><spring:message code="updateoffer.text.updateyourProduc" /></h2>


                                <form   method="post" enctype="multipart/form-data" action="updateoffer.htm" >
                                    <input type="hidden" value="${param['offerId']}" name="offerId"/>
                                    <div>

                                        <span><label><spring:message code="text.addoffer.product" />  </label></span>
                                        <span>
                                            <span style="    font-size: 23px;">${requestScope.lang eq 'en'?offer.product.nameEn:offer.product.nameAr}</span>
                                            <select name="product">

                                                <c:forEach var="item" items="${products}" >
                                                    <option value="${item.id}" class="form-control">${requestScope.lang eq 'en'?item.nameEn:item.nameAr}</option>
                                                </c:forEach>
                                            </select>
                                        </span>
                                    </div>


                                    <div >

                                        <span><label><spring:message code="text.preview.quantity" /> </label></span>
                                        <span> <input   id="quantity" type="number" value="${offer.quantity}" class="form-control"  name="quantity" required   /></span>



                                        <span><label><spring:message code="text.addoffer.quantityunit" /></label></span> 
                                        <span>
                                            <select name="quantityunit">
                                                <c:forEach var="item" items="${units}">
                                                    <option value="${item.id}">${requestScope.lang eq 'en'?item.nameEn:item.nameAr}</option>
                                                </c:forEach>
                                            </select>
                                        </span>

                                        <div>
                                            <span> <label><spring:message code="dropdown.offer_page.price" /> </label></span>
                                            <span> <input   id="quantity" type="number" class="form-control"  name="price" value="${offer.price}" title = "<spring:message code="validate.quantity" />" required /></span>




                                            <span><label><spring:message code="text.addoffer.css.per" /> </label></span>
                                            <span>
                                                <select name="unitprice">

                                                    <c:forEach var="item" items="${units}" >
                                                        <option class="form-control" value="${item.id}" >${requestScope.lang eq 'en'?item.nameEn:item.nameAr}</option>
                                                    </c:forEach>

                                                </select>
                                            </span>

                                        </div>



                                        <div >

                                            <span><label><spring:message code="text.addoffer.mobile" /> </label></span><span><input type="tel"   name="mobile"  class="form-control" value="${offer.userPhone}"  pattern="(01)\d{9}" title="<spring:message code="validate.mobile" />" required  /></span>


                                        </div>




                                        <div >
                                            <span> <label><spring:message code="text.addoffer.governerate" />  </label></span>

                                            <span>
                                                <select name="governerate" >
                                                    <c:forEach var="item" items="${requestScope.lang eq 'en'?states_us:states_ar}">
                                                        <option class="textbox" value="${item}">${item}</option>
                                                    </c:forEach>
                                                </select>
                                            </span>



                                        </div>

                                        <div >
                                            <span><label> <spring:message code="text.addoffer.image" /></label></span>
                                            <!--<div class="grid images_3_of_2" style="direction: <spring:message code="preview.css.dir"  />">-->
                                            <!--<div id="container">-->
                                            <!--<div id="products_example">-->
                                            <!--<div id="products">-->
                                            <img style="border: 1.1px solid #2969b0;
                                                 border-bottom: none;
                                                 width: 274px;
                                                 height: 164px;" src="${pageContext.request.contextPath}${offer.imageUrl}" alt=" " />                                                        
                                            <!--</div>-->
                                            <!--</div>-->
                                            <!--</div>-->
                                            <!--</div>-->
                                            <span><input type="file" name="file"  class="form-control"   /></span>
                                        </div>

                                        <div>


                                            <span><label><spring:message code="text.addoffer.description" /></label></span> <span><textarea    name="description" class="form-control"  pattern="[A-Za-z]{10,100}" title="<spring:message code="validate.description" />" required >${offer.description}</textarea></span>


                                        </div>

                                        <div >

                                            <input type="submit" class="form-control" value="<spring:message code="update.text.update" />" id="add" /><br/>
                                        </div>
                                    </div>

                                </form>

                            </div>
                        </div>

                    </div>		
                </div> 
            </div>
        </div>



    </body>
</html>

<jsp:include  page="footer/footer.jsp"/>
</body>
