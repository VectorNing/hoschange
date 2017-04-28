zfesBstable={
		laodTable:function(jqTableDom,jqFormDom,jqToolbarDom,url,columnx,bstOptions){
				var otptionx=$.extend({
							url:url,
							method:"post",
							dataType:"json",
							classes:"table",
							striped:true,//设置为 true 会有隔行变色效果
							search:false,
							showRefresh:true,//显示刷新按钮
							showToggle:true,
							showColumns:true,//显示隐藏列
						    queryParams: function(params){
						    	var formDataArray = zfesUtil.formToJson(jqFormDom);
						    	var queryParams={rows:params.pageSize,page:params.pageNumber,sort:params.sortName,order:params.sortOrder};$.extend(queryParams,formDataArray);
						    	return queryParams;
						    },
							queryParamsType:"limitx",//limit:queryParams={limit, offset, search, sort, order}，，
							//没有的话：queryParams={pageSize, pageNumber, searchText, sortName, sortOrder. }
							contentType: "application/x-www-form-urlencoded",
							singleSelect:false,
							clickToSelect:true,
							pagination: true,
							showPaginationSwitch:false,
							silent: true,  //刷新事件必须设置
							sidePagination:"server",//"serever" "client"
							pageSize:10,
							pageList: [10, 20, 50, 100, 200, 500],
							iconSize:"outline",
							icons:{refresh:"glyphicon glyphicon-repeat",toggle:"glyphicon glyphicon-list-alt",columns:"glyphicon glyphicon-list"},
							idField:'id',
							toolbar:jqToolbarDom,
							columns:columnx
				},bstOptions);
				var bstable=jqTableDom.bootstrapTable('destroy').bootstrapTable(otptionx);
				return  bstable[0];
		},
		
		laodTableTwo:function(jqTableDom,jqFormDom,jqToolbarDom,url,columnx,bstOptions){
			var otptionx=$.extend({
						url:url,
						method:"get",
						dataType:"json",
						classes:"table",
						striped:true,//设置为 true 会有隔行变色效果
						search:false,
						showRefresh:true,//显示刷新按钮
						showToggle:true,
						showColumns:true,//显示隐藏列
					    queryParams: function(params){
					    	var formDataArray = zfesUtil.formToJson(jqFormDom);
					    	var queryParams={rows:params.pageSize,page:params.pageNumber,sort:params.sortName,order:params.sortOrder};$.extend(queryParams,formDataArray);
					    	return queryParams;
					    },
						queryParamsType:"limitx",//limit:queryParams={limit, offset, search, sort, order}，，
						//没有的话：queryParams={pageSize, pageNumber, searchText, sortName, sortOrder. }
						contentType: "application/x-www-form-urlencoded",
						singleSelect:false,
						clickToSelect:true,
						pagination: true,
						showPaginationSwitch:false,
						silent: true,  //刷新事件必须设置
						sidePagination:"server",//"serever" "client"
						pageSize:10,
						pageList: [10, 20, 50, 100, 200, 500],
						iconSize:"outline",
						icons:{refresh:"glyphicon glyphicon-repeat",toggle:"glyphicon glyphicon-list-alt",columns:"glyphicon glyphicon-list"},
						idField:'id',
						toolbar:jqToolbarDom,
						columns:columnx
			},bstOptions);
			var bstable=jqTableDom.bootstrapTable('destroy').bootstrapTable(otptionx);
			return  bstable[0];
	},
		
		reload:function(jqTableDom){
			var getOptions=jqTableDom.bootstrapTable("getOptions");
			jqTableDom.bootstrapTable("refreshOptions",$.extend(getOptions.queryParams,{pageNumber:1}));//重构table即可
		},
		refresh:function(jqTableDom){
			var getOptions=jqTableDom.bootstrapTable("getOptions");
			jqTableDom.bootstrapTable("refreshOptions",$.extend(getOptions.queryParams,{}));//重构table即可
		},
		reloadByParm:function(jqTableDom,param){
			if(zfesUtil.isStrNull(param)){
				param={};
			}
			jqTableDom.bootstrapTable("refresh",{query: param});
		},
		getSelections:function(jqTableDom){
			 return jqTableDom.bootstrapTable("getSelections");
		},
		getAllSelections:function(jqTableDom){
			 return jqTableDom.bootstrapTable("getAllSelections");
		},
		getData:function(jqTableDom){
			 return jqTableDom.bootstrapTable("getData",true);
		},
		isOneRow:function(jqTableDom){
			 var row = zfesBstable.getSelections(jqTableDom);
		 	 if(row.length<1){
				 alertSwal.warningTitle("请选择要编辑的数据");
				 return false;
			 }
			 if(row.length>1){
				 alertSwal.warningTitle("请选择一条要编辑的数据");
			 	 return false;
			 }
			 return true;
		},
		isSelectedRow:function(jqTableDom){
			 var row = zfesBstable.getSelections(jqTableDom);
		 	 if(row.length<1){
				 alertSwal.warningTitle("请选择要编辑的数据");
				 return false;
			 }
			 return true;
		},
		getRowId:function(jqTableDom){
			var row = zfesBstable.getSelections(jqTableDom);
		 	 if(row.length<1){
				 alertSwal.warningTitle("请选择要编辑的数据");
				 return false;
			 }
			 if(row.length>1){
				 alertSwal.warningTitle("请选择一条要编辑的数据");
			 	 return false;
			 }
			 var id=row[0].id;
			 return id;
		},
		getRowIds:function(jqTableDom){
			var row = zfesBstable.getSelections(jqTableDom);
		 	 if(row.length<1){
				 alertSwal.warningTitle("请选择要编辑的数据");
				 return false;
			 }
			 var idArray = [];
			 $.each(row, function(index, item){
				idArray.push(item.id);
			 });
			return idArray;
		},
		getRowIds:function(jqTableDom,paramName){
			var row = zfesBstable.getSelections(jqTableDom);
		 	 if(row.length<1){
				 alertSwal.warningTitle("请选择要编辑的数据");
				 return false;
			 }
			 var idArray = [];
			 $.each(row, function(index, item){
				idArray.push(item.id);
			 });
			return idArray;
		}
}	 

