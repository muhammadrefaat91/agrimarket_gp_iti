<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<!DOCTYPE html>
<!-- saved from url=(0105)https://github.com/Jahia/rating/blob/master/src/main/resources/jmix_rating/html/rating.hidden.average.jsp -->
<html lang="en" class=" is-copy-enabled is-u2f-enabled gr__github_com"><head prefix="og: http://ogp.me/ns# fb: http://ogp.me/ns/fb# object: http://ogp.me/ns/object# article: http://ogp.me/ns/article# profile: http://ogp.me/ns/profile#"><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    

    <link crossorigin="anonymous" href="./rating_rating.hidden.average.jsp at master · Jahia_rating_files/frameworks-387cfeaa25ac463275aa2c2daaedb1e54d868005181afe4a5e50659e9a19f2bc.css" integrity="sha256-OHz+qiWsRjJ1qiwtqu2x5U2GgAUYGv5KXlBlnpoZ8rw=" media="all" rel="stylesheet">
    <link crossorigin="anonymous" href="./rating_rating.hidden.average.jsp at master · Jahia_rating_files/github-ca9b58477bbdd109d33bb26e79b74b80b46b29715d2811b49daaaebbacd880f0.css" integrity="sha256-yptYR3u90QnTO7JuebdLgLRrKXFdKBG0naquu6zYgPA=" media="all" rel="stylesheet">
    
    
    
    

    <link as="script" href="./rating_rating.hidden.average.jsp at master · Jahia_rating_files/frameworks-d788212915194925ff3d2ef2cfbaec5dc1333d76cdb222ff551bc0a8c9a23172.js" rel="preload">
    
    <link as="script" href="./rating_rating.hidden.average.jsp at master · Jahia_rating_files/github-60427945fb3a23bc232ceee1b89ce28cfa14a19de48dbe75a849af968fa3fa48.js" rel="preload">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Language" content="en">
    <meta name="viewport" content="width=device-width">
    
    
    <title>rating/rating.hidden.average.jsp at master · Jahia/rating</title>
    <link rel="search" type="application/opensearchdescription+xml" href="https://github.com/opensearch.xml" title="GitHub">
    <link rel="fluid-icon" href="https://github.com/fluidicon.png" title="GitHub">
    <link rel="apple-touch-icon" href="https://github.com/apple-touch-icon.png">
    <link rel="apple-touch-icon" sizes="57x57" href="https://github.com/apple-touch-icon-57x57.png">
    <link rel="apple-touch-icon" sizes="60x60" href="https://github.com/apple-touch-icon-60x60.png">
    <link rel="apple-touch-icon" sizes="72x72" href="https://github.com/apple-touch-icon-72x72.png">
    <link rel="apple-touch-icon" sizes="76x76" href="https://github.com/apple-touch-icon-76x76.png">
    <link rel="apple-touch-icon" sizes="114x114" href="https://github.com/apple-touch-icon-114x114.png">
    <link rel="apple-touch-icon" sizes="120x120" href="https://github.com/apple-touch-icon-120x120.png">
    <link rel="apple-touch-icon" sizes="144x144" href="https://github.com/apple-touch-icon-144x144.png">
    <link rel="apple-touch-icon" sizes="152x152" href="https://github.com/apple-touch-icon-152x152.png">
    <link rel="apple-touch-icon" sizes="180x180" href="https://github.com/apple-touch-icon-180x180.png">
    <meta property="fb:app_id" content="1401488693436528">

      <meta content="https://avatars2.githubusercontent.com/u/717255?v=3&amp;s=400" name="twitter:image:src"><meta content="@github" name="twitter:site"><meta content="summary" name="twitter:card"><meta content="Jahia/rating" name="twitter:title"><meta content="Jahia rating repository" name="twitter:description">
      <meta content="https://avatars2.githubusercontent.com/u/717255?v=3&amp;s=400" property="og:image"><meta content="GitHub" property="og:site_name"><meta content="object" property="og:type"><meta content="Jahia/rating" property="og:title"><meta content="https://github.com/Jahia/rating" property="og:url"><meta content="Jahia rating repository" property="og:description">
      <meta name="browser-stats-url" content="https://api.github.com/_private/browser/stats">
    <meta name="browser-errors-url" content="https://api.github.com/_private/browser/errors">
    <link rel="assets" href="https://assets-cdn.github.com/">
    <link rel="web-socket" href="wss://live.github.com/_sockets/OTM4MjczOTpmZDU4NDE2NDc3MTQ2Y2Y0ZWVmMTNjNzM4ZDZjYjljYzo4YjExNjA3YzkyN2ExMWQ3NWE4MzcxMGY0ODY2ODUwMGVhMTNiNTgzZDdjNjc0MGE2OGZlNGFlZjRhN2MyN2Q3--cb2f2e45559c173d9fbac4bd090567b623d0c420">
    <meta name="pjax-timeout" content="1000">
    <link rel="sudo-modal" href="https://github.com/sessions/sudo_modal">

    <meta name="msapplication-TileImage" content="/windows-tile.png">
    <meta name="msapplication-TileColor" content="#ffffff">
    

    <meta name="google-site-verification" content="KT5gs8h0wvaagLKAVWq8bbeNwnZZK1r1XQysX3xurLU">
