<template>
    <div class="am-g">
        <div class="am-u-lg-12">
            <video-player :video="video"></video-player>
        </div>
    </div>
</template>
<script>
    import videoPlayer from "./video_player.vue";
    export default{
        components:{
            "video-player":videoPlayer
        },
        data:function () {
            return {
                video:{
                }
            }
        },
        created:function () {
            this.$http.post("/media/getFile",{
                id:this.$route.params.id
            },{
                emulateJSON:true
            }).then(res=>{
                res.json().then(json=>{
                    if(json.success){
                        this.video = json.result;
                    }
                })
            })
        }
    }
</script>