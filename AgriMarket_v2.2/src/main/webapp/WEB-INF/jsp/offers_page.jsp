
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>

<!DOCTYPE HTML >
<html lang="ar_EG">
    <head>
        <title><spring:message code="title.offer_page" /></title>

        <link rel="icon" href="<spring:url value="/resources/images/agri_logo.png" />">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet">
        <link href="<spring:url value="/resources/css/slider.css" />" rel="stylesheet">
        <link href="<spring:url value="/resources/css/main.css" />" rel="stylesheet">
        <script src="<spring:url value="/resources/js/jquery.min.js" />"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">


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
        <script type="text/javascript" src="<spring:url value="/resources/js/jquery-1.7.2.min.js" />"></script>
        <script type="text/javascript" src="<spring:url value="/resources/js/jquery.cookie.js" />"></script>

    </head>
    <body>



        <div class="wrap">
            <!-- header--->
            <jsp:include page="header/headertop_desc.jsp" />
            <div class="call" style="    margin-top: -34px;">
                <p style="direction: <spring:message code="addoffer.css.contactform.dir" />;"> <spring:message code="text.lang" /> : <a class="lang" href="?name=${param['name']}&lang=en"><spring:message code="text.lang.english" /></a>|<a class="lang" href="?name=${param['name']}&lang=ar_EG"><spring:message code="text.lang.arbic" /></a></p>
            </div>
           
            <!---include header top -->
            <jsp:include page="header/header_top.jsp" />
            <jsp:include page="header/header_bottom_nav.jsp" />

            <!-- content--->
            <div class="main">
                <div class="content">
                    <div class="content_top" dir="<spring:message code="offer_page.css.content_top.dir" />">
                        <!-- search!-->

                        <div class="heading" style="float: <spring:message code="offer_page.css.heading.float" />;">

                            <div class="bs-example">

                                <div>
                                    <form action="${pageContext.request.contextPath}/web/sort.htm" method="get">
                                        <label><spring:message code="text.offer_page.sortby" /></label>
                                        <select value="${param.sortType}"  class="pcategory"   id="sort" name="sortType"  onchange="this.form.submit()" >
                                            <option ><spring:message code="dropdown.offer_page.relevance" /></option>
                                            <option  ><spring:message code="dropdown.offer_page.newest" /></option>
                                            <option ><spring:message code="dropdown.offer_page.price" /></option>
                                            <option  ><spring:message code="dropdown.offer_page.quantity" /></option>
                                        </select>
                                        <input type="hidden" value="${param.name}"  name="name"/>
                                    </form>
                                </div>
                                <!--end drop down for search!-->
                            </div>
                        </div>

                        <div >

                            <div class="add-cart" style="float: <spring:message code="offer_page.css.add-cart.float" />;">								
                                <h4><a href="<spring:url value="/web/addoffer.htm"/>"><spring:message code="link.addoffer" /></a></h4>
                            </div>
                            <div class="clear"></div>
                        </div>  
                        <%-- end list--%>
                        <div class="see">

                        </div>
                        <div class="clear"></div>
                    </div>
                    <!--view all offers -->
                    <div class="section group" style="  width: 106%;margin-left: -38px;
                         margin-left: <spring:message code="offer-page.css.margin" />px;  ">
                        <c:forEach items="${getAllOfferProducts}" var="offer">
                            <div class="grid_1_of_4 images_1_of_4" style="margin-left: <spring:message code="offer-page.css.padding.product.margin-left" />px;margin-right: 29px;float: <spring:message code="offer_page.css.heading.float" />;">
                                <a href="preview.htm?id=${offer.id}">
                                    <img  
                                        style="border: 1.1px solid #2969b0;
                                        border-bottom: none; width: 220px; height: 150px;"  src="${pageContext.request.contextPath}${offer.imageUrl}" /></a>

                                <h2>${requestScope.lang eq 'en'?offer.product.nameEn:offer.product.nameAr} </h2>

                                <div class="price-details">
                                    <div class="price-number">
                                        <p><span class="rupees">${offer.price}  <spring:message code="preview.money"  /></span></p>
                                    </div>
                                    <div class="add-cart">								
                                        <h4><a href="preview.htm?id=${offer.id}"><spring:message code="link.More.details" /></a></h4>
                                    </div>
                                    <div class="clear"></div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

        <!--Footer--->
        <jsp:include  page="footer/footer.jsp" />

        <a href="#" id="toTop"><span id="toTopHover"> </span></a>
        <script  src="<spring:url value="/resources/js/jquery.min.js" />" ></script>
        <script  src="<spring:url value="/resources/js/bootstrap.min.js" />" ></script>


    </body>

</html>


