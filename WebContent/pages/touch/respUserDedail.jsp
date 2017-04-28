<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:DataDict type="xb" var="xb"></t:DataDict>
<t:DataDict type="shoes" var="shoes"></t:DataDict>
<t:DataDict type="cloth" var="cloth"></t:DataDict>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">用户详情</h4>
</div>
<div class="modal-body no-padding">
<div class="row">
<div class="col-md-4">
	<div style="width: 120px;height: 148px;border: 1px solid red; margin-left: 30px;padding: 2px;margin-top: 20px;" >
		<img name="showimg1" id="showimg1" src="${pageContext.request.contextPath}/libs/img/people.png" width="116px" height="144px" alt="照片"/> 
	</div>
</div>
<div class="col-md-7">
<form action="" id="doctorForm" class="smart-form">
		<fieldset>
			<section>
				<div class="row">
					<label class="label col col-3">姓名</label>
					<div class="col col-9">
						<label class="">
							王子麒
						</label>
					</div>
				</div>
			</section>

			
			<section>
				<div class="row">
					<label class="label col col-3">性别</label>
					<div class="col col-9">
						<label class=""> 
						 男
						</label>
						
					</div>
				</div>
			</section>
			
			<section>
				<div class="row">
					<label class="label col col-3">手术信息</label>
					<div class="col col-9">
						<label class=""> 
						心脏手术
						</label>
					</div>
				</div>
			</section>
			
			<section>
				<div class="row">
					<label class="label col col-3">手术时间</label>
					<div class="col col-9">
						<label class=""> 
						2016年11月10日 上午  9：40 
						</label>
						
					</div>
				</div>
			</section>
			
			<section>
				<div class="row">
					<label class="label col col-3">手术室</label>
					<div class="col col-9">
						<label class=""> 
						二楼一号手术室
						</label>
						
					</div>
				</div>
			</section>
			
		</fieldset>


		<footer>
			<img  id="zzff" src="${pageContext.request.contextPath}/libs/img/jdt.gif" width="64" height="64"/> 
		</footer>
	</form>
</div>
	</div>
</div>
<script type="text/javascript" src="js/dict.js"></script>
<script>
</script>