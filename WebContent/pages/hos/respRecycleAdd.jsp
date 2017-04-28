<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:DataDict type="cloth" var="cloth"></t:DataDict>
<t:DataDict type="js" var="js"></t:DataDict>
<t:DataDict type="xb" var="xb"></t:DataDict>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">&times;</button>
	<h4 class="modal-title" id="myModalLabel">新增回收桶</h4>
</div>
<div class="modal-body no-padding">
	<div class="box-header with-border">
		<h4 class="box-title">&nbsp;</h4>
	</div>
	<form action="" id="addRecycleForm" class="smart-form">
		<fieldset>
			<section>
				<div class="row">
					<label class="label col col-2">编号</label>
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
					<label class="label col col-2">存放位置</label>
					<div class="col col-10" id="theater_asfasfasaa">
						<label class="select"> <km-select host="baseServer"
								url="hosTheater/loadTheByNumberForDict" id="theaterNumber"
								name="theNumber" vmodel="theaterNumber" label=""></km-select>
						</label>
					</div>
				</div>
			</section>

			<section>
				<div class="row">
					<label class="label col col-2">回收类别</label>
					<div class="col col-10" id="recycleType">
						<label class="select"> <select name="type" id="roomId">
								<option value="0">消毒鞋</option>
								<option value="1">手术衣</option>
								<option value="2">收纳盒</option>
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
				onclick="saveRecycle();">保存</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">
				取消</button>
		</footer>

	</form>
</div>
<script>
	function validateSterilizer() {
		return $("#addRecycleForm").validate({
			rules : {
				number : {
					required : true,
					maxlength : 50
				}
			},

			messages : {
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
		var url = "hosRecycle/selectRecycleByNumber"
		var number = $("#number").val();
		var params = {
			number : number
		};
		zfesAjax.ajaxTodo(url, params, function(data) {
			var count = data.strData;
			if (count > 0) {
				$("#message_span").html("编号重复，请重新输入");
			} else {
				$("#message_span").html("");
			}

		});
	}

	function saveRecycle() {
		var url = "hosRecycle/selectRecycleByNumber";
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
					var ajaxUrl = "hosRecycle/addHosRecycle";
					zfesAjax.ajaxTodo(ajaxUrl,
							$("#addRecycleForm").serialize(), function(data) {
								enabledBtn($("#saveBtn"));
								alertSwal.successText(data.message);
								$('#recycleModal').modal('hide');
							});
				}
			}
		});
	}
</script>