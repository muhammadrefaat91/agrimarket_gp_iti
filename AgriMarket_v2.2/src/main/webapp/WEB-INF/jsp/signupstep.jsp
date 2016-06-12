<%-- 
    Document   : signupstep
    Created on : Jun 11, 2016, 9:14:56 PM
    Author     : Amr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
       <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/css/style.css">
        <link rel="stylesheet" href="resources/css/mbr-additional.css" type="text/css">

      
    </head>
    <body>

        <div class="wrap">
            <div class="main">
                <div class="content">
                    <div class="section group">
                        <div class="col span_2_of_3">
                            <div class="contact-form">
                                <h2>sign up Continue</h2>



                                <div class="col-sm-8 col-sm-offset-2">
                                    <form   method="post" enctype="multipart/form-data" action="signupgplusstep2" >
                                        <div align="center">
                                            <%--<spring:message code="name" />--%>


                                            <div class="form-group">

                                                <label><spring:message code="text.addoffer.mobile" />  Mobile : </label><input type="tel"   name="mobile"  class="form-control" />


                                            </div>
                                            <div class="form-group">

                                                <label><spring:message code="text.addoffer.governerate" /> Governorate : </label><input type="text"   name="governerate" class="form-control"/>

                                            </div>

                                            <div class="form-group">
                                                <tr><td><spring:message code="text.addoffer.image" /></td><td><input type="file" name="file"  class="form-control" /></td></tr>
                                            </div>



                                            <div class="form-group">

                                                <input type="submit"  /><br/>
                                            </div>

                                        </div>

                                </div>

                                </form>

                            </div>
                        </div>

                    </div>		
                </div> 
            </div>
        </div>


    </div>-->
</body>
</html>
