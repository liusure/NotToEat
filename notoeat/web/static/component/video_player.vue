<template>
    <div class="video-player">
        <div class="player-view">
            <video id="videoElement"></video>
            <div class="video-controller-bar">
                <div class="video-control video-play-btn pause am-icon-play am-icon-md am-icon-btn-md" @click="play()"></div>
                <div class="video-control video-volume">
                    <span class="volume-cube" v-for="n in 10" :class="{'active':n/10<=volume}" @click="setVolume(n)">
                        <span class="volume-cube-color"></span>
                    </span>
                </div>
                <div class="video-control video-play-progress" @click="setProgress($event)">
                    <div class="video-play-progress-bar" style="width: 0"></div>
                </div>
                <div class="video-control video-fullscreen-btn am-icon-arrows-alt am-icon-md am-icon-btn-md" @click="fullScreen()"></div>
            </div>
        </div>

    </div>
</template>
<script>
    import flvjs from "flv.js";
    var flvPlayer;
    var videoElement;
    var playBtn;
    var fullScreen;
    var videoProgress;
    var videoProgressbar;
    var progressInter;
    export default{
        props:["video"],
        data:function () {
          return {
              player:{
                  volume:.5
              }
            }
        },
        methods:{
            init:function(config){
                if (flvjs.isSupported()) {
                    flvPlayer = flvjs.createPlayer(config);
                    flvPlayer.attachMediaElement(videoElement);
                    flvPlayer.load();
                    flvPlayer.volume = this.player.volume;
                }
                videoElement.addEventListener("click",()=>{
                    this.play();
                })
            },
            play:function () {
                var isPlaying = changePlayBtnStatus(playBtn);
                isPlaying?flvPlayer.play():flvPlayer.pause();
            },
            setVolume:function (n) {
                n/=10;
                flvPlayer.volume = n;
                this.player.volume = n;
            },
            setProgress:function (e) {
                var p = e.offsetX/e.currentTarget.offsetWidth;
                flvPlayer.currentTime = flvPlayer.duration * p;
                videoProgressbar.style.width = p*100+"%";
            },
            fullScreen:function () {
                changeFullScreen(videoElement);
            }
        },
        mounted:function () {
            videoElement = document.getElementById('videoElement');
            playBtn = document.querySelector(".video-control.video-play-btn");
            fullScreen = document.querySelector(".video-control.video-fullscreen-btn");
            videoProgress =  document.querySelector(".video-control.video-play-progress");
            videoProgressbar=  document.querySelector(".video-control.video-play-progress .video-play-progress-bar");
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
        },
        computed:{
            volume:function () {
                return this.player.volume;
            }
        }
    }
    function changePlayBtnStatus(btn){
        if(btn.classList.contains("playing")){
            btn.classList.remove("playing")
            btn.classList.remove("am-icon-pause");
            btn.classList.add("pause")
            btn.classList.add("am-icon-play");
            clearInterval(progressInter);
            return false;
        }else if(btn.classList.contains("pause")){
            btn.classList.remove("pause");
            btn.classList.remove("am-icon-play");
            btn.classList.add("playing");
            btn.classList.add("am-icon-pause");
            progressInter = setInterval(setProgress,100);
            return true;
        }
    }
    function setProgress() {
        var progressValue = (flvPlayer.currentTime/flvPlayer.duration)*100;
        videoProgressbar.style.width = progressValue+"%";
    }
    function changeFullScreen(el) {
        if(!videoElement.isFullScreen){
            if(el.requestFullscreen){
                el.requestFullscreen();
            }else if(el.mozRequestFullScreen) {
                el.mozRequestFullScreen();
            } else if(el.msRequestFullscreen){
                el.msRequestFullscreen();
            } else if(el.oRequestFullscreen){
                el.oRequestFullscreen();
            }else if(el.webkitRequestFullscreen){
                el.webkitRequestFullScreen();
            } else{
                var docHtml = document.documentElement;
                var docBody = document.body;
                var videobox = document.getElementById('videobox');
                var cssText = 'width:100%;height:100%;overflow:hidden;';
                docHtml.style.cssText = cssText;
                docBody.style.cssText = cssText;
                el.style.cssText = cssText+';'+'margin:0px;padding:0px;';
                document.IsFullScreen = true;
            }
            fullScreen.classList.remove("am-icon-arrows-alt");
            fullScreen.classList.add("am-icon-compress");
            el.isFullScreen=true;
        }else{
            if (document.exitFullscreen) {
                document.exitFullscreen();
            } else if (document.msExitFullscreen) {
                document.msExitFullscreen();
            } else if (document.mozCancelFullScreen) {
                document.mozCancelFullScreen();
            } else if(document.oRequestFullscreen){
                document.oCancelFullScreen();
            }else if (document.webkitExitFullscreen){
                document.webkitExitFullscreen();
            }else{
                var docHtml = document.documentElement;
                var docBody = document.body;
                var videobox = document.getElementById('videobox');
                docHtml.style.cssText = "";
                docBody.style.cssText = "";
                videobox.style.cssText = "";
                document.IsFullScreen = false;
            }
            fullScreen.classList.remove("am-icon-compress");
            fullScreen.classList.add("am-icon-arrows-alt");
            el.isFullScreen=false;
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
        left:0;
        bottom: 0px;
        opacity: .7;
        color: #FFFFFF;
        display: none;
        height: 64px;
        width: 100%;
        flex-wrap:nowrap;
        justify-content:space-between;
        align-items:stretch;
        z-index: 2147483648;
        background: linear-gradient(top , rgba(255, 255, 255,0) , rgb(0, 0, 0) 70%);
        background: -o-linear-gradient(top , rgba(255, 255, 255,0) , rgb(0, 0, 0) 70%);
        background: -ms-linear-gradient(top , rgba(255, 255, 255,0) , rgb(0, 0, 0) 70%);
        background: -moz-linear-gradient(top , rgba(255, 255, 255,0) , rgb(0, 0, 0) 70%);
        background: -webkit-linear-gradient(top , rgba(255, 255, 255,0) , rgb(0, 0, 0) 70%);

    }
    .video-controller-bar.active{
        display: flex;
    }
    .video-controller-bar .video-control{
        border: none;
        background-color:rgba(0,0,0,0);
        cursor: pointer;
        vertical-align: middle;
        padding: 6px;
        margin: 10px;
    }
    .video-control.video-volume{
        min-width: 100px;
        width: 10%;
        display: flex;
        flex-wrap: nowrap;
    }
    .video-control.video-volume .volume-cube{
        display: inline-block;
        height: 100%;
        width: auto;
        flex-grow: 1;
        padding: 2px;
    }
    .video-control.video-volume .volume-cube .volume-cube-color{
        display: block;
        width: 100%;
        height: 100%;
        background-color:#3D3D3D;
    }
    .video-control.video-volume .volume-cube.active .volume-cube-color{
        background-color: #0e9aef;
    }
    .video-control.video-play-progress{
        flex-grow: 1;
    }
    .video-play-progress-bar{
        height: 100%;
        background-color: #0e9aef;
    }
    video::-webkit-media-controls {
        display:none !important;
    }
</style>