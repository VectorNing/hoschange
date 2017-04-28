var showBox={
	dismissTimer:4000,
	success: function(text){
		$.smallBox({
			title : "操作成功",
			content : text,
			color : "#739E73",
			iconSmall : "fa fa-check",
			timeout : this.dismissTimer
		});
	},
	warning: function(text){
		$.smallBox({
			title : "警告",
			content : text,
			color : "#C79121",
			iconSmall : "fa fa-shield fadeInLeft animated",
			timeout : this.dismissTimer
		});
	},
	error: function(text){
		$.smallBox({
			title : "操作失败",
			content : text,
			color : "#C46A69",
			iconSmall : "fa fa-warning shake animated",
			timeout : this.dismissTimer
		});
	},
	confirm: function(obj,successFn,failFn){
		$.SmartMessageBox({
			title : obj.title || (typeof obj == 'string' && obj) ||  '请确认',
			content : obj.content || '',
			buttons : '[确定][取消]'
		}, function(ButtonPressed) {
			if (ButtonPressed === "确定") {
				typeof successFn != 'undefined' && successFn();
			}
			if (ButtonPressed === "取消") {
				typeof failFn != 'undefined' && failFn();
			}
		});
	}
}