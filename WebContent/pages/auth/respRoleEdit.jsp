<%@ page contentType="text/html; charset=utf-8" language="java"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">修改角色</h4>
</div>
<div class="modal-body no-padding">

	<form action="" id="editRoleForm" class="smart-form">
      <input type="hidden" name="id" value="${authRole.id}"/>
      <input type="hidden" name="enabled" value="${authRole.enabled}"/>
		<fieldset>
			<section>
				<div class="row">
					<label class="label col col-2">角色名称</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="name" value="${authRole.name}">
						</label>
					</div>
				</div>
			</section>
			<section>
				<div class="row">
					<label class="label col col-2">描述</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="description" value="${authRole.description}">
						</label>
						
					</div>
				</div>
			</section>
						<section>
				<div class="row">
					<label class="label col col-2">领取次数</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="lqcs" value="${authRole.lqcs}">
						</label>
						
					</div>
				</div>
			</section>
			
		</fieldset>

		<footer>
			<button type="button" class="btn btn-primary" onclick="editRole()">
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
	return $("#editRoleForm").validate({
		rules : {
			name : {required : true,maxlength: 50},
			description : {maxlength: 50}
		},
		messages : {
			name : {required : '请输入角色名称',maxlength:"长度不能超过50"},
			description : {maxlength: "长度过长"}
		},
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});
}

function editRole(){
	if(validateFrom().form()){
		var url="authRole/updateAuthRole";
		zfesAjax.ajaxTodo(url,$("#editRoleForm").serialize(), function(data){
			alertSwal.successText(data.message);
			$('#roleModal').modal('hide');
		})
	}
}
</script>