
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ include file="/libs/includeall.jsp"%>
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/libs/css/touch3.css">
<script src="${pageContext.request.contextPath}/js/dict.js"></script>

<t:DataDict type="shoes" var="shoes"></t:DataDict>
<t:DataDict type="cloth" var="cloth"></t:DataDict>

<html>
<head>
<meta charset="UTF-8">
<title>神思旭辉</title>
</head>
<body class="signin fixed-header minified">
	<header id="header">
		<div id="logo-group" style="width: 99%">
			<span id="logo"> <img
				src="${pageContext.request.contextPath}/libs/img/logo.png"
				alt="SmartAdmin"></span> <span style="float: right; margin: 7px;">
				<a class="btn btn-primary btn-rounded " href="javascript:goIndex();">返回首页</a>
			</span> <span style="float: right; margin: 7px;"> <a
				class="btn btn-primary btn-rounded " href="javascript:goBack();">返回上一页</a>
			</span> 
			<span style="float: right; margin: 7px;">
			<a class="btn btn-success btn-rounded " id="selectAllCell" state="0"
				href="javascript:;"><input type = "button" style="float: right; margin: 7px;" value="选中所有"/></a>
				</span> 
		</div>
	</header>
	<div class="modal-body no-padding" id="main">

		<fieldset>
			<div class="row" style="margin: 10px;">
				<div class="col-md-12">
					<div
						style="height: 90%; overflow: auto; background-color: #000000; background-color: rgba(0, 0, 0, 0.2);">
						<div class="col-md-9">
							<c:forEach items="${lists}" var="r" varStatus="vs">
								<input type="hidden" value="${r.number}" id="steNumber_number">
								<c:forEach items="${r.hoss}" var="u" varStatus="vs">
									<div class="seatCharts-cell stenumber" data-id="${u.number}">
										<div class="seatCharts-cell seatCharts-seat">
											<div class="steTitle" style="position: relative">
												<button type="button" id="selectAllBtn" class="selectAll btn btn-success"
													data-id="${u.number}" state="0">空柜全选</button>
												<p>鞋柜</p>
												<b style="color: black">${u.number}</b>
											</div>
											<div data-id="${u.id}" data-state="${u.state}"
												data-col="${u.columns}" data-row="${u.rows}"
												class="seatCharts-cell seatCharts-seat available">
												<!-- 右侧checkbox -->
												<div class="colSelector" data-id="${u.number}"></div>
												<!-- 左侧checkbox  -->
												<div class="rowSelector" data-id="${u.number}"></div>
												<table class="tableshoe">
													<c:forEach items="${u.containerList}" var="co"
														varStatus="sa">
														<c:if test="sa.count == 1">
															<tr>
														</c:if>
														<td data-id="${co.id}"
															data-state="${co.shoesSize==''?'4':co.state}"
															data-size="${co.shoesSize}"
															data-number="${co.lockerNumber}"
															data-steNumber="${u.number}" data-number="${u.number}"
															data-row="${Math.ceil((sa.count)/(u.columns))}"
															data-col="${(sa.count)%(u.columns)}"
															data-size="${co.shoesSize}"
															class="seatCharts-cell seatCharts-seat cell-con">
															<p class="number">${co.lockerNumber}</p>
															<p class="size">
																<c:if test="${co.shoesSize == ''}"></c:if>
																<c:if test="${co.shoesSize !=null}">
																	<sx:dataDic type="shoes" value="${co.shoesSize}" />
																</c:if>
															</p> <!-- 小柜为空的状态 --> <c:if test="${co.state==0}">
																<div
																	class="seatCharts-cell-con seatCharts-seat-con available war">
																</div>
															</c:if> <!-- 小柜有鞋子状态 --> <c:if test="${co.state == 1}">
																<div
																	class="seatCharts-cell-con seatCharts-seat-con unavailable">
																</div>
															</c:if> <!-- 存放使用人鞋子的状态 --> <c:if test="${co.state == 2}">
																<div
																	class="seatCharts-cell-con seatCharts-seat-con unavailable war">
																</div>
															</c:if>
														</td>
														<c:if test="${(sa.count)%(u.columns)==0}">
															</tr>
															<tr>
														</c:if>
													</c:forEach>
												</table>
											</div>
										</div>
									</div>
								</c:forEach>
							</c:forEach>
						</div>
						<div class="resultBox">
							<table class="table table-bordered">
								<tr>
									<th>尺码</th>
									<c:forEach items="${hosShoesList}" var="hsl" varStatus="hs">
										<td><span id="shoes-name-${hsl.shoeSize }">${hsl.shoeSizeName}</span><input
											type="hidden" id="size" value="${hsl.shoeSize}" /></td>
									</c:forEach>
								</tr>
								<tr id="resultCountRow">
									<th>分配</th>
									<c:forEach items="${hosShoesList}" var="hsl" varStatus="hs">
										<td id="result-count-${hsl.shoeSize }"></td>
									</c:forEach>
								</tr>
							</table>
							<footer style="text-align: center; margin-bottom: 20px;"
								id="btns">
								<button type="0" class="btn btn-primary"
									onclick="allotShoesOne();" id="allotShoes_One">确定</button>
								<button type="0" class="btn btn-primary"
									onclick="allotShoesSubmit();" id="allotShoes_Two">分配</button>
								<button type="1" class="btn btn-primary" onclick="empty()" id="">清空小柜1</button>
								<button type="2" class="btn btn-primary" onclick="empty2()"
									id="">清空小柜</button>
							</footer>
						</div>
					</div>
				</div>
			</div>
		</fieldset>
	</div>
	<div class="pageUpDown">
		<button class="btn btn-primary" type="button" onclick="pageUp()">
			<i class="fa fa-arrow-up"> </i>
		</button>
		<br>
		<button class="btn btn-primary" type="button" onclick="pageDown()">
			<i class="fa fa-arrow-down"> </i>
		</button>
		<br>
	</div>
