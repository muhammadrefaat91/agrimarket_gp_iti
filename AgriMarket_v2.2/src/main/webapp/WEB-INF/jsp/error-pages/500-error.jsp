<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>
 <html>
    <head>
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />

        <link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet" />
        <title>500 Internal Server Error</title>
    </head>
    <body>
        <div class="wrap">
<div class="header">        
        <jsp:include page="../header/headertop_desc.jsp" />
<!---include nav bar -->

<jsp:include page="../header/header_top.jsp" />
<%--<jsp:include page="../header/header.jsp" />--%></div>

<div class="container">
  <!-- Jumbotron -->
  <div class="jumbotron">
    <h1><span class="glyphicon glyphicon-fire red"></span> 500 Internal Server Error</h1>
    <p class="lead">The web server is returning an internal error for <em><span id="display-domain"></span></em>.</p>
    <a href="javascript:document.location.reload(true);" class="btn btn-default btn-lg text-center"><span class="green">Try This Page Again</span></a>
  </div>
</div>
<div class="container">
  <div class="body-content">
    <div class="row">
      <div class="col-md-6">
        <h2>What happened?</h2>
        <p class="lead">A 500 error status implies there is a problem with the web server's software causing it to malfunction.</p>
      </div>
      <div class="col-md-6">
        <h2>What can I do?</h2>
        <p class="lead">If you're a site visitor</p>
        <p> Nothing you can do at the moment. If you need immediate assistance, please send us an email instead. We apologize for any inconvenience.</p>
        <p class="lead">If you're the site owner</p>
         <p>This error can only be fixed by server admins, please contact your website provider.</p>
     </div>
    </div>
  </div>
</div>
 </div>
    <jsp:include  page="../footer/footer.jsp" />
    </body>
</html>