function jump(){
    console.log("启动jump方法")
    window.location.href='http://'+idahostaddress+':8080/ida/Community_wmk/'
}

function dashboard(){
    window.location.href="dashboard";
}

function qa() {
    var url = 'http://'+idahostaddress+':8010/index';                             //转向网页的地址;(这里就是填写第三方工具给的链接)
    var name = '智能问答';                    //网页名称，可为空;
    var iWidth = 400;                          //弹出窗口的宽度;
    var iHeight = 600;                         //弹出窗口的高度;
    //获得窗口的垂直位置
    var iTop = (window.screen.availHeight - iHeight - 100);
    //获得窗口的水平位置
    var iLeft = (window.screen.availWidth - iWidth - 100);
    window.open(url, name, 'height=' + iHeight + ',,innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
}