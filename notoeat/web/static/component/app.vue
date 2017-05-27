<template>
    <div>
        <header class="am-topbar am-topbar-inverse">
            <div class="amz-container">
                <h1 class="am-topbar-brand">
                    <a href="#" class="am-topbar-logo">
                        <img src="/static/assets/images/notoeat/logo.png?1" alt="">
                    </a>
                </h1>
                <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#doc-topbar-collapse-5'}">
                    <span class="am-sr-only">
                        导航切换
                    </span>
                    <span class="am-icon-bars"></span>
                </button>
                <div class="am-collapse am-topbar-collapse" id="doc-topbar-collapse-5">
                    <ul class="am-nav am-nav-pills am-topbar-nav">
                        <router-link tag="li"  to="/" exact>
                            <a>
                                首页
                            </a>
                        </router-link>
                        <li class="am-dropdown" data-am-dropdown="">
                            <a class="am-dropdown-toggle" data-am-dropdown-toggle="" href="javascript:void(0);">
                                画廊
                                <span class="am-icon-caret-down"></span>
                            </a>
                            <ul class="am-dropdown-content">
                                <router-link tag="li"  to="/pic_masonry">
                                    <a>
                                        全部
                                    </a>
                                </router-link>
                                <router-link tag="li"  to="/pic_my">
                                    <a>
                                        我的
                                    </a>
                                </router-link>
                            </ul>
                        </li>
                        <router-link tag="li" to="/article_list">
                            <a>
                                文章
                            </a>
                        </router-link>
                        <router-link tag="li" to="/article_editor">
                            <a>
                                发布
                            </a>
                        </router-link>
                        <router-link tag="li" to="/video">
                            <a>
                                视频
                            </a>
                        </router-link>
                    </ul>
                    <nav_menu class="am-topbar-right " :user="context.user"></nav_menu>
                </div>
            </div>
        </header>
        <router-view></router-view>
        <footer class="am_footer">
            <div class="am_footer_con">
                <div class="am_footer_link">
                    <span>关于</span>
                    <ul>
                        <li><a href="###">友情链接</a></li>
                    </ul>
                </div>
            </div>
            <div class="am_info_line"></div>
        </footer>
    </div>
</template>
<script>
    import nav_menu from "./nav_menu.vue"
    export default{
        name: "app",
        props:["context"],
        data: function () {
            return {

            }
        },
        components:{
           nav_menu:nav_menu
        },
        mounted:function () {
            $('.am-dropdown').on('click', function () {
                //$(this).addClass('am-active');
                $(this).dropdown('toggle');
            });
            $(window).resize(function () {
                $('.am_listimg_info .am_imglist_time').css('display', $('.am_list_block').width() <= 170 ? 'none' : 'block');
            });
            //@首页 图片列表长宽相等
            $(window).resize(function () {
                $('.am_img_bg').height($('.am_img_bg').width());
            });
        }
    }
</script>
<style>
    .small{
        font-size: 85%;
    }
    .btn-link {
        color: inherit;
    }
    .btn-link:hover,
    .btn-link:focus,
    .btn-link:active,
    .btn-link.active,
    .open .dropdown-toggle.btn-link {
        color: #686B6D;
        text-decoration: none;
    }
</style>