
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML>
<head>
    <title>M2hsol</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="<spring:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" />
    <!--<link href="/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" media="all"/>-->

    <!--<link href="/resources/css/easy-responsive-tabs.css" rel="stylesheet" type="text/css" media="all"/>-->

    <!--<link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>-->
    <!--<link href="/resources/css/bootstrap-theme.css" rel="stylesheet" type="text/css" media="all"/>-->

    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script> 
    <script type="text/javascript" src="js/move-top.js"></script>
    <script type="text/javascript" src="js/easing.js"></script>
    <script type="text/javascript" src="js/jquery.accordion.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#posts").accordion({
                header: "div.tab",
                alwaysOpen: false,
                autoheight: false
            });
        });
    </script>
</head>
<body>
    <div class="wrap">
        <div class="header">
            <jsp:include page="header/headertop_desc.jsp" />
            <jsp:include page="header/header_bottom_nav.jsp" />
    <c:if test="${empty userHasOffer}" >
        <c:redirect url="/web/getUser?id=${param['id']}" />
    </c:if>
        </div>
        <!--profile-->

        <hr class="">
        <div class="container target">
            <div class="row">
                <div class="col-sm-2"><a href="/users" class="pull-right"><img style="height: 117px;margin-right: 85px;" title="profile image" class="img-circle img-responsive" src="${pageContext.request.contextPath}/image/${userHasOffer.imageUrl}"></a>

                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-sm-3">
                    <!--left col-->
                    <ul class="list-group">
                        <li class="list-group-item text-muted" contenteditable="false">${userHasOffer.fullName}</li>
                        <!--<li class="list-group-item text-right"><span class="pull-left"><strong class="">Joined</strong></span> 2.13.2014</li>-->
                        <li class="list-group-item text-right"><span class="pull-left"><strong class="">Mobile</strong></span>${userHasOffer.mobile}</li>
                        <li class="list-group-item text-right"><span class="pull-left"><strong class="">Mail</strong></span> ${userHasOffer.mail}</li>
