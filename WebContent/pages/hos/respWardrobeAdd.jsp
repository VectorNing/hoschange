<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:DataDict type="cloth" var="cloth"></t:DataDict>
<t:DataDict type="js" var="js"></t:DataDict>
<t:DataDict type="xb" var="xb"></t:DataDict>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">&times;</button>
	<h4 class="modal-title" id="myModalLabel">新增手术衣柜</h4>
</div>
<div class="modal-body no-padding">

	<form action="" id="addWardrobeForm" class="smart-form">

		<fieldset>
			<section>
				<div class="row">
					<label class="label col col-2">衣柜编号</label>
					<div class="col col-10">
						<label class="input"> <input type="text" name="number"
							id="number" onblur="selectNumber()"> <!--onblur="isExist()"  -->
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
						<label class="input"> <input type="text" name="model">
						</label>

					</div>
				</div>
			</section>

			<section>
				<div class="row">
					<label class="label col col-2">托盘数量</label>
					<div class="col col-10">
						<label class="input"> <input type="text" name="traySum">
						</label>

					</div>
				</div>
			</section>

			<section>
				<div class="row">
					<label class="label col col-2">托盘容量</label>
					<div class="col col-10">
						<label class="input"> <input type="text" name="total">
						</label>

					</div>
				</div>
			</section>

			<section>
				<div class="row">
					<label class="label col col-2">手术区</label>
					<div class="col col-10" id="theater_addTheaterNumber">
						<label class="select"> <km-select host="baseServer"
								url="hosTheater/loadTheByNumberForDict" id="theaterNumber"
								name="theaterNumber" label=""></km-select>
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
						<label class="input"> <input type="text"
							name="description">
						</label>

					</div>
				</div>
			</section>


			<!-- <section>
				<div class="row">
					<label class="label col col-2">手术室</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="theaterNumber" >
						</label>
						
					</div>
				</div>
			</section> -->

		</fieldset>

		<footer>
			<button type="button" id="saveBtn" class="btn btn-primary"
				onclick="addWardrobe()">保存</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">
				取消</button>
		</footer>

	</form>
</div>
<script type="text/javascript" src="js/dict.js"></script>
<script>
	function validateWardrobe() {
		return $("#addWardrobeForm").validate({
			rules : {
				number : {
					required : true,
					maxlength : 100
				},
				model : {
					required : true,
					maxlength : 100
				},
				traySum : {
					required : true,
					maxlength : 20
				},
				total : {
					required : true,
					maxlength : 20
				}
			},

			messages : {
				number : {
					required : '请输入衣柜编号'
				},
				model : {
					required : '请输入衣柜型号'
				},
				traySum : {
					required : '请输入托盘数量'
				},
				total : {
					required : '请输入总容量'
				}
			},

			errorPlacement : function(error, element) {
				error.insertAfter(element.parent());
			}
		});
	}

	$(function() {
		validateWardrobe();
		ZVue.init();
	});

	function selectNumber() {
		var url = "hosWardrobe/selectWarByNumber"
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

	function addWardrobe() {
		var url = "hosWardrobe/selectWarByNumber"
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
				if (validateWardrobe().form()) {
					disabledBtn($("#saveBtn"));
					var url = "hosWardrobe/addHosWardrobe";
					zfesAjax.ajaxTodo(url, $("#addWardrobeForm").serialize(),
							function(data) {
								enabledBtn($("#saveBtn"));
								if (data.statusCode != 200) {
									alertSwal.errorTitle(data.message);
								} else {
									alertSwal.successText(data.message);
								}
								$('#wardrobeModal').modal('hide');
							});
				}
			}

		});

	}

	function isExist() {
		var num = $("#number").val();
		var url = "";
		zfesAjax.ajaxTodo(url, {
			number : number
		}, function(data) {
			if (data.statusCode)
				$("#number_div").html("衣柜编号已存在，请重新输入！");
		});
	}
</script>