
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>

<!DOCTYPE HTML>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="<spring:url value="/resources/css/slider.css" />" rel="stylesheet">
    <link href="<spring:url value="/resources/css/main.css" />" rel="stylesheet">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">

    <script type="text/javascript" src="resources/js/jquery-1.7.2.min.js"></script> 
    <script type="text/javascript" src="resources/js/move-top.js"></script>
    <script type="text/javascript" src="resources/js/easing.js"></script>
    <script type="text/javascript" src="resources/js/startstop-slider.js"></script>
    
    <script type="text/javascript">
        onunload = function ()
        {
            var foo = document.getElementById('sort');
            self.name = 'sortidx' + foo.selectedIndex;
        }
        onload = function ()
        {
            var idx, foo = document.getElementById('sort');
            foo.selectedIndex = (idx = self.name.split('sortidx')) ? idx[1] : 0;
        }
    </script>
</head>
<body>
    <div class="wrap">
        <!-- header--->
        <jsp:include page="header/headertop_desc.jsp" />
        <jsp:include page="header/header_bottom_nav.jsp" />


        <!-- content--->
        <div class="main">
            <div class="content">
                <div class="content_top">
                    <!-- search!-->

                    <div class="heading">

                        <div class="bs-example">
             
                             <div>
<!--                                <form action="${pageContext.request.contextPath}/web/getoffers" method="get">

                                    <input maxlength="100" type="text" value="${param.searchText}" placeholder="search for products.."   name="searchText" />
                                    <input  type="submit" value="submit"/>
                                </form>-->

                                
                                <form action="${pageContext.request.contextPath}/web/sort" method="get">
                                   <label>Sort by:</label>
                                    <select value="${param.searchType}"  class="pcategory"   id="sort" name="searchType"  onchange="this.form.submit()" >
                                        <option >Relevance</option>
                                        <option value="Newest" >Newest</option>
                                        <option value="Price">Price</option>
                                        <option value="Quantity">Quantity</option>
                                    </select>
                                    <input type="hidden" value="${param.name}"  name="name"/>
                                </form>
                            </div>
                            <!--end drop down for search!-->
                        </div>
                    </div>
                    <%-- end list--%>
                    <div class="see">

                        <!--<p><a href="offers.htm">See all Products</a></p>-->
                    </div>
                    <div class="clear"></div>
                </div>
                <!--view all offers -->
                <div class="section group">

                    <%--<c:if test="${empty getAllOfferProducts}" >--%>
                        <%--<c:redirect url="/web/getoffers?name=${param['pName']}" />--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${not empty getAllOfferProducts}">--%>
                        <c:forEach items="${getAllOfferProducts}" var="offer">
                        <div class="grid_1_of_4 images_1_of_4">
                            <a href="preview.htm?id=${offer.id}">
                                <img  
                                    style="border: 1.1px solid #2969b0;
                                    border-bottom: none;"  src="${pageContext.request.contextPath}/image/${offer.imageUrl}" /></a>
                            <div class="price-details">
                                <div class="price-number">
                                    <p><span class="rupees">$${offer.price}</span></p>
                                </div>
                                <div class="add-cart">								
                                    <h4><a href="preview.htm?id=${offer.id}"><spring:message code="link.More.details" /></a></h4>
                                </div>
                                <div class="clear"></div>
                            </div>
                        </div>
                    </c:forEach>
                    <%--</c:if>--%>
                </div>
            </div>
        </div>
    </div>

    <!--Footer--->
    <jsp:include  page="footer/footer.jsp" />
    <script  src="<spring:url value="/resources/js/jquery.min.js" />" ></script>
    <script  src="<spring:url value="/resources/js/bootstrap.min.js" />" ></script>


    <a href="#" id="toTop"><span id="toTopHover"> </span></a>
    
</body>
</html>


