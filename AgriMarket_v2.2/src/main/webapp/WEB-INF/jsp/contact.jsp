<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML>
<head>
    <title><spring:message code="title.contact" /></title>
    
    <link rel="icon" href="<spring:url value="/resources/images/agri_logo.png" />">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
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
            <p style="direction: <spring:message code="addoffer.css.contactform.dir" />;"> <spring:message code="text.lang" /> : <a href="?lang=en"><spring:message code="text.lang.english" /></a>|<a href="?lang=ar_EG"><spring:message code="text.lang.arbic" /></a></p>
        </div>
        <!---include header top -->
        <jsp:include page="header/header_top.jsp" />
        <jsp:include page="header/header_bottom_nav.jsp" />
        <div class="main">
            <div class="content">
                <div class="section group">
                    <div class="col span_2_of_3" style="direction: <spring:message code="contact.css.col span_2_of_3.dir" />;
                         float:<spring:message code="contact.css.col span_2_of_3.float" />; ">
                        <div class="contact-form">
                            <h2><spring:message code="title.contact" /></h2>
                            <br><br>
                            <ul>
                                <li><spring:message code="label.contact.mobile" /><span><a href="tel:+201113578260" data-rel=""  title="+201113578260">+20-11-135-78260</a></span></li>
                                <li><spring:message code="label.contact.fax" /><a href="tel:+20235355538" data-rel=""  title="tel:+20235355538"><span>+202-353-55538</span></a></li>
                                <li><spring:message code="label.contact.mail" /><span><a href="mailto:israakilany@gmail.com?subject=Support" data-rel=""  title="mail">support@agrimarket.com</a></span></li>
                                
                            </ul>
                        </div>
                    </div>
                    <div class="col span_1_of_3">
                        <div class="contact_info" style="text-align:<spring:message code="contact.css.contact_info.text-align" />; ">
                            <h3><spring:message code="text.contact.findushere" /></h3>
                            <div class="map">
                                <iframe width="100%" height="175" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.co.in/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=Lighthouse+Point,+FL,+United+States&amp;aq=4&amp;oq=light&amp;sll=26.275636,-80.087265&amp;sspn=0.04941,0.104628&amp;ie=UTF8&amp;hq=&amp;hnear=Lighthouse+Point,+Broward,+Florida,+United+States&amp;t=m&amp;z=14&amp;ll=26.275636,-80.087265&amp;output=embed"></iframe><br><small><a href="https://maps.google.co.in/maps?f=q&amp;source=embed&amp;hl=en&amp;geocode=&amp;q=Lighthouse+Point,+FL,+United+States&amp;aq=4&amp;oq=light&amp;sll=26.275636,-80.087265&amp;sspn=0.04941,0.104628&amp;ie=UTF8&amp;hq=&amp;hnear=Lighthouse+Point,+Broward,+Florida,+United+States&amp;t=m&amp;z=14&amp;ll=26.275636,-80.087265" style="color:#666;text-align:left;font-size:12px"><spring:message code="link.contact.viewlargermap" /></a></small>
                            </div>
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

