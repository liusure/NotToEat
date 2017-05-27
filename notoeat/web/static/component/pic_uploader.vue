<template>
    <div class="wrapper wrapper-content animated fadeIn">
        <div class="am-g">
            <div class="am-u-lg-12">
                <form id="dropzone" class="dropzone">
                    <div class="dropzone-previews"></div>
                    <button type="submit" class="am-btn am-btn-success am-fr">Submit this form!</button>
                    <div class="dz-default dz-message"><span>Drop files here to upload</span></div>
                </form>
                <div>
                    <div class="m text-right"><small>DropzoneJS is an open source library that provides drag'n'drop file uploads with image previews: <a href="https://github.com/enyo/dropzone" target="_blank">https://github.com/enyo/dropzone</a></small> </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    import Dropzone from "../assets/js/plugins/dropzone/dropzone.js";
    import {bus,vm} from "main";
    export default{
        mounted:function () {
            var dropzone = new Dropzone("#dropzone",{
                url: "/media/upload",
                autoProcessQueue: true,
                uploadMultiple: false,
                parallelUploads: 100,
                maxFiles: 100,
                init:function () {
                    this.on("success",function () {
                        bus.$emit("fileChange");
                    });
                    this.element.querySelector("button[type=submit]").addEventListener("click", e=> {
                        e.preventDefault();
                        e.stopPropagation();
                        this.processQueue();
                    });
                }
            });
        }
    }
</script>
<style scoped>
    @import "../assets/css/plugins/dropzone/basic.css";
    @import "../assets/css/plugins/dropzone/dropzone.css";
</style>