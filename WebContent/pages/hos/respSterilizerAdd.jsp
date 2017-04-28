<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:DataDict type="cloth" var="cloth"></t:DataDict>
<t:DataDict type="js" var="js"></t:DataDict>
<t:DataDict type="xb" var="xb"></t:DataDict>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">新增消毒鞋柜</h4>
</div>
<div class="modal-body no-padding">
	<div class="box-header with-border">
         <h4 class="box-title">&nbsp;</h4>
    </div> 
	<form action="" id="addSterilizerForm" class="smart-form">
		<fieldset>
			<section>
				<div class="row">
					<label class="label col col-2">设备编号</label>
					<div class="col col-10">
						<input type="text" name="number" id="number" class="form-control" onblur="selectNumber()">
					</div>
					<div>
					    <span id="message_span"></span>
					</div>
				</div>
			</section>
			
			<section>
				<div class="row">
					<label class="label col col-2">起始门号</label>
					<div class="col col-10">
						<input type="text" name="startDoor" id="startDoor" class="form-control">
					</div>
				</div>
			</section>
			
			<section>
				<div class="row">
					<label class="label col col-2">小柜总数</label>
					<div class="col col-10">
						<input type="text" name="total" id="total" class="form-control">
					</div>
				</div>
			</section>
			
			<section>
				<div class="row">
					<label class="label col col-2">存放位置</label>
					<div class="col col-10" id="theater_asfasfasaa">
						<label class="select"> 
							<km-select host="baseServer"  url="hosTheater/loadTheByNumberForDict" id="theaterNumber" name="theaterNumber" vmodel="theaterNumber" label=""></km-select>
						</label>	
					</div>
				</div>
			</section>
			
			<section>
				<div class="row">
					<label class="label col col-2">描述</label>
					<div class="col col-10">
						<input type="text" name="description" id="description" class="form-control">
					</div>
				</div>
			</section>
			
		</fieldset>	
			
		<footer>
           <button type="button" id="saveBtn" class="btn btn-primary" onclick="saveSterilizer();">
				保存
			</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">
				取消
			</button>
        </footer> 
		
	</form>
</div>
<script>
function validateSterilizer(){
	return $("#addSterilizerForm").validate({
		rules : {
			rows : {required : true,maxlength : 100,min:1},
			columns : {required : true,maxlength : 50,min:1},
			number : {required : true,maxlength : 50}
		},

		messages : {
			rows : {required : '请输入消毒柜行数',min:"请输入合法数字"},
			columns : {required : '请输入消毒柜列数',min:"请输入合法数字"},
			number : {required : '请输入编号'}
		},

		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});
}

$(function(){
	validateSterilizer();
	ZVue.init();
});
function selectNumber(){
	var url="hosSterilizer/selectSteByNumber"
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

function saveSterilizer(){
		var url="hosSterilizer/selectSteByNumber"
		var number=$("#number").val();
		var params={number:number};
		zfesAjax.ajaxTodo(url, params, function(data) {
			var count = data.strData;
			 if(count>0){
				$("#message_span").html("编号重复，请重新输入");
				return false;
			 }else{
				 if(validateSterilizer().form()){
					 	disabledBtn($("#saveBtn"));
						var ajaxUrl = "hosSterilizer/addHosSterilizer";
						zfesAjax.ajaxTodo(ajaxUrl, $("#addSterilizerForm").serialize(), function(data) {
							enabledBtn($("#saveBtn"));
							alertSwal.successText(data.message);
							$('#sterilizerModal').modal('hide');
						});
					}
			 }
		});
}
</script>