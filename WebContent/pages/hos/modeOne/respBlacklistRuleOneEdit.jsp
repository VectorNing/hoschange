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
					<label class="label col col-2">次数限制</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="sums" value="${blacklistRule.sums}" id="sums">
						</label>
						
					</div>
				</div>
				<br>
				<div class="row">
					<label class="label col col-2">最近次数</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="latelyNum" value="${blacklistRule.latelyNum}" id="latelyNum">
						</label>
						
					</div>
				</div>
			</section>
			
		</fieldset>

		<footer>
			<button type="button" class="btn btn-primary" onclick="updateShoes();">
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
	var latelyNum=parseInt($("#latelyNum").val());
	return $("#editBlackListForm").validate({
		rules : {
			sums : {required : true,maxlength : 50,max:latelyNum,min:0},
			latelyNum:{required :true,maxlength : 50}	
		},
		messages : {
			sums : {required : '请输入次数',max:'不能大于最近次数',min:'不得小于0'},
			latelyNum:{required : '请输入次数'}
		},
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});
}

$(function(){
	validateFrom();
});

function updateShoes(){
	if(validateFrom().form()){
		var ajaxUrl = "hosBlack/updateBlacklistRuleM1";
		zfesAjax.ajaxTodo(ajaxUrl, $("#editBlackListForm").serialize(), function(data) {
			alertSwal.successText(data.message);
			$('#ruleModal').modal('hide');
		});
	}
}
</script>