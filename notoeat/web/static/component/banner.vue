<template>
    <div class="get">
        <div class="am-g">
            <div class="am-u-lg-12">
                <div class="get-title">
                    <div class="get_font_left">已经有</div>
                    <div class="get_font_center" id="banner_num"></div>
                    <div class="get_font_rigth">人路过此地</div>
                </div>
                <div class="font_line"></div>
<!--                <p>
                    <a href="###" class="am-btn am-btn-sm get-btn  am-radius banner_ios am-icon-apple"> App store</a> <a href="###" class="am-btn am-btn-sm  am-radius get-btn banner_android am-icon-android"> Android</a>
                </p>-->
            </div>
        </div>
    </div>
</template>
<script>
    export default{
        mounted:function(){
            var banner_num;
            //@首页 数字跳动
            var options = {
                useEasing : true,
                useGrouping : true,
                separator : '',
                decimal : '.',
                prefix : '',
                suffix : ''
            };
            this.$http.get("/config/getVisitorNum").then(res=>{
                res.json().then(json=>{
                    if(json.success){
                        banner_num = new CountUp("banner_num", (json.msg-50)>0?json.msg-50:0, Number.parseInt(json.msg), 0, 5, options);
                        banner_num.start();
                    }
                })
            })
        }
    }
</script>