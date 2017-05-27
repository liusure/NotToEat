<template>
    <div>
        <gallery params="{ type: 'image',scope:'personal'}"></gallery>
        <pic-uploader></pic-uploader>
    </div>
</template>
<script>
    import picUploader from "./pic_uploader.vue";
    import gallery from "./gallery.vue";
    export default{
        components: {
            picUploader:picUploader,
            gallery:gallery
        }
    }
</script>