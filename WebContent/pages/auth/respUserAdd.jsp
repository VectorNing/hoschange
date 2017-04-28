<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:DataDict type="js" var="js"></t:DataDict>
<t:DataDict type="xb" var="xb"></t:DataDict>
<t:DataDict type="shoes" var="shoes"></t:DataDict>
<t:DataDict type="cloth" var="cloth"></t:DataDict>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">新增用户</h4>
</div>
<div class="modal-body no-padding">

<div  class="smart-form">
		<fieldset>
	<form action="" id="addUserForm" class="smart-form">
			<section>
				<div class="row">
					<label class="label col col-2">用户名</label>
					<div class="col col-10">
						<label class="input"> <i class="icon-append fa fa-user"></i>
							<input type="text" name="loginName" id="loginName"  onblur="selectLoginName()">
						</label>
						<div>
					       <span id="message_span"></span>
					    </div>
					</div>
				</div>
			</section>

			<section>
				<div class="row">
					<label class="label col col-2">密码</label>
					<div class="col col-10">
						<label class="input"> <i class="icon-append fa fa-lock"></i>
							<input type="password" name="password" id="password">
						</label>
						
					</div>
				</div>
			</section>
			
	</form>
	<form action="" id="userDeatilForm" class="smart-form">	
	 <input type="hidden" name="loginName" id="loginName_Detail"/>	
	 <input type="hidden" name="password" id="password_Detail"/>	
			<section>
				<div class="row">
					<label class="label col col-2">卡号</label>
					<div class="col col-10">
						<label class="input">
							<input type="text" name="loginName2" id="loginName2"  onblur="selectLoginName2()">
						</label>
						<div>
					       <span id="message_loginName2"></span>
					    </div>
					</div>
				</div>
			</section>
			<section>
				<div class="row">
					<label class="label col col-2">姓名</label>
					<div class="col col-10">
						<label class="input">
							<input type="text" name="userName" id="userName" value="${userDetail.userName}">
						</label>
					</div>
				</div>
			</section>
			<section>
				<div class="row">
					<label class="label col col-2">身份证号</label>
					<div class="col col-10">
						<label class="input">
							<input type="text" name="sfzh" id="sfzh" value="${userDetail.sfzh}">
						</label>
					</div>
				</div>
			</section>
			
			<section>
				<div class="row">
					<label class="label col col-2">性别</label>
					<div class="col col-10">
						<label class="select"> 
							<select dict="xb" name="sex" id="sex" data-value="${userDetail.sex}">
							</select>
						</label>
						
					</div>
				</div>
			</section>
			
			<section>
				<div class="row">
					<label class="label col col-2">鞋码</label>
					<div class="col col-10">
						<label class="select"> 
							<select dict="shoes" name="shoesSize" id="shoesSize" data-value="${userDetail.shoesSize}">
							</select>
						</label>
						
					</div>
				</div>
			</section>
			
			<section>
				<div class="row">
					<label class="label col col-2">衣码</label>
					<div class="col col-10">
						<label class="select"> 
							<select dict="cloth" name="clothesSize" id="clothesSize" data-value="${userDetail.clothesSize}">
							</select>
						</label>
						
					</div>
				</div>
			</section>
			<section>
				<div class="row">
					<label class="label col col-2">联系电话</label>
					<div class="col col-10">
						<label class="input control-label">
							<input type="text" name="telephone" id="telephone" data-value="${userDetail.telephone}">
						</label>
					</div>
				</div>
			</section>
			
			
			<section>
				<div class="row">
					<label class="label col col-2">角色</label>
					<div class="col col-10" id="theater">
						<label class="select"> 
							<km-select host="baseServer" url="authRole/selectAuthRoleList" id="roleId" name="roleId"  label=""></km-select>
						</label>	
					</div>
				</div>
			</section>
			
	</form>
		</fieldset>

		<footer>
			<button type="button" class="btn btn-primary" onclick="addUser();">
				保存
			</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">
				取消
			</button>
		</footer>
</div>
</div>
<script type="text/javascript" src="js/dict.js"></script>
<script>
function selectLoginName(){
	var url="authUser/selectUserByLoginName"
	var loginName=$("#loginName").val();
	var params={loginName:$.trim(loginName)};
	zfesAjax.ajaxTodo(url, params, function(data) {
		var count = data.strData;
		 if(count>0){
			$("#message_span").html("用户名重复，请重新输入");
		 }else{
			$("#message_span").html("");
		 }

	});
}
function selectLoginName2(){
	var url="authUser/selectUserByLoginName2"
	var loginName2=$("#loginName2").val();
	var params={loginName2:$.trim(loginName2)};
	zfesAjax.ajaxTodo(url, params, function(data) {
		var count = data.strData;
		 if(count>0){
			$("#message_loginName2").html("卡号重复，请重新输入");
		 }else{
			$("#message_loginName2").html("");
		 }
	});
}

function addUser(){
	if(validateUser().form()){
		var url="authUser/selectUserByLoginName"
		var loginName=$("#loginName").val();
		var params={loginName:$.trim(loginName)};
		zfesAjax.ajaxTodo(url, params, function(data) {
			var count = data.strData;
			 if(count>0){
				$("#message_span").html("用户名重复，请重新输入");
			 }else{
				$("#message_span").html("");
				
				var loginName2=$("#loginName2").val();//卡号
				if(loginName2==null || loginName2==""){
					saveUser();
				}else{
					var url="authUser/selectUserByLoginName2"
						var loginName2=$("#loginName2").val();
						var params={loginName2:$.trim(loginName2)};
						zfesAjax.ajaxTodo(url, params, function(data) {
							var count = data.strData;
							 if(count>0){
								$("#message_loginName2").html("卡号重复，请重新输入");
							 }else{
								$("#message_loginName2").html("");
								saveUser();
							 }
						});
				}
			 }
		});
	}
	
}

function saveUser(){
	var defaultRole = $("#defaultRole").val();
	var url="authUser/createAuthUserAll";
	var loginName = $("#loginName").val();
	var password = $("#password").val();
	 password=convertPassword(loginName,$.trim(password));
// 				var param = { "loginName" : $.trim(loginName),"password":password,"defaultRole":defaultRole};
	$("#loginName_Detail").val($.trim(loginName));
	$("#password_Detail").val(password);
	
	zfesAjax.ajaxTodo(url,$("#userDeatilForm").serialize(), function(data){
		alertSwal.successText(data.message);
		$('#userModal').modal('hide');
		
	})
}
function convertPassword(username,password){
	var salt=username+"@sdsesxh_94DABGioQOq2tTUO0AXYow";
	return hex_md5(password+salt);
}
$(function(){
	validateUser();
	ZVue.init();
});

function validateUser(){
	return $("#addUserForm").validate({
		rules : {
			loginName : {required : true},
			password : {required : true,minlength : 3,maxlength : 20}
		},

		messages : {
			loginName : {required : '请输入用户名'},
			password : {required : '请输入密码'}
		},

		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});
}

</script>