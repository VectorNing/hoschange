<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:DataDict type="cloth" var="cloth"></t:DataDict>
<t:DataDict type="js" var="js"></t:DataDict>
<t:DataDict type="xb" var="xb"></t:DataDict>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">&times;</button>
	<h4 class="modal-title" id="myModalLabel">编辑回收桶</h4>
</div>
<input type="hidden" value="${hosRecycle.type}" name="recycleType" id="recycleType" />
<div class="modal-body no-padding">

	<form action="" id="editRecycleForm" class="smart-form">
		<input type="hidden" value="${hosRecycle.id}" name="id" id="id_hidden" />
		<input type="hidden" value="${hosRecycle.state}" name="state" /> <input
			type="hidden" value="${hosRecycle.number}" name="oldNumber" /> <input
			type="hidden" value="${hosRecycle.recycle}" name="recycle" />
		<fieldset>
			<section>
				<div class="row">
					<label class="label col col-2">编号</label>
					<div class="col col-10">
						<label class="input"> <input type="text" name="number"
							value="${hosRecycle.number}" id="number" onblur="selectNumber()">
						</label>
						<div>
							<span id="message_span"></span>
						</div>
					</div>
				</div>
			</section>
			<section>
				<div class="row">
					<label class="label col col-2">存放位置</label>
					<div class="col col-10" id="theater_asetrhhh">
						<label class="select"> <km-select host="baseServer"
								url="hosTheater/loadTheByNumberForDict" id="theaterNumber"
								name="theNumber" value="${hosRecycle.theNumber}" lable=""></km-select>
						</label>
					</div>
				</div>
			</section>

			<section>
				<div class="row">
					<label class="label col col-2">回收类别</label>
					<div class="col col-10" id="recycleType">
						<label class="select"> <select name="type" id="recycleSelect">
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
						<label class="input"> <input type="text"
							name="description" value="${hosRecycle.description}">
						</label>

					</div>
				</div>
			</section>

		</fieldset>

		<footer>
			<button type="button" id="saveBtn" class="btn btn-primary"
				onclick="updateSterilizer();">保存</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">
				取消</button>
		</footer>

	</form>
</div>
<script>
	function validateSterilizer() {
		return $("#editRecycleForm").validate({
			rules : {
				number : {
					required : true,
					maxlength : 100
				},
			},

			messages : {
				number : {
					required : '请输入回收桶编号'
				},
			},

			errorPlacement : function(error, element) {
				error.insertAfter(element.parent());
			}
		});
	}

	$(function() {
		validateSterilizer();
		ZVue.init();
		selectType();
	});
	
	function selectType(){
		var type=$("#recycleType").val();
		$("#recycleSelect").val(type);
	}

	function selectNumber() {
		var url = "hosRecycle/selectRecycleByNumber"
		var number = $("#number").val();
		var id = $("#id_hidden").val();
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

	function updateSterilizer() {
		var url = "hosRecycle/selectRecycleByNumber"
		var number = $("#number").val();
		var id = $("#id_hidden").val();
		var params = {
			number : number,
			id : id
		};
		zfesAjax.ajaxTodo(url, params, function(data) {
			var count = data.strData;
			if (count > 0) {
				$("#message_span").html("编号重复，请重新输入");
				return false;
			} else {
				if (validateSterilizer().form()) {
					disabledBtn($("#saveBtn"));
					var ajaxUrl = "hosRecycle/updateHosRecycle";
					zfesAjax.ajaxTodo(ajaxUrl, $("#editRecycleForm")
							.serialize(), function(data) {
						enabledBtn($("#saveBtn"));
						alertSwal.successText(data.message);
						$('#recycleModal').modal('hide');
					});
				}
			}

		});
	}
</script>