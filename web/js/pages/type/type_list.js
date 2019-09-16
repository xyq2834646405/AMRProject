$(function(){
	$("[id*=editBtn-]").each(function(){
		var tid = this.id.split("-")[1] ;
		$(this).on("click",function(){
		    var title = $("#title-"+tid).val();
			console.log(tid+":"+title ) ;
            $.post("pages/type/edit.action",{tid:tid,title:title},function (data) {
                operateAlert(data.trim()=="true" , "办公用品分类信息修改成功！","办公用品分类信息修改失败！") ;
            },"text");
        }) ;
	})
})