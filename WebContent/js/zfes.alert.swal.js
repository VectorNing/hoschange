alertSwal = {
	dismissTimer : 5000,
	_types : {
		error : "error",
		info : "info",
		warn : "warn",
		correct : "correct",
		confirm : "confirm"
	},
	simple : function(title, text) {
		swal({
			title : title,
			text : text
		});
	},
	simpleTitle : function(title) {
		swal({
			title : title
		});
	},
	simpleText : function(text) {
		swal({
			title : "提示信息",
			text : text
		});
	},
	info : function(title, text) {
		swal({
			title : title,
			text : text,
			type : "info"
		});
	},
	infoTitle : function(title) {
		swal({
			title : title,
			type : "info"
		});
	},
	infoText : function(text) {
		swal({
			title : "提示信息",
			text : text,
			type : "info"
		});
	},
	success : function(title, text) {
		swal({
			title : title,
			text : text,
			type : "success",
			timer : this.dismissTimer
		});
	},
	successTitle : function(title) {
		swal({
			title : title,
			type : "success",
			timer : this.dismissTimer
		});
	},
	successText : function(text) {
		swal({
			title : "成功提示",
			text : text,
			type : "success",
			timer : this.dismissTimer
		});
	},
	warning : function(title, text) {
		swal({
			title : title,
			text : text,
			type : "warning"
		});
	},
	warningTitle : function(title) {
		swal({
			title : title,
			type : "warning"
		});
	},
	warningText : function(text) {
		swal({
			title : "警告提示",
			text : text,
			type : "warning"
		});
	},
	error : function(title, text) {
		swal({
			title : title,
			text : text,
			type : "error"
		});
	},
	errorTitle : function(title) {
		swal({
			title : title,
			type : "error"
		});
	},
	errorText : function(text) {
		swal({
			title : "错误提示",
			text : text,
			type : "error"
		});
	},

	confirm : function(title, text, callback) {
		swal({
			title : title,
			text : text,
			type : "warning",
			showCancelButton : true,
			confirmButtonText : "确认",
			cancelButtonText : "取消",
			closeOnConfirm : true,
			closeOnCancel : true
		}, callback);
	},
	prompt : function(title, text, callback) {
		swal({
			title : title,
			text : text,
			type : "input",
			showCancelButton : true,
			closeOnConfirm : false,
			animation : "slide-from-top"
		}, callback)

	},
	promptTitle : function(title) {
		swal({
			title : title,
			type : "input",
			showCancelButton : true,
			closeOnConfirm : false,
			animation : "slide-from-top"
		}, callback)
	},
	promptText : function(text) {
		swal({
			title : "请输入",
			text : text,
			type : "input",
			showCancelButton : true,
			closeOnConfirm : false,
			animation : "slide-from-top"
		}, callback)
	},
	closeTime : function(title, text, second) {
		swal({
			title : title,
			text : text,
			timer : second * 1000,
			showConfirmButton : false

		});

	},
	
	confirmSuccess : function(title, text, callback) {
		swal({
			title : title,
			text : text,
			type : "success",
			confirmButtonText : "确认",
			closeOnConfirm : true,
			closeOnCancel : true
		}, callback);
	},

}
