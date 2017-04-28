	$(function(){
		var table = $('.seatCharts-cell.seatCharts-seat.available');
		for(var i=0; i<table.length; i++){
			var oneTable = $(table[i]);
			var columns = parseInt(oneTable.attr('data-col'));
			var rows = parseInt(oneTable.attr('data-row'));
			for(var j=1; j<=columns; j++){
				var colnum = (j==columns) ? 0 : j;
				$('<input>').attr('type','checkbox')
							.val(colnum)
							.appendTo(oneTable.find('.colSelector'));
			}
			for(var j=1; j<=rows; j++){
				$('<input>').attr('type','checkbox')
							.val(j+'.0')
							.appendTo(oneTable.find('.rowSelector'));
			}
			
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
			}
			changeRowSelectState(boxnum);
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
	});
	
	$('.seatCharts-cell.seatCharts-seat.cell-con').click(function() {
		var cell = $(this);
		if(cell.attr('data-state') == 0)
			cell.toggleClass('selected');
		var boxid = $(this).attr('data-steNumber');
		var row = $(this).attr('data-row');
		var col = $(this).attr('data-col');
		updateColAndRowSelector(boxid,col,row);
	});
	
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
/**/
	$('.selectAll').click(function(){
		var btnState = $(this).attr('state');
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
					$(colCheckboxList[i]).click()
			}
		}
		var boxnum=$(this).attr('data-id');
		updateAllSelectBtn(boxnum);
	});
	
	$('#selectAllCell').click(function(){
		var btnState = $(this).attr('state');
		var list = $('.selectAll');
		for(var i=0; i<list.length; i++){
			if($(list[i]).attr('state')==btnState)
				$(list[i]).click();
		}
	});
	
	
	function changeAllSelectState(boxnum,state){
		var btn = $('.selectAll[data-id='+boxnum+']');
		btn.attr('state',state);
		if(state==0)
			btn.html('全选');
		else
			btn.html('全部取消');
	}
	
	function isSelectAll(boxnum){
		var checkList = $('.seatCharts-cell[data-id='+boxnum+']').find('.colSelector').find('input');
		for(var i=0; i<checkList.length; i++){
			if(!$(checkList[i]).is(':checked'))
				return false;
		}
		return true;
	}
	
	function selectCol(boxnum,colnum){
		var selectCells = $('.seatCharts-cell.seatCharts-seat.cell-con[data-stenumber='+boxnum+'][data-col='+colnum+'][data-state=0]');
		selectCells.addClass('selected');
	}
	function unSelectCol(boxnum,colnum){
		var selectCells = $('.seatCharts-cell.seatCharts-seat.cell-con[data-stenumber='+boxnum+'][data-col='+colnum+'][data-state=0]');
		selectCells.removeClass('selected');
	}
	function changeRowSelectState(boxnum){
		var rowSelectorCheckbox = $('.rowSelector[data-id='+boxnum+'] input');
		for(var i=0; i<rowSelectorCheckbox.length; i++){
			var rowIndex = $(rowSelectorCheckbox[i]).val();
			if(isRowAllSelected(boxnum,rowIndex))
				setRowCheckState(boxnum,rowIndex,true);
			else
				setRowCheckState(boxnum,rowIndex,false);
		}
	}
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
		var selectCells = $('.seatCharts-cell.seatCharts-seat.cell-con[data-stenumber='+boxnum+'][data-row="'+rownum+'"][data-state=0]');
		selectCells.addClass('selected');
	}
	function unSelectRow(boxnum,rownum){
		var selectCells = $('.seatCharts-cell.seatCharts-seat.cell-con[data-stenumber='+boxnum+'][data-row="'+rownum+'"][data-state=0]');
		selectCells.removeClass('selected');
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
	}
	
	function isColAllSelected(boxnum,colnum){
		var cellList = $('[data-stenumber='+boxnum+'][data-col='+colnum+']');
		for(var i=0; i<cellList.length; i++){
			if((! $(cellList[i]).hasClass('selected')) && ( $(cellList[i]).attr('data-state')=='0') )
				return false;
		}
		return true;
	}
	
	function isRowAllSelected(boxnum,rownum){
		var cellList = $('[data-stenumber='+boxnum+'][data-row="'+rownum+'"]');
		for(var i=0; i<cellList.length; i++){
			if((! $(cellList[i]).hasClass('selected')) && ( $(cellList[i]).attr('data-state')=='0') )
				return false;
		}
		return true;
	}
	
	function updateColAndRowSelector(boxid,colnum,rownum){
		if(isColAllSelected(boxid,colnum))
			setColCheckState(boxid,colnum,true);
		else
			setColCheckState(boxid,colnum,false);
		if(isRowAllSelected(boxid,rownum))
			setRowCheckState(boxid,rownum,true);
		else
			setRowCheckState(boxid,rownum,false);
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
			btn.html('全部取消');			
		}else{
			btn.html('全选');
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
	function calcResultIds(){
		var result = getSizeCountMap();
		console.log(result);
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
	