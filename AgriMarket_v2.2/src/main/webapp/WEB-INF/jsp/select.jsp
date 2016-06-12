<%-- 
    Document   : select
    Created on : Jun 8, 2016, 1:09:03 PM
    Author     : muhammad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
    <link rel="icon" href="<spring:url value="/resources/images/agri_logo.png" />">
        <script type="text/javascript">

onunload = function()
{
	var foo = document.getElementById('foo');
	self.name = 'fooidx' + foo.selectedIndex;
}

onload = function()
{
	var idx, foo = document.getElementById('foo');
	foo.selectedIndex = (idx = self.name.split('fooidx')) ?	idx[1] : 0;
}

</script>
</head>
<body>
<form>
<select id="foo" onchange=" this.form.submit()"><!-- thank you, <font color='teal'>stereofrog</font> -->
<option value="" selected="selected">choose</option>
<option value=""></option>
<option value="something">blah 1</option>
<option value="something">blah 2</option>
<option value="something">blah 3</option>
<option value="something">blah 4</option>
<option value="something">blah 5</option>
</select>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
