var postJSON = function (url, data, callback) {
	$.ajax({
		url:url,
		type:"POST",
		data:data,
		contentType:"application/json; charset=utf-8",
		dataType:"json",
		success: callback
	});
};