<meta name="google-site-verification" content="ZzhVyEFwb7w3e0-uOTltm8Jsck2F5StVihD0exw2fsA">
    <meta name="google-analytics" content="UA-3769691-2">

<meta content="collector.githubapp.com" name="octolytics-host"><meta content="github" name="octolytics-app-id"><meta content="D9378310:04BD:1B377D:5755A5F1" name="octolytics-dimension-request_id"><meta content="9382739" name="octolytics-actor-id"><meta content="muhammadrefaat91" name="octolytics-actor-login"><meta content="ad900d556f3a27e855fd6c84ac9f227cc7d37bd37ff9febdd74f841b6f9b3e1a" name="octolytics-actor-hash">




  <meta class="js-ga-set" name="dimension1" content="Logged In">



        <meta name="hostname" content="github.com">
    <meta name="user-login" content="muhammadrefaat91">

        <meta name="expected-hostname" content="github.com">
      <meta name="js-proxy-site-detection-payload" content="OWFlMDI3NDY1M2ZkNTQ2ZTJlM2E0ZWZiOTE5Mjc5M2UyZThlMjM4NDJhYjVhNTRlMTFkYTFlYTY4M2M1ZDc4M3x7InJlbW90ZV9hZGRyZXNzIjoiMjE3LjU1LjEzMS4xNiIsInJlcXVlc3RfaWQiOiJEOTM3ODMxMDowNEJEOjFCMzc3RDo1NzU1QTVGMSIsInRpbWVzdGFtcCI6MTQ2NTIzMDgzM30=">


      <link rel="mask-icon" href="https://assets-cdn.github.com/pinned-octocat.svg" color="#4078c0">
      <link rel="icon" type="image/x-icon" href="https://assets-cdn.github.com/favicon.ico">

    <meta name="html-safe-nonce" content="d69c58480af7a26194d8e5ab06a928eefc61f98c">
    <meta content="1618073f2f712122a762375aba1646ba2693d90d" name="form-nonce">

    <meta http-equiv="x-pjax-version" content="5d616bdfd471a893d0a60921cb5cc7cd">
    

      
  <meta name="description" content="Jahia rating repository">
  <meta name="go-import" content="github.com/Jahia/rating git https://github.com/Jahia/rating.git">

  <meta content="717255" name="octolytics-dimension-user_id"><meta content="Jahia" name="octolytics-dimension-user_login"><meta content="7939553" name="octolytics-dimension-repository_id"><meta content="Jahia/rating" name="octolytics-dimension-repository_nwo"><meta content="true" name="octolytics-dimension-repository_public"><meta content="false" name="octolytics-dimension-repository_is_fork"><meta content="7939553" name="octolytics-dimension-repository_network_root_id"><meta content="Jahia/rating" name="octolytics-dimension-repository_network_root_nwo">
  <link href="https://github.com/Jahia/rating/commits/master.atom" rel="alternate" title="Recent Commits to rating:master" type="application/atom+xml">


      
  <meta name="selected-link" value="repo_source" data-pjax-transient="true"><meta content="/&lt;user-name&gt;/&lt;repo-name&gt;/blob/show" data-pjax-transient="true" name="analytics-location"><link rel="canonical" href="https://github.com/Jahia/rating/blob/master/src/main/resources/jmix_rating/html/rating.hidden.average.jsp" data-pjax-transient="true"><link href="https://github.com/Jahia/rating/commits/master.atom" rel="alternate" title="Recent Commits to rating:master" type="application/atom+xml" data-pjax-transient="true"></head>


  <body class="logged-in   env-production linux vis-public" data-gr-c-s-loaded="true" cz-shortcut-listen="true">


