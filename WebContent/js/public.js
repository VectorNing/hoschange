	function loadMenu(data){
		var menu = data;
		var rootMenuId=0,menuList=[],idIndexMap={};
		for(var i=0; i<menu.length; i++){
			/*if(menu[i].parentId==-1){
				rootMenuId = menu[i].id;
				continue;
			}*/
			if(menu[i].parentId == 0){
				menuList.push({
					id:menu[i].id,
					name:menu[i].name,
					list:[]
				});
				idIndexMap[menu[i].id]=menuList.length-1;
				continue;
			}
			menuList[ idIndexMap[menu[i].parentId] ].list.push({
				name:menu[i].name,
				url:menu[i].url
			})
			
		}
		var ul = $('nav>ul');
		for(var i=0;i<menuList.length;i++){
			var li = $('<li>');
			var a = $('<a>').attr('href','#');
			$('<span>').addClass("menu-item-parent").html(menuList[i].name).appendTo(a);
			a.appendTo(li);
			var ulItem = $('<ul>');

			ulItem.appendTo(li);
			li.appendTo(ul);
			for(var j=0;j<menuList[i].list.length;j++){
				var liItem = $('<li>');
				$('<a>').html(menuList[i]['list'][j].name)
						.attr('href',menuList[i]['list'][j].url)
						.appendTo(liItem);
				liItem.appendTo(ulItem);
			}
		}
		
		$('nav').jarvismenu();
		if(document.location.hash=='' || document.location.hash=='#undefined'){
			document.location.hash='#'+$('nav>ul>li>ul>li>a:first').attr('href');
		}
		var link = document.location.hash.substr(1);
		var sublinkNode = $('nav a[href="'+link+'"]');
		sublinkNode.closest('li').addClass('active');
		var rootlinkNode = sublinkNode.closest('li').parent().closest('li');
		rootlinkNode.find('a:first').click();
		
	}
	
	function timer(btnId,btnDisplay,btnName) {
		 var btn = btnId;
		 var defaultDisplay = "保存中";
		 var defaultName = "保存";
		 btn.attr("disabled", true);  //按钮禁止点击
		 if(btnDisplay != '' && btnDisplay !=null){
			 defaultDisplay = btnDisplay;
		 }
		 btn.html(defaultDisplay);
		 
		 if(btnName != '' && btnName !=null )
	     		defaultName = btnName;
		 
		 var hander = setInterval(function() {
	     clearInterval(hander); //清除倒计时
		     btn.html(defaultName);
		     btn.attr("disabled", false);
		     return false;
	     }, 2000);
	}
	function disabledBtn(btnId){
		 var btn = btnId;
		 btn.attr("disabled", true);  //按钮禁止点击
		 btn.html("保存中");
	}
	function enabledBtn(btnId){
		 var btn = btnId;
		 btn.attr("disabled", false);
		 btn.html("保存");
	}