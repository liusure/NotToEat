<template>
    <div class="am-g">
        <div class="am-u-lg-12" v-for="article in articles">
            <div class="ibox">
                <div class="ibox-content">
                    <router-link :to="'/article/'+article.id" class="btn-link">
                        <h2>
                            {{article.name}}
                        </h2>
                    </router-link>
                    <div class="small m-b-xs">
                        <strong>{{article.creator.alias}}</strong> <span class="text-muted"><i class="am-icon-clock-o"></i> {{article.createTime|fromNow}}</span>
                    </div>
                    <p class="content" v-html="article.content"></p>
                    <div class="am-g">
                        <div class="am-u-md-6">
                            <h5>Tags:</h5>
                            <button class="am-btn am-btn-primary am-btn-xs" type="button">Publishing</button>
                            <button class="am-btn am-btn-white am-btn-xs" type="button">Model</button>
                        </div>
                        <div class="am-u-md-6">
                            <div class="small am-text-right">
                                <h5>Stats:</h5>
                                <div> <i class="am-icon-comments-o"> </i> {{article.talking.commentsCount}} comments </div>
                                <i class="am-icon-eye"> </i> {{article.visitedCount}} views
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <pagination url="/article/getArticles" show-size="7" page-size="5" :params="params" ref="pagination"></pagination>
    </div>
</template>
<script>
    import pagination from "./pagination.vue"
    export default{
        components:{
            pagination:pagination
        },
        data:function () {
            return {
                articles:[]
            }
        },
        computed:{
          params:function(){
              return {
                  type:1
              }
          }
        },
        methods:{
            setResults:function (results) {
                this.articles = results;
            }
        }
    }
</script>
<style>

    .ibox-content.text-box {
        padding-bottom: 0;
        padding-top: 15px;
    }
    .ibox-content .content img{
        max-height:200px;
    }
    .ibox {
        clear: both;
        margin-bottom: 25px;
        margin-top: 0;
        padding: 0;
    }
    .ibox.collapsed .ibox-content {
        display: none;
    }
    .ibox.collapsed .fa.fa-chevron-up:before {
        content: "\f078";
    }
    .ibox.collapsed .fa.fa-chevron-down:before {
        content: "\f077";
    }
    .ibox:after,
    .ibox:before {
        display: table;
    }
    .ibox-title {
        -moz-border-bottom-colors: none;
        -moz-border-left-colors: none;
        -moz-border-right-colors: none;
        -moz-border-top-colors: none;
        background-color: #ffffff;
        border-color: #e7eaec;
        border-image: none;
        border-style: solid solid none;
        border-width: 3px 0 0;
        color: inherit;
        margin-bottom: 0;
        padding: 14px 15px 7px;
        min-height: 48px;
    }
    .ibox-content {
        background-color: #ffffff;
        color: inherit;
        padding: 15px 20px 20px 20px;
        border-color: #e7eaec;
        border-image: none;
        border-style: solid solid none;
        border-width: 1px 0;
    }
    .ibox-footer {
        color: inherit;
        border-top: 1px solid #e7eaec;
        font-size: 90%;
        background: #ffffff;
        padding: 10px 15px;
    }
    .ibox-content {
        clear: both;
    }
    .ibox-heading {
        background-color: #f3f6fb;
        border-bottom: none;
    }
    .ibox-heading h3 {
        font-weight: 200;
        font-size: 24px;
    }
    .ibox-title h5 {
        display: inline-block;
        font-size: 14px;
        margin: 0 0 7px;
        padding: 0;
        text-overflow: ellipsis;
        float: left;
    }
    .ibox-title .label {
        float: left;
        margin-left: 4px;
    }
    .ibox-tools {
        display: block;
        float: none;
        margin-top: 0;
        position: relative;
        padding: 0;
        text-align: right;
    }
    .ibox-tools a {
        cursor: pointer;
        margin-left: 5px;
        color: #c4c4c4;
    }
    .ibox-tools a.btn-primary {
        color: #fff;
    }
    .ibox-tools .dropdown-menu > li > a {
        padding: 4px 10px;
        font-size: 12px;
    }
    .ibox .ibox-tools.open > .dropdown-menu {
        left: auto;
        right: 0;
    }
    .ibox.fullscreen {
        z-index: 2030;
        position: fixed;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        overflow: auto;
        margin-bottom: 0;
    }
    .ibox.fullscreen .collapse-link {
        display: none;
    }
    .ibox.fullscreen .ibox-content {
        min-height: calc(100% - 48px);
    }
</style>