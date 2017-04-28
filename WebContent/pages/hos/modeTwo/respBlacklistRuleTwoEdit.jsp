<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<t:DataDict type="shoes" var="shoes"></t:DataDict>
<script type="text/javascript" src="js/dict.js"></script>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">修改规则</h4>
</div>
<div class="modal-body no-padding">

	<form action="" id="editBlackListForm" class="smart-form">
	    <input type="hidden" name="id" value="${blacklistRule.id}">
		<fieldset>
			<section>
				<div class="row">
					<label class="label col col-2">时间段(天)</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="rosterTime" value="${blacklistRule.rosterTime}" >
						</label>
						
					</div>
				</div>
			</section>
			<section>
				<div class="row">
					<label class="label col col-2">次数限制</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="sums" value="${blacklistRule.sums}" >
						</label>
						
					</div>
				</div>
			</section>
			
		</fieldset>

		<footer>
			<button type="button" class="btn btn-primary" onclick="updateBlack();">
				保存
			</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">
				取消
			</button>
		</footer>

	</form>
</div>
<script>
function validateFrom(){
	return $("#editBlackListForm").validate({
		rules : {
			sums : {required : true,maxlength : 50},
			rosterTime : {required : true,maxlength : 50}
		},
		messages : {
			sums : {required : '请输入次数'},
			rosterTime : {required : '请输入时间段(天)'}
		},
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});
}

$(function(){
	validateFrom();
});

function updateBlack(){
	if(validateFrom().form()){
		var ajaxUrl = "hosBlack/updateBlacklistRuleM2";
		zfesAjax.ajaxTodo(ajaxUrl, $("#editBlackListForm").serialize(), function(data) {
			alertSwal.successText(data.message);
			$('#ruleModal').modal('hide');
		});
	}
}
</script>