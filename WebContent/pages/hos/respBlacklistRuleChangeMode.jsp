<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:DataDict type="shoes" var="shoes"></t:DataDict>
<script type="text/javascript" src="js/dict.js"></script>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">&times;</button>
	<h4 class="modal-title" id="myModalLabel">修改模式</h4>
</div>
<div class="modal-body no-padding">

	<form action="" id="editBlackListForm" class="smart-form">
		<fieldset>
			<section>
				<div class="row">
					<label class="label col col-2">当前模式</label>
					<c:choose>
						<c:when test="${mode==1 }">
							<div class="col col-10">
								<label class="input"> <input type="text"
									name="oldRosterModel" value="模式一">
								</label>
							</div>
						</c:when>
						<c:otherwise>
							<div class="col col-10">
								<label class="input"> <input type="text"
									name="oldRosterModel" value="模式二">
								</label>
							</div>
						</c:otherwise>
					</c:choose>
				</div>

				<br>

				<div class="row">
					<label class="label col col-2">修改模式</label>
					<div class="col col-10">
						<label class="select"> <select name="rosterModel"
							id="select">
								<option value="0">请选择</option>
								<option value="1">模式一</option>
								<option value="2">模式二</option>
						</select>
						</label>
					</div>
				</div>
			</section>

		</fieldset>

		<footer>
			<button type="button" class="btn btn-primary" onclick="updateMode();">修改</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">
				取消</button>
		</footer>

	</form>
</div>
<script>
	/* function validateFrom() {
		return $("#editBlackListForm").validate({
			rules : {
				sums : {
					required : true,
					maxlength : 50
				}
			},
			messages : {
				sums : {
					required : '请输入次数'
				}
			},
			errorPlacement : function(error, element) {
				error.insertAfter(element.parent());
			}
		});
	} */
	function validateFrom(flag) {
		var val = $("#select").val();
		if (val == '0') {
			flag = false;
		}
		return flag;
	}
	$(function() {
	});
	function updateMode() {
		var flag = true;
		flag = validateFrom(flag);
		if (flag) {
			var ajaxUrl = "hosBlack/updateBlacklistRuleMode";

			zfesAjax.ajaxTodo(ajaxUrl, $("#editBlackListForm").serialize(),
					function(data) {
						alertSwal.successText(data.message);
						$('#ruleModal').modal('hide');
						window.location.reload();
						//window.location.href = window.location.href;
					});
		} else {
			alertSwal.warningText("请选择模式");
		}

	}
</script>