</body>

<script type="text/javascript">
	var idArr = [], aa = [];
	var lockerNumber = [],size= [],number = [],state=[];
	$('.war').hover(function() {
		$(this).addClass("focused");
	}, function() {
		$(this).removeClass("focused");
	});
	
	var selectedType = -1;
	$('.seatCharts-cell.seatCharts-seat.cell-con').click(function() {
		var cell = $(this);
		if(cell.attr('data-state')=='4'){
			alertSwal.warning("未分配尺码");
			return ;
		}
		if(selectedType == -1){  //未选择
			selectedType = cell.attr('data-state');
		}else if(selectedType != cell.attr('data-state')){  //选择不同分类的
			alertSwal.warning("请选择相同的类别");
			return;
		}
		cell.toggleClass('selected');
		if(selectedType == 0){
			var boxid = $(this).attr('data-steNumber');
			var row = $(this).attr('data-row');
			var col = $(this).attr('data-col');
			updateColAndRowSelector(boxid,col,row);
		}
		if( ( !cell.hasClass('selected')) && $('.seatCharts-cell.seatCharts-seat.cell-con.selected[data-state='+cell.attr('data-state')+']').length==0)
			selectedType = -1;
		
	});
	setInterval(function(){
		/* if( selectedType!=-1 )
			$('#btns').attr('class','btns-'+selectedType);
		else
			$('#btns').attr('class','btns-no'); */
		
		if(selectedType == 1 || selectedType ==2)
			$('#btns').attr('class','btns-2');
		else if(selectedType ==-1)
			$('#btns').attr('class','btns-no');
		else
		$('#btns').attr('class','btns-'+selectedType);
		
	},500);
	//选中结果输出
	function getSelectCell(){
		var result=[];
		var list = $('.seatCharts-cell.selected');
		list.each(function(){
			var boxid = $(this).attr('data-stenumber');
			var dataid = $(this).attr('data-id');
			var size = $(this).attr('data-size')
			result.push({boxid:boxid,id:dataid,size:size});
		});
		return result;
		
	}

	$('.selectAll').click(function(){
		var btnState = $(this).attr('state');
		if(selectedType != 0 && selectedType != -1){
			alertSwal.warning("请选择相同的类别");
			return ;
		}
		/* if(selectedType == 0){
			selectedType = -1;
		} */
		if(btnState == 0 && selectedType == -1){
			selectedType = 0;
		}
		
		//全选中按钮是选中时，把colcheckbox全部选中 state 0 空柜全选 1 取消全选
		var table = $(this).closest('.seatCharts-seat');
		var colCheckboxList = table.find('.colSelector').find('input');
		 for(var i=0; i<colCheckboxList.length; i++){
			if(btnState == 0){
				if(!$(colCheckboxList[i]).is(':checked'))
					$(colCheckboxList[i]).click();
			}else{
				if(!$(colCheckboxList[i]).is(':checked'))
					$(colCheckboxList[i]).click().click();
				else
					$(colCheckboxList[i]).click();

			}
		} 
		var boxnum=$(this).attr('data-id');
		updateAllSelectBtn(boxnum);
		if($('.seatCharts-cell.seatCharts-seat.cell-con.selected').length<=0){
			selectedType = -1;
		}
	});
	
	$('#selectAllCell').click(function(){
		var btnState = $(this).attr('state');
		var list = $('.selectAll');
		for(var i=0; i<list.length; i++){
			if($(list[i]).attr('state')==btnState)
				$(list[i]).click();
		}
		/* if(btnState != 0 ){
			selectedType = -1;
		} */
			
	});
	
	function changeAllSelectState(boxnum,state){
		var btn = $('.selectAll[data-id='+boxnum+']');
		btn.attr('state',state);
		if(state==0)
			btn.html('空柜全选');
		else
			btn.html('取消全选');
	}
	//如果有checkbox没有被选中就返回false全选按钮显示为不是全选状态
	function isSelectAll(boxnum){
		var checkList = $('.seatCharts-cell[data-id='+boxnum+']').find('.colSelector').find('input');
		for(var i=0; i<checkList.length; i++){
			if(!$(checkList[i]).is(':checked') && !$(checkList[i]).attr("disabled"))
				return false;
		}
		return true;
	}
	//colcheckbox被打勾则选中该列所有空柜
	function selectCol(boxnum,colnum){
		if(selectedType != 0 && selectedType != -1){
			alert('不同分类a');
			return ;
		}
		var selectCells = $('.seatCharts-cell.seatCharts-seat.cell-con[data-stenumber='+boxnum+'][data-col='+colnum+'][data-state=0]');
		//把未选中的状态改为选中的
		if(selectCells.filter('[data-state=0]').length>0){
			selectedType = 0;
			selectCells.filter('[data-state=0]').addClass('selected');
		}
	}
	function unSelectCol(boxnum,colnum){
		var selectCells = $('.seatCharts-cell.seatCharts-seat.cell-con[data-stenumber='+boxnum+'][data-col='+colnum+'][data-state=0]');
		selectCells.removeClass('selected');
	}
	//rowSelect
	function changeRowSelectState(boxnum){
		var rowSelectorCheckbox = $('.rowSelector[data-id='+boxnum+'] input');
		for(var i=0; i<rowSelectorCheckbox.length; i++){
			var rowIndex = $(rowSelectorCheckbox[i]).val();
			if(isRowAllSelected(boxnum,rowIndex)){
				setRowCheckState(boxnum,rowIndex,true);
			}
			 
			else
				setRowCheckState(boxnum,rowIndex,false);
		}
	}
	//colSelect
	function changeColSelectState(boxnum){
		var colSelectorCheckbox = $('.colSelector[data-id='+boxnum+'] input');
		for(var i=0; i<colSelectorCheckbox.length; i++){
			var colIndex = $(colSelectorCheckbox[i]).val();
			if(isColAllSelected(boxnum,colIndex))
				setColCheckState(boxnum,colIndex,true);
			else
				setColCheckState(boxnum,colIndex,false);
		}
	}
	function selectRow(boxnum,rownum){
		if(selectedType != 0 && selectedType != -1){
			alert('不同分类b');
			return ;
		}
		var selectCells = $('.seatCharts-cell.seatCharts-seat.cell-con[data-stenumber='+boxnum+'][data-row="'+rownum+'"][data-state=0]');
		if(selectCells.filter('[data-state=0]').length>0){
			selectedType = 0;
			selectCells.filter('[data-state=0]').addClass('selected');
		}
	}
	function unSelectRow(boxnum,rownum){
		var selectCells = $('.seatCharts-cell.seatCharts-seat.cell-con[data-stenumber='+boxnum+'][data-row="'+rownum+'"][data-state=0]');
		selectCells.removeClass('selected');
		
		if($('.seatCharts-cell.seatCharts-seat.cell-con.selected[data-state=0]').length == 0)
			selectedType = -1;
	}
	
	function setRowCheckState(boxnum,rownum,state){
		var checkbox = $('.seatCharts-cell[data-id='+boxnum+']').find('.rowSelector').find('[value="'+rownum+'"]');
		checkbox.prop('checked',state);
		updateAllSelectBtn(boxnum);
	}
	function setColCheckState(boxnum,colnum,state){
		var checkbox = $('.seatCharts-cell[data-id='+boxnum+']').find('.colSelector').find('[value='+colnum+']');
		checkbox.prop('checked',state);
		
		updateAllSelectBtn(boxnum);
		if($('.seatCharts-cell.seatCharts-seat.cell-con.selected[data-state=0]').length == 0)
				selectedType = -1;
	}
	
	/* col全选  */
	function isColAllSelected(boxnum,colnum){
		var cellList = $('[data-stenumber='+boxnum+'][data-col='+colnum+']');
		var countcol = 0;
		var selcol = 0;
		for(var i=0; i<cellList.length; i++){
			if($(cellList[i]).attr('data-state')==0){
				countcol ++;
				if($(cellList[i]).hasClass('selected')){
					selcol ++;
				}
			}
		}
		/* alert("第 " + colnum + "列：" + "空柜" + countcol + "选中" + selcol); */
		if(countcol == selcol){
			if(countcol !=0){
				return true;
			}
		}
		return false;
	}
	/* row全选   data-state  有鞋:1，没鞋:0，使用中:2 */
	/*当row中有空柜并全选中的时候checkbox打对勾，没有空柜或空柜没有全选中则不打勾*/
	function isRowAllSelected(boxnum,rownum){
		var cellList = $('[data-stenumber='+boxnum+'][data-row="'+rownum+'"]');
		var countrow = 0;
		var selrow = 0;
		for(var i=0; i<cellList.length; i++){
			if($(cellList[i]).attr('data-state')==0){
				countrow ++;
				if($(cellList[i]).hasClass('selected')){
					selrow ++;
				}
			}
		}
		if(countrow == selrow){
			if(countrow !=0){
				return true;
			}
		}
		return false;
	}
	//点击小鞋柜后调用方法，判断是否要给checkbox改变状态
	function updateColAndRowSelector(boxid,colnum,rownum){
		if(isColAllSelected(boxid,colnum)){
			setColCheckState(boxid,colnum,true);
		}else{
			setColCheckState(boxid,colnum,false);
		}
		if(isRowAllSelected(boxid,rownum)){
			setRowCheckState(boxid,rownum,true);
		}else{
			setRowCheckState(boxid,rownum,false);
			}
	}
	
	function updateAllSelectBtn(boxnum){
		if(isSelectAll(boxnum)){
			changeAllSelectState(boxnum,1);
		}else{
			changeAllSelectState(boxnum,0);
		}
		
		if(isAllCellSelect()){
			updateAllCellSelectState(1);
		}else{
			updateAllCellSelectState(0);
		}
	}
	
	function isAllCellSelect(){
		var list = $('.selectAll');
		for(var i=0; i<list.length; i++){
			if($(list[i]).attr('state')==0)
				return false;
		}
		return true;
	}
	
	function updateAllCellSelectState(state){
		var btn=$('#selectAllCell');
		btn.attr('state',state);
		if(state==1){
			btn.html('取消全选');			
		}else{
			btn.html('空柜全选');
		}
		calcResultCount();
	}
	
	function calcResultCount(){
		var result = getSizeCountMap();
		$('#resultCountRow td').html('');
		for(var i in result){
			$('#result-count-'+i).html(result[i]);
		}
	}
	//获取尺码与数量对应json数组
	function getSizeCountMap(){
		var boxList = getSelectCell();
		var result = {};
		boxList.forEach(function(box){
			if(typeof result[box.size] == 'undefined')
				result[box.size]=0
			result[box.size]++;
		})
		return result;
	}
	//获取选中小柜id数组	
	function getSelectIds(){
		var ids = [];
		$('.seatCharts-cell.seatCharts-seat.cell-con.selected').each(function(){
			ids.push($(this).attr('data-id'));
		})
		return ids;
	}
	//获取选中小柜编号
	function getSelectNumbers(){
		var obj = new Object();
		$('.seatCharts-cell.stenumber').each(function(){
			var numberss = [];
			$(this).find('.seatCharts-cell.seatCharts-seat.cell-con.selected').each(function(){
				numberss.push($(this).attr('data-number'));
			})
			obj[$(this).attr('data-id')]=numberss;
		})
		return JSON.stringify(obj);
	}
	//本地存储保存选中小柜id数组
	function saveSelectedLocal(){
		localStorage.setItem('sterilizerLocal',JSON.stringify(getSelectIds()));
	}
	//加载本地存储保存的选中小柜id数组 存在返回true 无内容加载返回false
	function loadSelectedLocal(){
		if(localStorage.getItem('sterilizerLocal')){
			var list = JSON.parse(localStorage.getItem('sterilizerLocal'));
			for(var i=0; i<list.length; i++){
				$('.seatCharts-cell.seatCharts-seat.cell-con[data-id='+list[i]+']').click();
			}
			return true;
		}
		return false;
	}
	//清空本地存储保存选中小柜id数组
	function clearSelectedLocal(){
		localStorage.removeItem('sterilizerLocal');
		/* location.reload(); */
		/* $(td).removeClass("selected"); 
			这里是重新分配。可以加上清空数据的同时把所有选中的小柜取消选中
		*/
	}
	
	$(function(){
		var table = $('.seatCharts-cell.seatCharts-seat.available');
		for(var i=0; i<table.length; i++){
			var oneTable = $(table[i]);
			var colnums = parseInt(oneTable.attr('data-col'));
			var rownums = parseInt(oneTable.attr('data-row'));
			for(var j=1; j<=colnums; j++){
				var colnum = (j==colnums) ? 0 : j;
				var cabinets = $(oneTable).find('[data-col='+colnum+']');
				var disabled = true;
				for(var k = 0;k<=cabinets.length;k++){
					if($(cabinets[k]).attr('data-state')==0){
						disabled = false;
					}
				}
				$('<input>').attr({type:'checkbox',id: colnum })
							.val(colnum)
							.attr('disabled',disabled)
							.appendTo(oneTable.find('.colSelector'));
			}
			for(var j=1; j<=rownums; j++){
				var rownum = j;
				var rows = rownum + '.0';
				 var cabinets = $(oneTable).find('[data-row="'+ rows +'"]');
				var disabled = true;
				for(var k =0;k<=cabinets.length;k++){
					if($(cabinets[k]).attr('data-state')==0){
						disabled = false;
					}
				} 
				$('<input>').attr({type:'checkbox',id: rownum + '.0'})
							.val(rows)
							 .attr('disabled',disabled) 
							.appendTo(oneTable.find('.rowSelector'));
			} 
			var inputNumber = $(oneTable).find(".colSelector").find(":enabled");
			if(inputNumber.length == 0){//判断是否需要禁用鞋柜上的全选按钮
				$(oneTable).parent().find(".steTitle").find(":button").hide();
			}
		}
		var selectAllBtnbutton = $('.selectAll.btn.btn-success');
		var selectAllCellButton = true;
		for(var i=0; i<selectAllBtnbutton.length; i++){
			var onebutton = $(selectAllBtnbutton[i]);
			if(!onebutton.attr("disabled")){
				selectAllCellButton=false;
			}
		}
		if(selectAllCellButton){//判断是否需要禁用总的全选按钮
			$('#selectAllCell').attr('disabled',true);
			$('#selectAllCell').hide();
		}
		$('.colSelector>input').change(function(e){
			var boxnum = $(this).closest('.colSelector').attr('data-id');
			var colnum = $(this).val();
			if($(this).is(':checked')){
				selectCol(boxnum,colnum);
				if(isSelectAll(boxnum)){
					changeAllSelectState(boxnum,1);
				}
			}else{
				unSelectCol(boxnum,colnum);
				changeAllSelectState(boxnum,0);
				if($('.seatCharts-cell.seatCharts-seat.cell-con[data-state=0]').length==0)
					selectedType = -1;
			}
			changeRowSelectState(boxnum);
		});
		$('.rowSelector>input').click(function(e){
			if(($(this).is(':checked'))&& selectedType!=0 && selectedType!=-1){
				e.preventDefault(); 
				alertSwal.warning("请选择相同的类别");
			}
		});
		$('.colSelector>input').click(function(e){
			if(($(this).is(':checked'))&& selectedType!=0 && selectedType!=-1){
				e.preventDefault(); 
				alertSwal.warning("请选择相同的类别");
			}
		});
		$('.rowSelector>input').change(function(e){
			
			var boxnum = $(this).closest('.rowSelector').attr('data-id');
			var rownum = $(this).val();
			if($(this).is(':checked')){
				selectRow(boxnum,rownum);
				if(isSelectAll(boxnum)){
					changeAllSelectState(boxnum,1);
				}
			}else{
				unSelectRow(boxnum,rownum);
				changeAllSelectState(boxnum,0);
			}
			changeColSelectState(boxnum);
		});
		
		if(loadSelectedLocal()){//true 第二次进入
			swal({
				title:"确认分配",
				text:"请确认分配信息是否需要修改",
				type: "warning",
				showCancelButton: true,
				showConfirmButton: true,
				closeOnConfirm: false,
				confirmButtonText: "确认分配",
				cancelButtonText: "修改分配信息",
				confirmButtonColor: "#ec6c62"
			},function(){
				allotShoesSubmit()
				});
		
			$("#allotShoes_One").hide();
			$("#allotShoes_Two").show();
			//$("#allotShoes_Two_cli").hide();
		}else{ //false 第一次进入
			if(!$('#selectAllCell').attr("disabled")){
				$('#selectAllCell').click();
			}
			$("#allotShoes_One").show();
			$("#allotShoes_Two").hide();
		//	$("#allotShoes_Two_cli").hide();
		}
		
	});
	
	//================================业务部分============================================//
	
	//第一次确认分配  保存本地，返回刷卡界面
	function allotShoesOne(){
		/* saveSelectedLocal();
		var ids = getSelectIds();
		ids = ids.join(",");
		var params = {"ids":ids};
		var ajaxUrl = "${pageContext.request.contextPath}/touch/allotShoesOne";
		zfesAjax.ajaxTodo(ajaxUrl, params, function(data) {
			swal({title:'请在设备分放相对应的手术鞋，完成后请再次确认!',type:"success",timer: 4000});
			setTimeout(goIndex,5000);
		}); */
		saveSelectedLocal();
		var steNumber_number = $("#steNumber_number").val();
		var ws = new WebSocket("ws://localhost:8080/hoschange/SocketServer?machineNumber="+steNumber_number);
		ws.onopen=function(event){
			ws.send(getSelectNumbers());
		}
		swal({title:'请在设备分放相对应的手术鞋，完成后请再次确认!',type:"success",timer: 4000});
		setTimeout(goIndex,5000);
	}
	
	//第二次确认 保存分配
	function allotShoesSubmit(){
		/* if(!validateAllot()){
			validateAllot();
// 			alertSwal.warning('手术鞋剩余数量不足，请查看');
			return ;
		} */
		var ids = getSelectIds();
		var sizeCountMap = getSizeCountMap();
		var jsonArray = [];
		var steNumber = $("#steNumber_number").val();
		for(var i in sizeCountMap){
			jsonArray.push({
				size:i,
				count:sizeCountMap[i]
			})
		}
		jsonArray = JSON.stringify(jsonArray);
		ids = ids.join(",");
		var params = {"ids":ids,"jsonArray":jsonArray};
		var ajaxUrl = "${pageContext.request.contextPath}/touch/allotShoesToSterilizerContainerForZD";
		zfesAjax.ajaxTodo(ajaxUrl, params, function(data) {
			alertSwal.successText(data.message);
			clearSelectedLocal();//清空缓存
			window.location.href="${pageContext.request.contextPath}/touch/allotSterilizer?number="+steNumber;
		});
	} 
	
	
	
	function empty(){
		var ids = getSelectIds();
		ids = ids.join(",");
		if(ids[0] == null || ids[0] == ""){
			msg = "请选择要清除的小柜";
			alertSwal.warning(msg);
			return false;
		}
		var steNumber = $("#steNumber_number").val();
		alertSwal.confirm("清空", "是否确认清空选中的消毒小柜?", function(isConfirm) {
			if(isConfirm){
				var ajaxUrl = "${pageContext.request.contextPath}/touch/emptyContainerShoes";
				var param = {"ids":ids};
				zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
					alertSwal.successText(data.message);
					location.reload();
// 					window.location.href="${pageContext.request.contextPath}/touch/allotSterilizer?number="+steNumber;
				});
			}
		});
	}
	function empty2(){
		var ids = getSelectIds();
		ids = ids.join(",");
		if(ids[0] == null || ids[0] == ""){
			msg = "请选择要清除的小柜";
			alertSwal.warning(msg);
			return false;
		}
		var steNumber = $("#steNumber_number").val();
		alertSwal.confirm("清空", "是否确认清空选中的消毒小柜?", function(isConfirm) {
			if(isConfirm){
				var ajaxUrl = "${pageContext.request.contextPath}/touch/emptyContainerShoes2";
				var param = {"ids":ids};
				zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
					alertSwal.successText(data.message);
					location.reload();
// 					window.location.href="${pageContext.request.contextPath}/touch/allotSterilizer?number="+steNumber;
				});
			}
		});
	}
	function emptyAll(){
		var steNumber = $("#steNumber_number").val();
		alertSwal.confirm("清空", "是否确认清空该消毒柜?", function(isConfirm) {
			if(isConfirm){
				var number=$("#steNumber_number").val();
				var ajaxUrl = "${pageContext.request.contextPath}/touch/emptyTouchAllSteContainer";
				var param = {"number":number};
				zfesAjax.ajaxTodo(ajaxUrl, param, function(data) {
					alertSwal.successText(data.message);
					window.location.href="${pageContext.request.contextPath}/touch/allotSterilizer?number="+steNumber;
				});
			}
		});
	}
	function goBack(){
	    window.history.go(-1);
	}
	function goIndex(){
		window.location.href="${pageContext.request.contextPath}/touch/enterTouchScreenSterilizer"
	}
	
	function pageUp(){
		$('html body').scrollTop($('html body').scrollTop()-500);
	}
	function pageDown(){
		$('html body').scrollTop($('html body').scrollTop()+500);
	}
	
	function validateAllot(){
		var flag = 0;
		var name = "";
		$('[id^=canAllot-]').each(function(){
			var id = $(this).attr('id').split('-')[1];
			var canAllot = parseInt($(this).html()) ? parseInt($(this).html()) : 0;//可分
			var count = parseInt($('#result-count-'+id).html()) ? parseInt($('#result-count-'+id).html()) : 0;
			var shoesName = $('#shoes-name-'+id).html()
			if (!(canAllot >= count)){
				name = shoesName;
				flag = 1;
			}
		})
		if(flag==1){
			alertSwal.warning(name+'手术鞋剩余数量不足，请查看');
			return false;//不足
		}
		return true;
	}
</script>
</html>