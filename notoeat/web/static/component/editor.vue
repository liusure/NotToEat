<template>
    <div>
        <form class="am-form">
            <div class="am-form-group">
                <label for="name">标题</label>
                <input type="text" class="am-form-field" id="name" placeholder="输入文章标题">
            </div>
            <div class="am-form-group">
                <label for="tags">标签</label>
                <input type="text" class="am-form-field" id="tags" placeholder="输入标签，请以逗号分隔">
            </div>
        </form>
        <div id="editor" style="height: 500px;max-height: 600px">
            {{defaultText}}
        </div>
        <div class="am-modal am-modal-alert" tabindex="-1" id="editor-alert">
            <div class="am-modal-dialog">
                <div class="am-modal-hd">Notoeat</div>
                <div class="am-modal-bd">

                </div>
                <div class="am-modal-footer">
                    <span class="am-modal-btn">确定</span>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    var wangEditor = require('wangeditor');
    var editor;
    export default{
        data:function () {
            return {
                defaultText:"请输入…"
            }
        },
        mounted:function () {
            editor = new wangEditor('editor');
            editor.config.uploadImgUrl="/media/uploadArticleImage";
            editor.config.uploadImgFileName="file";
            editor.create();
        },
        methods:{
            getArticle:function (cb) {
                var name = $("#name");
                var tags = $("#tags");
                var content = editor.$txt.html();
                var article = {
                    name:name.val(),
                    content:content,
                    tags:tags.val()
                };
                if(!article.name){
                    name.closest(".am-form-group").addClass("am-form-error");
                    return;
                }
                if(!content||editor.$txt.text()==this.defaultText){
                    $("#editor-alert .am-modal-bd").text("文章内容不能为空");
                    $("#editor-alert").modal("open");
                    return;
                }
                name.closest(".am-form-group").removeClass("am-form-error");
                cb.call(this,article);
            }
        }
    }
</script>