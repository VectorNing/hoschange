<%@ page contentType="text/html; charset=utf-8" language="java"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">新增手术区</h4>
</div>
<div class="modal-body no-padding">

	<form action="" id="addTheaterForm" class="smart-form">

		<fieldset>
			<section>
				<div class="row">
					<label class="label col col-2">手术区名称</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="name" >
						</label>
					</div>
				</div>
			</section>
			
			<section>
				<div class="row">
					<label class="label col col-2">手术区编号</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="number" id="number" onblur="selectNumber()">
						</label>
						<div>
					       <span id="message_span"></span>
					    </div>
						
					</div>
				</div>
			</section>
			<section>
				<div class="row">
					<label class="label col col-2">位置</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="address" >
						</label>
						
					</div>
				</div>
			</section>
			<section>
				<div class="row">
					<label class="label col col-2">描述</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="description" >
						</label>
						
					</div>
				</div>
			</section>
			
		</fieldset>

		<footer>
			<button type="button" class="btn btn-primary" onclick="addTheater()">
				保存
			</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">
				取消
			</button>
		</footer>

	</form>
</div>
<script>
function validateTheater(){
	return $("#addTheaterForm").validate({
		rules : {
			number : {required : true,maxlength : 100},
			name : {required : true,maxlength : 50}
		},

		messages : {
			number : {required : '请输入手术区编号'},
			name : {required : '请输入手术区名称'}
		},

		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});
}

$(function(){
	validateTheater();
});

function selectNumber(){
	var url="hosTheater/selectTheByNumber"
	var number=$("#number").val();
	var params={number:number};
	zfesAjax.ajaxTodo(url, params, function(data) {
		var count = data.strData;
		 if(count>0){
			$("#message_span").html("编号重复，请重新输入");
		 }else{
			$("#message_span").html("");
		 }

	});
}

function addTheater(){
	var url="hosTheater/selectTheByNumber"
		var number=$("#number").val();
		var params={number:number};
		zfesAjax.ajaxTodo(url, params, function(data) {
			var count = data.strData;
			 if(count>0){
				$("#message_span").html("编号重复，请重新输入");
				alet;
				return false;
			 }else{
				 if(validateTheater().form()){
						var url="hosTheater/addHosTheater";
						zfesAjax.ajaxTodo(url,$("#addTheaterForm").serialize(), function(data){
							if(data.statusCode!=200){
								alertSwal.errorTitle(data.message);
							}else{
								alertSwal.successText(data.message);
							}
							$('#theaterModal').modal('hide');
						});
					}
			 }
		});
}
</script>