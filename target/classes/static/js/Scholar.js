

var outerHtml='<div class="container-fluid card mt-1" style="height: 100%"></div>';
var centerHtml='<div class="media px-2 py-2"></div>';
var lastHtml='<div class="media-body p-3" style="font-size: 90%;"></div>';

var rowHtml='<div class="row"></div>';
var titleHtml='<a href="#" style="font-size:80%;overflow:hidden;text-overflow:ellipsis;display:-webkit-box; -webkit-box-orient:vertical;-webkit-line-clamp:1;"></a>';
var h5Html='<h5></h5>';
var h5RightHtml='<h5 class="ml-auto"></h5>';
var spanRightHtml='<span class="ml-auto"></span>';
var spanHtml='<span></span>'
var yearHtml='<span class="ml-auto text-right"></span>';

//article的html代码
var keyWordHTml='<span style="overflow:hidden;text-overflow:ellipsis;display:-webkit-box; -webkit-box-orient:vertical;-webkit-line-clamp:1; "></span>'
var PartnerHTml='<span style="color:green;"></span>'

//verticalProject的html代码
var levelHtml='<span class="badge badge-pill badge-success"></span>'
var stateHtml='<span class="badge badge-pill badge-info"></span>'




var ARTICLE="article"
var ARTICLEBYYEAR="articlebyyear"
var VERTICALPROJECT="verticalproject"
var HORIZONTALPROJECT="horizontalproject"
var PATENT="patent"

var wantAttr="scholarName"


var pageURL=0;
var scholarNameURL=getQueryVariable(wantAttr)

function previousAndNextURL(where,year){
    // if(year==undefined){
    //     return '<div class="row mt-3"><div class="btn-group mx-auto">' +
    //         '<a class="btn  btn-outline-primary btn-lg" id="previous" onclick="previousPage(\''+where+'\')">Previous</a>' +
    //         '<a class="btn  btn-outline-primary btn-lg" id="next" onclick="nextPage(\''+where+'\')">Next</a>' +
    //         '</div></div>'
    // }else{
    //     return '<div class="row mt-3"><div class="btn-group mx-auto">' +
    //         '<a class="btn  btn-outline-primary btn-lg" id="previous" onclick="previousPage(\''+where+'\')">Previous</a>' +
    //         '<a class="btn  btn-outline-primary btn-lg" id="next" onclick="nextPage(\''+where+'\',\''+year+'\')">Next</a>' +
    //         '</div></div>'
    // }

    return '<div id="previousAndNextURL"><div class="row mt-3" id="previousAndNextURLChild"><div class="btn-group mx-auto">' +
        '<a class="btn  btn-outline-primary btn-lg" id="previous" onclick="previousPage(\''+where+'\',\''+year+'\')">Previous</a>' +
        '<a class="btn  btn-outline-primary btn-lg" id="next" onclick="nextPage(\''+where+'\',\''+year+'\')">Next</a>' +
        '</div></div></div>'

}


function gotoEChartsForpublishArticleCount(){
    window.location.href="/ida/echars/seecharts?scholarName="+getQueryVariable("scholarName");
}


function getArticleInfoByYear(year){
    prepareInfo(getQueryVariable(wantAttr),0,ARTICLE,year)
}


function  getArticleInfo() {
    prepareInfo(getQueryVariable(wantAttr),0,ARTICLE)
}



function getVerticalProjectInfo(){
    prepareInfo(getQueryVariable(wantAttr),0,VERTICALPROJECT)
}

function getHorizontalProjectInfo(){
    prepareInfo(getQueryVariable(wantAttr),0,HORIZONTALPROJECT)
}

function getPatentInfo(){
    prepareInfo(getQueryVariable(wantAttr),0,PATENT)
}

function getRequestUrl(scholarName,page,where,year){
    var requestUrl="";
    if(year==undefined||year=="undefined"){
        if(where==ARTICLE){
            requestUrl="/ida/article/findarticlesbyscholarname?scholarName="+scholarName+"&page="+page
        }else if(where==VERTICALPROJECT){
            requestUrl="/ida/verticalproject/findverticalprojectbyscholarname?scholarName="+scholarName+"&page="+page
        }else if(where==HORIZONTALPROJECT){
            requestUrl="/ida/horizontalproject/findhorizontalprojectbyscholarname?scholarName="+scholarName+"&page="+page
        }else if(where==PATENT){
            requestUrl="/ida/patent/findpatentbyscholarname?scholarName="+scholarName+"&page="+page
        }
    }else{
        if(where==ARTICLE){
            requestUrl="/ida/article/findarticlesbyscholarnameandyear?scholarName="+scholarName+"&page="+page+"&year="+year;
            $("#myTab a[href='#ArticleInfoContent']").tab("show");
        }
    }
    console.log("返回的url为："+requestUrl)
    return requestUrl;
}

