var zfesAjax={
		validateFrom:function(form){
			var $form = $(form);
			return (zfesCore.validateType=="jquery")?$form.valid():$form.form('validate');
		},
		submitForm:function(form, callback){
			if(!zfesAjax.validateFrom(form)){
				return false;
			}
			var $form = $(form);
			var _submitFn=function(){
				var param=zfesUtil.formToJson($form);
				(zfesCore.ajaxVersion=="1.x")?ajaxTodo_old(url, param, callback):ajaxTodo(url, param, callback);
			}
			return true;
		},
		ajax:function(url, param, callback,fialCallback){
			$.ajax({
				type : 'POST',
				url : url,
				data : param,
				dataType : "json",
				cache : false,
				beforeSend: function(request){
					request.setRequestHeader("X-Requested-With_x_x", "XMLHttpRequest");
					if(sessionStorage){request.setRequestHeader("cs_token", sessionStorage.getItem("cs_token"));}
				}//token ...
			})
			.done(function(data){
				$.extend(data,{"handled":"true"});
				if(callback){
					callback(data);
				}else{
					alert("普通 ajax请求，必须自定义callback，此函数不予处理");
					//zfesCore.ajaxDone(data);
				}
			})
			.fail(function(jqXHR, textStatus, errorThrown){
				if(fialCallback){
					fialCallback(jqXHR, textStatus, errorThrown);
				}else{
					zfesCore.ajaxError(jqXHR, textStatus, errorThrown);
				}
			})
			.always(function(){})//do nothing
			.then(function(){});//do nothing
		},
	  ajaxHtml:function(url, param, callback,fialCallback){
		$.ajax({
			type : 'POST',
			url : url,
			data : param,
			dataType : "html",
			cache : true,
			beforeSend: function(request){
				request.setRequestHeader("X-Requested-With_x", "XMLHttpRequest");
				if(sessionStorage){request.setRequestHeader("cs_token", sessionStorage.getItem("cs_token"))}
			}
		})
		.done(function(data, textStatus, jqXHR){
			$.extend(data,{"handled":"true"});
			if(callback){
				callback(data, textStatus, jqXHR);
			}else{
				alert("普通 ajax请求，必须自定义callback，此函数不予处理");
				//zfesCore.ajaxDone(data);
			}
		})
		.fail(function(jqXHR, textStatus, errorThrown){
				if(fialCallback){
					fialCallback(jqXHR, textStatus, errorThrown);
				}else{
					zfesCore.ajaxError(jqXHR, textStatus, errorThrown);
				}
		})
		.always(function(){})//do nothing
		.then(function(){});//do nothing
	},
	ajaxTodo:function(url, param, callback,fialCallback){
		//if(!zfesApp.isSidSyn()){alert("系统初始化错误，请刷新后重试");return;}
		$.ajax({
			type : 'POST',
			url : url,
			data : param,
			dataType : "json",
			cache : false,
			beforeSend: function(request){
				request.setRequestHeader("X-Requested-With_x", "XMLHttpRequest");
				if(sessionStorage){request.setRequestHeader("cs_token", sessionStorage.getItem("cs_token"))}
			}//token ...
		})
		.done(function(data){
			$.extend(data,{"handled":"true"});
			if(data.statusCode==zfesCore.statusCode.success){
				callback(data)
			}else{
				if(fialCallback){
					fialCallback(data);
				}else{
					zfesCore.ajaxDone(data);
				}
			}
			//(data.statusCode==zfesCore.statusCode.success)?callback(data):(fialCallback?fialCallback:zfesCore.ajaxDone(data));
		})
		.fail(function(jqXHR, textStatus, errorThrown){
			zfesCore.ajaxError(jqXHR, textStatus, errorThrown);
		})
		.always(function(){})//do nothing
		.then(function(){});//do nothing
	},
	
	ajaxTodo_old:function(url, param, callback,fialCallback){
		if(!zfesApp.isSidSyn()){alert("系统初始化错误，请刷新后重试");return;}
		$.ajax({
			type : 'POST',
			url : url,
			data : param,
			dataType : "json",
			cache : false,
			beforeSend: function(request){
				request.setRequestHeader("X-Requested-With_x", "XMLHttpRequest");
				if(sessionStorage){request.setRequestHeader("cs_token", sessionStorage.getItem("cs_token"))}
			},//token ...
			success :function(data){
				$.extend(data,{"handled":"true"});
				if(data.statusCode==zfesCore.statusCode.success){
					callback(data)
				}else{
					if(fialCallback){
						fialCallback(data);
					}else{
						zfesCore.ajaxDone(data);
					}
				}
			},
			error : zfesCore.ajaxError,
			complete : function() {}
		});
	}
};


