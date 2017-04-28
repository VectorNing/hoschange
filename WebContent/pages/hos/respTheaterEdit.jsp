<%@ page contentType="text/html; charset=utf-8" language="java"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">编辑手术区</h4>
</div>
<div class="modal-body no-padding">

	<form action="" id="editTheaterForm" class="smart-form">
       <input type="hidden" name="id" value="${hosTheater.id}" id="id_hidden"/> 
       <input type="hidden" name="state" value="${hosTheater.state}"/>
       <input type="hidden" name="oldNumber" value="${hosTheater.number}"/>
		<fieldset>
			<section>
				<div class="row">
					<label class="label col col-2">手术区名称</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="name" value="${hosTheater.name }" >
						</label>
					</div>
				</div>
			</section>
            <section>
				<div class="row">
					<label class="label col col-2">手术区编号</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="number" value="${hosTheater.number }" id="number" onblur="selectNumber()">
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
							<input type="text" name="address" value="${hosTheater.address }" >
						</label>
						
					</div>
				</div>
			</section>
			<section>
				<div class="row">
					<label class="label col col-2">描述</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="description" value="${hosTheater.description }" >
						</label>
						
					</div>
				</div>
			</section>
			
		</fieldset>

		<footer>
			<button type="button" class="btn btn-primary" onclick="editTheater();">
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
	return $("#editTheaterForm").validate({
		rules : {
			number : {required : true,maxlength : 100},
			name : {required : true,maxlength : 50}
		},

		messages : {number : {required : '请输入手术区编号'},
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
	var id=$("#id_hidden").val();
	var params={number:number,id:id};
	zfesAjax.ajaxTodo(url, params, function(data) {
		var count = data.strData;
		 if(count>0){
			$("#message_span").html("编号重复，请重新输入");
		 }else{
			$("#message_span").html("");
		 }

	});
}

function editTheater(){
	var url="hosTheater/selectTheByNumber"
		var number=$("#number").val();
		var id=$("#id_hidden").val();
		var params={number:number,id:id};
		zfesAjax.ajaxTodo(url, params, function(data) {
			var count = data.strData;
			 if(count>0){
				$("#message_span").html("编号重复，请重新输入");
				return false;
			 }else{
				 if(validateTheater().form()){
						var url="hosTheater/updateHosTheater";
						zfesAjax.ajaxTodo(url,$("#editTheaterForm").serialize(), function(data){
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