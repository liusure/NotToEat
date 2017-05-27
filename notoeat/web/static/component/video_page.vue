<template>
    <video-player :video="video"></video-player>
    <button @click="play">start</button>
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