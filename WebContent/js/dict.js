$(function() {
	$('select[dict]').each(function() {
		var dict = $(this).attr('dict');
		var data = window.top[dict];
		var value = $(this).attr('data-value');
		var option = "<option value=''>请选择...</option>";
		$(this).append(option);
//		console.log(data)
		for(var i=0;i<data.length;i++){
			$('<option>').html(data[i].name).attr('value',data[i].code).appendTo($(this));
		}
		if(value != null && value != "" && value !=undefined && value !="undefined" ){
			$(this).val(value);
		}
		$(this).removeAttr('dict');
	});
});

function dictFormat(dict){
	return function(value){
		if(window.top[dict]){
			for(var i=0; i<window.top[dict].length; i++){
				if (window.top[dict][i].code == value) return window.top[dict][i].name;
			}
		}
		return value;
	}
}