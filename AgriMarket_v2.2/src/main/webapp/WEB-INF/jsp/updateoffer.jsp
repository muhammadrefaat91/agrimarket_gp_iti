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



        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
        <link rel="stylesheet" href="style.css" />
        <title>jQuery Example</title>
        <!DOCTYPE html>
   
</head>
<!--style="background-image: url(resources/images/istock-000016896298xlarge-4200x2833-56.jpg);"-->
<body  >

    <div class="fb-like" data-href="https://www.facebook.com/ChatAppJits/" data-width="200" data-layout="button_count" data-action="like" data-show-faces="true" data-share="true"></div>

    <!---include header description -->

    <jsp:include page="header/headertop_desc.jsp" />

    <!---include header top -->
    <%--<jsp:include page="header/header_top.jsp" />--%>

    <!---include nav bar -->
    <jsp:include page="header/header_bottom_nav.jsp" />
    <div class="wrap">
        <div class="main">
            <div class="content">
                <div class="section group">
                    <div class="col span_2_of_3">
                        <div class="contact-form">
                            <h2>update offer</h2>


                            <form   method="post" enctype="multipart/form-data" action="updateoffer" >

                                <%--<spring:message code="name" />--%>


                                <div >

                                    <span><label>Product : </label></span>
                                    <span>
                                        <select name="product">
                                            <c:forEach var="item" items="${products}">
                                                <option value="${item.id}" class="textbox">${item.nameEn}</option>
                                            </c:forEach>
                                        </select>
                                    </span>
                                </div>


                                <div >

                                    <span><label>Quantity: </label></span>
                                    <span> <input   id="quantity" type="number" class="textbox"  name="quantity" required /></span>



                                    <span><label>Quantity Unit :</label></span> 
                                    <span>
                                        <select name="quantityunit">
                                            <c:forEach var="item" items="${units}">
                                                <option value="${item.id}">${item.nameEn}</option>
                                            </c:forEach>
                                        </select>
                                    </span>

                                    <div>
                                        <span> <label>Price: </label></span>
                                        <span> <input   id="quantity" type="number" class="textbox"  name="price" value="${offer.quantity}" required /></span>




                                        <span><label>per </label></span>
                                        <span>
                                            <select name="unitprice">
                                                
                                                <c:forEach var="item" items="${units}" >
                                                <option class="textbox" value="${item.id}" >${item.nameEn}</option>
                                                </c:forEach>
                                                
                                            </select>
                                        </span>

                                    </div>



                                    <div >

                                        <span><label>Telephone: </label></span><span><input type="tel"   name="mobile"  class="textbox" value="${offer.userPhone}"  /></span>


                                    </div>




                                    <div >

                                        <span> <label>Governerate: </label></span><span><input    name="governerate" class="textbox" value="${offer.userLocation}" /></span>



                                    </div>

                                    <div >
                                        <span><label> Image :</label></span><span><input type="file" name="file"  class="textbox" /></span>
                                    </div>

                                    <div >


                                        <span><label>Description :</label></span> <span><textarea    name="description" class="textbox"  >${offer.description}</textarea></span>


                                    </div>

                                    <div >

                                        <input type="submit" value="Submit" id="add" /><br/>
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
