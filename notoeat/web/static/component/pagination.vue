<template>
    <ul v-if="page.totalCount" data-am-widget="pagination" class="am-pagination am-pagination-default am-u-lg-12" >
        <li class="am-pagination-first ">
            <a href="javascript:void(0)" @click="setCurrentPage(1)" class="">第一页</a>
        </li>

        <li class="am-pagination-prev ">
            <a href="javascript:void(0)"  @click="setCurrentPage(page.currentPage-1)" class="">上一页</a>
        </li>

        <li class="" v-if="" :class="{'am-active':n===page.currentPage}" v-for="n in showPages">
            <a href="javascript:void(0)"  @click="setCurrentPage(n)" class="">{{n}}</a>
        </li>

        <li class="am-pagination-next ">
            <a href="javascript:void(0)" @click="setCurrentPage(page.currentPage+1)" class="">下一页</a>
        </li>

        <li class="am-pagination-last ">
            <a href="javascript:void(0)" @click="setCurrentPage(page.pageCount)" class="">最末页</a>
        </li>
    </ul>
</template>
<script>
    export default{
        props:["url","showSize","pageSize","params"],
        data: function () {
            return {
                page:{
                    currentPage:1,
                    pageCount:0,
                    totalCount:0,
                    pageSize:this.pageSize||20
                }
            }
        },
        computed:{
            showPages:function () {
                if(this.showSize){
                    this.showSize = Number.parseInt(this.showSize);
                    var showPages = new Array(this.showSize>this.page.pageCount?this.page.pageCount:this.showSize);
                    var basePoint = Math.floor(this.showSize/2);//基准点在中
                    var left = this.page.currentPage-basePoint;
                    var right = this.page.currentPage+basePoint;
                    var start = (left<1?1:(right>this.page.pageCount?this.page.pageCount-showPages.length:right-showPages.length)+1);
                    for (var i = 0; i<showPages.length; i++) {
                        showPages[i] = start+i;
                    }
                    return showPages;
                }
            }
        },
        methods: {
            reload:function () {
                this.getPage();
            },
            getPage:function () {
                this.$http.post(this.url,{
                    currentPage:this.page.currentPage,
                    pageSize:this.page.pageSize
                },{
                    params:this.params,
                    emulateJSON:true
                }).then(res=>{
                    res.json().then(json=>{
                        if(json.results){
                            this.page = json;
                            this.$parent.setResults(json.results);
                        }
                    })
                })
            },
            setCurrentPage:function (currentPage) {
                currentPage = (currentPage=currentPage>this.page.pageCount?this.page.pageCount:currentPage)<1?1:currentPage;
                this.page.currentPage = currentPage;
            }
        },
        watch:{
            "page.currentPage":function () {
                this.reload();
            },
            "params":function () {
                this.reload();
            },
            "pageSize":function (to) {
                this.page.pageSize = to;
            }
        },
        created:function () {
            this.reload();
        }
    }
</script>