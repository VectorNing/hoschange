<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>神思旭辉</title>
<meta name="description" content="">
<meta name="author" content="">
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> -->
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<!-- #CSS Links -->
<!-- Basic Styles -->
<link rel="stylesheet" type="text/css" media="screen"
	href="libs/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="libs/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="libs/js/bootstrap-table/bootstrap-table.min.css">
<!-- SmartAdmin Styles : Caution! DO NOT change the order -->
<link rel="stylesheet" type="text/css" media="screen"
	href="libs/css/smartadmin-production-plugins.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="libs/css/smartadmin-production.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="libs/css/smartadmin-skins.min.css">

<link rel="stylesheet" type="text/css" media="screen"
	href="libs/js/sweetalert/sweetalert.css">

<link rel="stylesheet" type="text/css" media="screen"
	href="libs/css/bootstrap-spinner.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="libs/css/bootstrap-table-editable.css">

<!-- DEV links : turn this on when you like to develop directly -->
<!--<link rel="stylesheet" type="text/css" media="screen" href="../Source_UNMINIFIED_CSS/smartadmin-production.css">-->
<!--<link rel="stylesheet" type="text/css" media="screen" href="../Source_UNMINIFIED_CSS/smartadmin-skins.css">-->

<!-- SmartAdmin RTL Support -->
<link rel="stylesheet" type="text/css" media="screen"
	href="libs/css/smartadmin-rtl.min.css">

<!-- We recommend you use "your_style.css" to override SmartAdmin
		     specific styles this will also ensure you retrain your customization with each SmartAdmin update.
		<link rel="stylesheet" type="text/css" media="screen" href="css/your_style.css"> -->

<!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
<link rel="stylesheet" type="text/css" media="screen"
	href="libs/css/demo.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="libs/css/WdatePicker.css">
<!-- #FAVICONS -->
<link rel="shortcut icon" href="libs/img/favicon/favicon.ico"
	type="image/x-icon">
<link rel="icon" href="libs/img/favicon/favicon.ico" type="image/x-icon">

<!-- #GOOGLE FONT 
		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">
		-->
<!-- FONT -->
<link rel="stylesheet" href="">
<!-- #APP SCREEN / ICONS -->
<!-- Specifying a Webpage Icon for Web Clip 
			 Ref: https://developer.apple.com/library/ios/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html -->
<link rel="apple-touch-icon"
	href="libs/img/splash/sptouch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="76x76"
	href="libs/img/splash/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="120x120"
	href="libs/img/splash/touch-icon-iphone-retina.png">
<link rel="apple-touch-icon" sizes="152x152"
	href="libs/img/splash/touch-icon-ipad-retina.png">

<!-- iOS web-app metas : hides Safari UI Components and Changes Status Bar Appearance -->
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<!-- Startup image for web apps -->
<link rel="apple-touch-startup-image"
	href="libs/img/splash/ipad-landscape.png"
	media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:landscape)">
<link rel="apple-touch-startup-image"
	href="libs/img/splash/ipad-portrait.png"
	media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:portrait)">
<link rel="apple-touch-startup-image" href="libs/img/splash/iphone.png"
	media="screen and (max-device-width: 320px)">

<link href="libs/chosen/chosen.css" rel="stylesheet">
  <!-- 选择时间插件 -->  
   <script type="text/javascript" src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
</head>

<!--

	TABLE OF CONTENTS.
	
	Use search to find needed section.
	
	===================================================================
	
	|  01. #CSS Links                |  all CSS links and file paths  |
	|  02. #FAVICONS                 |  Favicon links and file paths  |
	|  03. #GOOGLE FONT              |  Google font link              |
	|  04. #APP SCREEN / ICONS       |  app icons, screen backdrops   |
	|  05. #BODY                     |  body tag                      |
	|  06. #HEADER                   |  header tag                    |
	|  07. #PROJECTS                 |  project lists                 |
	|  08. #TOGGLE LAYOUT BUTTONS    |  layout buttons and actions    |
	|  09. #MOBILE                   |  mobile view dropdown          |
	|  10. #SEARCH                   |  search field                  |
	|  11. #NAVIGATION               |  left panel & navigation       |
	|  12. #MAIN PANEL               |  main panel                    |
	|  13. #MAIN CONTENT             |  content holder                |
	|  14. #PAGE FOOTER              |  page footer                   |
	|  15. #SHORTCUT AREA            |  dropdown shortcuts area       |
	|  16. #PLUGINS                  |  all scripts and plugins       |
	
	===================================================================
	
	-->

