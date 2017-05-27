<template>
    <div>
        <banner></banner>
        <gallery></gallery>
    </div>
</template>
<script>
    import banner from "./banner.vue";
    import gallery from "./gallery.vue";
    export default{
        components:{
            banner:banner,
            gallery:gallery
        }
    }
</script>