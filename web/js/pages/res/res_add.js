$(function() {
    $("#type\\.tid").on("change",function () {
        var tid=$(this).val();
        if(/^\d+$/.test(tid)){
            $.post("pages/type/getSubType.action",{tid:tid},function (data) {
                $("#subtype\\.stid option").remove();
                for (var x = 0; x < data.length; x++) {
                    var stid = data[x].stid;
                    var title = data[x].title;
                    $("#subtype\\.stid").append("<option value='"+stid+"'>"+title+"</option>");
                }
            },"json");
        }else{
            $("#subtype\\.stid option").remove();
        }
    });
	$("#myform").validate({
		debug : true, // 取消表单的提交操作
		submitHandler : function(form) {
			form.submit(); // 提交表单
		},
		errorPlacement : function(error, element) {
			$("#" + $(element).attr("id").replace(".", "\\.") + "Msg").append(error);
		},
		highlight : function(element, errorClass) {
			$(element).fadeOut(1,function() {
				$(element).fadeIn(1, function() {
					$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-error");
				});

			})
		},
		unhighlight : function(element, errorClass) {
			$(element).fadeOut(1,function() {
				$(element).fadeIn(1,function() {
						$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-success");
				});
			})
		},
		errorClass : "text-danger",
        messages:{
		    "type.tid":"必须选择购买商品的分类",
            "subtype.stid":"必须选择购买商品的子分类"
        },
		rules : {
			"title" : {
				required : true
			},
			"type.tid" : {
				required : true
			},
			"subtype.stid" : {
				required : true
			},
			"price" : {
				required : true,
                number : true
			},
			"pic" : {
				required : true
			}
		}
	});
})