<template>
    <!--图片上传框-->
    <div class="am-modal am-modal-no-btn up-modal-frame" tabindex="-1" id="up-modal-frame">
        <div class="am-modal-dialog up-frame-parent up-frame-radius">
            <div class="am-modal-hd up-frame-header">
                <label>修改头像</label>
                <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
            </div>
            <div class="am-modal-bd  up-frame-body am-cf">
                <div class="am-g am-fl">
                    <div class="am-form-group am-form-file">
                        <div class="am-fl">
                            <button type="button" class="am-btn am-btn-default am-btn-sm"><i class="am-icon-cloud-upload"></i> 选择要上传的文件</button>
                            <input type="file" class="up-img-file">
                        </div>
                        <span id="msg">

                        </span>
                    </div>
                </div>
                <div class="am-g am-fl">
                    <div class="up-pre-before up-frame-radius">
                        <img alt="" src="" class="up-img-show" id="up-img-show" >
                    </div>
                    <div class="up-pre-after up-frame-radius">
                    </div>
                </div>
                <div class="am-g am-fl">
                    <div class="up-control-btns">
                        <span class="am-icon-rotate-left am-btn am-btn-default"   id="up-btn-left"></span>
                        <span class="am-icon-rotate-right am-btn am-btn-default"  id="up-btn-right"></span>
                        <span class="am-icon-check up-btn-ok am-btn am-btn-default">
    				</span>
                    </div>
                </div>

            </div>
        </div>
    </div>
</template>
<script>
    import {bus,vm} from "main";
    export default{
        methods:{
          upload:function () {
              $("#up-modal-frame").modal();
          }
        },
        mounted:function(){
            // 初始化
        var $image = $('#up-img-show');
        $image.cropper({
            aspectRatio: '1',
            autoCropArea:0.8,
            preview: '.up-pre-after',
            responsive:true,
        });
        // 上传图片
        var $inputImage = $('.up-modal-frame .up-img-file');
        var URL = window.URL || window.webkitURL;
        var blobURL;
        if (URL) {
            $inputImage.change(function () {
                var files = this.files;
                var file;
                if (files && files.length) {
                    file = files[0];
                    if (/^image\/\w+$/.test(file.type)) {
                        blobURL = URL.createObjectURL(file);
                        $image.one('built.cropper', function () {
                            // Revoke when load complete
                            URL.revokeObjectURL(blobURL);
                        }).cropper('reset').cropper('replace', blobURL);
                        $inputImage.val('');
                    } else {
                        window.alert('Please choose an image file.');
                    }
                }
            });
        } else {
            $inputImage.prop('disabled', true).parent().addClass('disabled');
        }
        //绑定上传事件
        $('.up-modal-frame .up-btn-ok').on('click',()=>{
            var img_src=$image.attr("src");
            if(!img_src){
                set_alert_info("没有选择上传的图片");
                return false;
            }
            var parame_json = {width:'100',height:'100'};
            var width=parame_json.width;
            var height=parame_json.height;
            //控制裁剪图片的大小
            var canvas=$image.cropper('getCroppedCanvas',{width: width,height: height});
            var data = canvas.toDataURL(); //转成base64
            this.$http.post("/user/uploadFace",data.toString()).then(res=>{
                res.json().then(json=>{
                    if(json.success){
                        bus.$emit("userFaceUpdate",json.result);
                        set_alert_info("上传成功");
                        $("#up-modal-frame").modal('close');
                    }
                })
            })
        });
        $('#up-btn-left').on('click',function(){
            $("#up-img-show").cropper('rotate', 90);
        });
        $('#up-btn-right').on('click',function(){
            $("#up-img-show").cropper('rotate', -90);
        });
        }
     }
     var timeOut;
    function set_alert_info(content){
        clearTimeout(timeOut);
        $("#msg").html(content);
        timeOut = setTimeout(function(){
            $("#msg").html("");
        },3000);
    }
</script>
<style scoped>
    @import "../assets/css/plugins/cropper/cropper.css";
    .msg{line-height: 30px}
    .up-modal-frame .up-frame-radius{padding-bottom: 20px;border-radius: 5px;}
    .up-modal-frame .am-modal-dialog{width: 600px;}
    .up-frame-parent .up-frame-header{height: 50px;padding: 0px;line-height: 50px;border-bottom:solid 1px #e5e5e5;}
    .up-frame-parent .up-frame-header label{font-size: 16px;float: left;margin-left: 14px;color: #808080;}
    .up-frame-parent .up-frame-body{padding:20px;}
    .up-frame-parent .up-pre-before {width:327px;height: 327px;padding:0px;float: left;background: #fcfcfc;border: 1px solid #e3e3e3;}
    .up-frame-parent .up-pre-before img{}
    .up-frame-parent .up-frame-body .up-pre-after{background: #fcfcfc;overflow: hidden;width: 186px;min-width: 186px;min-height: 186px;height: 186px;border: 1px solid #e3e3e3;float: left;margin-left: 25px;}
    .up-frame-parent .up-control-btns{width: 150px;margin-left: auto;margin-right: auto;text-align: center;line-height: 30px; margin-top: 30px;}
    .up-frame-parent .up-control-btns span{width: 30%;}
    @media screen and (max-width: 1024px){
        .up-frame-parent .up-pre-before {width: 100%;}
        .up-frame-parent .up-frame-body .up-pre-after{margin-left: 0px;margin-top: 10px;}
    }

    @media screen and (max-width: 600px){
        .up-modal-frame .am-modal-dialog{width: 90%;}
    }
</style>