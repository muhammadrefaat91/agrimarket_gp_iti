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
        <!DOCTYPE html>
    <!--
    <style>
    input[type=text], select {
        width: 100%;
        padding: 12px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }
    
    input[type=submit] {
        width: 100%;
        background-color: #4CAF50;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
    
    input[type=submit]:hover {
        background-color: #45a049;
    }
    
    div {
        border-radius: 5px;
        background-color: #f2f2f2;
        padding: 20px;
    }
    </style>
    -->








</head>
<!--style="background-image: url(resources/images/istock-000016896298xlarge-4200x2833-56.jpg);"-->
<body  >

        <div class="wrap">

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
                        <h2>add offer</h2>


                        <form   method="post" enctype="multipart/form-data" action="addoffer" >

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




                            <!--
                                                                    <div >
                                                                        <label>unit of price: </label>
                                                                        <input   id="quantity" type="number" class="textbox"  name="unitprice" required />
                            
                                                                    </div>-->

                            <div >
                                
                                <span><label>Quantity: </label></span>
                                <span> <input   id="quantity" type="number" class="textbox"  name="quantity" required /></span>

     
                                
                                <span><label>Quantity Unit :</label></span> 
                                <span>
                                    <select name="quantityunit">
                                        <c:forEach var="item" items="${units}">
                                            <option value="${item.id}">${item.nameAr}</option>
                                        </c:forEach>
                                    </select>
                                </span>
                    

 
                                
                                
                                
                       <div>
                                                      <span> <label>Price: </label></span>
                                <span> <input   id="quantity" type="number" class="textbox"  name="price" required /></span>

                           
                           
                           
                                <span><label>per </label></span>
                                <span>
                                    <select name="unitprice">
                                        <c:forEach var="item" items="${units}">
                                            <option class="textbox" value="${item.id}">${item.nameEn}</option>
                                        </c:forEach>
                                    </select>
                                </span>

                            </div>


   
                            <div >

                                <span><label>Telephone: </label></span><span><input type="tel"   name="mobile"  class="textbox" /></span>


                            </div>




                            <div >

                                <span> <label>Governerate: </label></span><span><input    name="governerate" class="textbox" /></span>



                            </div>


                            <!--
                                                                    <div >
                                                                        <label>product id: </label>
                                                                        <input   id="quantity" type="number" class="form-control"  name="product" required />
                            
                                                                    </div>-->



                            <div >
                                <span><label> Image :</label></span><span><input type="file" name="file"  class="textbox" /></span>
                            </div>

                            
                            
                            
                            <div >


                                <span><label>Description :</label></span> <span><textarea    name="description" class="textbox"  ></textarea></span>


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
