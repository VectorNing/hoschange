<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:DataDict type="js" var="js"></t:DataDict>
<t:DataDict type="xb" var="xb"></t:DataDict>
<t:DataDict type="shoes" var="shoes"></t:DataDict>
<t:DataDict type="cloth" var="cloth"></t:DataDict>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">编辑发衣柜</h4>
</div>
 <input type="hidden" name="roomIdd" id="roomIdd" value="${hosRoom.name}"/>
<div class="modal-body no-padding">

	<form action="" id="addUserForm" class="smart-form">
	  <input type="hidden" name="id" value="${hosWardrobe.id}" id="id_hidden"/>
	   <input type="hidden" name="state" value="${hosWardrobe.state}"/>
	   <input type="hidden" name="oldNumber" value="${hosWardrobe.number}"/>
	   <input type="hidden" name="oldTheaterNumber" value="${hosWardrobe.theaterNumber}"/>
		<fieldset>
			<section>
				<div class="row">
					<label class="label col col-2">编号</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="number" value="${hosWardrobe.number}" id="number" onblur="selectNumber()">
						</label>
						<div>
					       <span id="message_span"></span>
					    </div>
					</div>
				</div>
			</section>

            <section>
				<div class="row">
					<label class="label col col-2">型号</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="model" value="${hosWardrobe.model}">
						</label>
						
					</div>
				</div>
			</section>
			<section>
				<div class="row">
					<label class="label col col-2">手术区</label>
					<div class="col col-10" id="theater">
						<label class="select"> 
							<km-select host="baseServer"  url="hosTheater/loadTheByNumberForDict" id="theaterNumber" name="theaterNumber" value="${hosWardrobe.theaterNumber}" label=""></km-select>
						</label>	
					</div>
				</div>
			</section>
			
			<section>
				<div class="row">
					<label class="label col col-2">更衣室</label>
					<div class="col col-10" id="roomId">
						<label class="select"> <select name="roomId" id="roomIds">
								<option value="0">男更衣室</option>
								<option value="1">女更衣室</option>
						</select>
						</label>
					</div>
				</div>
			</section>
			
			<section>
				<div class="row">
					<label class="label col col-2">描述</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="description" value="${hosWardrobe.description}">
						</label>
						
					</div>
				</div>
			</section>
			<%-- <section>
				<div class="row">
					<label class="label col col-2">手术室</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="theaterNumber" value="${hosWardrobe.theaterNumber}">
						</label>
						
					</div>
				</div>
			</section> --%>
			
		</fieldset>

		<footer>
			<button type="button" id="saveBtn" class="btn btn-primary" onclick="editWardrobe();">
				保存
			</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">
				取消
			</button>
		</footer>

	</form>
</div>
<script>
function editWardrobe(){
	var url="hosWardrobe/selectWarByNumber"
		var number=$("#number").val();
		var id=$("#id_hidden").val();
		var params={number:number,id:id};
		zfesAjax.ajaxTodo(url, params, function(data) {
			var count = data.strData;
			 if(count>0){
				$("#message_span").html("编号重复，请重新输入");
				return false;
			 }else{
				 if(val().form()){
						disabledBtn($("#saveBtn"));
						var url="hosWardrobe/updateHosWardrobe";
						
						zfesAjax.ajaxTodo(url,$("#addUserForm").serialize(), function(data){
							enabledBtn($("#saveBtn"));
							alertSwal.successText(data.message);
							$('#wardrobeModal').modal('hide');
						});
					}
			 }
		});
}


$(function(){
	ZVue.init();
	val();
	selectRoom();
})

function selectRoom(){
	var roomId=$("#roomIdd").val();
	$("#roomIds").val(roomId);
}

function selectNumber(){
	var url="hosWardrobe/selectWarByNumber"
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

function val(){
	return $("#addUserForm").validate({
		rules : {
			number : {required : true}
			//description : {required : true,maxlength :255 }
		},

		messages : {
			number : {required : '请输入手术衣柜编号'},
		},

		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});
}

</script>