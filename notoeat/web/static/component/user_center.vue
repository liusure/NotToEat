<template>
    <div class="am_user">
        <div class="am_user_head">
            <div class="am_user_head_content">
                <div class="am_user_head_l">
                    <div class="am_user_head_l_ico">
                        <div id="face" @click="openFile" v-bind:style="{backgroundImage:'url('+(user.facePath||'../static/assets/images/notoeat/default_face.png')+')'}"></div>
                    </div>
                    <div class="am_user_head_l_ico_info">
                        <span class="am_user_head_l_alias">{{user.alias}}</span>
                        <span class="am_user_head_l_name">#{{user.id}}</span>
                        <span class="am_user_head_l_map"><i class="am-icon-map-marker"></i> 香港特别行政区</span>
                    </div>
                </div>
                <div class="am_user_head_r">
                    <div class="am_user_head_r_tag">
                        <div class="am_user_head_l_ico_info_ti">
                            已发布<span class="cff5656">{{params.fileCount||0}}</span>个作品,<span class="cff5656">{{params.articleCount||0}}</span>篇文章。
                        </div>
                        <span> <i class="am-icon-star"></i> 粉丝：<span class="am_user_head_unm">15</span> </span>
                        <span> <i class="am-icon-user"></i> 关注：<span class="am_user_head_unm">6</span> </span>
                        <span> <i class="am-icon-heart"></i>被喜欢：<span class="am_user_head_unm">60</span> </span>
                    </div>
                </div>
            </div>
        </div>
        <face_uploader ref="faceUploader"></face_uploader>
    </div>
</template>
<script>
    import face_uploader from "./face_uploader.vue";
    import {bus,vm} from "main";
    export default{
        components:{
           face_uploader:face_uploader
        },
        data:function(){
            return {
                user:{
                    face:{filePath:""},
                    files:[]
                },
                params:{}
            }
        },
        methods:{
            openFile:function () {
                this.$refs.faceUploader.upload();
            }
        },
        created:function(){
            this.$http.get("/user/getUser").then(res=>{
            res.json().then(json=>{
                if(json.success){
                    this.user=json.result;
                    this.params = json.params;
                }
            })
            });
            bus.$on("userFaceUpdate",face=>{
                this.user.facePath = face.filePath;
            })
        }
    }
</script>
<style>
    #face{
        width: 65px;
        height: 65px;
        display: block;
        background-position: 50% 50%;
        background-size: contain;
        cursor: pointer;
    }
</style>