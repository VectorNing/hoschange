<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:DataDict type="cloth" var="cloth"></t:DataDict>
<t:DataDict type="js" var="js"></t:DataDict>
<t:DataDict type="xb" var="xb"></t:DataDict>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">&times;</button>
	<h4 class="modal-title" id="myModalLabel">新增储物柜</h4>
</div>
<div class="modal-body no-padding">
	<div class="box-header with-border">
		<h4 class="box-title">&nbsp;</h4>
	</div>
	<form action="" id="addClothesPressForm" class="smart-form">
		<fieldset>
			<section>
				<div class="row">
					<label class="label col col-2">设备编号</label>
					<div class="col col-10">
						<input type="text" name="number" id="number" class="form-control"
							onblur="selectNumber()">
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
						<input type="text" name="startDoor" id="startDoor"
							class="form-control">
					</div>
				</div>
			</section>


			<section>
				<div class="row">
					<label class="label col col-2">总数</label>
					<div class="col col-10">
						<input type="text" name="total" id="total" class="form-control">
					</div>
				</div>
			</section>
			<section>
				<div class="row">
					<label class="label col col-2">手术区</label>
					<div class="col col-10" id="theater_asfasfasaa">
						<label class="select"> <km-select host="baseServer"
								url="hosTheater/loadTheByNumberForDict" id="theaterNumber"
								name="theaterNumber" vmodel="theaterNumber" label=""></km-select>
						</label>
					</div>
				</div>
			</section>

			<section>
				<div class="row">
					<label class="label col col-2">更衣室</label>
					<div class="col col-10" id="roomId">
						<label class="select"> <select name="roomId" id="roomId">
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
						<input type="text" name="description" id="description"
							class="form-control">
					</div>
				</div>
			</section>

		</fieldset>

		<footer>
			<button type="button" id="saveBtn" class="btn btn-primary"
				onclick="saveClothesPress();">保存</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">
				取消</button>
		</footer>

	</form>
</div>
<script>
	function validateSterilizer() {
		return $("#addClothesPressForm").validate({
			rules : {
				/* rows : {
					required : true,
					maxlength : 100
				},
				columns : {
					required : true,
					maxlength : 50
				}, */
				total : {
					required : true,
					maxlength : 50
				},
				number : {
					required : true,
					maxlength : 50
				}
			
			},

			messages : {
		/* 		rows : {
					required : '请输入消毒柜行数'
				},
				columns : {
					required : '请输入消毒柜列数'
				}, */
				total : {
					required : '请输入总数'
				},
				number : {
					required : '请输入编号'
				}
			},

			errorPlacement : function(error, element) {
				error.insertAfter(element.parent());
			}
		});
	}

	$(function() {
		validateSterilizer();
		ZVue.init();
	});
	function selectNumber() {
		var url = "hosClothesPress/selectClothesPressByNumber"
		var number = $("#number").val();
		var params = {
			number : number
		};
		zfesAjax.ajaxTodo(url, params, function(data) {
			var count = data.strData;
			console.info(count);
			if (count > 0) {
				$("#message_span").html("编号重复，请重新输入");
			} else {
				$("#message_span").html("");
			}

		});
	}

	function saveClothesPress() {
		var url = "hosClothesPress/selectClothesPressByNumber"
		var number = $("#number").val();
		var params = {
			number : number
		};
		zfesAjax.ajaxTodo(url, params, function(data) {
			var count = data.strData;
			if (count > 0) {
				$("#message_span").html("编号重复，请重新输入");
				return false;
			} else {
				if (validateSterilizer().form()) {
					disabledBtn($("#saveBtn"));
					var ajaxUrl = "hosClothesPress/addClothesPress";
					zfesAjax.ajaxTodo(ajaxUrl, $("#addClothesPressForm")
							.serialize(), function(data) {
						enabledBtn($("#saveBtn"));
						alertSwal.successText(data.message);
						$('#clothesPressModal').modal('hide');
					});
				}
			}
		});
	}
</script>