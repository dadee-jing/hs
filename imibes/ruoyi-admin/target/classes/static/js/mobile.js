
//编辑框放到最大
var innerWidth = $(window).width();
if(innerWidth <= 750) {
    $(".layui-layer-max", window.parent.document)[0].click();
    $("body",window.parent.document).addClass("mini-navbar");
    $("#mobile_add").show();
}

else{
    $("#mobile_add").hide();
}