function getAnchor(where){
    var anchor="";
    if(where==ARTICLE){
        anchor="#ArticleInfoContent"
    }else if(where==VERTICALPROJECT){
        anchor="#VerticalProjectInfoContent"
    }else if(where==HORIZONTALPROJECT){
        anchor="#HorizontalProjectInfoContent"
    }else if(where==PATENT){
        anchor="#PatentInfoContent"
    }
    return anchor;
}

function prepareInfo(scholarName, page,where,year) {
    console.log("year="+year)
    console.log("year==undefined||year==\"undefined\":"+(year==undefined||year=="undefined"))

    pageURL=page;
    scholarNameURL=scholarName;
    var anchor
    console.log("当前页为："+pageURL)
    requestUrl=getRequestUrl(scholarName,page,where,year);
    anchor=getAnchor(where);
    $.ajax({
        url: requestUrl,
        dataType: "json",
        type: "get",
        success: function (data) {
            $("#previousAndNextURL").remove()
            var List=data.list;
            var hasNext=data.hasNext;
            var isFirst=data.isFirst;
            if(List.length!=0){


                $(anchor).empty()
                $.each(List,function (index,item) {
                    var outerHtmlInsert=$(outerHtml).appendTo(anchor)
                    var centerHtmlInsert=$(centerHtml).appendTo(outerHtmlInsert)
                    var lastHtmlInsert=$(lastHtml).appendTo(centerHtmlInsert)


                    //进行相关信息的请求
                    if(where==ARTICLE){//查询论文
                        var titleRowHtmlInsert=$(rowHtml).appendTo(lastHtmlInsert)
                        var titleH5HtmlInsert=$(h5Html).appendTo(titleRowHtmlInsert)
                        var titleHtmlInsert=$(titleHtml).appendTo(titleH5HtmlInsert)
                        var titleDataInsert=$(titleHtmlInsert).append(item.title)

                        var yearAndPartnerRowHtmlInsert=$(rowHtml).appendTo(lastHtmlInsert)
                        var partnerHtmlInsert=$(PartnerHTml).appendTo(yearAndPartnerRowHtmlInsert)
                        partnerList=item.partner.split(",");
                        partnerLength=partnerList.length
                        // console.log("partnerLength="+partnerLength)
                        $.each(partnerList,function (index,item){
                            var partnerHrefHtmlInsert=$('<a href="/ida/scholar/findscholarsbynamelike?scholarName='+item+'" style="color: green;"></a>').appendTo(partnerHtmlInsert)
                            var partnerDataInsert=$(partnerHrefHtmlInsert).append(item)
                            if(parseInt(index)!=partnerLength-1){
                                var partnerSeqDataInsert=$(partnerHtmlInsert).append("、");
                            }
                        })
                        var yearH5HtmlLightInsert=$(h5RightHtml).appendTo(yearAndPartnerRowHtmlInsert)
                        var yearHtmlInsert=$(yearHtml).appendTo(yearH5HtmlLightInsert)
                        var yearDataInsert=$(yearHtmlInsert).append(item.year)

                        if(item.journal!=null){
                            var journalRowHtmlInsert=$(rowHtml).appendTo(lastHtmlInsert)
                            var journalHtmlInsert=$(spanHtml).appendTo(journalRowHtmlInsert)
                            var JournalDataInsert=$(journalHtmlInsert).append(item.journal.name)
                        }
                        var keyWordRowHtmlInsert=$(rowHtml).appendTo(lastHtmlInsert)
                        var keyWordHtmlInsert=$(keyWordHTml).appendTo(keyWordRowHtmlInsert)
                        keyWordList=item.keyWord.split(",");
                        var keyWordLength=keyWordList.length;
                        $.each(keyWordList,function (index,item){
                            var keyWordDataInsert=$(keyWordHtmlInsert).append(item)
                            if(parseInt(index)!=keyWordLength-1){
                                var keyWordSeqDataInsert=$(keyWordHtmlInsert).append("、");
                            }
                        })

                        var citeCountRowHtmlInsert=$(rowHtml).appendTo(lastHtmlInsert)
                        var citeCountHtmlInsert=$(spanHtml).appendTo(citeCountRowHtmlInsert)
                        var citeCountDataInsert=$(citeCountHtmlInsert).append("引用量："+item.citeCount)
                    }
                    else if(where==VERTICALPROJECT){//查询纵向项目
                        var titleAndDateRowHtmlInsert=$(rowHtml).appendTo(lastHtmlInsert)
                        var titleH5HtmlInsert=$(h5Html).appendTo(titleAndDateRowHtmlInsert)
                        var titleHtmlInsert=$(titleHtml).appendTo(titleH5HtmlInsert)
                        var titleDataInsert=$(titleHtmlInsert).append(item.name)
                        var startAndEndDateHtmlInsert=$(spanRightHtml).appendTo(titleAndDateRowHtmlInsert)
                        var startAndEndDateDataInsert=$(startAndEndDateHtmlInsert).append(item.startDate+"-"+item.endDate)


                        var leaderAndLevelAndStateAndMoneyRowHtmlInsert=$(rowHtml).appendTo(lastHtmlInsert)
                        var spanHtmlInsert=$(spanHtml).appendTo(leaderAndLevelAndStateAndMoneyRowHtmlInsert)
                        var leaderHtmlInsert=$('<a href="/ida/scholar/findscholarsbynamelike?scholarName='+item.leader+'"></a>').appendTo(spanHtmlInsert)
                        var leaderDataInsert=$(leaderHtmlInsert).append(item.leader+"&emsp;")
                        var levelHtmlInsert=$(levelHtml).appendTo(spanHtmlInsert)
                        var levelDataInsert=$(levelHtmlInsert).append(item.level)
                        var stateHtmlInsert=$(stateHtml).appendTo(spanHtmlInsert)
                        var stateDataInsert=$(stateHtmlInsert).append(item.state)
                        var moneyH5HtmlInsert=$(h5RightHtml).appendTo(leaderAndLevelAndStateAndMoneyRowHtmlInsert)
                        var moneyHtmlInsert=$(yearHtml).appendTo(moneyH5HtmlInsert)
                        var moneyDataInsert=$(moneyHtmlInsert).append(item.planedMoney+"万")


                        var sourceAndOrganizationRowHtmlInsert=$(rowHtml).appendTo(lastHtmlInsert)
                        var sourceHtmlInsert=$(spanHtml).appendTo(sourceAndOrganizationRowHtmlInsert)
                        var sourceDataInsert=$(sourceHtmlInsert).append(item.source)
                        var organizationHtmlInsert=$(spanRightHtml).appendTo(sourceAndOrganizationRowHtmlInsert)
                        var organizationDataInsert=$(organizationHtmlInsert).append(item.organization)

                    }
                    else if(where==HORIZONTALPROJECT){//查询横向项目
                        var titleAndDateRowHtmlInsert=$(rowHtml).appendTo(lastHtmlInsert)
                        var titleH5HtmlInsert=$(h5Html).appendTo(titleAndDateRowHtmlInsert)
                        var titleHtmlInsert=$(titleHtml).appendTo(titleH5HtmlInsert)
                        var titleDataInsert=$(titleHtmlInsert).append(item.name)
                        var startAndEndDateHtmlInsert=$(spanRightHtml).appendTo(titleAndDateRowHtmlInsert)
                        var startAndEndDateDataInsert=$(startAndEndDateHtmlInsert).append(item.startDate+"-"+item.endDate)


                        var leaderAndLevelAndStateAndMoneyRowHtmlInsert=$(rowHtml).appendTo(lastHtmlInsert)
                        var spanHtmlInsert=$(spanHtml).appendTo(leaderAndLevelAndStateAndMoneyRowHtmlInsert)
                        var leaderHtmlInsert=$('<a href="/ida/scholar/findscholarsbynamelike?scholarName='+item.leader+'"></a>').appendTo(spanHtmlInsert)
                        var leaderDataInsert=$(leaderHtmlInsert).append(item.leader+"&emsp;")
                        var levelHtmlInsert=$(levelHtml).appendTo(spanHtmlInsert)
                        var levelDataInsert=$(levelHtmlInsert).append(item.nature)
                        var stateHtmlInsert=$(stateHtml).appendTo(spanHtmlInsert)
                        var stateDataInsert=$(stateHtmlInsert).append(item.area)
                        var moneyH5HtmlInsert=$(h5RightHtml).appendTo(leaderAndLevelAndStateAndMoneyRowHtmlInsert)
                        var moneyHtmlInsert=$(yearHtml).appendTo(moneyH5HtmlInsert)
                        var moneyDataInsert=$(moneyHtmlInsert).append(item.planedMoney+"万")


                        var sourceAndOrganizationRowHtmlInsert=$(rowHtml).appendTo(lastHtmlInsert)
                        var sourceHtmlInsert=$(spanHtml).appendTo(sourceAndOrganizationRowHtmlInsert)
                        var sourceDataInsert=$(sourceHtmlInsert).append(item.boss)
                        var organizationHtmlInsert=$(spanRightHtml).appendTo(sourceAndOrganizationRowHtmlInsert)
                        var organizationDataInsert=$(organizationHtmlInsert).append(item.organization)

                    }
                    else if(where==PATENT){//查询专利
                        var titleAndDateRowHtmlInsert=$(rowHtml).appendTo(lastHtmlInsert)
                        var titleH5HtmlInsert=$(h5Html).appendTo(titleAndDateRowHtmlInsert)
                        var titleHtmlInsert=$(titleHtml).appendTo(titleH5HtmlInsert)
                        var titleDataInsert=$(titleHtmlInsert).append(item.name)
                        var startAndEndDateHtmlInsert=$(spanRightHtml).appendTo(titleAndDateRowHtmlInsert)
                        var startAndEndDateDataInsert=$(startAndEndDateHtmlInsert).append(item.applyDate)


                        var leaderAndLevelAndStateAndMoneyRowHtmlInsert=$(rowHtml).appendTo(lastHtmlInsert)
                        var spanHtmlInsert=$(spanHtml).appendTo(leaderAndLevelAndStateAndMoneyRowHtmlInsert)
                        var leaderHtmlInsert=$('<a href="/ida/scholar/findscholarsbynamelike?scholarName='+item.firstInventor+'"></a>').appendTo(spanHtmlInsert)
                        var leaderDataInsert=$(leaderHtmlInsert).append(item.firstInventor+"&emsp;")
                        var levelHtmlInsert=$(levelHtml).appendTo(spanHtmlInsert)
                        var levelDataInsert=$(levelHtmlInsert).append(item.type)
                        var stateHtmlInsert=$(stateHtml).appendTo(spanHtmlInsert)
                        var stateDataInsert=$(stateHtmlInsert).append(item.state)


                        var sourceAndOrganizationRowHtmlInsert=$(rowHtml).appendTo(lastHtmlInsert)
                        var sourceHtmlInsert=$(spanHtml).appendTo(sourceAndOrganizationRowHtmlInsert)
                        var sourceDataInsert=$(sourceHtmlInsert).append(item.applicant)
                        var organizationHtmlInsert=$(spanRightHtml).appendTo(sourceAndOrganizationRowHtmlInsert)
                        var organizationDataInsert=$(organizationHtmlInsert).append(item.organization)

                    }
                })

                //分页的按钮
                var pageHtmlInsert=$(previousAndNextURL(where,year)).appendTo(anchor)
                if(!hasNext){
                    $("#next").addClass("disabled");
                }
                if(isFirst){
                    $("#previous").addClass("disabled");
                }


            }
            else{
                alert("已无相关记录")
            }

        }
    })
}



function previousPage(where){
    prepareInfo(scholarNameURL,parseInt(pageURL)-parseInt(1),where)
}

function nextPage(where,year){
    prepareInfo(scholarNameURL,parseInt(pageURL)+parseInt(1),where,year)
}


function getQueryVariable(variable){
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] == variable){return pair[1];}
    }
    return(false);
}

$(".scholarInfoA").click(function(){
    console.log("点击了")
    $("#scholarInfoUl").slideToggle();
});

$(".scholarInfoB").click(function () {
    console.log("点击了B")
    if(!$("#scholarInfoUl").is(":hidden")){
        console.log("开始清除active")
        $("#scholarInfoUl").slideToggle();
        // $("#scholarInfoUl li").removeClass("active")
        // $("#scholarInfoUl li").removeClass("show")
        // $("#scholarInfoUlLI").addClass("active")
    }
});


