<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>

<link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet" />
    <link href="<spring:url value="/resources/css/slider.css" />" rel="stylesheet" />	
 
<div class="header_top">
        <div class="logo">
            <a href="${pageContext.request.contextPath}/index.htm"><img src="${pageContext.request.contextPath}/resources/images/agri_logo_vs.png" alt="" /></a>
            <a  href="${pageContext.request.contextPath}/index.htm" style="font-size:3.0em; 
                font-weight:bold;
                color:#3d9c3e; 
                text-transform:uppercase;" ><span><spring:message code="title.index.agrimarket" /> </span></a>
        </div>

        <script type="text/javascript">
            function DropDown(el) {
                this.dd = el;
                this.initEvents();
            }
            DropDown.prototype = {
                initEvents: function () {
                    var obj = this;

                    obj.dd.on('click', function (event) {
                        $(this).toggleClass('active');
                        event.stopPropagation();
                    });
                }
            }

            $(function () {

                var dd = new DropDown($('#dd'));

                $(document).click(function () {
                    // all dropdowns
                    $('.wrapper-dropdown-2').removeClass('active');
                });

            });

        </script>
        <div class="clear"></div>
    </div>