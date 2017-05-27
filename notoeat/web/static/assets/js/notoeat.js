$(function () {

//     if($('.get').width() < 640){
//     $('.get').height($('.get').width() / ( 1920 / 278 ));
//     alert($('.get').height());
// }

    //     var mobile_prtscn_width  = $('.am-max540').width();
    // var mobile_views_width   =  mobile_prtscn_width;
    // var mobile_views_heigth  =  mobile_views_width / (1200 / 220);
    // $('.am-bottom-don2').css({'height':mobile_views_heigth,'width':mobile_views_width});




//@首页 底部下载按钮

    // function mouse_over_out(obj, style, overcss, outcss) {
    //      obj.bind('mouseover', function() {
    //          $(this).css(style, overcss);
    //      });
    //      obj.bind('mouseout', function() {
    //          $(this).css(style, outcss);
    //      });
    //  }


//@首页 用户名截取
    $('.am_imglist_user_font').each(
        function () {
            if ($(this).text().length >= 13) {
                $(this).html($(this).text().substr(0, 13));
            }
        }
    );

    banner_num.start();
});

//动画效果
function OpenDonghua(Chufa, Mubiao, Xiaoguo) {
    Chufa.on('mouseover', function () {
        $(this).find(Mubiao).addClass(Xiaoguo);
    });
}
function CloseDonghua(Chufa, Mubiao, Xiaoguo) {
    Chufa.on('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
        Mubiao.removeClass(Xiaoguo);
    });
}



