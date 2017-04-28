<%@ page contentType="text/html; charset=utf-8" language="java"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">新增权限</h4>
</div>
<div class="modal-body no-padding">

	<form action="" id="addPermissionForm" class="smart-form">

		<fieldset>
			<section>
				<div class="row">
					<label class="label col col-2">名称</label>
					<div class="col col-10">
						<label class="input">
							<input type="text" name="name">
						</label>
					</div>
				</div>
			</section>
			<section>
				<div class="row">
					<label class="label col col-2">编码</label>
					<div class="col col-10">
						<label class="input">
							<input type="text" name="permCode">
						
						</label>
						
					</div>
				</div>
			</section>
			<section>
				<div class="row">
					<label class="label col col-2">描述</label>
					<div class="col col-10">
						<label class="input">
							<input type="text" name="description">
						</label>
						
					</div>
				</div>
			</section>
			
		</fieldset>

		<footer>
			<button type="button" class="btn btn-primary" onclick="addPerm()">
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
	return $("#addPermissionForm").validate({
		rules : {
			name : {required : true,maxlength: 50},
			permCode : {required : true,maxlength: 100},
			description : {maxlength: 100},
		},
		messages : {
			name : {required : '请输入角色名称',maxlength:"长度不能超过50"},
			permCode : {required : '请输入编码',maxlength:"长度不能超过50"},
			description : {maxlength: "长度过长"},
		},
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});
}

function addPerm(){
	if(validateFrom().form()){
		var url="authPermission/addAuthPerm";
		zfesAjax.ajaxTodo(url,$("#addPermissionForm").serialize(), function(data){
			$("#permissionModal").modal('hide');
			alertSwal.successText(data.message);
		})
	}
}

</script>