<template>
    <div class="am-g">
        <div class="am-u-lg-10 am-u-lg-offset-1">
            <editor ref="editor"></editor>
            <button id="save" class="am-btn am-btn-primary" @click="saveArticle">发表</button>
        </div>
        <div class="am-modal am-modal-confirm" tabindex="-1" id="article-confirm">
            <div class="am-modal-dialog">
                <div class="am-modal-hd">Notoeat</div>
                <div class="am-modal-bd">

                </div>
                <div class="am-modal-footer">
                    <span class="am-modal-btn" data-am-modal-cancel>取消</span>
                    <span class="am-modal-btn" data-am-modal-confirm>确定</span>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    import editor from "./editor.vue"
    export default{
        components: {
            editor: editor
        },
        methods: {
            saveArticle: function () {
                this.$refs.editor.getArticle(article => {
                    this.$http.post("/article/saveArticle",article,{emulateJSON:true}).then(res=>{
                        res.json().then(json=>{
                            if(json.success){
                                $("#article-confirm .am-modal-bd").text("发表成功，希望跳转到文章页吗？");
                                $("#article-confirm").modal({
                                    onConfirm:()=>{
                                        this.$router.push("/article/"+json.result.id);
                                    }
                                });
                            }
                        })
                    })
                })
            }
        }
    }
</script>