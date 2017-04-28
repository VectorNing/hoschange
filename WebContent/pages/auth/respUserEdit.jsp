<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:DataDict type="js" var="js"></t:DataDict>
<t:DataDict type="xb" var="xb"></t:DataDict>
<t:DataDict type="shoes" var="shoes"></t:DataDict>
<t:DataDict type="cloth" var="cloth"></t:DataDict>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">修改用户</h4>
</div>
<div class="modal-body no-padding">

	<form action="" id="addUserForm" class="smart-form">
<%--        <input type="hidden" id="user_id" name="id" value="${user.id}"> --%>
       <input type="hidden" id="userId_detail" name="userId" value="${userDetail.userId}">
		<fieldset>
			<section>
				<div class="row">
					<label class="label col col-2">用户名</label>
					<div class="col col-10">
						<label class="input"> <i class="icon-append fa fa-user"></i>
							<input type="text" name="loginName" value="${userDetail.loginName}" id="loginName" onblur="selectLoginName()">
						</label>
						<div>
					       <span id="message_span"></span>
					    </div>
					</div>
				</div>
			</section>
			
			<section>
				<div class="row">
					<label class="label col col-2">卡号</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="loginName2" id="loginName2" value="${userDetail.loginName2}"  onblur="selectLoginName2()">
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
						<label class="input">
							<input type="text" name="telephone" id="telephone" value="${userDetail.telephone}">
						</label>
					</div>
				</div>
			</section>
		</fieldset>

		<footer>
			<button type="button" class="btn btn-primary" onclick="editUser();">
				保存
			</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">
				取消
			</button>
		</footer>

	</form>
</div>
<script type="text/javascript" src="js/dict.js"></script>
<script>
function selectLoginName(){
	var url="authUser/selectUserByLoginName"
	var loginName=$("#loginName").val();
	var id=$("#userId_detail").val();
	alert(id);
	var params={loginName:loginName,id:id};
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
	var id=$("#userId_detail").val();
	var params={loginName2:$.trim(loginName2),id:id};
	zfesAjax.ajaxTodo(url, params, function(data) {
		var count = data.strData;
		 if(count>0){
			$("#message_loginName2").html("卡号重复，请重新输入");
		 }else{
			$("#message_loginName2").html("");
		 }
	});
}

function editUser(){
	if(validateUser().form()){
		var url="authUser/selectUserByLoginName"
			var loginName=$("#loginName").val();
			var id=$("#userId_detail").val();
			var params={loginName:loginName,id:id};
			zfesAjax.ajaxTodo(url, params, function(data) {
				var count = data.strData;
				 if(count>0){
					$("#message_span").html("用户名重复，请重新输入");
				 }else{
					$("#message_span").html("");

					var loginName2=$("#loginName2").val();
					if(loginName2==null || loginName2==""){
						saveUser();
					}else{
						var url="authUser/selectUserByLoginName2"
							var loginName2=$("#loginName2").val();
							var id=$("#userId_detail").val();
							var params={loginName2:$.trim(loginName2),id:id};
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
//		var url="authUser/updateAuthUser";
	var url="authUser/updateUserDedailAllById";//11-25改
	zfesAjax.ajaxTodo(url,$("#addUserForm").serialize(), function(data){
		alertSwal.successText(data.message);
		$('#userModal').modal('hide');
	})
}

$(function(){
	validateUser();
});
function validateUser(){
	return $("#addUserForm").validate({
		rules : {
			loginName : {required : true}
		},

		messages : {
			loginName : {required : '请输入用户名'}
		},

		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});
}
</script>