<!-- #BODY -->
<!-- Possible Classes

		* 'smart-style-{SKIN#}'
		* 'smart-rtl'         - Switch theme mode to RTL
		* 'menu-on-top'       - Switch to top navigation (no DOM change required)
		* 'no-menu'			  - Hides the menu completely
		* 'hidden-menu'       - Hides the main menu but still accessable by hovering over left edge
		* 'fixed-header'      - Fixes the header
		* 'fixed-navigation'  - Fixes the main menu
		* 'fixed-ribbon'      - Fixes breadcrumb
		* 'fixed-page-footer' - Fixes footer
		* 'container'         - boxed layout mode (non-responsive: will not work with fixed-navigation & fixed-ribbon)
	-->
<body class="smart-style-0 fixed-header fixed-ribbon fixed-navigation">

	<!-- #HEADER -->
	<header id="header">
		<div id="logo-group">

			<!-- PLACE YOUR LOGO HERE -->
			<span id="logo"> <img src="libs/img/logo.png" alt="SmartAdmin">
			</span>
			<!-- END LOGO PLACEHOLDER -->


			<!-- AJAX-DROPDOWN : control this dropdown height, look and feel from the LESS variable file -->
			<div class="ajax-dropdown">

				<!-- the ID links are fetched via AJAX to the ajax container "ajax-notifications" -->
				<div class="btn-group btn-group-justified" data-toggle="buttons">
					<label class="btn btn-default"> <input type="radio"
						name="activity" id="ajax/notify/mail.html"> Msgs (14)
					</label> <label class="btn btn-default"> <input type="radio"
						name="activity" id="ajax/notify/notifications.html">
						notify (3)
					</label> <label class="btn btn-default"> <input type="radio"
						name="activity" id="ajax/notify/tasks.html"> Tasks (4)
					</label>
				</div>

				<!-- notification content -->
				<div class="ajax-notifications custom-scroll">

					<div class="alert alert-transparent">
						<h4>Click a button to show messages here</h4>
						This blank page message helps protect your privacy, or you can
						show the first message here automatically.
					</div>

					<i class="fa fa-lock fa-4x fa-border"></i>

				</div>
				<!-- end notification content -->

				<!-- footer: refresh area -->
				<span> Last updated on: 12/12/2013 9:43AM
					<button type="button"
						data-loading-text="<i class='fa fa-refresh fa-spin'></i> Loading..."
						class="btn btn-xs btn-default pull-right">
						<i class="fa fa-refresh"></i>
					</button>
				</span>
				<!-- end footer -->

			</div>
			<!-- END AJAX-DROPDOWN -->
		</div>



		<!-- #TOGGLE LAYOUT BUTTONS -->
		<!-- pulled right: nav area -->
		<div class="pull-right">

			<!-- collapse menu button -->
			<div id="hide-menu" class="btn-header pull-right">
				<span> <a href="javascript:void(0);" data-action="toggleMenu"
					title="Collapse Menu"><i class="fa fa-reorder"></i></a>
				</span>
			</div>
			<!-- end collapse menu -->

			<!-- #MOBILE -->
			<!-- Top menu profile link : this shows only when top menu is active -->
			<ul id="mobile-profile-img"
				class="header-dropdown-list hidden-xs padding-5">
				<li class=""><a href="#"
					class="dropdown-toggle no-margin userdropdown"
					data-toggle="dropdown"> <img src="libs/img/avatars/sunny.png"
						alt="John Doe" class="online" />
				</a>
					<ul class="dropdown-menu pull-right">
						<li><a href="javascript:void(0);"
							class="padding-10 padding-top-0 padding-bottom-0"><i
								class="fa fa-cog"></i> Setting</a></li>
						<li class="divider"></li>
						<li><a href="#ajax/profile.html"
							class="padding-10 padding-top-0 padding-bottom-0"> <i
								class="fa fa-user"></i> <u>P</u>rofile
						</a></li>
						<li class="divider"></li>
						<li><a href="javascript:void(0);"
							class="padding-10 padding-top-0 padding-bottom-0"
							data-action="toggleShortcut"><i class="fa fa-arrow-down"></i>
								<u>S</u>hortcut</a></li>
						<li class="divider"></li>
						<li><a href="javascript:void(0);"
							class="padding-10 padding-top-0 padding-bottom-0"
							data-action="launchFullscreen"><i class="fa fa-arrows-alt"></i>
								Full <u>S</u>creen</a></li>
						<li class="divider"></li>
						<li><a href="login.html"
							class="padding-10 padding-top-5 padding-bottom-5"
							data-action="userLogout"><i class="fa fa-sign-out fa-lg"></i>
								<strong><u>L</u>ogout</strong></a></li>

						<li class="divider"></li>
						<li><a href="javascript:void(0);"
							class="padding-10 padding-top-0 padding-bottom-0"
							data-action="user_password"><i class="fa fa-arrows-alt"></i>
								Full <u>S</u>creen</a></li>
					</ul></li>
			</ul>

			<!-- logout button -->
			<div id="logout" class="btn-header transparent pull-right">
				<span> <a href="auth/logout" title="Sign Out"
					data-action="userLogout"
					data-logout-msg="You can improve your security further after logging out by closing this opened browser"><i
						class="fa fa-sign-out"></i></a>
				</span>
			</div>
			<!-- end logout button -->

			<!-- search mobile button (this is hidden till mobile view port) -->
			<div id="search-mobile" class="btn-header transparent pull-right">
				<span> <a href="javascript:void(0)" title="Search"><i
						class="fa fa-search"></i></a>
				</span>
			</div>
			<!-- end search mobile button -->


			<!-- fullscreen button -->
			<div id="fullscreen" class="btn-header transparent pull-right">
				<span> <a href="javascript:void(0);"
					data-action="launchFullscreen" title="Full Screen"><i
						class="fa fa-arrows-alt"></i></a>
				</span>
			</div>
			<!-- end fullscreen button -->

		</div>
		<!-- end pulled right: nav area -->

	</header>

	<!-- END HEADER -->

	<!-- #NAVIGATION -->
	<!-- Left panel : Navigation area -->
	<!-- Note: This width of the aside area can be adjusted through LESS/SASS variables -->
	<aside id="left-panel">

		<!-- User info -->
		<div class="login-info">
			<span> <!-- User image size is adjusted inside CSS, it should stay as is -->

				<a href="javascript:void(0);"> <input type="hidden"
					id="isSuperAdmin" name="isSuperAdmin"
					value="${_SESSIONUSER_.superAdminRoleCode}"> <span>${_SESSIONUSER_.loginName}
				</span>
			</a> <a href="javascript:updatePassWord(); " style="float: right;"> <span
					style="font-size: 15px;" onclick="updatePassWord()"> [修改密码]
				</span>
			</a>

			</span>
		</div>
		<!-- end user info -->

		<!-- NAVIGATION : This navigation is also responsive

			To make this navigation dynamic please make sure to link the node
			(the reference to the nav > ul) after page load. Or the navigation
			will not initialize.
			-->
		<nav>
			<!-- 
				NOTE: Notice the gaps after each icon usage <i></i>..
				Please note that these links work a bit different than
				traditional href="" links. See documentation for details.
				-->

			<ul>


			</ul>
		</nav>
		<span class="minifyme" data-action="minifyMenu"> <i
			class="fa fa-arrow-circle-left hit"></i>
		</span>
	</aside>
	<!-- END NAVIGATION -->

	<!-- #MAIN PANEL -->
	<div id="main" role="main">

		<!-- Dynamic Modal -->
		<div class="modal fade" id="update_password" tabindex="-1"
			role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content" id="modal-content"></div>
			</div>
		</div>

		<!-- RIBBON -->
		<div id="ribbon">

			<span class="ribbon-button-alignment"> <span id="refresh"
				class="btn btn-ribbon" data-action="resetWidgets"
				data-title="refresh" rel="tooltip" data-placement="bottom"
				data-original-title="<i class='text-warning fa fa-warning'></i> Warning! This will reset all your widget settings."
				data-html="true" data-reset-msg="确定要刷新此页面?"><i
					class="fa fa-refresh"></i></span>
			</span>

			<!-- breadcrumb -->
			<ol class="breadcrumb">
				<!-- This is auto generated -->
			</ol>
			<!-- end breadcrumb -->

			<!-- You can also add more buttons to the
				ribbon for further usability

				Example below:

				<span class="ribbon-button-alignment pull-right" style="margin-right:25px">
					<a href="#" id="search" class="btn btn-ribbon hidden-xs" data-title="search"><i class="fa fa-grid"></i> Change Grid</a>
					<span id="add" class="btn btn-ribbon hidden-xs" data-title="add"><i class="fa fa-plus"></i> Add</span>
					<button id="search" class="btn btn-ribbon" data-title="search"><i class="fa fa-search"></i> <span class="hidden-mobile">Search</span></button>
				</span> -->

		</div>
		<!-- END RIBBON -->

		<!-- #MAIN CONTENT -->
		<div id="content">
			<!-- <div style="width: auto;height: auto;">
			
				<img alt="" src="libs/img/welcome_01.jpg" style="width: 99%;height: auto;">
			</div> -->
		</div>

		<!-- END #MAIN CONTENT -->

	</div>
	<!-- END #MAIN PANEL -->



	<!-- #SHORTCUT AREA : With large tiles (activated via clicking user name tag)
			 Note: These tiles are completely responsive, you can add as many as you like -->
	<div id="shortcut">
		<ul>
			<li><a href="#ajax/inbox.html"
				class="jarvismetro-tile big-cubes bg-color-blue"> <span
					class="iconbox"> <i class="fa fa-envelope fa-4x"></i> <span>Mail
							<span class="label pull-right bg-color-darken">14</span>
					</span>
				</span>
			</a></li>
			<li><a href="#ajax/calendar.html"
				class="jarvismetro-tile big-cubes bg-color-orangeDark"> <span
					class="iconbox"> <i class="fa fa-calendar fa-4x"></i> <span>Calendar</span>
				</span>
			</a></li>
			<li><a href="#ajax/gmap-xml.html"
				class="jarvismetro-tile big-cubes bg-color-purple"> <span
					class="iconbox"> <i class="fa fa-map-marker fa-4x"></i> <span>Maps</span>
				</span>
			</a></li>
			<li><a href="#ajax/invoice.html"
				class="jarvismetro-tile big-cubes bg-color-blueDark"> <span
					class="iconbox"> <i class="fa fa-book fa-4x"></i> <span>Invoice
							<span class="label pull-right bg-color-darken">99</span>
					</span>
				</span>
			</a></li>
			<li><a href="#ajax/gallery.html"
				class="jarvismetro-tile big-cubes bg-color-greenLight"> <span
					class="iconbox"> <i class="fa fa-picture-o fa-4x"></i> <span>Gallery
					</span>
				</span>
			</a></li>
			<li><a href="#ajax/profile.html"
				class="jarvismetro-tile big-cubes selected bg-color-pinkDark"> <span
					class="iconbox"> <i class="fa fa-user fa-4x"></i> <span>My
							Profile </span>
				</span>
			</a></li>
		</ul>
	</div>
	<!-- END SHORTCUT AREA -->

	<!--================================================== -->

	<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)
		<script data-pace-options='{ "restartOnRequestAfter": true }' src="js/plugin/pace/pace.min.js"></script>-->


	<!-- #PLUGINS -->
	<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
	<script src="libs/js/jquery.min.js"></script>
	<script>
		if (!window.jQuery) {
			document
					.write('<script src="libs/js/libs/jquery-2.1.1.min.js"><\/script>');
		}
	</script>

	<script src="libs/js/jquery-ui.min.js"></script>
	<script>
		if (!window.jQuery.ui) {
			document
					.write('<script src="libs/js/libs/jquery-ui-1.10.3.min.js"><\/script>');
		}
	</script>

	<!-- IMPORTANT: APP CONFIG -->
	<script src="libs/js/app.config.js"></script>

	<!-- JS TOUCH : include this plugin for mobile drag / drop touch events-->
	<script src="libs/js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script>
            <!--  时间选择 -->    
	<!-- BOOTSTRAP JS -->
	<script src="libs/js/bootstrap/bootstrap.min.js"></script>
	<script src="libs/js/bootstrap-table/bootstrap-table.min.js"></script>
	<script
		src="libs/js/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
	<!-- CUSTOM NOTIFICATION -->
	<script src="libs/js/notification/SmartNotification.min.js"></script>

	<!-- JARVIS WIDGETS -->
	<script src="libs/js/smartwidgets/jarvis.widget.min.js"></script>

	<!-- EASY PIE CHARTS -->
	<script
		src="libs/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>

	<!-- SPARKLINES -->
	<script src="libs/js/plugin/sparkline/jquery.sparkline.min.js"></script>

	<!-- JQUERY VALIDATE -->
	<script src="libs/js/plugin/jquery-validate/jquery.validate.min.js"></script>

	<!-- JQUERY MASKED INPUT -->
	<script src="libs/js/plugin/masked-input/jquery.maskedinput.min.js"></script>

	<!-- JQUERY SELECT2 INPUT -->
	<script src="libs/js/plugin/select2/select2.min.js"></script>

	<!-- JQUERY UI + Bootstrap Slider -->
	<script src="libs/js/plugin/bootstrap-slider/bootstrap-slider.min.js"></script>

	<!-- browser msie issue fix -->
	<script src="libs/js/plugin/msie-fix/jquery.mb.browser.min.js"></script>

	<!-- FastClick: For mobile devices: you can disable this in app.js -->
	<script src="libs/js/plugin/fastclick/fastclick.min.js"></script>

	<!--[if IE 8]>
			<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>
		<![endif]-->

	<!-- Demo purpose only -->
	<script src="libs/js/demo.min.js"></script>

	<!-- MAIN APP JS FILE -->
	<script src="libs/js/app.min.js"></script>

	<!-- ENHANCEMENT PLUGINS : NOT A REQUIREMENT -->
	<!-- Voice command : plugin -->
	<script src="libs/js/speech/voicecommand.min.js"></script>
	<script src="libs/js/pace/pace.min.js" type="text/javascript"></script>
	<!-- SmartChat UI : plugin -->
	<script src="libs/js/smart-chat-ui/smart.chat.ui.min.js"></script>
	<script src="libs/js/smart-chat-ui/smart.chat.manager.min.js"></script>

	<!-- Form Validator -->
	<script src="libs/js/plugin/jquery-form/jquery-form.min.js"></script>
	<script src="libs/js/sweetalert/sweetalert.min.js"></script>

	<script src="js/zfes.alert.swal.js"></script>
	
	<script src="js/zfes.ajax.js"></script>
	<script src="js/zfes.core.js"></script>
	<script src="js/zfes.showbox.js"></script>
	<!-- <script src="js/vue.js"></script> -->
	<script src="js/zfes.util.js"></script>
	<script src="js/zfes.bootstrap.table.js"></script>
	<script src="js/public.js"></script>
	<script src="js/md5-min.js"></script>
	<!-- 		<script src="js/dict.js"></script> -->
	<script src="libs/js/jquery.spinner.min.js"></script>
	<script src="libs/js/bootstrap-table/bootstrap-table-editable.min.js"></script>

	<script src="libs/chosen/chosen.jquery.js" type="text/javascript"></script>
	<script src="js/vue.min.js" type="text/javascript"></script>
	<script src="js/zfes.vue.js" type="text/javascript"></script>

	<!-- Your GOOGLE ANALYTICS CODE Below -->
	<script type="text/javascript">
		var _gaq = _gaq || [];
		_gaq.push([ '_setAccount', 'UA-XXXXXXXX-X' ]);
		_gaq.push([ '_trackPageview' ]);

		/*
		(function() {
		  var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
		  ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
		  var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
		})();
		 */
	</script>

	<script>
		$(function() {
			$
					.ajax({
						url : "${pageContext.request.contextPath}/authMenu/loadMenu",
						type : 'GET',
						dataType : "json",
						cache : false,
						success : function(data) {
							loadMenu(data);
						},
						error : function(xhr, textStatus) {
							alertSwal.confirm("登录超时","是否重新登录",function(){window.location.href = "${pageContext.request.contextPath}/login.jsp"});
							/* alertSwal.warningText("请重新登录");
							window.location.href = "${pageContext.request.contextPath}/login.jsp" */
						},
					});
		});

		function updatePassWord() {
			$("#update_password").modal({
				remote : "auth/enterUserPassWord"
			});
		}
	</script>




</body>

</html>