<c:set var="cookieName" value="rated${currentNode.identifier}"/>
<c:choose>
    <c:when test="${renderContext.loggedIn and (empty cookie[cookieName])}">
        <jcr:nodeProperty node="${currentNode}" name="j:nbOfVotes" var="nbVotes"/>
        <jcr:nodeProperty node="${currentNode}" name="j:sumOfVotes" var="sumVotes"/>
        <c:set var="id" value="${currentNode.identifier}"/>
        <c:if test="${nbVotes.long > 0}">
            <c:set var="avg" value="${sumVotes.long / nbVotes.long}"/>
        </c:if>
        <c:if test="${nbVotes.long == 0}">
            <c:set var="avg" value="0.0"/>
        </c:if>
        <template:addResources type="css" resources="uni-form.css,ui.stars.css"/>
        <template:addResources type="javascript" resources="jquery.min.js,jquery-ui.min.js,ui.stars.js"/>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#avg${id}").children().not(":input").hide();
                $("#rat${id}").children().not("select").hide();
                // Create stars for: Average rating
                $("#avg${id}").stars();
                // Create stars for: Rate this
                $("#rat${id}").stars({
                    inputType: "select",
                    cancelShow: false,
                    captionEl: $("#caption${id}"),
                    callback: function(ui, type, value) {
                        // Disable Stars while AJAX connection is active
                        ui.disable();
                        // Display message to the user at the begining of request
                        $("#messages${id}").text("Saving...").stop().css("opacity", 1).fadeIn(30);
                        // Send request to the server using POST method
                        $.post("<c:url value='${url.base}${currentNode.path}'/>.rate.do",
                        {'j:lastVote': value,'jcrMethodToCall':"post",'jcrCookieName':"rated${currentNode.identifier}",'jcrCookieValue':"${currentNode.identifier}"},
                                function(result) {
                                    // Select stars from "Average rating" control to match the returned average rating value
                                    $("#avg${id}").stars("select",
                                            Math.round(result.j_sumOfVotes / result.j_nbOfVotes));
                                    // Update other text controls...
                                    $("#all_votes${id}").text(result.j_nbOfVotes);
                                    $("#all_avg${id}").text(('' + result.j_sumOfVotes / result.j_nbOfVotes).substring(0,
                                            3));
                                    // Display confirmation message to the user
                                    $("#messages${id}").html("<br/>Rating saved (" + value +
                                                             "). Thanks!").stop().css("opacity", 1).fadeIn(30);
                                    // Hide confirmation message and enable stars for "Rate this" control, after 2 sec...
                                    setTimeout(function() {
                                        $("#messages${id}").fadeOut(1000, function() {
                                            ui.enable();
                                        });
                                    }, 2000);
                                }, "json");
                    }
                });
                // Since the <option value="3"> was selected by default, we must remove selection from Stars.
                $("#rat${id}").stars("selectID", -1);
                // Create element to use for confirmation messages
                $('<div id="messages${id}"/>').appendTo("#rat${id}");
            });
        </script>

        <div class="ratings">

            <div class="rating-L"><strong><fmt:message key="label.AverageRating"/></strong>
        <span>(<span id="all_votes${id}">${nbVotes.long}</span> votes; <span
                id="all_avg${id}">${fn:substring(avg,0,3)}</span>)</span>

                <form id="avg${id}" style="width: 200px">


                    <input type="radio" name="rate_avg" value="1" title="Poor"
                           disabled="disabled"
                           <c:if test="${avg > 1.0}">checked="checked"</c:if> />
                    <input type="radio" name="rate_avg" value="2" title="Fair"
                           disabled="disabled"
                           <c:if test="${avg > 2.0}">checked="checked"</c:if> />
                    <input type="radio" name="rate_avg" value="3" title="Average"
                           disabled="disabled"
                           <c:if test="${avg > 3.0}">checked="checked"</c:if> />
                    <input type="radio" name="rate_avg" value="4" title="Good"
                           disabled="disabled"
                           <c:if test="${avg > 4.0}">checked="checked"</c:if> />
                    <input type="radio" name="rate_avg" value="5" title="Excellent"
                           disabled="disabled"
                           <c:if test="${avg > 5.0}">checked="checked"</c:if> />

                </form>
            </div>


            <div class="rating-R"><strong><fmt:message key="label.rateThis"/>:</strong> <span id="caption${id}"></span>

                <form id="rat${id}" action="<c:url value='${url.base}${currentNode.path}'/>" method="post">
                    <select name="j:lastVote">
                        <option value="1"><fmt:message key="label.rateThis.poor"/></option>
                        <option value="2"><fmt:message key="label.rateThis.fair"/></option>
                        <option value="3"><fmt:message key="label.rateThis.average"/></option>
                        <option value="4"><fmt:message key="label.rateThis.good"/></option>
                        <option value="5"><fmt:message key="label.rateThis.excellent"/></option>
                    </select>
                    <input type="submit" value="<fmt:message key="label.rateThis.rateIt"/>"/>
                </form>
            </div>

        </div>
    </c:when>
    <c:otherwise>
        <%@include file="rating.hidden.average.readonly.jsp" %>
    </c:otherwise>
</c:choose>

</body></html>