<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">托盘维护</h4>
</div>
<div class="modal-body no-padding">

	<form action="" id="addContaineForm" class="smart-form">
     <input type="hidden" name="number" value="${hosWardrobe.number }" />
     <input type="hidden" name="total" value="${hosWardrobe.total }" />
      <input type="hidden" name="traySum" value="${hosWardrobe.traySum }" />
		<fieldset>
			<c:forEach items="${con}"  varStatus="s" var="i"><!-- begin="1" end="${hosWardrobe.traySum}" -->
			   <input type="hidden" name="id${s.index+1}" value="${i.id}"/>
			   <section>
				<div class="row">
					<label class="label col col-2">托盘  ${s.index+1}编号:</label>
					<div class="col col-10">
						<label class="input"> 
							<input  data-number="${s.index+1}" type="text" name="trayNumber${s.index+1}" value="${i.trayNumber}" >
						</label>
						<div>
					       <span class="war" id="message_span${s.index+1}"></span>
					    </div>
					</div>
				</div>
			 </section>
			</c:forEach>
			<!-- <section>
				<div class="row">
					<label class="label col col-2">托盘数量</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="traySum" >
						</label>
						
					</div>
				</div>
			</section>
			
			<section>
				<div class="row">
					<label class="label col col-2">托盘容量</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="total" >
						</label>
						
					</div>
				</div>
			</section> -->
		</fieldset>

		<footer>
			<button type="button" class="btn btn-primary" onclick="addContaine()">
				生成
			</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">
				取消
			</button>
		</footer>

	</form>
</div>
<script>
$(".input").children("input").blur(function(){
	var num = $(".input").children("input").length;
	for(var i=0;i<num;i++){
		var aa = $.trim($(this).val());
		if(aa == null || aa == ""){
			return ;
		}
		var bb = $.trim($(".input").children("input").not($(this)).eq(i).val());
// 		console.log( $(".input").children("input").not($(this)).eq(i).val());
		var index = $(this).attr("data-number");
		var spanId = "#message_span"+index;
		if(aa==bb){5
			$(spanId).html("编号不能相同");
			return false;
		}else{
			$(spanId).html("");
		}
	}
})
function selectNum(){
	console.log($(".input"));
	var num = $(".input").children("input").length;
	for(var i=0;i<num;i++){
		$(".input").children("input").eq(i)
	}
// 	$(".input").find('')
}


function validateContaine(){
	return $("#addContaineForm").validate({
		rules : {
			traySum : {required : true,maxlength : 20},
			total : {required : true,maxlength : 20}
		},

		messages : {
			traySum : {required : '请输入托盘数量'},
			total : {required : '请输入总容量'}
		},

		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});
}

$(function(){
	validateContaine();
});

function addContaine(){
	if(isNumber()){
		var url="hosWardrobe/addContaineForm";
		
		zfesAjax.ajaxTodo(url,$("#addContaineForm").serialize(), function(data){
			alertSwal.successText(data.message);
			$('#wardrobeModal').modal('hide');
		});
	}
}

function isNumber(){
	var num = $(".input").children("input").length;
	var flag = true;
	for(var i=0;i<num;i++){
		aa = $.trim($(".war").eq(i).html());
		if(aa != null && aa != ""){
			flag = false;
		}
	}
	for(var i=0;i<num;i++){
		var bb = $.trim($(".input").children("input").eq(i).val());
		var spanId = "#message_span"+(i+1);
		if(bb==null || bb==""){
			$(spanId).html("编号不能为空");
			flag = false;
		}
	}
	return flag;
}

function isExist(){
	var num = $("#number").val();
	var url = "";
	zfesAjax.ajaxTodo(url,{number:number}, function(data){
		if(data.statusCode)
		$("#number_div").html("衣柜编号已存在，请重新输入！");
	});
}
</script>