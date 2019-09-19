$(function () {
    $("[id*=appendBut-]").each(function () {
        var rid = this.id.split("-")[1];
        $(this).on("click",function () {
           $.post("pages/res/append.action",{rid:rid},function (data) {
               operateAlert(data.trim() == "true","已追加购买！","追加购买失败！") ;
           },"text");
        });
    });
})