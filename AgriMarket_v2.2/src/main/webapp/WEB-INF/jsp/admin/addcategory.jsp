<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" >

        <link rel="icon" href="<spring:url value="/resources/images/agri_logo.png" />">
        <title><spring:message code="title.addoffer.addproduct" /></title>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <!--<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>-->
    </head>
    <body>
        <div class="wrap">
            <div class="fb-like" data-href="https://www.facebook.com/ChatAppJits/" data-width="200" data-layout="button_count" data-action="like" data-show-faces="true" data-share="true"></div>
            
            <!-- header--->
            <jsp:include page="header/headertop_desc.jsp" />
            <div class="call" style="    margin-top: -34px;">
                <p> <spring:message code="text.lang" /> : <a href="?name=${param['name']}&lang=en"><spring:message code="text.lang.english" /></a>|<a href="?name=${param['name']}&lang=ar_EG"><spring:message code="text.lang.arbic" /></a></p>
            </div>
            <!---include header top -->
            <jsp:include page="header/header_top.jsp" />
            <jsp:include page="header/header_bottom_nav.jsp" />
            <div class="wrap">
                <div class="main">
                    <div class="content">
                        <div class="section group">
                            <div class="col span_2_of_3">
                                <div class="contact-form">
                                    <div class="col-sm-8 col-sm-offset-2">
                                        <h2><spring:message code="title.addoffer.addproduct" /> </h2>
                                        <form   method="post" enctype="multipart/form-data" action="addcategory" >
                                                   
                                                    <span><label><spring:message code="text.add_category.name_parent" /> </label></span>
                                                    <span>
                                                        <select name="parentCategoryId">
                                                            <c:forEach var="item" items="${categories}">
                                                                <option class="textbox" value="${item.id}">${item.nameEn}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </span>
                                                </div>
                                                <div >
                                                    <span><label><spring:message code="text.add_category.name_ar" />  </label></span><span><input type="tel"   name="nameAr"  class="form-control" /></span>
                                                </div>
                                                <div >
                                                    <span> <label><spring:message code="text.add_category.name_en" />  </label></span><span><input    name="nameEn" class="form-control" /></span>
                                                </div>
                                                <div >
                                                    <span><label><spring:message code="text.add_category.image" />  </label></span><span><input type="file" name="file"  class="form-control" /></span>
                                                </div>
   
                                                    <input type="submit" class="form-control" value="<spring:message code="button.addoffer.add" />" id="add" /><br/>
                                                </div>
                                            </div>
                                        </form>

                                    </div>
                                </div>

                            </div>		
                        </div> 
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>

</body>
