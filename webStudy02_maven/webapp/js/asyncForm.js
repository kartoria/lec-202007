/**
 * form 태그를 대상으로 비동기 요청 사용.
 */

$.fn.asyncForm = function(param){
	
	let form = this;
	let makeData = function(){
		let data = {}
		let inputs = form.find(":input[name]");
		$(inputs).each(function(index, input){
			let name = $(this).attr("name");
			let value = $(this).val();
			data[name] = value; 
		});
		return data;
	}
	
	form.on("submit", function(event){
		event.preventDefault();
		let options = {}
		options.url = form.action;
		options.method = form.method;
		options["data"] = makeData();
		if(param){
			options.dataType = param.dataType ? param.dataType : "json";
			options.success = param.success ? param.success : function(resp){
				console.log("success function 필요")
				console.log(resp);
			};
			if(param.error) options.error = param.error;
		}
		$.ajax(options);
		return false;
	});
	
	return this;
}