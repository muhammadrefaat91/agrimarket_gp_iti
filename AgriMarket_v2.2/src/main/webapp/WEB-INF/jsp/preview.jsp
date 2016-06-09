


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<head>
    <title>Free Home Shoppe Website Template | Preview :: w3layouts</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script> 
    <script type="text/javascript" src="js/move-top.js"></script>
    <script type="text/javascript" src="js/easing.js"></script>
    <script src="js/easyResponsiveTabs.js" type="text/javascript"></script>
    <link href="css/easy-responsive-tabs.css" rel="stylesheet" type="text/css" media="all"/>
    <link rel="stylesheet" href="css/global.css">
    <script src="js/slides.min.jquery.js"></script>
    <!--    <script>
            $(function () {
                $('#products').slides({
                    preload: true,
                    preloadImage: 'img/loading.gif',
                    effect: 'slide, fade',
                    crossfade: true,
                    slideSpeed: 350,
                    fadeSpeed: 500,
                    generateNextPrev: true,
                    generatePagination: false
                });
            });
        </script>-->
</head>
<body>
    <div class="wrap">
        <jsp:include page="header/headertop_desc.jsp" />
        <jsp:include page="header/header_bottom_nav.jsp" />
    
    <c:if test="${empty offerProduct}" >
        <c:redirect url="/web/getOffer?id=${param['id']}" />
    </c:if>
<div class="main">
    <div class="content">
        <div class="section group">
            <div class="cont-desc span_1_of_2">
                <div class="product-details">				
                    <div class="grid images_3_of_2">
                        <div id="container">
                            <div id="products_example">
                                <div id="products">
                                    <img style="border: 1.1px solid #2969b0;
                                    border-bottom: none; width: 250px;height: 250px;" src="${pageContext.request.contextPath}${offerProduct.imageUrl}" alt=" " />                                                        
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="desc span_3_of_2">
                        <h2>${offerProduct.product.nameEn}</h2>
                        <p>${offerProduct.description}</p>					
                        <div class="price">
                            <p>Quantity: <span>${offerProduct.quantity} ${offerProduct.unitByUnitId.nameEn}</span></p>
                            <p>Price: <span>${offerProduct.price} L.E/${offerProduct.unitByUnitId.nameEn}</span></p>
                        </div>
                        <div class="available">
                            <p>User Name: <a href="${pageContext.request.contextPath}/web/user.htm?id=${offerProduct.user.id}"><span>${offerProduct.user.fullName}</span></a></p>
                            <p>Location: <span>${offerProduct.userLocation}</span></p>
                        </div>
                        <!--				<div class="share-desc">
                                                                <div class="share">
                                                                        <p>Share Product :</p>
                                                                        <ul>
                                                                        <li><a href="#"><img src="images/facebook.png" alt="" /></a></li>
                                                                        <li><a href="#"><img src="images/twitter.png" alt="" /></a></li>					    
                                                                </ul>
                                                                </div>
                                                                <div class="button"><span><a href="#">Add to Cart</a></span></div>					
                                                                <div class="clear"></div>
                                                        </div>-->
<!--                        <div class="wish-list">
                            <p>Rate: <span>starts</span></p>
                        </div>-->
                    </div>
                    <div class="clear"></div>
                </div>
                        </div>  
<!--                		<div class="product_desc">	
                                        <div id="horizontalTab">
                                                <ul class="resp-tabs-list">
                                                        <li>Product Details</li>
                                                        <li>product Tags</li>
                                                        <li>Product Reviews</li>
                                                        <div class="clear"></div>
                                                </ul>
                                                <div class="resp-tabs-container">
                                                        <div class="product-desc">
                                                                <p>Lorem Ipsum is simply dummy text of the <span>printing and typesetting industry</span>. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.</p>
                                                                <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, <span>when an unknown printer took a galley of type and scrambled</span> it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.<span> It has survived not only five centuries</span>, but also the leap into electronic typesetting, remaining essentially unchanged.</p>
                                                                <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.</p>					</div>
                
                                                 <div class="product-tags">
                                                                 <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.</p>
                                                        <h4>Add Your Tags:</h4>
                                                        <div class="input-box">
                                                                <input type="text" value="">
                                                        </div>
                                                        <div class="button"><span><a href="#">Add Tags</a></span></div>
                                            </div>	
                
                                                <div class="review">
                                                        <h4>Lorem ipsum Review by <a href="#">Finibus Bonorum</a></h4>
                                                         <ul>
                                                                <li>Price :<a href="#"><img src="images/price-rating.png" alt="" /></a></li>
                                                                <li>Value :<a href="#"><img src="images/value-rating.png" alt="" /></a></li>
                                                                <li>Quality :<a href="#"><img src="images/quality-rating.png" alt="" /></a></li>
                                                         </ul>
                                                         <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.</p>
                                                  <div class="your-review">
                                                         <h3>How Do You Rate This Product?</h3>
                                                          <p>Write Your Own Review?</p>
                                                          <form>
                                                                <div>
                                                                        <span><label>Nickname<span class="red">*</span></label></span>
                                                                        <span><input type="text" value=""></span>
                                                                    </div>
                                                                    <div><span><label>Summary of Your Review<span class="red">*</span></label></span>
                                                                        <span><input type="text" value=""></span>
                                                                    </div>						
                                                                    <div>
                                                                        <span><label>Review<span class="red">*</span></label></span>
                                                                        <span><textarea> </textarea></span>
                                                                    </div>
                                                                   <div>
                                                                                <span><input type="submit" value="SUBMIT REVIEW"></span>
                                                                  </div>
                                                            </form>
                                                         </div>				
                                                </div>
                                        </div>
                                 </div>
                         </div>-->
                <script type="text/javascript">
                    $(document).ready(function () {
                        $('#horizontalTab').easyResponsiveTabs({
                            type: 'default', //Types: default, vertical, accordion           
                            width: 'auto', //auto or any width like 600px
                            fit: true   // 100% fit in a container
                        });
                    });
                </script>		
            </div>
        </div>
    </div>
</div>
</div>
<jsp:include page="footer/footer.jsp"/>
<script type="text/javascript">
    $(document).ready(function () {
        $().UItoTop({easingType: 'easeOutQuart'});

    });
</script>
<a href="#" id="toTop"><span id="toTopHover"> </span></a>
</body>
</html>

