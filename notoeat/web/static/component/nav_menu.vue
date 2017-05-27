<template>
        <ul class="am-topbar-nav">
            <li><router-link to="/user_center"><a href="javascript:void(0)"><img :src="user.facePath||'../static/assets/images/notoeat/default_face.png'" width="40" height="40"/>{{user.alias}}</a></router-link></li>
            <li><router-link to=""><a href="javascript:void(0)">消息</a></router-link></li>
            <li><router-link to=""><a href="javascript:void(0)">登出</a></router-link></li>
        </ul>
</template>
<script>
    export default{
        props:["user"]
    }
</script>
<style scoped>
    ul li{
        float: left;
    }
</style>