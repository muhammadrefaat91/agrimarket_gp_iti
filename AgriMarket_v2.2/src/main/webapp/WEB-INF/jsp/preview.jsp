

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<head>
    <title>Free Home Shoppe Website Template | Preview :: w3layouts</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>

</head>
<body>
     <div class="wrap">
         
        <jsp:include page="header/headertop_desc.jsp" />
        <div class="call" style="    margin-top: -34px;">
            <p> Language : <a href="?id=${param['id']}&lang=en">English</a>|<a href="?id=${param['id']}&lang=ar_EG">عربي</a></p>
        </div>
        <jsp:include page="header/header_bottom_nav.jsp" />
    
    <c:if test="${empty offerProduct}" >
        <c:redirect url="/web/getOffer?id=${param['id']}" />
    </c:if>
        <div class="main" style="height: 400px;" >
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
                    </div>
                    <div class="clear"></div>
                </div>
                        </div>  
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

