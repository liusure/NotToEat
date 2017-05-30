<template>
        <ul class="am-topbar-nav">
            <li><router-link to="/user_center"><img :src="user.facePath||'../static/assets/images/notoeat/default_face.png'" width="40" height="40"/>{{user.alias}}</router-link></li>
            <li><router-link to="/message">消息</router-link></li>
            <li><a href="/logout">登出</a></li>
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