"use strict";(self["webpackChunkaminer_demo"]=self["webpackChunkaminer_demo"]||[]).push([[200],{8200:function(e,t,i){i.r(t),i.d(t,{default:function(){return c}});var n=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("transition",{attrs:{name:e.transitionName}},[i("div",{directives:[{name:"show",rawName:"v-show",value:e.visible,expression:"visible"}],staticClass:"back-to-ceiling",style:e.customStyle,on:{click:e.backToTop}},[i("span",[e._v("返回顶部")])])])},s=[],o={name:"BackToTop",props:{visibilityHeight:{type:Number,default:400},backPosition:{type:Number,default:0},customStyle:{type:Object,default:function(){return{right:"50px",bottom:"50px",width:"40px",height:"40px","border-radius":"4px","line-height":"45px",background:"#e7eaf1"}}},transitionName:{type:String,default:"fade"}},data(){return{visible:!1,interval:null,isMoving:!1}},mounted(){window.addEventListener("scroll",this.handleScroll)},beforeDestroy(){window.removeEventListener("scroll",this.handleScroll),this.interval&&clearInterval(this.interval)},methods:{handleScroll(){this.visible=window.pageYOffset>this.visibilityHeight},backToTop(){if(this.isMoving)return;const e=window.pageYOffset;let t=0;this.isMoving=!0,this.interval=setInterval((()=>{const i=Math.floor(this.easeInOutQuad(10*t,e,-e,500));i<=this.backPosition?(window.scrollTo(0,this.backPosition),clearInterval(this.interval),this.isMoving=!1):window.scrollTo(0,i),t++}),5)},easeInOutQuad(e,t,i,n){return(e/=n/2)<1?i/2*e*e+t:-i/2*(--e*(e-2)-1)+t}}},a=o,r=i(1001),l=(0,r.Z)(a,n,s,!1,null,"fc888562",null),c=l.exports}}]);