//ZHTEASYUtil
var zfesUtil = {
	_serializeObject : function(params) {
		var o = {};
		$.each(params, function() {
			if (o[this.name]) {
				if (!o[this.name].push) {
					o[this.name] = [ o[this.name] ];
				}
				o[this.name].push(this.value || '');
			} else {
				o[this.name] = this.value || '';
			}
		});
		return o;
	},
	formToJson : function(form) {
		if (zfesUtil.isStrNull(form)) {
			return {};
		}
		var serializeObj = {};
		var array = form.serializeArray();
		$(array).each(
				function() {
					var valuex = this.value;
					if (valuex && "" != valuex && null != valuex
							&& "null" != valuex && "undefined" != valuex
							&& undefined != valuex && $.trim(valuex) != "") {
						if (serializeObj[this.name]) {
							serializeObj[this.name] = serializeObj[this.name]
									+ "," + this.value;
							/*
							 * if($.isArray(serializeObj[this.name])){
							 * serializeObj[this.name].push(this.value);
							 * console.log("-------------1-------------");
							 * console.log(this.value); }else{
							 * serializeObj[this.name]=[serializeObj[this.name],this.value];
							 * console.log("-------------2-------------");
							 * console.log([serializeObj[this.name],this.value]); }
							 */

						} else {
							serializeObj[this.name] = this.value;
						}
					}

				});
		return serializeObj;
	},

	parseJson : function(data) {
		if (data) {
			var result = $.parseJSON(data);
			return result;
		}
		return null;
	},
	// evel漏洞
	// parseEval:function (data){
	// try{
	// if ($.type(data) == 'string'){
	// return eval('(' + data + ')');
	// }
	// else{
	// return data;
	// }
	// } catch (e){
	// return {};
	// }
	// },
	rowsIdToArray : function(rows, idkey) {
		var idArray = [];
		$.each(rows, function(index, item) {
			idArray.push(item[idkey]);
		});
		return idArray;
	},
	isSpecialCharacter : function(value) {
		var reg = new RegExp(
				"[`~!@#$^&*=|{}':;',\\[\\].<>/?~！@#￥……&*——|{}【】‘；：”“'。，、？]");
		if (reg.test(value)) {
			return true;
		} else {
			return false;
		}
	},
	isStrHavaValue : function(valuex) {
		if (valuex && typeof (valuex) != "undefined" && "" != valuex
				&& null != valuex && "null" != valuex && "undefined" != valuex
				&& undefined != valuex && "" != $.trim(valuex)) {
			return true;
		}
		return false;
	},
	isStrNull : function(valuex) {
		if (!valuex || typeof (valuex) == "undefined" || "" == valuex
				|| null == valuex || "null" == valuex || "undefined" == valuex
				|| undefined == valuex || "" == $.trim(valuex)) {
			return true;
		}
		return false;
	},
	isEmpty : function(valuex) {
		if (!valuex || valuex === "[]" || valuex === "{}"
				|| typeof (valuex) == "undefined" || "" == valuex
				|| null == valuex || "null" == valuex || "undefined" == valuex
				|| undefined == valuex || "" == $.trim(valuex)) {
			return true;
		}
		return false;
	},
	locationWithParams : function(url, param) {
		var paramArray = [];
		if (param) {
			$.each(param, function(key, val) {
				paramArray.push($('<input>', {
					name : key,
					value : val
				}));
			});
			paramArray.push($('<input type=submit id=zfesxxx_submit_xxxxxxx>',
					{}));
		}
		/*
		 * $('<form>', { method: 'post', action: url
		 * }).append(paramArray).submit();
		 */

		/*
		 * $('<form >', { method: 'post', action: url
		 * }).append(paramArray).submit();
		 */

		$('<form>', {
			method : 'post',
			action : url
		}).append(paramArray).find("input[id$='zfesxxx_submit_xxxxxxx']")
				.click();
	},
	// ---------------------------------------------------------------------------------
	formatterOmit : function(value, row, index) {// 备注过长时显示省略号,鼠标悬停显示全部信息
		var newValue = null;
		if (value != null && value.length > 10) {
			newValue = '<p title=' + value + '>' + value.substring(0, 10)
					+ "..." + '</p>'
		} else {
			newValue = value;
		}
		return newValue;
	},

	formatterUser : function(value, row, index) {
		if (value == null) {
			return "自主";
		} else {
			return value;
		}
	},

	formatterRecoveryGoods : function(value, row, index) {
		if ("0" == value || 0 == value) {
			return "消毒鞋";
		} else if ("1" == value || 1 == value) {
			return "手术衣";
		}else if ("2" == value || 2 == value) {
			return "收纳盒";
		}
	},

	formatBool : function(value, row, index) {
		if (true == value || "true" == value || "1" == value || 1 == value) {
			return '<span class="label label-success">启用</span>';
		} else if (false == value || "false" == value || "0" == value
				|| 0 == value) {
			return '<span class="label label-danger">禁用</span>';
		} else {
			return value;
		}

	},
	formatStateDistribution : function(value, row, index) {
		if (true == value || "true" == value || "1" == value || 1 == value) {
			return '<span class="label label-danger">已分配</span>';
		} else if (false == value || "false" == value || "0" == value
				|| 0 == value) {
			return '<span class="label label-success">未分配</span>';
		} else {
			return value;
		}

	},
	formatStateUse : function(value, row, index) {
		if (true == value || "true" == value || "1" == value || 1 == value) {
			return '<span class="label label-danger">已使用</span>';
		} else if (false == value || "false" == value || "0" == value
				|| 0 == value) {
			return '<span class="label label-success">未使用</span>';
		} else {
			return value;
		}

	},
	formatYesNo : function(value, row, index) {
		if (true == value || "true" == value || "1" == value || 1 == value) {
			return '<span class="label label-success">是</span>';
		} else if (false == value || "false" == value || "0" == value
				|| 0 == value) {
			return '<span class="label label-danger">否</span>';
		} else {
			return value;
		}

	},
	signNoYes : function(value, row, index) {
		if (true == value || "true" == value || "1" == value || 1 == value) {
			return '<span class="label label-success">签退</span>';
		} else if (false == value || "false" == value || "0" == value
				|| 0 == value) {
			return '<span class="label label-danger">签到</span>';
		} else {
			return value;
		}

	},
	formatNoYes : function(value, row, index) {
		if (true == value || "true" == value || "1" == value || 1 == value) {
			return '<span class="label label-danger">是</span>';
		} else if (false == value || "false" == value || "0" == value
				|| 0 == value) {
			return '<span class="label label-success ">否</span>';
		} else {
			return value;
		}

	},

	formatConfig : function(value, row, index) {
		return '<span class="label label-success ">' + value + '</span>';
	},

	formatRecycleType : function(value, row, index) {
		if (0 == value || "0" == value) {
			return "消毒鞋";
		} else if (1 == value || "1" == value) {
			return "手术衣";
		} else if (2 == value || "2" == value) {
			return "收纳盒";
		} else {
			return value;
		}
	},

	/*----------时间格式转化--------------------------------------masheng---------------------------------*/
	formatDate : function(value) {
		if (value && value.length >= 10) {
			return value.substr(0, 10);
		}
		if (value) {
			var date = new Date(value);
			if (date) {
				// alert("date "+date);
			}
			var month = date.getMonth() + 1;
			var day = date.getDate();
			// alert(date+" "+month+" "+ day);
			if (month < 10) {
				month = '0' + month;
			}
			if (day < 10) {
				day = '0' + day;
			}
			return date.getFullYear() + '-' + month + '-' + day;
		}
		return '';
	},
	setCookie : function setCookie(c_name, value, expiredays, path) {
		path = (path == undefined || path == null) ? "/" : path + "/"
		var exdate = new Date()
		exdate.setDate(exdate.getDate() + expiredays)
		document.cookie = c_name
				+ "="
				+ escape(value)
				+ ((expiredays == null) ? "" : ";expires="
						+ exdate.toUTCString()) + ";path=" + path
	},
	getCookie : function getCookie(name) {
		var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
		if (arr = document.cookie.match(reg))
			return unescape(arr[2]);
		else
			return null;
	},

	// --------------------注意：此部分，曹仁道自写，正式使用需要询问----------------------------------------------
	/**
	 * 函数描述：已知明确的数组，数组格式[{code:"",name:"",className:""},...],code:代表该属性返回值，name：代表该属性列所需要现实的值，className：表示样式渲染，使用此属性必须将isClass参数设置成true
	 * 参数：value：列表渲染值；arr：格式数据数组；isClass：表示是否对显示结果样式渲染
	 */
	formatRender : function(value, arr, isClass) {
		var flag = false;
		for (var i = 0; i < arr.length; i++) {
			if (value == arr[i].code) {
				if (typeof (isClass) == "undefined" || isClass == null
						|| isClass == "" || isClass == "null") {
					return '<span class="label label-default">' + arr[i].name
							+ '</span>';
				} else {
					return '<span class="label ' + arr[i].className + '">'
							+ arr[i].name + '</span>';
				}
				flag = true;
			}
		}
		if (!flag) {
			return value;
		}
	},
	/**
	 * 函数描述：显示项为字典项时使用
	 * 参数：value：数据值；index：索引；tableDom：所渲染的表格dom对象；itemName:字典编号，col:所渲染的值的列索引，0开始
	 */
	formatDic : function(value, index, col, tableDom, itemName) {
		DicStore.getDicNoByUrl(itemName, function(dicData) {
			for (var i = 0; i < dicData.length; i++) {
				dicData[i].name = dicData[i].text;
				if (itemName == "SF") {
					if (dicData[i].code == "0") {
						dicData[i].className = "label-danger";
					} else {
						dicData[i].className = "label-success";
					}
				}
			}
			zfesUtil.formatByCallBack(value, index, col, tableDom, dicData,
					true);
		});
	},
	/**
	 * 函数描述：ajax异步请求回调时使用，适用于非字典，需要编号和显示结果不一样的渲染情况
	 * 参数：value:数据值；index：索引；tableDom：所渲染的表格dom对象；arrData：格式化数组数据，isClass：是否做样式渲染，指定不同值渲染格式，请在格式化数组中指定className属性,col:所渲染的值的列索引，0开始
	 * 数组格式：[{code:"",name:"",className:""},....],code即value，name显示值，className：特殊样式渲染类，该属性需要参数isClass设置为true才有效
	 */
	formatByCallBack : function(value, index, col, tableDom, arrData, isClass) {
		tableDom.on("post-body.bs.table", function(dom) {
			tableDom.find("tbody").find("tr").each(
					function() {
						var $indexTr = $(this);
						var index_x = $indexTr.attr("data-index");
						if (index_x == index) {
							$indexTr.find("td").each(
									function(x) {
										var $targetTd = $(this);
										if (x == col) {
											$targetTd.html(zfesUtil
													.formatRender(value,
															arrData, isClass));
										}
									});
						}
					});
		});
	},
	isNull : function(str) {
		if (typeof (str) == "undefined" || str == null || str == "null"
				|| str == "") {
			return true;
		} else {
			return false;
		}
	},
	clearForm : function(formEle) {
		$(':input', '#' + formEle).not(':button, :submit, :reset').val('')
				.removeAttr('checked').removeAttr('selected');
	}

};

/*
 * function getRootPath_web() { //获取当前网址，如：
 * http://localhost:8083/projectName/abc/meun.jsp var curWwwPath =
 * window.document.location.href; 如： /projectName/abc/meun.jsp var pathName =
 * window.document.location.pathname; var pos = curWwwPath.indexOf(pathName);
 * //获取主机地址，如： http://localhost:8083 var localhostPaht = curWwwPath.substring(0,
 * pos); //获取带"/"的项目名，如：/uimcardprj var projectName = pathName.substring(0,
 * pathName.substr(1).indexOf('/') + 1); return (localhostPaht + projectName); }
 */

