<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>神思旭辉</title>
<!-- BOOTSTRAP JS -->
<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
<script src="${pageContext.request.contextPath}/libs/js/bootstrap/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/libs/js/libs/jquery-2.1.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/libs/js/sweetalert/sweetalert.min.js"></script>
<!-- MAIN APP JS FILE -->
<script src="${pageContext.request.contextPath}/js/zfes.alert.swal.js"></script>
<script src="${pageContext.request.contextPath}/js/zfes.ajax.js"></script>
<script src="${pageContext.request.contextPath}/js/zfes.core.js"></script>
<script
	src="${pageContext.request.contextPath}/libs/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/libs/js/sweetalert/sweetalert.css">
<style type="text/css">
html, body {
	margin: 0;
	padding: 0;
	background: url('${pageContext.request.contextPath}/libs/img/img/0.png')
		no-repeat;
	background-attachment: fixed;
	background-size: cover;
	width: 100%;
	height: 100%;
}

.sslogo {
	padding-top: 30px;
	padding-left: 20px;
}

.sslogo img {
	display: block;
}

#anniu {
    height:600px;   
   margin-top:12%;
}
    .restart:hover {   
    transform:scale(1.2,1.2);    
    }
.btn{
			width: 150px;
			height: 40px;
			border-radius: 5px;
			color:#009F5C ;
			text-align: center;
			line-height: 40px;
			font-family: "微软雅黑";
			overflow: hidden;
			margin-top: -48px;
			float: right;
			margin-right: 50px;
			padding:6px 22px;
		}
	.btn:hover{
			border:1px solid #009F5C ;
			color: #009F5C;
			cursor: pointer;
		}
	.btn img{
			float: left;
			margin-left:10px;
			
		}
	.btn p{
        float:left;
			color:#009F5C ;
            margin-top:5px;
            font-size:medium;
            margin-left:10px;
	}
</style>
<link rel="stylesheet" type="text/css" media="screen"
      href="${pageContext.request.contextPath}/libs/css/bootstrap.min.css">
</head>
<body>
	<input type="hidden" value="${carNum}" id="admin_str_carNum">
	<input type="hidden" value="${thNumber}" id="admin_str_thNumber">
	<div class="sslogo">
		<img
			src="${pageContext.request.contextPath}/libs/img/img/神思旭辉logo.png" />
	</div>
	<div class="btn" onclick="goIndex()">
		<img src="${pageContext.request.contextPath}/libs/img/img/back.png" />
		<p>返回首页</p>
	</div>
	<div id="anniu">
		<img src="${pageContext.request.contextPath}/libs/img/img/消毒鞋.png"
			class="restart col-md-4 col-md-offset-2" onclick="tempLogin2()"/> <img
			src="${pageContext.request.contextPath}/libs/img/img/重新.png"
			class="restart col-md-4" onclick="tempLogin1()" />
	</div>

</body>
<script type="text/javascript">

var webSocket = new WebSocket('ws://localhost:8080/hoschange/SocketServer');
webSocket.onopen = function(event) {
};

webSocket.onmessage = function(event) {
	var data=JSON.parse(event.data);
	var type=data.message;
	
	//alert(type);
	
	
	if(type=="doctor"){
		carNum=data.cardNumber;
		cardLogin1(carNum);//登录
	}
	
};



	$(function() {
		adminCarNum = $("#admin_str_carNum").val();
		number = $("#admin_str_thNumber").val();
	})

	function tempLogin2() {
		var url = "${pageContext.request.contextPath}/touch/selectUserByPermission";
		var param = {
			theNumber : number,
			cardNum : adminCarNum
		};
		zfesAjax.ajaxTodo(url,param,function(data) {
			window.location.href = "${pageContext.request.contextPath}/touch/allotSterilizer?number="+ number;
			});
	}

	function alertbrshcard(){
		
		sweetAlert({
			title:"请刷卡",
			text:"",
			type:"warning",
			confirmButtonColor:"#DD6B55",
			showConfirmButton:false,
		})
	};
	
	//重新领取
	function tempLogin1() {
		
		alertbrshcard();
		setTimeout("webSocket.send('doctor')",5000);
	}

	//登录
	function cardLogin1(carNum) {
		//硬件带回手术室id 设备编号 和 用户身份证号或id 
		var loginname = carNum;
		var ajaxUrl = "${pageContext.request.contextPath}/touch/login";
		var param = {
			"loginname" : $.trim(loginname)
		};
		zfesAjax.ajaxTodo(ajaxUrl,param,function(data) {
			if (data.statusCode == 200) {
				var type = data.strData;
				console.log(type);
				if (type == 0) {//医生 直接领取
					receiveDevices1(carNum);
				  } else {//管理员跳转 操作页面
		            window.location.href = "${pageContext.request.contextPath}/touch/allotSterilizer?number="+ number;
						}
							} else {
								alertSwal.warning(data.message);
							}
						});
	}
	//领取
	function receiveDevices1(carNum) {
		//硬件带回手术室id 设备编号 和 用户身份证号或id 

		var url = "${pageContext.request.contextPath}/touch/randomReceiveShoeByUserIdTheNumber";
		//硬件带回手术室id 设备编号 和 用户身份证号或id 
		//		var theNumber = "SSS-001";
		var param = {
			cardNum : carNum,
			theNumber : number,
			mark : 1
		};
		zfesAjax.ajaxTodo(url, param, function(data) {
			var steNumber = data.data.steNumber;
			var lockerNumber = data.data.lockerNumber;
			var shoesSize = data.data.shoesSize;
			var message = "手术鞋在大柜编号" + steNumber + "的小柜：" + lockerNumber
					+ "号柜内。鞋码：" + shoesSize;
			alertSwal.successText(message);
		});
	}
	
	function goIndex(){
		window.location.href="${pageContext.request.contextPath}/touch/enterTouchScreenSterilizer"
	}
</script>
</html>