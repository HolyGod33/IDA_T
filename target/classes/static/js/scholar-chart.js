function drawYearChart(scholar) {
    var yearChart = echarts.init(document.getElementById('yearChart'), null, {renderer: 'svg'});

    // function findAchievementByName() {
    //
    // }


    function fetchData(cb) {
        $.ajax({
            url: "/ida/scholar/findpublisharticlecountbyscholarname1?scholarName=" + scholar,
            dataType: "json",
            type: "get",
            success: function (data,status) {
                yearChart.hideLoading();
                // data.data.forEach(function (node) {
                //     node.smooth= true,
                //         node.areaStyle={}
                // });

                option = {
                    title: {
                        text: 'IDA_ZJUT',
                        // subtext: 'xzs',
                        top: 'top',
                        left: 'left'
                    },
                    tooltip: {
                        trigger: 'axis',
                        // axisPointer: {
                        //     type: 'cross',
                        //     label: {
                        //         backgroundColor: '#6a7985'
                        //     }
                        // }
                    },
                    legend: {
                        data: data.legend
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    // toolbox: {
                    //     show:true,
                    //     feature: {
                    //         saveAsImage: {
                    //             show:true,
                    //         },
                    //         dataView:{
                    //             readOnly:true
                    //         }
                    //     }
                    // },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: data.xAxis
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: data.data
                };
                yearChart.setOption(option);
            }
        })
    }


    option = {
        title: {
            text: 'IDA_ZJUT',
            // subtext: 'xzs',
            top: 'top',
            left: 'left'
        },
        legend: {
            data: ['发表数量'],
            right: 20
        },
        xAxis: {
            type: 'category',
            data: []
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            silent:true,
            name:"发表数量1",
            data: [],
            type: 'bar',
            showBackground: true,
            backgroundStyle: {
                color: 'rgba(220, 220, 220, 0.8)'
            },
            toolbox: {
                show:true,
                feature: {
                    saveAsImage: {
                        show:true
                    },
                    dataView:{
                        readOnly:true
                    }
                }
            },
        }]
    };

    yearChart.showLoading();

    fetchData(function (data) {
        yearChart.hideLoading();
        yearChart.setOption({
            xAxis: {
                data: data.year
            },
            series: [{
                // 根据名字对应到相应的系列
                name: '发表数量',
                data: data.count
            }]
        });
    });
    // 使用刚指定的配置项和数据显示图表。
    yearChart.setOption(option);
    window.addEventListener("resize", function () {
        yearChart.resize();
    });


}

// function drawScholarsGraph(data, partnersGraphChart) {
//     var option = {
//             title: {},
//             animationDurationUpdate: 1500,
//         // animationEasingUpdate: 'quinticInOut',
//             series: [
//                 {
//                     type: 'graph',
//                     focusNodeAdjacency: true,
//                     layout: 'force',
//                     symbolSize: 40,
//                     cursor: 'pointer',
//                     force: {
//                         repulsion: 800,
//                         layoutAnimation: false
//                     },
//                     roam: true,
//                     label: {
//                         normal: {
//                             show: true
//                         }
//                     },
//                     edgeSymbol: ['circle', 'arrow'],
//                     edgeSymbolSize: [1, 8],
//                     edgeLabel: {
//                         normal: {
//                             textStyle: {
//                                 fontSize: 12
//                             }
//                         }
//                     },
//                     data: data.data,
//                     links: data.links,
//                     lineStyle: {
//                         normal: {
//                             opacity: 0.9,
//                             width: 2,
//                             curveness: 0,
//                             color: '#616262'
//                         }
//                     },
//                     itemStyle: {
//                         color: "#2F4554"
//                     }
//                 }
//             ]
//         }
//     ;
//     partnersGraphChart.hideLoading();
//     // 使用刚指定的配置项和数据显示图表。
//     partnersGraphChart.setOption(option);
//     // window.addEventListener("resize", function () {
//     //     partnersGraphChart.resize();
//     // });
//     partnersGraphChart.on('click', function (param) {
//         if (param.dataType === 'node') {
//             window.location.href = '/ida/scholar/findscholarsbynamelike?scholarName=' + param.name;
//         }
//     });
//
// }

