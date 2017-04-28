<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<t:DataDict type="shoes" var="shoes"></t:DataDict>
<script type="text/javascript" src="js/dict.js"></script>
<div class="modal-header">
	<button type="button" class="shoe" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">设定尺码</h4>
</div>
<div class="modal-body no-padding">

	<form action="" id="addShoeRuleSetSizeForm" class="smart-form">

		<fieldset>
			<input type="hidden" value="${code}" id="code" name="code">
			<section>
				<div class="row">
					<label class="label col col-2">尺码</label>
					<div class="col col-10">
						<label class="select"> 
							<select dict="shoes" name="shoesSize" id="shoesSize">
							</select>
						</label>
						
					</div>
				</div>
			</section>
		</fieldset>
		<footer>
			<button type="button" class="btn btn-primary" onclick="addShoeRule();">
				保存
			</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">
				取消
			</button>
		</footer>

	</form>
</div>
<script>
$(function(){
	ZVue.init();
});

function addShoeRule(){
		var ajaxUrl = "hosSterilizer/addShoeRule";
		zfesAjax.ajaxTodo(ajaxUrl, $("#addShoeRuleSetSizeForm").serialize(), function(data) {
			alertSwal.successText(data.message);
			$('#ruleModal').modal('hide');
		});
}
</script>