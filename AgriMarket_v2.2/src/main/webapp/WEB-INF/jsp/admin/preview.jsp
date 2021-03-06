

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML>
<head>
    <title>${offerProduct.product.nameEn}</title>

    <link rel="icon" href="<spring:url value="/resources/images/agri_logo.png" />">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>

</head>
<body>
    <div class="wrap">

        <jsp:include page="header/headertop_desc.jsp" />
        <div class="call" style="    margin-top: -34px;">
            <p> <spring:message code="text.lang" /> : <a href="?id=${param['id']}&lang=en"><spring:message code="text.lang.english" /></a>|<a href="?id=${param['id']}&lang=ar_EG"><spring:message code="text.lang.arbic" /></a></p>
        </div>
        <!---include header top -->
        <jsp:include page="header/header_top.jsp" />

        <jsp:include page="header/header_bottom_nav.jsp" />

        <c:if test="${empty offerProduct}" >
            <c:redirect url="/admin/getOffer.htm?id=${param['id']}" />
        </c:if>
        <div class="main" style="height: 400px;" >
            <div class="content">
                <div class="section group" style="direction: <spring:message code="preview.css.dir"  />;">
                    <div class="cont-desc span_1_of_2" style="direction: <spring:message code="preview.css.dir"  />;">
                        <div class="product-details">				
                            <div class="grid images_3_of_2" style="direction: <spring:message code="preview.css.dir"  />">
                                <div id="container">
                                    <div id="products_example">
                                        <div id="products">
                                            <img style="border: 1.1px solid #2969b0;    margin-left: 800px;
                                                 border-bottom: none; width: 250px;height: 250px;" src="${pageContext.request.contextPath}${offerProduct.imageUrl}" alt=" " />                                                        
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="desc span_3_of_2" style="direction: <spring:message code="preview.css.desc span_3_of_2.div.dir"  />">
                                <h2 style="margin-right: 45px;">${requestScope.lang eq 'en'?offerProduct.product.nameEn:offerProduct.product.nameAr}</h2>
                                <p style="    margin-right: <spring:message code="offer_page.css.p.margin.right"  />;white-space: pre-line;">${offerProduct.description}</p>					
                                <div class="price" style="    margin-left: <spring:message code="preview.css.div.price.margin"/>;">
                                    <spring:message code="text.preview.quantity"  />
                                    <p style="font-size: 0.6125em; direction: <spring:message code="preview.css.desc span_3_of_2.div.dir"  />"><span style="    margin-left: 56px;">${offerProduct.quantity}  ${requestScope.lang eq 'en'?offerProduct.unitByUnitId.nameEn:offerProduct.unitByUnitId.nameAr} </span></p>
                                    <spring:message code="text.preview.price" />
                                    <p style="font-size: 0.6125em; direction: <spring:message code="preview.css.desc span_3_of_2.div.dir"  />"><span style="    margin-left: 26px;">${offerProduct.price}  <spring:message code="preview.money"  />/ ${requestScope.lang eq 'en'?offerProduct.unitByUnitId.nameEn:offerProduct.unitByUnitId.nameAr}</span></p>
                                    <spring:message code="text.preview.date" />

                                    <p style="font-size: 0.6125em; direction: <spring:message code="preview.css.desc span_3_of_2.div.dir"  />">
                                        <span style="    margin-left: 26px;">${offerProduct.startDate}</span>
                                    </p>
                                </div>
                                <div class="available">
                                    <p><spring:message code="text.preview.name" /> <span>${offerProduct.user.fullName}</span></p>
                                    <p><spring:message code="text.preview.location" /> <span>${offerProduct.userLocation}</span></p>
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

