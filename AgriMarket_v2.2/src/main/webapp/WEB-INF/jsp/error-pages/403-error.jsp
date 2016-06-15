<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>
 <html>
    <head>
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />

        <link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet" />
        <title>403 Forbidden</title>
    </head>
    <body>
        <div class="wrap">
<div class="header">        
        <jsp:include page="../header/headertop_desc.jsp" />
<!---include nav bar -->

<jsp:include page="../header/header_top.jsp" />
<%--<jsp:include page="../header/header.jsp" />--%></div>

<div class="container">
<div class="jumbotron">
  <h1><i class="fa fa-ban red"></i> {{page.title}}</h1>
  <p class="lead">Sorry! You don't have access permissions for that on <em><span id="display-domain"></span></em>.</p>
    <p><a onclick=javascript:checkSite(); class="btn btn-default btn-lg green">Take Me To The Homepage</a>
      <script type="text/javascript">
          function checkSite(){
            var currentSite = window.location.hostname;
              window.location = "http://" + currentSite;
          }
      </script>
    </p>
</div>
</div>
<div class="container">
  <div class="body-content">
    <div class="row">
      <div class="col-md-6">
        <h2>What happened?</h2>
        <p class="lead">A 403 error status indicates that you don't have permission to access the file or page. In general, web servers and websites have directories and files that are not open to the public web for security reasons.</p>
      </div>
      <div class="col-md-6">
        <h2>What can I do?</h2>
        <p class="lead">If you're a site visitor</p>
        <p>Please use your browsers back button and check that you're in the right place. If you need immediate assistance, please send us an email instead.</p>
        <p class="lead">If you're the site owner</p>
         <p>Please check that you're in the right place and get in touch with your website provider if you believe this to be an error.</p>
     </div>
    </div>
  </div>
</div>
 </div>
    <jsp:include  page="../footer/footer.jsp" />
    </body>
</html>