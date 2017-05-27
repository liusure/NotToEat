<template>
    <div class="am-g am-imglist">
        <ul  id="img_list" data-am-widget="gallery" class="am-gallery am-avg-sm-2 am-avg-md-3 am-avg-lg-6 am-gallery-default">
            <li v-for="file in files">
                <div class="am-gallery-item am_list_block">
                    <a :href="href(file)" class="am_img_bg" :class="'type-'+file.fileTypeShort" :title="file.fileName">
                        <img class="am_img animated" src="../assets/images/notoeat/am-loading.gif" :data-original="file.thumbnailPath" alt=""/>
                    </a>
                    <div class="am_listimg_info">
                        <span class="am-icon-heart"> 132</span>
                        <span class="am-icon-comments"> 67</span>
                        <span class="am_imglist_time"> {{file.createTime|calendar}}</span>
                    </div>
                </div>
                <a class="am_imglist_user">
                    <span class="am_imglist_user_ico">
                        <img :src="file.creator.facePath||'../static/assets/images/notoeat/default_face.png'" alt="">
                    </span>
                    <span class="am_imglist_user_font">{{file.creator.alias}}</span>
                </a>
            </li>
        </ul>
        <div id="blueimp-gallery" class="blueimp-gallery">
            <div class="slides"></div>
            <h3 class="title"></h3>
            <a class="prev">‹</a>
            <a class="next">›</a>
            <a class="close">×</a>
            <a class="play-pause"></a>
            <ol class="indicator"></ol>
        </div>
    </div>
</template>
<script>
    import Vue from "vue";
    import {bus, vm} from "main";
    export default {
        props: ["params"],
        data(){
            return {
                animation: "active",
                files: []
            }
        },
        methods: {
            initData: function () {
                var _this = this;
                this.$http.get("/media/getFiles", {
                    params: this.params
                }).then(res => {
                    return res.json();
                }).then(json => {
                    if (json.success) {
                        this.files = json.result;
                        Vue.nextTick(() => {
                            //@懒加载
                            $("img.am_img").lazyload();
                            $("a.am_img_bg").lazyload({
                                effect: 'fadeIn'
                            });
                            //@首页 图片效果
                            $(".am_list_block").on('mouseenter ', function () {
                                var $this = $(this).find(".am_img_bg");
                                $this.addClass(_this.animation).one("mouseleave", () => {
                                    $this.removeClass(_this.animation);
                                });
                            });
                            if ($(window).width() < 700) {
                                $('.am_list_block').off();
                            }
                           $('#img_list').on("click",".am_list_block .type-image", function (e) {
                                var target = e.target || e.srcElement,link = target.src ? target.parentNode : target,
                                    options = {
                                        index: link,
                                        event: e
                                    },
                                    links = $(this).closest("ul")[0].querySelectorAll('a.am_img_bg.type-image');
                                blueimp.Gallery(links, options);
                            })
                        })
                    } else {
                        console.log(json.msg)
                    }
                });
            },
            href:function (file) {
                switch(file.fileTypeShort){
                    case "image":
                        return file.filePath;
                    case "video":
                        return this.$router.options.base.substring(0,this.$router.options.base.length-1)+"#/video_page/"+file.id;
                }
            }
        },
        components: {
            picture: {
                template: "#hello-world-template"
            }
        },
        mounted: function () {
            this.initData();
            bus.$on("fileChange", () => {
                this.initData();
            });
        }
    }
</script>
<style scoped>
    @import url("../assets/css/plugins/blueimp/css/blueimp-gallery.css");

    .am_img_bg {
        transition: all .2s;
        box-shadow: 0px 0px 10px #030303;
    }

    .am_img_bg.active {
        transform: scale(1.05, 1.05);
        box-shadow: 0px 0px 5px #080808;
    }
</style>

