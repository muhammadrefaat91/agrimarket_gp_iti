<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" >

        <link rel="icon" href="<spring:url value="/resources/images/agri_logo.png" />">
        <title><spring:message code="title.addoffer.addproduct" /></title>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <!--<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>-->
    </head>
    <body>
        <div class="wrap">
            <div class="fb-like" data-href="https://www.facebook.com/ChatAppJits/" data-width="200" data-layout="button_count" data-action="like" data-show-faces="true" data-share="true"></div>
            <!---include header description -->
            <jsp:include page="../header/headertop_desc.jsp" />
            <div class="call" style="    margin-top: -34px;">
                <p> <spring:message code="text.lang" /> : <a href="?lang=en"><spring:message code="text.lang.english" /></a>|<a href="?lang=ar_EG"><spring:message code="text.lang.arbic" /></a></p>
            </div>
            <!---include nav bar -->
            <jsp:include page="../header/header_bottom_nav.jsp" />
            <div class="wrap">
                <div class="main">
                    <div class="content">
                        <div class="section group">
                            <div class="col span_2_of_3">
                                <div class="contact-form">
                                    <div class="col-sm-8 col-sm-offset-2">
                                        <h2><spring:message code="title.addoffer.addproduct" /> </h2>
                                        <form   method="post" enctype="multipart/form-data" action="addoffer" >
                                            <div >

                                                <span><label><spring:message code="text.addoffer.product" />  </label></span>
                                                <span>
                                                    <select name="product">
                                                        <c:forEach var="item" items="${products}">
                                                            <option value="${item.id}" class="textbox">${item.nameEn}</option>
                                                        </c:forEach>
                                                    </select>
                                                </span>
                                            </div>                                       
                                            <div >
                                                <span><label><spring:message code="text.preview.quantity" /></label></span>
                                                <span> <input   id="quantity" type="number" class="form-control"  name="quantity" required /></span>
                                                <span><label><spring:message code="text.addoffer.quantityunit" /></label></span> 
                                                <span>
                                                    <select name="quantityunit">
                                                        <c:forEach var="item" items="${units}">
                                                            <option value="${item.id}">${item.nameAr}</option>
                                                        </c:forEach>
                                                    </select>
                                                </span>
                                                <div>
                                                    <span> <label><spring:message code="dropdown.offer_page.price" /> </label></span>
                                                    <span> <input   id="quantity" type="number" class="form-control"  name="price" required /></span>
                                                    <span><label><spring:message code="text.addoffer.css.per" /> </label></span>
                                                    <span>
                                                        <select name="unitprice">
                                                            <c:forEach var="item" items="${units}">
                                                                <option class="textbox" value="${item.id}">${item.nameEn}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </span>
                                                </div>
                                                <div >
                                                    <span><label><spring:message code="text.addoffer.mobile" />  </label></span><span><input type="tel"   name="mobile"  class="form-control" /></span>
                                                </div>
                                                <div >
                                                    <span> <label><spring:message code="text.addoffer.governerate" />  </label></span><span><input    name="governerate" class="form-control" /></span>
                                                </div>
                                                <div >
                                                    <span><label><spring:message code="text.addoffer.image" />  </label></span><span><input type="file" name="file"  class="form-control" /></span>
                                                </div>
                                                <div >
                                                    <span><label><spring:message code="text.addoffer.description" /> </label></span> <span><textarea    name="description" class="form-control" ></textarea></span>
                                                </div>
                                                <div class="form-group">

                                                    <input type="submit" class="form-control" value="<spring:message code="button.addoffer.add" />" id="add" /><br/>
                                                </div>
                                            </div>
                                        </form>

                                    </div>
                                </div>

                            </div>		
                        </div> 
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>

<jsp:include  page="../footer/footer.jsp"/>
</body>