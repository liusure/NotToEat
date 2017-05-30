import Vue from "vue";
import Vuex from 'vuex'
import VueRouter from "vue-router";
import VueResource from 'vue-resource';
import app from "../component/app.vue";
import index from "../component/index.vue";
import pic_my from "../component/pic_my.vue";
import article_list from "../component/article_list.vue";
import article from "../component/article.vue";
import article_editor from "../component/article_editor.vue";
import user_center from "../component/user_center.vue";
import video from "../component/video.vue";
import video_page from "../component/video_page.vue";
import pic_masonry from "../component/pic_masonry.vue";
Vue.use(Vuex)
Vue.use(VueRouter);
Vue.use(VueResource);
const routes = [{
    path: "/",
    component: index
},{
    path: "/pic_masonry",
    component: pic_masonry
},{
    path:"/pic_my",
    component: pic_my
},{
    path:"/article_list",
    component: article_list
},{
    path:"/article_editor",
    component:article_editor
},{
    path:"/article/:id",
    component:article
},{
    path:"/user_center",
    component: user_center
},{
    path:"/video",
    component:video
},{
    path:"/video_page/:id",
    component:video_page
}]
const router = new VueRouter({
    base:"/index/",
    routes,
    linkActiveClass: 'am-active ',
})
var bus = new Vue();
var vm = new Vue({
    el: "#app",
    router,
    data:{
        context:{
            user:{face:{filePath:""}}
        }
    },
    components: {
        app: app
    },
    created:function () {
        this.$http.get("/user/getUser").then(res=>{
            res.json().then(json=>{
                if(json.success){
                    this.context.user=json.result;
                }
            })
        });
        bus.$on("userFaceUpdate",face=>{
            this.context.user.facePath = face.filePath;
        })
    }
})
Vue.filter('calendar', function (value,format) {
    return moment(value).calendar();
})
Vue.filter('fromNow', function (value,format) {
    return moment(value).fromNow();
})
moment.lang("zh-cn",require("moment/locale/zh-cn"));
export {bus,vm};