function drawScholarsGraph(data, partnersGraphChart) {

    console.log(data)
    partnersGraphChart.hideLoading();

    var graph = data
    var categories = [];

    for(i=0;i<graph.category.length;i++){
        categories[i] = {
            name: graph.category[i]
        };
    }

    graph.nodes.forEach(function (node) {
        node.draggable = true;
        if('category' in node){
            node.itemStyle={
                color:'blue',
            }
        }
    });
    graph.links.forEach(function (link) {
        if(link.value>50){
            link.lineStyle={
                color:'red',
            }
        }
    });
    const tableToExcel = () => {
        let str = `教师1,合作详情,教师2\n`;
        for(let i = 0 ; i <graph.links.length ; i++ ){
            for(const key in graph.links[i]){
                str+=`${graph.links[i][key] + '\t'},`;
            }
            str+='\n';
        }
        const uri = 'data:text/csv;charset=utf-8,\ufeff' + encodeURIComponent(str);
        // 通过创建a标签实现
        const link = document.createElement("a");
        link.href = uri;
        // 对下载的文件命名
        link.download =  "教师表.xlsx";
        link.click();
    }
    option = {
        title: {
            text: 'IDA_ZJUT',
            subtext: '红色：合作关系紧密',
            top: 'top',
            left: 'left'
        },
        toolbox: {
            show: true,
            feature: {
                myTool1: {
                    show: true,
                    title: '导出',
                    icon: 'image://data:image/gif;base64,R0lGODlhEAAQAMQAAORHHOVSKudfOulrSOp3WOyDZu6QdvCchPGolfO0o/XBs/fNwfjZ0frl3/zy7////wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH5BAkAABAALAAAAAAQABAAAAVVICSOZGlCQAosJ6mu7fiyZeKqNKToQGDsM8hBADgUXoGAiqhSvp5QAnQKGIgUhwFUYLCVDFCrKUE1lBavAViFIDlTImbKC5Gm2hB0SlBCBMQiB0UjIQA7',
                    onclick: function () {
                        tableToExcel();
                    }
                },
            },
        },
        tooltip: {
            trigger: 'item',
            showContent: true,
            formatter : function (params) {
                if(params.data.value){
                    return "合作次数为" + params.data.value + "次" ;
                }
               else {
                   return params.data.name;
                }
            },
            position:'right',
            fontSize:12
        },
        // legend: [{
        //     selectedMode: 'multiple',
        //     data: categories.map(function (a) {
        //         return a.name;
        //     })
        // }],
        animation: false,
        series : [
            {
                name: 'Les Miserables',
                type: 'graph',
                layout: 'force',
                data: graph.nodes,
                links: graph.links,
                categories: categories,
                roam: true,
                label: {
                    show:graph.nodes.length <= 300,
                    position: 'right',
                    fontSize:12
                },
                // edgeLabel: {
                //     show:graph.links.length,
                //     position:'right',
                //     fontSize:12
                // },
                force: {
                    repulsion: 500
                },
                focusNodeAdjacency: true,

            }
        ]
    };



    partnersGraphChart.setOption(option);

    if (option && typeof option === "object") {
        partnersGraphChart.setOption(option, true);
    }
    partnersGraphChart.on('click', function (param) {
        console.log('param---->', param);
        if (param.dataType === 'node') {
            window.location.href = '/ida/scholar/findscholarsbynamelike?scholarName=' + param.name;
        }else {
            $.ajax({
                data:{id1:param.data.target, id2:param.data.source},
                url: "/ida/achievement/findachievementbyname",
                dataType: "json",
                type: "post",
                success:function (data) {
                    console.log(data);
                    let achievement = new Array();
                    let patentList = data.patentList;
                    let horizontalProjectList = data.horizontalProjectList;
                    let verticalProjectList = data.verticalProjectList;
                    let articleList = data.articleList;
                    for (let i in patentList){
                        achievement.push(patentList[i].name);
                    }
                    for (let i in horizontalProjectList){
                        achievement.push(horizontalProjectList[i].name);
                    }
                    for (let i in verticalProjectList){
                        achievement.push(verticalProjectList[i].name);
                    }
                    for (let i in articleList){
                        achievement.push(articleList[i].title);
                    }

                    var c = "";
                    for (var i = 0, l = achievement.length; i < l; i++) {
                        c = c + achievement[i]+"\n";
                    }
                    alert(c);   //a和c自定义
                    console.log(achievement);
                }
            })
        }
    });
}

