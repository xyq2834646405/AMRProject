$(function(){
	$("[id*=editBtn-]").each(function(){
		var did = this.id.split("-")[1] ;
		$(this).on("click",function(){
            var title = $("#title-"+did).val();
            if (title!=""){
                $.post("pages/dept/edit.action",{did:did,title:title},function (data) {
                    operateAlert(data.trim()=="true" , "部门名称修改成功！","部门名称修改失败！") ;
                },"text");
            }else {
                operateAlert(false , "部门名称修改成功！","部门名称修改失败！") ;
            }
		}) ;
	})
})