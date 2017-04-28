<%@ page contentType="text/html; charset=utf-8" language="java"%>
<script src="../../libs/js/jquery.min.js"></script>
<script src="../../libs/js/jquery.SuperSlide.2.1.1.js"></script>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>手术室自助更衣系统黑名单</title>
		<style type="text/css">
		html,body{
			width: 100%;
		}
		*{
			padding:0;
			margin:0;
		}
		article,aside,details,figcaption,figure,footer,header,hgroup,main,nav,section,summary{
			display:block;
		}
		audio,canvas,video{
			display:inline-block;
		}
		audio:not([controls]){
			display:none;height:0;
		}
		li {
			list-style-type:none;
		}
		body{
			
			background: url("../../libs/img/blacklist.png") no-repeat scroll 0 0;
			background-size: cover;
		}
		.bd{
			height:100%;
		}
		.header{
			width:100%;
			height: 10%;
			text-align: center;
			color:#fff;
		}
		.header h1{
			font-family: 华文行楷 ;
			margin-top:2%;
			font-size: 4vw;
			word-spacing:8px;
			display: block;
			height: 100%;
		}
		.header h1 span{ 
			font-family: Microsoft YaHei;
		}
		.marquee_content {
		    background-color: #fff;
		    height: 70%;		 
		    margin: 5% auto 2%;
		    width: 90%;
		   /* font-size: 22px;*/
		    /*line-height: 41px;*/
		   font-size: 100%;
		    text-align: center;
		    color:#4A5050;
		    overflow: hidden;
		}
		.marquee_content_header{
			height:6%;
			width:100%;
		}
		.marquee_content_header li{
			background-color: #92e2ff;
		    border-bottom: 1px solid #eee;
		    float: left;
		    font-size: 1.3vw;
		    height: 120%;
		    line-height: 4.5vh;
		    width: 25%;
		}
		.txtMarquee-top{
			height: 93%;
		    width: 100%;
		}
		.tempWrap{
			width: 100%;
			height: 98% !important;
		}
		 
		 .infoList{
		 	height: 90%;
		 }
		.infoList li{		
		    height: 4.5vh !important;
		    line-height: 4.5vh;
		    width: 100%;
		}
		.infoList li div{
			height:100%;
			float: left;
			width:25%;
			font-size: 1vw;
			border-bottom: 1px solid #eee;
		}
		</style>
	</head>
	<body>
		<header class="header">
			 <h1><span>xx</span>医院手术室自助更衣系统黑名单</h1>
		</header>
		<section class="marquee_content">
			
		</section>
	</body>
</html>
<script type="text/javascript">
	var url ="${pageContext.request.contextPath}/touch/queryBlackListResult";
	$.post(url,function(data){
		$(".marquee_content").html(data);
	});
	
	function myrefresh(){
		window.location.reload();
	}
	setTimeout('myrefresh()',300000); //指定300秒刷新一次 
</script>
