<%@ page contentType="text/html; charset=utf-8" language="java"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">新增菜单</h4>
</div>
<div class="modal-body no-padding">

	<form action="" id="addUserForm" class="smart-form">

		<fieldset>
			<section>
				<div class="row">
					<label class="label col col-2">菜单名称</label>
					<div class="col col-10">
						<label class="input"> <i class="icon-append fa fa-user"></i>
							<input type="text" name="username">
						</label>
					</div>
				</div>
			</section>

			<section>
				<div class="row">
					<label class="label col col-2">编码</label>
					<div class="col col-10">
						<label class="input"> <i class="icon-append fa fa-user"></i>
							<input type="text" name="password">
						</label>
						
					</div>
				</div>
			</section>
			
			<section>
				<div class="row">
					<label class="label col col-2">排列顺序</label>
					<div class="col col-10">
						<label class="input"> <i class="icon-append fa fa-user"></i>
							<input type="text" name="password">
						</label>
						
					</div>
				</div>
			</section>
			
			
		</fieldset>

		<footer>
			<button type="submit" class="btn btn-primary">
				保存
			</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">
				取消
			</button>
		</footer>

	</form>
</div>
<script>
$("#addUserForm").validate({
	// Rules for form validation
	rules : {
		username : {
			required : true
		},
		password : {
			required : true,
			minlength : 3,
			maxlength : 20
		}
	},

	// Messages for form validation
	messages : {
		username : {
			required : '请输入用户名'
		},
		password : {
			required : '请输入密码'
		}
	},

	// Do not change code below
	errorPlacement : function(error, element) {
		error.insertAfter(element.parent());
	}
});
</script>