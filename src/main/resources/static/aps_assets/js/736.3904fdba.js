"use strict";(self["webpackChunkaminer_demo"]=self["webpackChunkaminer_demo"]||[]).push([[736],{4736:function(a,t,s){s.r(t),s.d(t,{default:function(){return p}});var e=function(){var a=this,t=a.$createElement,s=a._self._c||t;return s("div",{staticClass:"container"},[s("div",{staticClass:"title"},[s("span",[a._v(a._s(a.$t("scholarDetails.authorStatistics")))])]),s("div",{staticClass:"content"},[s("div",{ref:"chart",staticStyle:{height:"200px"}}),s("div",{staticClass:"data_content"},[s("p",[s("span",{staticClass:"label"},[a._v("#Papers: ")]),s("span",{staticClass:"num"},[a._v(a._s(a.radarData[0]))])]),s("p",[s("span",{staticClass:"label"},[a._v("#Citation: ")]),s("span",{staticClass:"num"},[a._v(a._s(a.radarData[6]))])]),s("p",[s("span",{staticClass:"label"},[a._v("H-Index: ")]),s("span",{staticClass:"num"},[a._v(a._s(a.radarData[5]))])]),s("p",[s("span",{staticClass:"label"},[a._v("G-Index: ")]),s("span",{staticClass:"num"},[a._v(a._s(a.radarData[4]))])]),s("p",[s("span",{staticClass:"label"},[a._v("Sociability: ")]),s("span",{staticClass:"num"},[a._v(a._s(a.radarData[3]))])]),s("p",[s("span",{staticClass:"label"},[a._v("Diversity: ")]),s("span",{staticClass:"num"},[a._v(a._s(a.radarData[2]))])]),s("p",[s("span",{staticClass:"label"},[a._v("Activity: ")]),s("span",{staticClass:"num"},[a._v(a._s(a.radarData[1]))])])])])])},i=[],n=s(1570),r={created(){this.getRadarData()},data(){return{radarData:[0,0,0,0,0,0,0]}},methods:{getRadarData(){(0,n.IT)({scholarId:this.$route.params.id}).then((a=>{this.radarData=[a.data.papers,a.data.activity,a.data.diversity,a.data.sociability,a.data.gIndex,a.data.hIndex,a.data.citation],this.setChart()}))},setChart(){const a=this.$echarts.init(this.$refs.chart),t={radar:{center:["35%","50%"],radius:[40,90],axisName:{color:"#000",fontWeight:500},splitNumber:4,shape:"circle",nameGap:-33,scale:!1,splitLine:{lineStyle:{color:"#d0d0cf"}},splitArea:{areaStyle:{color:["#ebeba6","#efefb9","#f4f4ce","#f9f9e5"]}},indicator:[{name:"#Papers",max:200},{name:"Activity",max:5,min:-1},{name:"Diversity",max:5,min:-1},{name:"Sociability",max:5,min:-1},{name:"G-Index",max:50},{name:"H-Index",max:50},{name:"#Citation",max:2e3}]},series:[{type:"radar",animation:!1,data:[{value:this.radarData}],itemStyle:{opacity:0},lineStyle:{color:"#78be54"},areaStyle:{color:new this.$echarts.graphic.RadialGradient(.1,.6,1,[{color:"#78be54",offset:0}])}}]};a.setOption(t)}}},l=r,c=s(1001),d=(0,c.Z)(l,e,i,!1,null,"9199adf4",null),p=d.exports}}]);