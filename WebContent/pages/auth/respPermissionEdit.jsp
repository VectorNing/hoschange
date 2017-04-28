<%@ page contentType="text/html; charset=utf-8" language="java"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">修改权限</h4>
</div>
<div class="modal-body no-padding">

	<form action="" id="editPermissionForm" class="smart-form">
	  <input type="hidden"  name="id" value="${authPerm.id}"/>
	  <input type="hidden"  name="enabled" value="${authPerm.enabled}"/>
		<fieldset>
			<section>
				<div class="row">
					<label class="label col col-2">名称</label>
					<div class="col col-10">
						<label class="input">
							<input type="text" name="name" value="${authPerm.name}">
						</label>
					</div>
				</div>
			</section>
            <section>
				<div class="row">
					<label class="label col col-2">编码</label>
					<div class="col col-10">
						<label class="input">
							<input type="text" name="permCode" value="${authPerm.permCode}">
						
						</label>
						
					</div>
				</div>
			</section>
			<section>
				<div class="row">
					<label class="label col col-2">描述</label>
					<div class="col col-10">
						<label class="input">
							<input type="text" name="description" value="${authPerm.description}">
						</label>
						
					</div>
				</div>
			</section>
			<section>
				<div class="row">
					<label class="label col col-2">url</label>
					<div class="col col-10">
						<label class="input">
							<input type="text" name="url" value="${authPerm.url}">
						
						</label>
						
					</div>
				</div>
			</section>
			
		</fieldset>

		<footer>
			<button type="button" class="btn btn-primary" onclick="editPerm();">
				保存
			</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">
				取消
			</button>
		</footer>

	</form>
</div>
<script>
$(function(){
	validateFrom();
});
function validateFrom(){
	return $("#editPermissionForm").validate({
		rules : {
			name : {required : true,maxlength: 50},
			permCode : {required : true,maxlength: 100},
			description : {maxlength: 100},
			url:{required : true,maxlength: 255}
		},
		messages : {
			name : {required : '请输入角色名称',maxlength:"长度不能超过50"},
			permCode : {required : '请输入编码',maxlength:"长度不能超过50"},
			description : {maxlength: "长度过长"},
			url:{required : '请输入URL',maxlength:"长度不能超过255"}
		},
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});
}

function editPerm(){
	var url="authPermission/updateAuthPerm";
	zfesAjax.ajaxTodo(url,$("#editPermissionForm").serialize(), function(data){
		$("#permissionModal").modal('hide');
		alertSwal.successText(data.message);
	})
}
</script>