<!--                        <li class="list-group-item text-right"><span class="pull-left"><strong class="">Role: </strong></span> Pet Sitter
                        </li>-->
                    </ul>
                    <div class="panel panel-default">
                        <div class="panel-heading">Insured / Bonded?

                        </div>
                        <div class="panel-body"><i style="color:green" class="fa fa-check-square"></i> Yes, I am insured and bonded.

                        </div>
                    </div>
                </div>
                <!--/col-3-->
                <div class="col-sm-9" contenteditable="false" style="margin-top: -109px;">
                    <div class="panel panel-default">
                        <div class="panel-heading">Starfox221's Bio</div>
                        <div class="panel-body"> A long description about me.

                        </div>
                    </div>
                    <div class="panel panel-default target">
                        <div class="see">
                            <p><a href="${pageContext.request.contextPath}/offers.htm">See all Products</a></p>
                        </div>
                        <div class="panel-heading" contenteditable="false">User Products</div>

                        <div class="panel-body">
                            <div class="row">
                                <c:forEach items="${userHasOffer.userOfferProductFixeds}" var="offer">
                                    <div class="col-md-4">
                                        <div class="thumbnail">
                                            <img alt="300x200" src="${pageContext.request.contextPath}/image/${offer.imageUrl}" />
                                            <div class="caption">
                                                <h3>
                                                    ${offer.price}
                                                </h3>
                                                <p>
                                                    ${offer.description}
                                                </p>
                                                <p>

                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>


                            </div>

                        </div>

                    </div>
                    </div>


                <div id="push"></div>
            </div>
        </div>
        <script src="/plugins/bootstrap-select.min.js"></script>
        <script src="/codemirror/jquery.codemirror.js"></script>
        <script src="/beautifier.js"></script>
        <script>
        (function (i, s, o, g, r, a, m) {
            i['GoogleAnalyticsObject'] = r;
            i[r] = i[r] || function () {
                (i[r].q = i[r].q || []).push(arguments)
            }, i[r].l = 1 * new Date();
            a = s.createElement(o),
                    m = s.getElementsByTagName(o)[0];
            a.async = 1;
            a.src = g;
            m.parentNode.insertBefore(a, m)
        })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');
        ga('create', 'UA-40413119-1', 'bootply.com');
        ga('send', 'pageview');
        </script>
        <script>
            jQuery.fn.shake = function (intShakes, intDistance, intDuration, foreColor) {
                this.each(function () {
                    if (foreColor && foreColor != "null") {
                        $(this).css("color", foreColor);
                    }
                    $(this).css("position", "relative");
                    for (var x = 1; x <= intShakes; x++) {
                        $(this).animate({left: (intDistance * -1)}, (((intDuration / intShakes) / 4)))
                                .animate({left: intDistance}, ((intDuration / intShakes) / 2))
                                .animate({left: 0}, (((intDuration / intShakes) / 4)));
                        $(this).css("color", "");
                    }
                });
                return this;
            };
        </script>
        <script>
            $(document).ready(function () {

                $('.tw-btn').fadeIn(3000);
                $('.alert').delay(5000).fadeOut(1500);

                $('#btnLogin').click(function () {
                    $(this).text("...");
                    $.ajax({
                        url: "/loginajax",
                        type: "post",
                        data: $('#formLogin').serialize(),
                        success: function (data) {
                            //console.log('data:'+data);
                            if (data.status == 1 && data.user) { //logged in
                                $('#menuLogin').hide();
                                $('#lblUsername').text(data.user.username);
                                $('#menuUser').show();
                                /*
                                 $('#completeLoginModal').modal('show');
                                 $('#btnYes').click(function() {
                                 window.location.href="/";
                                 });
                                 */
                            }
                            else {
                                $('#btnLogin').text("Login");
                                prependAlert("#spacer", data.error);
                                $('#btnLogin').shake(4, 6, 700, '#CC2222');
                                $('#username').focus();
                            }
                        },
                        error: function (e) {
                            $('#btnLogin').text("Login");
                            console.log('error:' + JSON.stringify(e));
                        }
                    });
                });
                $('#btnRegister').click(function () {
                    $(this).text("Wait..");
                    $.ajax({
                        url: "/signup?format=json",
                        type: "post",
                        data: $('#formRegister').serialize(),
                        success: function (data) {
                            console.log('data:' + JSON.stringify(data));
                            if (data.status == 1) {
                                $('#btnRegister').attr("disabled", "disabled");
                                $('#formRegister').text('Thanks. You can now login using the Login form.');
                            }
                            else {
                                prependAlert("#spacer", data.error);
                                $('#btnRegister').shake(4, 6, 700, '#CC2222');
                                $('#btnRegister').text("Sign Up");
                                $('#inputEmail').focus();
                            }
                        },
                        error: function (e) {
                            $('#btnRegister').text("Sign Up");
                            console.log('error:' + e);
                        }
                    });
                });

                $('.loginFirst').click(function () {
                    $('#navLogin').trigger('click');
                    return false;
                });

                $('#btnForgotPassword').on('click', function () {
                    $.ajax({
                        url: "/resetPassword",
                        type: "post",
                        data: $('#formForgotPassword').serializeObject(),
                        success: function (data) {
                            if (data.status == 1) {
                                prependAlert("#spacer", data.msg);
                                return true;
                            }
                            else {
                                prependAlert("#spacer", "Your password could not be reset.");
                                return false;
                            }
                        },
                        error: function (e) {
                            console.log('error:' + e);
                        }
                    });
                });

                $('#btnContact').click(function () {

                    $.ajax({
                        url: "/contact",
                        type: "post",
                        data: $('#formContact').serializeObject(),
                        success: function (data) {
                            if (data.status == 1) {
                                prependAlert("#spacer", "Thanks. We got your message and will get back to you shortly.");
                                $('#contactModal').modal('hide');
                                return true;
                            }
                            else {
                                prependAlert("#spacer", data.error);
                                return false;
                            }
                        },
                        error: function (e) {
                            console.log('error:' + e);
                        }
                    });
                    return false;
                });

                /*
                 $('.nav .dropdown-menu input').on('click touchstart',function(e) {
                 e.stopPropagation();
                 });
                 */





            });
            $.fn.serializeObject = function ()
            {
                var o = {};
                var a = this.serializeArray();
                $.each(a, function () {
                    if (o[this.name] !== undefined) {
                        if (!o[this.name].push) {
                            o[this.name] = [o[this.name]];
                        }
                        o[this.name].push(this.value || '');
                    } else {
                        o[this.name] = this.value || '';
                    }
                });
                return o;
            };
            var prependAlert = function (appendSelector, msg) {
                $(appendSelector).after('<div class="alert alert-info alert-block affix" id="msgBox" style="z-index:1300;margin:14px!important;">' + msg + '</div>');
                $('.alert').delay(3500).fadeOut(1000);
            }
        </script>
        <!-- Quantcast Tag -->
        <script type="text/javascript">
            var _qevents = _qevents || [];

            (function () {
                var elem = document.createElement('script');
                elem.src = (document.location.protocol == "https:" ? "https://secure" : "http://edge") + ".quantserve.com/quant.js";
                elem.async = true;
                elem.type = "text/javascript";
                var scpt = document.getElementsByTagName('script')[0];
                scpt.parentNode.insertBefore(elem, scpt);
            })();

            _qevents.push({
                qacct: "p-0cXb7ATGU9nz5"
            });
        </script>
        <noscript>
        &amp;amp;amp;amp;amp;amp;amp;lt;div style="display:none;"&amp;amp;amp;amp;amp;amp;amp;gt;
        &amp;amp;amp;amp;amp;amp;amp;lt;img src="//pixel.quantserve.com/pixel/p-0cXb7ATGU9nz5.gif" border="0" height="1" width="1" alt="Quantcast"/&amp;amp;amp;amp;amp;amp;amp;gt;
        &amp;amp;amp;amp;amp;amp;amp;lt;/div&amp;amp;amp;amp;amp;amp;amp;gt;
        </noscript>
        <!-- End Quantcast tag -->
        <div id="completeLoginModal" class="modal hide">
            <div class="modal-header">
                <a href="#" data-dismiss="modal" aria-hidden="true" class="close">�</a>
                <h3>Do you want to proceed?</h3>
            </div>
            <div class="modal-body">
                <p>This page must be refreshed to complete your login.</p>
                <p>You will lose any unsaved work once the page is refreshed.</p>
                <br><br>
                <p>Click "No" to cancel the login process.</p>
                <p>Click "Yes" to continue...</p>
            </div>
            <div class="modal-footer">
                <a href="#" id="btnYes" class="btn danger">Yes, complete login</a>
                <a href="#" data-dismiss="modal" aria-hidden="true" class="btn secondary">No</a>
            </div>
        </div>
        <div id="forgotPasswordModal" class="modal hide">
            <div class="modal-header">
                <a href="#" data-dismiss="modal" aria-hidden="true" class="close">�</a>
                <h3>Password Lookup</h3>
            </div>
            <div class="modal-body">
                <form class="form form-horizontal" id="formForgotPassword">    
                    <div class="control-group">
                        <label class="control-label" for="inputEmail">Email</label>
                        <div class="controls">
                            <input name="_csrf" id="token" type="hidden" value="CkMEALL0JBMf5KSrOvu9izzMXCXtFQ/Hs6QUY=">
                            <input type="email" name="email" id="inputEmail" placeholder="you@youremail.com" required="">
                            <span class="help-block"><small>Enter the email address you used to sign-up.</small></span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer pull-center">
                <a href="#" data-dismiss="modal" aria-hidden="true" class="btn">Cancel</a> 	
                <a href="#" data-dismiss="modal" id="btnForgotPassword" class="btn btn-success">Reset Password</a>
            </div>

        </div>
        <div id="upgradeModal" class="modal hide">
            <div class="modal-header">
                <a href="#" data-dismiss="modal" aria-hidden="true" class="close">�</a>
                <h4>Would you like to upgrade?</h4>
            </div>
            <div class="modal-body">
                <p class="text-center"><strong></strong></p>
                <h1 class="text-center">$4<small>/mo</small></h1>
                <p class="text-center"><small>Unlimited plys. Unlimited downloads. No Ads.</small></p>
                <p class="text-center"><img src="/assets/i_visa.png" width="50" alt="visa"> <img src="/assets/i_mc.png" width="50" alt="mastercard"> <img src="/assets/i_amex.png" width="50" alt="amex"> <img src="/assets/i_discover.png" width="50" alt="discover"> <img src="/assets/i_paypal.png" width="50" alt="paypal"></p>
            </div>
            <div class="modal-footer pull-center">
                <a href="/upgrade" class="btn btn-block btn-huge btn-success"><strong>Upgrade Now</strong></a>
                <a href="#" data-dismiss="modal" class="btn btn-block btn-huge">No Thanks, Maybe Later</a>
            </div>
        </div>
        <div id="contactModal" class="modal hide">
            <div class="modal-header">
                <a href="#" data-dismiss="modal" aria-hidden="true" class="close">�</a>
                <h3>Contact Us</h3>
                <p>suggestions, questions or feedback</p>
            </div>
            <div class="modal-body">
                <form class="form form-horizontal" id="formContact">
                    <input name="_csrf" id="token" type="hidden" value="CkMEALL0JBMf5KSrOvu9izzMXCXtFQ/Hs6QUY=">
                    <div class="control-group">
                        <label class="control-label" for="inputSender">Name</label>
                        <div class="controls">
                            <input type="text" name="sender" id="inputSender" class="input-large" placeholder="Your name">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="inputMessage">Message</label>
                        <div class="controls">
                            <textarea name="notes" rows="5" id="inputMessage" class="input-large" placeholder="Type your message here"></textarea>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="inputEmail">Email</label>
                        <div class="controls">
                            <input type="text" name="email" id="inputEmail" class="input-large" placeholder="you@youremail.com (for reply)" required="">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer pull-center">
                <a href="#" data-dismiss="modal" aria-hidden="true" class="btn">Cancel</a>     
                <a href="#" data-dismiss="modal" aria-hidden="true" id="btnContact" role="button" class="btn btn-success">Send</a>
            </div>
        </div>




        <script src="/plugins/bootstrap-pager.js"></script>
    </div>
    <jsp:include page="footer/footer.jsp" />
    <script type="text/javascript">
            $(document).ready(function () {
                $().UItoTop({easingType: 'easeOutQuart'});

            });
    </script>
    <a href="#" id="toTop"><span id="toTopHover"> </span></a>
</body>
</html>

