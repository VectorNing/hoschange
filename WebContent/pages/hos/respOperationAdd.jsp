<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">新增手术衣</h4>
</div>
<div class="modal-body no-padding">

	<form action="" id="addHosOperationForm" class="smart-form">

		<fieldset>
			<c:if test="${strategy == 2}">
				<section>
					<div class="row">
						<label class="label col col-2">编号</label>
						<div class="col col-10">
							<label class="input"> 
								<input type="text" name="number">
							</label>
							
						</div>
					</div>
				</section>
			</c:if>
			
			<section>
				<div class="row">
					<label class="label col col-2">尺码</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="clothSize">
						</label>
					</div>
				</div>
			</section>

			
			<section>
				<div class="row">
					<label class="label col col-2">数量</label>
					<div class="col col-10">
						<!-- <div class="well">
							<div class="input-append spinner" data-trigger="spinner">
						          <input type="text" value="1" name="count" data-rule="quantity">
						          <div class="add-on">
						            <a href="javascript:;" class="spin-up" data-spin="up"><i class="icon-sort-up"></i></a>
						            <a href="javascript:;" class="spin-down" data-spin="down"><i class="icon-sort-down"></i></a>
						          </div>
						     </div>
						  </div>    -->
						     <label class="input"> 
						     <input type="text" value="1" name="count">
						</label>
					</div>
				</div>
			</section>
			
		</fieldset>

		<footer>
			<button type="button" class="btn btn-primary" onclick="saveOperation();">
				保存
			</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">
				取消
			</button>
		</footer>

	</form>
</div>
<script>
$("#addHosOperationForm").validate({
	// Rules for form validation
	rules : {
		clothSize : {
			required : true
		},
		code : {
			required : true
		},
		count : {
			required : true
		}
	},

	// Messages for form validation
	messages : {
		clothSize : {
			required : '请输入尺码'
		},
		code : {
			required : '请输入编号'
		},
		count : {
			required : '请输入数量'
		}
	},

	// Do not change code below
	errorPlacement : function(error, element) {
		error.insertAfter(element.parent());
	}
});

function saveOperation(){
	var ajaxUrl = "hosOperation/addHosOperation";
	zfesAjax.ajaxTodo(ajaxUrl, $("#addHosOperationForm").serialize(), function(data) {
		//showBox.success(data.message);
		$('#operationModal').modal('hide');
		zfesBstable.refresh(jqTableDom);
	});
}
</script>