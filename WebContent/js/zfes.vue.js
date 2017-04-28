/**
 * @author:曹仁道
 * 2016年8月22日 上午10:26:03
 * @描述：公共vue渲染标签库
 */
var ZVue={
		//-----------------------------------------------------------------------button-----------------------------------------------
		renderBtn:function(ele){//
			var btnTpl='<button type="button" class="{{btnclass}}" permname="{{permname}}" onclick="{{onclick}}"><i class="{{iconclass}}" aria-hidden="true"></i>';
			btnTpl+='<span class="hidden-xs">{{label}}</span></button>'
			Vue.component("km-bt-com",{
				 props: {
					  label:{type: String, required: true},
					  onclick:{type: String, required: true},
					  btnclass:{type: String, default:"btn btn-default"},
					  iconclass:{type: String, default:"glyphicon glyphicon-cog"},
					  permname:{type: String,default:""}
					  },
			    template: btnTpl
			});
			Vue.component('km-bt-add', {
				  props: {
					  label:{type: String, required: true},
					  onclick:{type: String, required: true},
					  btnclass:{type: String, default:"btn btn-primary"},
					  iconclass:{type: String, default:"glyphicon glyphicon-plus"},
					  permname:{type: String,default:""}
					  },
				  template: btnTpl
			});
			Vue.component('km-bt-edit', {
				  props: {
					  label:{type: String, required: true},
					  onclick:{type: String, required: true},
					  btnclass:{type: String, default:"btn btn-info"},
					  iconclass:{type: String, default:"glyphicon glyphicon-edit"},
					  permname:{type: String,default:""}
					  },
				  template: btnTpl
			});
			Vue.component('km-bt-delete', {
				  props: {
					  label:{type: String, required: true},
					  onclick:{type: String, required: true},
					  btnclass:{type: String, default:"btn btn-danger"},
					  iconclass:{type: String, default:"glyphicon glyphicon-trash"},
					  permname:{type: String,default:""}
					  },
				  template: btnTpl
			});
			Vue.component('km-bt-query', {
				  props: {
					  label:{type: String, required: true},
					  onclick:{type: String, required: true},
					  btnclass:{type: String, default:"btn btn-info"},
					  iconclass:{type: String, default:"glyphicon glyphicon-search"},
					  permname:{type: String,default:""}
					  },
				  template: btnTpl
			});
			Vue.component('km-bt-yes', {
				  props: {
					  label:{type: String, required: true},
					  onclick:{type: String, required: true},
					  btnclass:{type: String, default:"btn btn-success"},
					  iconclass:{type: String, default:"glyphicon glyphicon-ok"},
					  permname:{type: String,default:""}
					  },
				  template: btnTpl
			});
			Vue.component('km-bt-no', {
				  props: {
					  label:{type: String, required: true},
					  onclick:{type: String, required: true},
					  btnclass:{type: String, default:"btn btn-warning"},
					  iconclass:{type: String, default:"glyphicon glyphicon-remove"},
					  permname:{type: String,default:""}
					  },
				  template: btnTpl
			});
			Vue.component('km-bt-print', {
				  props: {
					  label:{type: String, required: true},
					  onclick:{type: String, required: true},
					  btnclass:{type: String, default:"btn btn-primary"},
					  iconclass:{type: String, default:"glyphicon glyphicon-print"},
					  permname:{type: String,default:""}
					  },
				  template: btnTpl
			});
			Vue.component('km-bt-show', {
				  props: {
					  label:{type: String, required: true},
					  onclick:{type: String, required: true},
					  btnclass:{type: String, default:"btn btn-info"},
					  iconclass:{type: String, default:"glyphicon glyphicon-eye-open"},
					  permname:{type: String,default:""}
					  },
				  template: btnTpl
			});
			Vue.component('km-bt-save', {
				  props: {
					  label:{type: String, required: true},
					  onclick:{type: String, required: true},
					  btnclass:{type: String, default:"btn btn-primary"},
					  iconclass:{type: String, default:"glyphicon glyphicon-save"},
					  permname:{type: String,default:""}
					  },
				  template: btnTpl
			});
			Vue.component('km-bt-upload', {
				  props: {
					  label:{type: String, required: true},
					  onclick:{type: String, required: true},
					  btnclass:{type: String, default:"btn btn-primary"},
					  iconclass:{type: String, default:"glyphicon glyphicon-cloud-upload"},
					  permname:{type: String,default:""}
					  },
				  template: btnTpl
			});
			Vue.component('km-bt-download', {
				  props: {
					  label:{type: String, required: true},
					  onclick:{type: String, required: true},
					  btnclass:{type: String, default:"btn btn-primary"},
					  iconclass:{type: String, default:"glyphicon glyphicon-cloud-download"},
					  permname:{type: String,default:""}
					  },
				  template: btnTpl
			});
			Vue.component('km-bt-lock', {
			  props: {
				  label:{type: String, required: true},
				  onclick:{type: String, required: true},
				  btnclass:{type: String, default:"btn btn-primary"},
				  iconclass:{type: String, default:"glyphicon glyphicon-lock"},
				  permname:{type: String,default:""}
				  },
			  template: btnTpl
		});
			new Vue({
				el: "#"+ele
			});
			var btnTag=$("#"+ele).find("button");
			for(var i=0;i<btnTag.length;i++){
				var permname=$(btnTag[i]).attr("permname");
				var isShowBtn=true; //PermStore.isExistByPerm(permname);
				if(!isShowBtn){
					//法案一，仅仅隐藏按钮
					//$(btnTag[i]).hide();
					//方案二：整个按钮删除
					$(btnTag[i]).remove();
				}
			}
		},
		//--------------------------------------------------------------------------input--------------------------------------------------------
		comInput:function(pele){
			var inputTpl='<label for="{{id}}" class="{{labelClass}}">{{label}}</label> ';
			inputTpl+='<input type="{{type}}" name="{{name}}" v-model="{{vmodel}}" class="{{inputClass}}" id="{{id}}" placeholder="{{placeholder}}" onfocus="{{onfocus}}" onblur="{{onblur}}" ';
			var required=$("#"+pele).find("km-input").attr("required");
			if(!zfesUtil.isEmpty(required)){
				inputTpl+=' required ';
			}
			var readonly=$("#"+pele).find("km-input").attr("readonly");
			if(!zfesUtil.isEmpty(readonly)){
				inputTpl+=' readonly="readonly" ';
			}
			inputTpl+=' > ';
			Vue.component('km-input', {
			  props: {
				  label:{type: String, required: true},
				  id:{type: String, required: true},
				  name:{type: String, required: true},
				  type:{type: String, default:"text"},
				  inputClass:{type: String, default:"form-control"},
				  labelClass:{type: String, default:"control-label"},
				  /*placeholder:{String,default:""}*/
				  placeholder:{type:String,default:""}
				 },
			  template: inputTpl
		});
			new Vue({
				el: "#"+pele
			});
			ZVue.initFormCss();
		},
		//------------------------------------------------------------------------------select------------------------------------------
		options:function(data,url,param,fnBack){
			var option='<option class="{{optionclass}}" value="">请选择...</option>';
			if(zfesUtil.isEmpty(data)){
				if(zfesUtil.isEmpty(url)){
					fnBack(option);
				}else{
					zfesAjax.ajax(url,param, function(data){
						for(var i=0;i<data.length;i++){
							option+='<option class="{{optionclass}}" value="'+data[i].code+'">'+data[i].name+'</option>';
						}
						fnBack(option);
	        		});
				}
			}else{
				for(var i=0;i<data.length;i++){
					option+='<option class="{{optionclass}}" value="'+data[i].code+'">'+data[i].name+'</option>';
				}
				fnBack(option);
			}
		},
		comOptions:function(){
			var options='{{reOptions}}';
			ZVue.options(data,url,param,function(reOptions){
				Vue.component('km-options', {
					props: {
					  reOptions:reOptions
					},
					template:options
				});
				new Vue({
					el: "#"+ele
				});
			});
		},
		comSelect:function(pele,$el,options){
			var selectId=$el.attr("id");
			var selectTpl='<label for="name" ';
			var labelClass=$el.attr("labelclass");
			if(zfesUtil.isEmpty(labelClass)){
				labelClass="control-label";
			}
			var lableName=$el.attr("label");
			if(zfesUtil.isStrNull(lableName)){
				lableName="";
			}
			selectTpl+=' class="'+labelClass+'" >'+lableName+'</label>';
			selectTpl+='<select data-placeholder="请选择..." ';
			var selectclass=$el.attr("selectclass");
			if(zfesUtil.isEmpty(selectclass)){
				selectclass=" form-control ";
			}
			selectTpl+='class="'+selectclass+'" id="'+selectId+'" name="'+$el.attr("name")+'" ';
			var vmodel=$el.attr("vmodel");
			if(!zfesUtil.isEmpty(vmodel)){
				selectTpl+=' v-model="'+vmodel+'" ';
			}
			var value=$el.attr("value");
			if(!zfesUtil.isEmpty(value)){
				//对字符串进行数组格式化
				if(value.indexOf(',')>0){
					value=value.split(',');
				}
				selectTpl+=' value="'+value+'" ';
			}
			var onchange=$el.attr("onchange");
			if(!zfesUtil.isEmpty(onchange)){
				selectTpl+=' onchange="'+onchange+'" ';
			}
			var multiple=$el.attr("multiple");
			if(!zfesUtil.isEmpty(multiple)){
				selectTpl+=' multiple="'+multiple+'" ';
			}
			
			var required=$el.attr("required");
			if(!zfesUtil.isEmpty(required)){
				selectTpl+=required;
			}
			selectTpl+=' ><option v-for="item in items" value="{{item.code}}" selected="{{item.selected}}" ';
			
			var optionclass=$el.attr("optionclass");
			if(!zfesUtil.isEmpty(optionclass)){
				selectTpl+=' class="'+optionclass+'" ';
			}
			selectTpl+=' >{{item.name}}</option></select>';
			$el.replaceWith(selectTpl);
		//默认都加上请选择
		options.splice(0,0,{code:"",name:"请选择...",selected:"selected"});
			
			new Vue({
				el: "#"+pele,
				 data: {
						items:options
					}
			});
			
		//最后初始化表单样式
			ZVue.initFormCss();
			$("#"+pele).find("#"+selectId).chosen({no_results_text: "没有找到您需要的数据"});
			
			if(!zfesUtil.isEmpty(value)){
				$("#"+pele).find("#"+selectId).val(value);
				$("#"+pele).find("#"+selectId).trigger('chosen:updated');
			}
			$("#"+pele).find(".chosen-single").addClass("form-control");
			$("select").next("div[class='chosen-container chosen-container-single']").css("display","none");
			$("select").css("display","block");
	},
	dicSelect:function(ele,itemNum,$el){
		DicStore.getDicNoByUrl(itemNum,function(dicData){
			ZVue.comSelect(ele, $el, dicData);
		});
	},
	dicByFatherSelect:function(ele,fatherId,$el){
		DicStore.getDicByFatherId(fatherId,function(dicData){
			ZVue.comSelect(ele,$el, dicData);
		});
	},
	maintainSelect:function(ele,$el,maintainCode){
		dicData=[];
		var url="/gradms/dictionary/loadDictList";
		if(maintainCode=="all"){
			maintainCode="";
		}
		zfesAjax.ajax(url,{code:maintainCode}, function(data){
			for(var i=0;i<data.rows.length;i++){
				dicData.push({code:data.rows[i].type,name:data.rows[i].name});
			}
			ZVue.comSelect(ele, $el, dicData);
		});
	},
	maintainValueSelect:function(ele,$el,dtCode,param){
		var dicData=[];
		var url=host_auth+"gradms/dicValue/loadDicAndColValList";
		if(zfesUtil.isEmpty(dtCode)){
			alertSwal.errorText("select初始化错误，dtCode属性是必须");
			//zfesCore.alertMsgz({message:"select初始化错误，dtCode属性是必须"});
			return;
		}
		
		//构建参数
		var paramResult={dtCode:dtCode};
		if(!zfesUtil.isEmpty(param)){
			var params=param.split(",");
			for(var i=0;i<params.length;i++){
				var tmp=params[i].split(":");
				paramResult.dcCode=tmp[0];
				paramResult.value=tmp[1];
			}
		}
		zfesAjax.ajax(url,paramResult, function(data){
			for(var i=0;i<data.length;i++){
				dicData.push({code:data[i].code,name:data[i].name});
			}
			ZVue.comSelect(ele, $el, dicData);
		});
	},
	dataSelect:function(ele,data,$el){
		ZVue.comSelect(ele,$el, data);
	},
	urlSelect:function(ele,url,param,$el){
		zfesAjax.ajax(url,param, function(data){
			ZVue.comSelect(ele, $el, data);
		});
	},
	//初始化表单样式
	initFormCss:function(){
//		$("label.control-label").css("width", "150px");
		$("[for=name]").parent().css("margin-top", "-15px");
		$("select").next("div[class='chosen-container chosen-container-single']").css("display","none");
		$("select").css("display","block");
//		$(".select").css("margin-top", "-15px");//.css("margin-left", "30px").css("margin-bottom","10px");
//		$(".form-control").each(function(){
//			var oldStyle=$(this).attr("style");
//			if(zfesUtil.isStrNull(oldStyle)){
//				$(this).css("width", "300px");
//			}else	if(oldStyle.indexOf("width")==-1){
//				$(this).css("width", "300px");
//			}
//		});
	},
	setFormVueData:function(formEl,data){
		var $fel=$("#"+formEl);
		$.each(data, function(i) {
	    //ZVue.zvueModel.$data[i]=data[i];
			var $ele=$fel.find("#"+i);
			if($ele.attr("type")=="date"){
				$ele.val(zfesUtil.formatDate(data[i]));
			}else{
				var result=data[i];
				if((typeof(result)=="boolean"&&result==true)||result=="true"){
					data[i]="1";
				}
				if((typeof(result)=="boolean"&&result==false)||result=="false"){
					data[i]="0";
				}
				$ele.val(data[i]);
				$ele.attr("value",data[i]);
			}
			if(!zfesUtil.isEmpty($ele[0])){
				if($ele[0].tagName.toUpperCase()=="SELECT"){
					$ele.trigger('chosen:updated');
				}
			}
		});
	},
	init:function(){
		//$("body").on("mouseover",function(){
		//下拉
			var $kmSelect=$("body").find("km-select");
			$kmSelect.each(function(){
				var parentDiv=$(this).closest("div");
				if(parentDiv.length>0){
					var parentId=parentDiv.attr("id");
					//ZVue.comSelect(parentId,$(this),"");
					var code=$(this).attr("code");
					var dicFather=$(this).attr("dicFather");
					var maintainCode=$(this).attr("maintainCode");
					var dtCode=$(this).attr("dtCode");
					var param=$(this).attr("param");
					if(!zfesUtil.isEmpty(code)){//只要code存在，都认为是字典
						ZVue.dicSelect(parentId,code,$(this));
					}else if(!zfesUtil.isEmpty(dicFather)){//如果存在dicFather，认为是根据父id查询字典
						ZVue.dicByFatherSelect(parentId,dicFather,$(this));
					}else if(!zfesUtil.isEmpty(maintainCode)){//如果存在maintainList，认为是获取维护项列表
						ZVue.maintainSelect(parentId,$(this),maintainCode);
					}else if(!zfesUtil.isEmpty(dtCode)){
						ZVue.maintainValueSelect(parentId,$(this),dtCode,param);
					}else{//如果code不存在，使用自定义data
						var keys=$(this).attr("keys");
						var values=$(this).attr("values");
						if(!zfesUtil.isEmpty(keys)&&!zfesUtil.isEmpty(values)){//两者皆不为空代表自定义data
							var data=[];
							var keyArr=keys.split(",");
							var valueArr=values.split(",");
							for(var i=0;i<keyArr.length;i++){
								if(!zfesUtil.isEmpty(keyArr[i])&&!zfesUtil.isEmpty(valueArr[i])){
									data.push({code:keyArr[i],name:valueArr[i]});
								}
							}
							ZVue.dataSelect(parentId,data,$(this));
						}else{
							var url=$(this).attr("url");
							var host=$(this).attr("host");
							if(zfesUtil.isStrNull(host)){
								//host=host_auth;
							}else if(host=="coreServer"){
								host=host_gradms_core.host;
							}else if(host=="recruitServer"){
								host=km_gradms_recruit.host
							}else if(host=="baseServer"){
								host="";//host_auth
							}else{
								host=host_auth;
							}
							ZVue.urlSelect(parentId,url,{},$(this));
						}
					}
				}
			});
			//按钮
			var btparentIds=[];
			var tags=["km-bt-add","km-bt-edit","km-bt-delete","km-bt-query","km-bt-yes","km-bt-no","km-bt-print","km-bt-show","km-bt-save","km-bt-upload","km-bt-download","km-bt-lock"];
			for(var i=0;i<tags.length;i++){
				var tag=tags[i];
				var btParentDiv=$(tag).closest("div");
				if(btParentDiv.length>0){
					var pid=btParentDiv.attr("id");
					if(btparentIds.length<1){
						btparentIds.push(pid);
						ZVue.renderBtn(pid);
					}else{
						var isExit=false;
						for(var j=0;j<btparentIds.length;j++){
							if(pid==btparentIds[j]){
								isExit=true;
								break;
							}
						}
						if(!isExit){
							btparentIds.push(pid);
							ZVue.renderBtn(pid);
						}
					}
				}
			}
		//});
			//input
			var $kmInput=$("body").find("km-input");
			$kmInput.each(function(){
				var parentDiv=$(this).closest("div");
				if(parentDiv.length>0){
					var parentId=parentDiv.attr("id");
					ZVue.comInput(parentId);
				}
			});
			
		//渲染树
		//ZFTree.init();	
	}
};

/*$(function(){
	//setInterval(function(){
		ZVue.init();
	//}, 500);
});*/