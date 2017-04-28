function jsonpcallback(data) {
}

function callApi(command,overtime) {
    $.ajax({
        url: 'http://127.0.0.1:5050',
        type: 'GET', //GET
        async: true, //或false,是否异步
        contentType: "application/json; charset=utf-8",
        data: { cmd: command },
        timeout: overtime || 50000, //超时时间
        dataType: 'jsonp', //返回的数据格式：json/xml/html/script/jsonp/text
        jsonpCallback: "jsonpcallback",
        beforeSend: function (xhr) {
        	
        },
        success: function (data, textStatus, xhr) {
            if (data == null) {
                return;
            }
            if (data.status === "OK") {
                //alert("s");
                if (data.data != null) {
                    alert(data.data);
                }
            } else {
                //返回错误信息
                alert(data.description);
            }
            
        },
        error: function (xhr, textStatus) {
            alert("调用失败error");
            console.log(xhr);
            console.log(textStatus);
        },
        complete: function () {
            //alert("complete");
        }
    });
}