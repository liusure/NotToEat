<template>
    <div class="wrapper wrapper-content  animated fadeInRight article">
        <div class="am-g">
            <div class="am-u-lg-10 am-u-lg-offset-1">
                <div class="ibox">
                    <div class="ibox-content">
                        <div class="pull-right">
                            <button class="am-btn am-btn-white am-btn-xs" type="button">Model</button>
                            <button class="am-btn am-btn-white am-btn-xs" type="button">Publishing</button>
                            <button class="am-btn am-btn-white am-btn-xs" type="button">Modern</button>
                        </div>
                        <div class="am-text-center article-title">
                            <h1>
                                {{article.name}}
                            </h1>
                            <span class="text-muted">
                                <span>{{article.creator.alias}}</span>
                                <i class="am-icon-clock-o"></i>{{article.createTime|calendar}}
                            </span>
                        </div>
                       <div v-html="article.content"></div>
                        <hr>
                        <div class="am-g">
                            <div class="am-u-md-6">
                                <h5>Tags:</h5>
                                <button class="am-btn am-btn-primary am-btn-xs" type="button">Model</button>
                                <button class="am-btn am-btn-white am-btn-xs" type="button">Publishing</button>
                            </div>
                            <div class="am-u-md-6">
                                <div class="small am-text-right">
                                    <h5>Stats:</h5>
                                    <div> <i class="am-icon-comments-o"> </i> 56 comments </div>
                                    <i class="am-icon-eye"> </i> 144 views
                                </div>
                            </div>
                        </div>
                        <comments :id="article.talking.id"></comments>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    import comments from "./comments.vue";
    export default{
        components:{
            comments:comments
        },
        data: function () {
            return {
                article:{
                    name:"",
                    createTime:"",
                    content:"",
                    talking:{id:undefined},
                    creator:{
                        alias:""
                    }
                }
            }
        },
        watch:{
            "$route":function (to,from) {
                this.init(to);
            }
        },
        methods:{
            init:function (id) {
                this.$http.post("/article/getArticle",{
                    id:id
                },{
                    emulateJSON:true
                }).then(res=>{
                    res.json().then(json=>{
                        if(json.success){
                            this.article=json.result;
                        }
                    })
                })
            }
        },
        created:function () {
            this.init(this.$route.params.id);
        }
    }
</script>
<style>
    .article h1 {
        font-size: 48px;
        font-weight: 700;
        color: #2F4050;
    }
    .article p {
        font-size: 15px;
        line-height: 26px;
    }
    .article-title {
        text-align: center;
        margin: 40px 0 100px 0;
    }
    .article .ibox-content {
        padding: 40px;
    }
    p.content{
        max-height: 100px;
        overflow: hidden;
    }
</style>