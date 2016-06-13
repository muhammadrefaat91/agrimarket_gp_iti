<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML>
<head>
    <title><spring:message code="link.nav.about" /></title>
    
    <link rel="icon" href="<spring:url value="/resources/images/agri_logo.png" />">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet" />

    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script> 
    <script type="text/javascript" src="js/move-top.js"></script>
    <script type="text/javascript" src="js/easing.js"></script>
    <style type="text/css">
        .search_box form input[type="submit"]{
                margin-top: <spring:message code="search.icon.margin-top" />px;
        }
    </style>
</head>
<body>
    <div class="wrap">
        <jsp:include page="header/headertop_desc.jsp" />
        <div class="call" style="    margin-top: -34px;">
            <p> <spring:message code="text.lang" /> : <a href="?lang=en"><spring:message code="text.lang.english" /></a>|<a href="?lang=ar_EG"><spring:message code="text.lang.arbic" /></a></p>
        </div>
        <!---include header top -->
        <jsp:include page="header/header_top.jsp" />
        <jsp:include page="header/header_bottom_nav.jsp" />
        <div class="main">
            <div class="content" style="text-align: <spring:message code="about.css.content.text-align" />">
                <div class="section group">
                    <div class="col_1_of_3 span_1_of_3">
                        <h3><spring:message code="title.about.whoweare" /> </h3>
                        <img src="<spring:url value="/resources/images/agri_logo_s.png" />" alt="">
                    </div>
                    <div class="col_1_of_3 span_1_of_3">
                        
                        <p><spring:message code="paragraph.about.who1" /> </p>
                        <p><spring:message code="paragraph.about.who2" /> </p>
                    </div>
                    <div class="col_1_of_3 span_1_of_3">
                        
                        <h3><spring:message code="title.about.vision" /></h3>
                        <div class="history-desc">
                            <p class="history"><spring:message code="paragraph.about.vision" /></p>
                            <div class="clear"></div>
                        </div>
                        <h3><spring:message code="title.about.mission" /></h3>
                        <div class="history-desc">
                            <p class="history"><spring:message code="paragraph.about.mission" /></h3></p>
                            <div class="clear"></div>
                        </div>
                        <h3><spring:message code="title.about.team" /></h3>
                        <p><spring:message code="paragraph.about.team" /></p>

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

