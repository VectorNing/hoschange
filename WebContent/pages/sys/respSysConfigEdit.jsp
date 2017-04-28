<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/dict.js"></script>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">&times;</button>
	<h4 class="modal-title" id="myModalLabel">修改<c:out value="${sysConfig.name}"></c:out>配置</h4>
</div>
<div class="modal-body no-padding">

	<form action="" id="editSysConfigForm" class="smart-form">
		<input type="hidden" name="id" value="${sysConfig.id}">
		<input type="hidden" name="configName" value="${sysConfig.configName}">
		<fieldset>
			<c:choose>
				<c:when test="${sysConfig.configName=='isOrNoRoster' }">
					<section>
						<div class="row">
							<label class="label col col-2">当前配置</label>
							<div class="col col-10">
								<label class="input"> <input type="text"
									name="nowConfig" value="${sysConfig.nowConfig}">
								</label>

							</div>
						</div>
					</section>
					<br>
					<section>
						<div class="row">
							<label class="label col col-2">修改配置</label>
							<div class="col col-10">
								<label class="select"> <select name="config"
									id="select">
										<!-- <option value="0">请选择</option> -->
										<option value="0">关闭</option>
										<option value="1">开启</option>
								</select>
								</label>
							</div>
						</div>
					</section>
				</c:when>
				
				
					<c:when test="${sysConfig.configName=='yesOrNoHaveShoeBox' }">
					<section>
						<div class="row">
							<label class="label col col-2">当前配置</label>
							<div class="col col-10">
								<label class="input"> <input type="text"
									name="nowConfig" value="${sysConfig.nowConfig}">
								</label>

							</div>
						</div>
					</section>
					<br>
					<section>
						<div class="row">
							<label class="label col col-2">修改配置</label>
							<div class="col col-10">
								<label class="select"> <select name="config"
									id="select">
										<!-- <option value="0">请选择</option> -->
										<option value="0">无</option>
										<option value="1">有</option>
								</select>
								</label>
							</div>
						</div>
					</section>
				</c:when>
				
				
					<c:when test="${sysConfig.configName=='isOrNotRecycled' }">
					<section>
						<div class="row">
							<label class="label col col-2">当前配置</label>
							<div class="col col-10">
								<label class="input"> <input type="text"
									name="nowConfig" value="${sysConfig.nowConfig}">
								</label>

							</div>
						</div>
					</section>
					<br>
					<section>
						<div class="row">
							<label class="label col col-2">修改配置</label>
							<div class="col col-10">
								<label class="select"> <select name="config"
									id="select">
										<!-- <option value="0">请选择</option> -->
										<option value="0">不可回收</option>
										<option value="1">可回收</option>
								</select>
								</label>
							</div>
						</div>
					</section>
				</c:when>
				
				
					<c:when test="${sysConfig.configName=='brushCardTime' }">
					<section>
						<div class="row">
							<label class="label col col-2">当前配置</label>
							<div class="col col-10">
								<label class="input"> <input type="text"
									name="config" value="${sysConfig.config}">
								</label>

							</div>
						</div>
					</section>
				</c:when>
			</c:choose>
		</fieldset>

		<footer>
			<button type="button" class="btn btn-primary"
				onclick="updateSysConfig();">保存</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">
				取消</button>
		</footer>

	</form>
</div>
<script>
	function validateFrom() {
		return $("#editSysConfigForm").validate({
			rules : {
				sums : {
					required : true,
					maxlength : 50
				},
				rosterTime : {
					required : true,
					maxlength : 50
				}
			},
			messages : {
				sums : {
					required : '请输入次数'
				},
				rosterTime : {
					required : '请输入时间段(天)'
				}
			},
			errorPlacement : function(error, element) {
				error.insertAfter(element.parent());
			}
		});
	}

	$(function() {
		validateFrom();
	});

	function updateSysConfig() {
		if (validateFrom().form()) {
			var ajaxUrl = "sys/updateSysConfig";
			zfesAjax.ajaxTodo(ajaxUrl, $("#editSysConfigForm").serialize(),
					function(data) {
						alertSwal.successText(data.message);
						$('#sysConfigModal').modal('hide');
						window.location.reload();
					});
		}
	}
</script>