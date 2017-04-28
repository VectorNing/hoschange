<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:DataDict type="js" var="js"></t:DataDict>
<t:DataDict type="xb" var="xb"></t:DataDict>
<t:DataDict type="shoes" var="shoes"></t:DataDict>
<t:DataDict type="cloth" var="cloth"></t:DataDict>
<div class="modal-header">
	<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button> -->
	<h4 class="modal-title" id="myModalLabel">修改密码</h4>
</div>
<div class="modal-body no-padding">

<div  class="smart-form">
		<fieldset>
			<form action="" id="updatePassWord" class="smart-form">
					<!-- <section>
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
					</section> -->
					<input type="hidden" value="${user.loginName}" id="loginName_user">
					<section>
						<div class="row">
							<label class="label col col-2">原密码</label>
							<div class="col col-10">
								<label class="input"> <i class="icon-append fa fa-lock"></i>
									<input type="password" name="old_passWord" id="old_passWord" onblur="selectPassWord()">
								</label>
								
								<span id="message_password"></span>
							</div>
						</div>
					</section>
					
					<section>
						<div class="row">
							<label class="label col col-2">新密码</label>
							<div class="col col-10">
								<label class="input"> <i class="icon-append fa fa-lock"></i>
									<input type="password" name="password" id="new_password">
								</label>
							</div>
						</div>
					</section>
					<section>
						<div class="row">
							<label class="label col col-2">确认密码</label>
							<div class="col col-10">
								<label class="input"> <i class="icon-append fa fa-lock"></i>
									<input type="password" name="new_password_two" id="new_password_two" onblur="newPassWord()">
								</label>
								
								<span id="new_password_two_span"></span>
							</div>
						</div>
					</section>
			</form>
		</fieldset>

		<footer>
			<button type="button" class="btn btn-primary" onclick="updatePassword()">
				保存
			</button>
			<!-- <button type="button" class="btn btn-default" data-dismiss="modal">
				取消
			</button> -->
		</footer>
</div>
</div>
<script type="text/javascript" src="js/dict.js"></script>
<script>
function selectPassWord(){
	var url = "auth/selectUserPassWord";
	var loginName = $("#loginName_user").val();
	var password = $("#old_passWord").val();
	password=convertPassword(loginName,$.trim(password));
	var params = {loginname:loginName,password:password};
	zfesAjax.ajaxTodo(url, params, function(data) {
		var mess = data.message;
		var count = data.strData;
		console.log(count)
		$("#message_password").html(mess);
	});
}

function updatePassword(){
	if(validateUser().form()){
		var url = "auth/selectUserPassWord";
		var loginName = $("#loginName_user").val();
		var password = $("#old_passWord").val();//原密码
		password=convertPassword(loginName,$.trim(password));
		var params = {loginname:loginName,password:password};
		
		var newPass = $("#new_password").val();//新密码
		var newPass_two = $("#new_password_two").val();//确认密码
		zfesAjax.ajaxTodo(url, params, function(data) {
			var mess = data.message;
			var count = data.strData;
			$("#message_password").html(mess);
			if(count>0){
// 				alert("原密码输入有误")
			}else{
				if((newPass==null || newPass=="" || newPass_two==null || newPass_two=="") || newPass!=newPass_two){
// 		 			alert("两次新密码输入错误！");
					return;
				}else{
					var ajaxUrl = "auth/changePasswordSalt"
					password=convertPassword(loginName,$.trim(newPass));
					var params = {loginName:loginName,password:password};
					zfesAjax.ajaxTodo(ajaxUrl,params , function(data) {
						$('#updatePassWord')[0].reset();
						alertSwal.confirmSuccess(data.message,"请重新登录",function(){
							window.location.href="${pageContext.request.contextPath}/auth/logout";
						});
					});
				}
			}
			
		});
	}
}

function convertPassword(username,password){
	var salt=username+"@sdsesxh_94DABGioQOq2tTUO0AXYow";
console.log("mima---"+hex_md5(password+salt))
	return hex_md5(password+salt);
}
$(function(){
	validateUser();
});

function validateUser(){
	return $("#updatePassWord").validate({
		rules : {
			old_passWord : {required : true,minlength : 3,maxlength : 20},
			password : {required : true,minlength : 3,maxlength : 20},
			new_password_two : {required : true,minlength : 3,maxlength : 20}
		},
		messages : {
			old_passWord : {required : '请输入原密码'},
			password : {required : '请输入新密码'},
			new_password_two : {required : '请输入确认密码'}
		},

		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});
}

function newPassWord(){
	var newPass = $("#new_password").val();
	var newPass_two = $("#new_password_two").val();
	if(newPass_two == null || newPass_two == ''){
		$("#new_password_two_span").html("请输入确认密码");
	}else if(newPass_two != newPass){
		$("#new_password_two_span").html("两次密码输入不一致");
	}else if(newPass_two == newPass){
		$("#new_password_two_span").html("");
	}
}
</script>