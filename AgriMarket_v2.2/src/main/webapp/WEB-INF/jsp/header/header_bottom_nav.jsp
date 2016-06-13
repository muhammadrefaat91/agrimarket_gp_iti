<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>

<!--<link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet" />-->
<!--<link href="<spring:url value="/resources/css/slider.css" />" rel="stylesheet" />-->

<script src="<spring:url value="/resources/js/jquery.min.js" />"></script>
<script>
    $(document).ready(function () {
        if (window.location.pathname == "${pageContext.request.contextPath}/contact.htm")
            $("#contact").attr("class", "active");
        else if (window.location.pathname == "${pageContext.request.contextPath}/about.htm")
            $("#about").attr("class", "active");
        else if (window.location.pathname == "${pageContext.request.contextPath}/index.htm")
            $("#home").attr("class", "active");
        else if (window.location.pathname == "${pageContext.request.contextPath}/web/getoffers.htm")
            $("#products").attr("class", "active");
    });
</script>
<script type="text/javascript" src="<spring:url value="/resources/js/jquery-1.7.2.min.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resources/js/jquery.cookie.js" />"></script>
    <script type="text/javascript">
        console.log("nav"+$.cookie("myAppLocaleCookie"));
        var lang = $.cookie("myAppLocaleCookie");
        if (lang === 'ar_EG') {
            $('li:first').appendTo('ul');

            $(document).ready(function () {
                $.fn.exchangePositionWith = function (selector) {
                    var other = $(selector);
                    this.after(other.clone());
                    other.after(this).remove();
                };
                $(".menu ul li:eq(0)").exchangePositionWith(".menu ul li:eq(3)");
                $(".menu ul li:eq(1)").exchangePositionWith(".menu ul li:eq(2)");
            });
        }
    </script>
<div class="header_bottom">
    <div class="menu" style="float:<spring:message code="header-nav.css.div.menu.float" /> ;">
        <ul>
            <li id="home" name="home"><a href="${pageContext.request.contextPath}/index.htm"><spring:message code="link.home" /></a></li>
            <li id="products" name="products" ><a id="product" href="${pageContext.request.contextPath}/web/getoffers.htm"><spring:message code="link.nav.products" /></a></li>
            <li id="about" name="about" ><a href="${pageContext.request.contextPath}/about.htm"><spring:message code="link.nav.about" /></a></li>
            <li id="contact" name="contact" ><a href="${pageContext.request.contextPath}/contact.htm"><spring:message code="link.nav.contact" /></a></li>
            <!--<div class="clear"></div>-->
        </ul>
    </div>
    <div class="search_box" style="float: <spring:message code="header-nav.css.div.search-box.float" />;
         margin-left: <spring:message code="header-nav.css.div.search-box.margin-left" />px;">
        <form action="${pageContext.request.contextPath}/web/getoffers.htm" method="get">
            <input style="margin-left:<spring:message code="header-nav.css.div.search-box.input-text,margin-left" />px;" type="text" value="${param.name}" name="name" placeholder="<spring:message code="input.header_bottom.searchtext" />">
            <input type="submit" value=""  >
            <!--style= margin-top:<spring:message code="header-nav.css.div.search-box.input-text,margin-top" />px;-->
        </form>
    </div>
    <div class="clear"></div>
</div>
