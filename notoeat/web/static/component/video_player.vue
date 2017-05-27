<template>
    <div class="video-player">
        <div class="player-view">
            <video id="videoElement"></video>
            <div class="video-controller-bar">
                <button class="video-control video-play-btn pause am-icon-play" @click="play($event)"></button>
                <div class="video-control video-volume"></div>
                <div class="video-control video-play-progress"></div>
                <button class="video-control video-fullscreen-btn"></button>
            </div>
        </div>

    </div>
</template>
<script>
    import flvjs from "flv.js";
    var flvPlayer;
    var videoElement;
    export default{
        props:["video"],
        methods:{
            init:function(config){
                if (flvjs.isSupported()) {
                    flvPlayer = flvjs.createPlayer(config);
                    flvPlayer.attachMediaElement(videoElement);
                    flvPlayer.load();
                }
            },
            play:function (e) {
                var btn = e.target;
                if(btn.classList.contains("playing")){
                    flvPlayer.pause();
                    btn.classList.remove("playing")
                    btn.classList.remove("am-icon-pause");
                    btn.classList.add("pause")
                    btn.classList.add("am-icon-play");
                }else if(btn.classList.contains("pause")){
                    flvPlayer.play();
                    btn.classList.remove("pause");
                    btn.classList.remove("am-icon-play");
                    btn.classList.add("playing");
                    btn.classList.add("am-icon-pause");
                }
            }
        },
        mounted:function () {
            videoElement = document.getElementById('videoElement');
            $(".player-view").on("mouseenter",function(){
                var bar = $(this).find(".video-controller-bar").addClass("active");
                $(this).one("mouseleave",()=>{
                    bar.removeClass("active")
                })
            })
        },
        watch:{
            "video":function (to) {
                if(to.filePath&&to.fileContentType){
                    this.init({
                        type: this.video.fileContentType,
                        url: this.video.filePath
                    });
                }
            }
        }
    }
</script>
<style>
    .player-view{
        position: relative;
        display: flex;
    }
    #videoElement{
        width: 100%;
        height: 100%;
    }
    .video-player .video-controller-bar{
        position: absolute;
        bottom: 0px;
        opacity: .7;
        color: #FFFFFF;
        display: none;
    }
    .video-controller-bar.active{
        display: flex;
    }
    .video-play-btn,.video-volume,.video-fullscreen-btn{
        flex: none;
        background-color: #000000;
    }
    .video-player .video-controller-bar .video-play-progress{
        flex: auto;
    }
</style>