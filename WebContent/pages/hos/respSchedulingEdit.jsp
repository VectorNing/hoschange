<%@ page contentType="text/html; charset=utf-8" language="java"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">新增手术排班</h4>
</div>
<div class="modal-body no-padding">

	<form action="" id="addUserForm" class="smart-form">

		<fieldset>
		
			<section>
				<div class="row">
					<label class="label col col-2">手术名称</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="name">
						</label>
					</div>
				</div>
			</section>
			
			<section>
				<div class="row">
					<label class="label col col-2">起始时间</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="starttime">
						</label>
					</div>
				</div>
			</section>

			<section>
				<div class="row">
					<label class="label col col-2">结束时间</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="endtime">
						</label>
					</div>
				</div>
			</section>
			<section>
				<div class="row">
					<label class="label col col-2">手术室</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="thId">
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
		number : {
			required : true
		},
		code : {
			required : true
		},
		count : {
			required : true
		}
	},

	// Messages for form validation
	messages : {
		number : {
			required : '请输入尺码'
		},
		code : {
			required : '请输入编号'
		},
		count : {
			required : '请输入数量'
		}
	},

	// Do not change code below
	errorPlacement : function(error, element) {
		error.insertAfter(element.parent());
	}
});
</script>