function drawCommunityGraph(data, communityGraph,scholar){
    communityGraph.hideLoading();

    var graph = data
    var categories = [];



    graph.nodes.forEach(function (node) {
        node.id=node.id.toString()
        node.category=node.community.toString()
        console.log(node.name==scholar)
        if(node.name==scholar){
            node.symbolSize=50
        }
    });

    graph.links.forEach(function (link) {
        link.source=link.startNode.toString()
        link.target=link.endNode.toString()
        link.value=link.properties[0].value
    });

    console.log(graph.nodes)
    console.log(graph.links)

    for(i=0;i<graph.community.length;i++){
        categories[i] = {
            name: graph.community[i]
        };
    }

    graph.nodes.forEach(function (node) {
        node.itemStyle = null;
        node.draggable = true;
    });

    option = {
        title: {
            text: 'IDA_ZJUT',
            // subtext: 'xzs',
            top: 'top',
            left: 'left'
        },
        tooltip: {},
        // legend: [{
        //     selectedMode: 'multiple',
        //     data: categories.map(function (a) {
        //         return a.name;
        //     })
        // }],
        animation: false,
        series : [
            {
                name: 'Les Miserables',
                type: 'graph',
                layout: 'force',
                data: graph.nodes,
                links: graph.links,
                categories: categories,
                roam: true,
                label: {
                    show:graph.nodes.length <= 300,
                    position: 'right',
                    fontSize:12
                },
                force: {
                    repulsion: 800,
                    edgeLength: 200//连线的长度
                },
                toolbox: {
                    show: true,
                    feature: {
                        mark: {
                            show: true
                        },
                    }
                },
                focusNodeAdjacency: true,

            }
        ]
    };

    communityGraph.setOption(option);

    if (option && typeof option === "object") {
        communityGraph.setOption(option, true);
    }
    communityGraph.on('click', function (param) {
        if (param.dataType === 'node') {
            window.location.href = '/ida/scholar/findscholarsbynamelike?scholarName=' + param.name;
        }
    });
}

function drawResearchFieldGraph(res, researchFieldGraph,scholar){
    researchFieldGraph.hideLoading();

    var keywords = res;

    var data = [];
    for (var name in keywords) {
        data.push({
            name: name,
            value: Math.sqrt(keywords[name])
        })
    }

    var maskImage = new Image();

    var option = {
        series: [ {
            type: 'wordCloud',
            sizeRange: [10, 100],
            rotationRange: [-90, 90],
            rotationStep: 45,
            gridSize: 2,
            shape: 'pentagon',
            maskImage: maskImage,
            drawOutOfBound: false,
            textStyle: {
                normal: {
                    color: function () {
                        return 'rgb(' + [
                            Math.round(Math.random() * 160),
                            Math.round(Math.random() * 160),
                            Math.round(Math.random() * 160)
                        ].join(',') + ')';
                    }
                },
                emphasis: {
                    color: 'red'
                }
            },
            data: data.sort(function (a, b) {
                return b.value  - a.value;
            })
        } ]
    };

    maskImage.onload = function () {
        option.series[0].maskImage
        researchFieldGraph.setOption(option);
    }

    maskImage.src = '/ida/img/wordCloudLogo.png';

    window.onresize = function () {
        researchFieldGraph.resize();
    }
}



/**
 * 右上角图节点数据
 */
$(function () {

    var scholar = $('#scholarName').html();

    drawYearChart(scholar);

    var partnersGraphChart = echarts.init(document.getElementById('partnersGraph'), null, {renderer: 'svg'});
    partnersGraphChart.showLoading();
    $.ajax({
        url: "/ida/scholar/getPartners?scholarName=" + scholar + "&count=10", // 节点个数
        dataType: "json",
        type: "get",
        success: function (data) {
            drawScholarsGraph(data, partnersGraphChart);
        }
    });
});


/**
 * 详情页图节点数据
 */
function showPartnerRelationDetail() {
    var scholar = $('#scholarName').html();
    var partnersGraphDetail = echarts.init(document.getElementById('partnersGraphDetail1'));
    partnersGraphDetail.showLoading();
    $.ajax({
        url: "/ida/scholar/getPartners2?scholarName=" + scholar + "&count=10", // 节点个数
        dataType: "json",
        type: "post",
        success: function (data) {
            drawScholarsGraph(data, partnersGraphDetail);
        }
    });
}


function showCommunity(){
    var scholar = $('#scholarName').html();
    // var organization=$("#scholarInfoOrganization").val()
    var communityGraph = echarts.init(document.getElementById('communityGraph'));
    communityGraph.showLoading();
    $.ajax({
        // url: "/ida/pc/findCommunity?scholarName=" + document.getElementById('scholarInfoOrganization').innerText.replace(/\s+/g,""),// 节点个数
        url: "/ida/pc/findCommunity?scholarName=" + scholar,
        dataType: "json",
        type: "post",
        success: function (data) {
            drawCommunityGraph(data, communityGraph,scholar);
        }
    });
}

function showResearchField(){
    var scholar = $('#scholarName').html();
    // var organization=$("#scholarInfoOrganization").val()
    var researchFieldGraph = echarts.init(document.getElementById('researchFieldGraph'));
    researchFieldGraph.showLoading();
    $.ajax({
        url: "/ida/scholar/findscholarsbynamelike1?scholarName=" + scholar,// 节点个数
        dataType: "json",
        type: "post",
        success: function (data) {
            drawResearchFieldGraph(data, researchFieldGraph,scholar);
        }
    });
}


