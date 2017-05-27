<template>
    <div class="am-g">
        <div class="am-u-lg-12">
            <h2>Comments:</h2>
            <div v-if="comments" class="social-feed-box" v-for="comment in comments">
                <div class="social-avatar">
                    <a href="article.html" class="am-fl">
                        <img alt="image" :src="comment.creator.facePath||'../static/assets/images/notoeat/default_face.png'">
                    </a>
                    <div class="media-body">
                        <a href="article.html#">
                            {{comment.creator.alias}}
                        </a>
                        <small class="text-muted">{{comment.createTime|calendar}}</small>
                    </div>
                </div>
                <div class="social-body" v-html="comment.content"></div>
            </div>
        </div>
        <pagination url="/comments/getCommentPages" show-size="7" :params="params" ref="pagination"></pagination>
        <div class="am-u-lg-12">
            <div id="editor" style="height: 300px"></div>
            <button class="am-btn am-btn-primary" @click="saveComment">发布</button>
        </div>
    </div>
</template>
<script>
    import pagination from "./pagination.vue"
    var wangEditor = require('wangeditor');
    var editor;
    export default{
        props: ["id"],
        components:{
            pagination:pagination
        },
        data: function () {
            return {
                talking: {},
                comments: []
            }
        },
        computed:{
            params:function () {
                return {'talkingId':this.talking.id}
            }
        },
        methods: {
            init: function (id) {
                this.$http.post("/talking/getTalking", {
                    id: id
                }, {
                    emulateJSON: true
                }).then(res => {
                    res.json().then(json => {
                        if (json.success) {
                            this.talking = json.result;
                        }
                    })
                })
            },
            saveComment: function () {
                if(!editor.$txt.text()){
                    return;
                }
                this.$http.post("/comments/saveComment", {
                    content: editor.$txt.html(),
                    talking:{
                        id:this.id
                    }
                }).then(res => {
                    res.json().then(json => {
                        if (json.success) {
                            this.$refs.pagination.reload();
                        }
                    })
                })
            },
            setResults:function (results) {
                this.comments = results;
            }
        },
        watch: {
            "id": function (to, from) {
                this.init(to);
            }
        },
        mounted: function () {
            editor = new wangEditor('editor');
            editor.config.menus = ['source',
                '|',
                'bold',
                'underline',
                'italic',
                'strikethrough',
                'eraser',
                'forecolor',
                'bgcolor'];
            editor.create();
        }
    }
</script>
<style>
    .social-feed-box {
        margin-bottom: 0;
        border-bottom: none;
    }
    .social-feed-box:last-child {
        margin-bottom: 0;
        border-bottom: 1px solid #e7eaec;
    }
    .social-feed-box p {
        font-size: 13px;
        line-height: 18px;
    }
    /* Social feed */
    .social-feed-separated .social-feed-box {
        margin-left: 62px;
    }
    .social-feed-separated .social-avatar {
        float: left;
        padding: 0;
    }
    .social-feed-separated .social-avatar img {
        width: 52px;
        height: 52px;
        border: 1px solid #e7eaec;
    }
    .social-feed-separated .social-feed-box .social-avatar {
        padding: 15px 15px 0 15px;
        float: none;
    }
    .social-feed-box {
        /*padding: 15px;*/
        border: 1px solid #e7eaec;
        background: #fff;
        margin-bottom: 15px;
    }
    .social-feed-box {
        margin-bottom: 0;
        border-bottom: none;
    }
    .social-feed-box:last-child {
        margin-bottom: 0;
        border-bottom: 1px solid #e7eaec;
    }
    .social-feed-box p {
        font-size: 13px;
        line-height: 18px;
    }
    .social-action {
        margin: 15px;
    }
    .social-avatar {
        padding: 15px 15px 0 15px;
    }
    .social-comment .social-comment {
        margin-left: 45px;
    }
    .social-avatar img {
        height: 40px;
        width: 40px;
        margin-right: 10px;
    }
    .social-avatar .media-body a {
        font-size: 14px;
        display: block;
    }
    .social-body {
        padding: 15px;
    }
    .social-body img {
        margin-bottom: 10px;
    }
    .social-footer {
        border-top: 1px solid #e7eaec;
        padding: 10px 15px;
        background: #f9f9f9;
    }
    .social-footer .social-comment img {
        width: 32px;
        margin-right: 10px;
    }
    .social-comment:first-child {
        margin-top: 0;
    }
    .social-comment {
        margin-top: 15px;
    }
    .social-comment textarea {
        font-size: 12px;
    }
</style> 