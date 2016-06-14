<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>   
<div class="footer">
    <div class="wrap">	
        <div class="section group">
            <div class="col_1_of_4 span_1_of_4">
                <h4><spring:message code="text.footer.information" /></h4>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/about.htm"><spring:message code="link.footer.aboutus" /></a></li>
                    <li><a href="${pageContext.request.contextPath}/contact.htm"><spring:message code="link.footer.contactus" /></a></li>
                </ul>
            </div>
            <div class="col_1_of_4 span_1_of_4">
                <h4><spring:message code="text.footer.followus" /></h4>
                <ul>
                    <!--<li><a href="#">Privacy Policy</a></li>-->
                    <li><a href="${pageContext.request.contextPath}/contact.htm"><spring:message code="link.footer.sitemap" /></a></li>
                </ul>
                <div class="social-icons">
                    <!--<h4>Follow Us</h4>-->
                    <ul>
                        <li><a href="#" target="_blank"><img src="${pageContext.request.contextPath}/resources/images/facebook.png" alt="" /></a></li>
                        <li><a href="#" target="_blank"><img src="${pageContext.request.contextPath}/resources/images/twitter.png" alt="" /></a></li>
<!--                        <li><a href="#" target="_blank"><img src="${pageContext.request.contextPath}/resources/images/skype.png" alt="" /> </a></li>
                        <li><a href="#" target="_blank"> <img src="${pageContext.request.contextPath}/resources/images/dribbble.png" alt="" /></a></li>
                        <li><a href="#" target="_blank"> <img src="${pageContext.request.contextPath}/resources/images/linkedin.png" alt="" /></a></li>-->
                        <div class="clear"></div>
                    </ul>
                </div>

            </div>
            <div class="col_1_of_4 span_1_of_4">
                <h4><spring:message code="text.footer.myaccount" /></h4>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/signup.htm"><spring:message code="link.footer.signin" /></a></li>
                    <!--							<li><a href="#">Track My Order</a></li>-->
                    <li><a href="${pageContext.request.contextPath}/contact.htm"><spring:message code="link.footer.help" /></a></li>
                </ul>
            </div>
            <div class="col_1_of_4 span_1_of_4">
                <h4><spring:message code="text.footer.contact" /></h4>
                <ul>
                    <li><span><a href="tel:+201113578260" data-rel=""  title="+201113578260">+20-11-135-78260</a></span></li>
                    <li><a href="tel:+20235355538" data-rel=""  title="tel:+20235355538"><span>+202-353-55538</span></a></li>
                </ul>
                <!--						<div class="social-icons">
                                    <h4>Follow Us</h4>
                                    <ul>
                                                                              <li><a href="#" target="_blank"><img src="${pageContext.request.contextPath}/resources/images/facebook.png" alt="" /></a></li>
                                                                              <li><a href="#" target="_blank"><img src="${pageContext.request.contextPath}/resources/images/twitter.png" alt="" /></a></li>
                                                                              <li><a href="#" target="_blank"><img src="${pageContext.request.contextPath}/resources/images/skype.png" alt="" /> </a></li>
                                                                              <li><a href="#" target="_blank"> <img src="${pageContext.request.contextPath}/resources/images/dribbble.png" alt="" /></a></li>
                                                                              <li><a href="#" target="_blank"> <img src="${pageContext.request.contextPath}/resources/images/linkedin.png" alt="" /></a></li>
                                        <div class="clear"></div>
                                    </ul>
                                                                </div>-->
            </div>

        </div>			
    </div>
    <div class="copy_right">
        <!--<p>Company Name © All rights Reseverd | Design by  <a href="http://w3layouts.com">W3Layouts</a> </p>-->
    </div>
</div>