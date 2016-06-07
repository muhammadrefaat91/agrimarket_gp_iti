<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>


<!DOCTYPE HTML>
<head>
    <title>Mhsoln2</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="<spring:url value="/resources/css/slider.css" />" rel="stylesheet">

    <!--<link href="resources/css/style.css" rel="stylesheet" type="text/css" media="all"/>-->
    <!--<link href="resources/css/slider.css" rel="stylesheet" type="text/css" media="all"/>-->
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script> 
    <script type="text/javascript" src="js/move-top.js"></script>
    <script type="text/javascript" src="js/easing.js"></script>
    <script type="text/javascript" src="js/startstop-slider.js"></script>
</head>
<body>
    <div class="wrap">

        <!-- header--->
        <!---include header description -->
        
        <jsp:include page="header/headertop_desc.jsp" />

        <!---include header top -->
        <jsp:include page="header/header_top.jsp" />

        <!---include nav bar -->
        <jsp:include page="header/header_bottom_nav.jsp" />
        <jsp:include page="header/header.jsp" />
        

        <!-- content--->
        <div class="main">
            <div class="content">
                <div class="content_top">
                    <div class="heading">
                        <h3>New Products</h3>
                    </div>
                    <div class="see">
                        
                        <p><a href="offers.htm"><spring:message code="link.all.Products" /></a></p>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="section group">
                    <div class="grid_1_of_4 images_1_of_4">
                        <a href="preview.html"><img src="resources/images/8.gif" alt="" /></a>
                        <h2>Lorem Ipsum is simply </h2>
                        <div class="price-details">
                            <div class="price-number">
                                <p><span class="rupees">$620.87</span></p>
                            </div>
                            <div class="add-cart">								
                                <h4><a href="preview.html">Add to Cart</a></h4>
                            </div>
                            <div class="clear"></div>
                        </div>

                    </div>
                    <div class="grid_1_of_4 images_1_of_4">
                        <a href="preview.html"><img src="resources/images/7.jpg" alt="" /></a>
                        <h2>Lorem Ipsum is simply </h2>
                        <div class="price-details">
                            <div class="price-number">
                                <p><span class="rupees">$899.75</span></p>
                            </div>
                            <div class="add-cart">								
                                <h4><a href="preview.html">Add to Cart</a></h4>
                            </div>
                            <div class="clear"></div>
                        </div>

                    </div>
                    <div class="grid_1_of_4 images_1_of_4">
                        <a href="preview.html"><img src="resources/images/6.jpg" alt="" /></a>
                        <h2>Lorem Ipsum is simply </h2>
                        <div class="price-details">
                            <div class="price-number">
                                <p><span class="rupees">$599.00</span></p>
                            </div>
                            <div class="add-cart">								
                                <h4><a href="preview.html">Add to Cart</a></h4>
                            </div>
                            <div class="clear"></div>
                        </div>
                    </div>
                    <div class="grid_1_of_4 images_1_of_4">
                        <a href="preview.html"><img src="resources/images/5.jpg" alt="" /></a>
                        <h2>Lorem Ipsum is simply </h2>
                        <div class="price-details">
                            <div class="price-number">
                                <p><span class="rupees">$679.87</span></p>
                            </div>
                            <div class="add-cart">								
                                <h4><a href="preview.html">Add to Cart</a></h4>
                            </div>
                            <div class="clear"></div>
                        </div>				     
                    </div>
                </div>
                <div class="content_bottom">
                    <div class="heading">
                        <h3>Feature Products</h3>
                    </div>
                    <div class="see">
                        <p><a href="#">See all Products</a></p>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="section group">
                    <div class="grid_1_of_4 images_1_of_4">
                        <a href="preview.html"><img src="resources/images/1.JPG" alt="" /></a>					
                        <h2>Lorem Ipsum is simply </h2>
                        <div class="price-details">
                            <div class="price-number">
                                <p><span class="rupees">$849.99</span></p>
                            </div>
                            <div class="add-cart">								
                                <h4><a href="preview.html">Add to Cart</a></h4>
                            </div>
                            <div class="clear"></div>
                        </div>
                    </div>
                    <div class="grid_1_of_4 images_1_of_4">
                        <a href="preview.html"><img src="resources/images/2.jpg" alt="" /></a>
                        <h2>Lorem Ipsum is simply </h2>
                        <div class="price-details">
                            <div class="price-number">
                                <p><span class="rupees">$599.99</span></p>
                            </div>
                            <div class="add-cart">								
                                <h4><a href="preview.html">Add to Cart</a></h4>
                            </div>
                            <div class="clear"></div>
                        </div>
                    </div>
                    <div class="grid_1_of_4 images_1_of_4">
                        <a href="preview.html"><img src="resources/images/4.jpg" alt="" /></a>
                        <h2>Lorem Ipsum is simply </h2>
                        <div class="price-details">
                            <div class="price-number">
                                <p><span class="rupees">$799.99</span></p>
                            </div>
                            <div class="add-cart">								
                                <h4><a href="preview.html">Add to Cart</a></h4>
                            </div>
                            <div class="clear"></div>
                        </div>
                    </div>
                    <div class="grid_1_of_4 images_1_of_4">
                        <a href="preview.html"><img src="resources/images/3.jpg" alt="" /></a>
                        <h2>Lorem Ipsum is simply </h2>					 
                        <div class="price-details">
                            <div class="price-number">
                                <p><span class="rupees">$899.99</span></p>
                            </div>
                            <div class="add-cart">								
                                <h4><a href="preview.html">Add to Cart</a></h4>
                            </div>
                            <div class="clear"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!--Footer--->
    <jsp:include  page="footer/footer.jsp" />
    <script type="text/javascript">
        $(document).ready(function () {
            $().UItoTop({easingType: 'easeOutQuart'});

        });
    </script>
    <a href="#" id="toTop"><span id="toTopHover"> </span></a>
</body>
</html>


