<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:DataDict type="cloth" var="cloth"></t:DataDict>
<t:DataDict type="js" var="js"></t:DataDict>
<t:DataDict type="xb" var="xb"></t:DataDict>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">编辑储物柜</h4>
</div>
<input type="hidden" name="roomIdd" id="roomIdd" value="${hosRoom.name}"/>
<div class="modal-body no-padding">

	<form action="" id="editSterilizerForm" class="smart-form">
      <input type="hidden" value="${hosClothesPress.id}" name="id" id="id_hidden"/>
      <input type="hidden" value="${hosClothesPress.state}" name="state"/>
      <input type="hidden" value="${hosClothesPress.number}" name="oldNumber"/>
      <input type="hidden" value="${hosClothesPress.theaterNumber}" name="oldTheaterNumber"/>
		<fieldset>
			<section>
				<div class="row">
					<label class="label col col-2">编号</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="number" value="${hosClothesPress.number}" id="number" onblur="selectNumber()">
						</label>
						<div>
					       <span id="message_span"></span>
					    </div>
					</div>
				</div>
			</section>
			
			<section>
				<div class="row">
					<label class="label col col-2">手术区</label>
					<div class="col col-10" id="theater_asetrhhh">
						<label class="select"> 
							<km-select host="baseServer"  url="hosTheater/loadTheByNumberForDict" id="theaterNumber" name="theaterNumber" value="${hosClothesPress.theaterNumber}" lable=""></km-select>
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
							<input type="text" name="description" value="${hosClothesPress.description}">
						</label>
						
					</div>
				</div>
			</section>
		</fieldset>

		<footer>
			<button type="button" id="saveBtn" class="btn btn-primary" onclick="updateSterilizer();">
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
	return $("#editSterilizerForm").validate({
		rules : {
			number : {required : true,maxlength : 100},
		},

		messages : {
			number : {required : '请输入消毒鞋柜编号'},
		},

		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});
}

$(function(){
	validateSterilizer();
	ZVue.init();
	selectRoom();
});

function selectRoom(){
	var roomId=$("#roomIdd").val();
	$("#roomIds").val(roomId);
}
function updateSterilizer(){
		var url="hosClothesPress/selectClothesPressByNumber"
		var number=$("#number").val();
		var id=$("#id_hidden").val();
		var params={number:number,id:id};
		zfesAjax.ajaxTodo(url, params, function(data) {
			var count = data.strData;
			 if(count>0){
				$("#message_span").html("编号重复，请重新输入");
				return false;
			 }else{ 
				 if(validateSterilizer().form()){
						disabledBtn($("#saveBtn"));
						var ajaxUrl = "hosClothesPress/updateClothesPress";
						zfesAjax.ajaxTodo(ajaxUrl, $("#editSterilizerForm").serialize(), function(data) {
							enabledBtn($("#saveBtn"));	
							alertSwal.successText(data.message);
							$('#clothesPressModal').modal('hide');
						});
					}
	 		 }

		}); 
}

</script>