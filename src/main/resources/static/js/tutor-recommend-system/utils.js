// 是否为空
function isEmpty(obj) {
    return typeof obj === 'undefined' || obj == null || obj === '';
}

let tokenUserAttr=['studentId','studentName','studentClass']

// 弹出 alert
function alertMsg(alertComp, msgComp, msg) {
    msgComp.text(msg)
    alertComp.slideDown(500)
    setTimeout(() => {
        alertComp.fadeOut(1000)
    }, 1000)
}
const alertUtil = {
    /**
     * 弹出消息框
     * @param msg 消息内容
     * @param type 消息框类型（参考bootstrap的alert）
     */
    alert: function(msg, type){
        // 未传入type则默认为success类型的消息框
        if(typeof(type) =="undefined") {
            type = "success";
        }
        // 创建bootstrap的alert元素
        let divElement = $("<div></div>")
            .addClass('alert')
            .addClass('alert-'+type)
            .addClass('alert-dismissible')
            .addClass('col-md-4')
            .addClass('col-md-offset-4');
        // 消息框的定位样式
        divElement.css({
            "position": "absolute",
            "top": "80px"
        });
        // 设置消息框的内容
        divElement.text(msg);
        // 消息框添加可以关闭按钮
        let closeBtn = $('<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>');
        $(divElement).append(closeBtn);
        // 消息框放入到页面中
        $('body').append(divElement);
        return divElement;
    },

    /**
     * 短暂显示后上浮消失的消息框
     * @param msg 消息内容
     * @param type 消息框类型
     */
    message: function(msg, type) {
        // 生成Alert消息框
        let divElement = alertUtil.alert(msg, type);
        // 鼠标是否在消息框中
        let isIn = false;

        // 在setTimeout执行之前先判定鼠标是否在消息框中
        divElement.on({
            mouseover : function(){isIn = true;},
            mouseout  : function(){isIn = false;}
        });

        // 短暂延时后上浮消失
        setTimeout(function() {
            // 每次上浮的间隔毫秒
            let IntervalMS = 20;
            // 上浮的空间(px)
            let floatSpace = 60;
            // 获取元素当前的top值
            let nowTop = divElement.offset().top;
            // 上浮停止时的top值
            let stopTop = nowTop - floatSpace;
            // 设置元素淡出
            divElement.fadeOut(IntervalMS * floatSpace);

            // 开始上浮
            let upFloat = setInterval(function(){
                // 判断当前消息框top是否还在可上升的范围内
                if (nowTop >= stopTop) {
                    // 消息框的top上升1px
                    divElement.css({"top": nowTop--});
                } else {
                    // 关闭上浮
                    clearInterval(upFloat);
                    // 移除元素
                    divElement.remove();
                }
            }, IntervalMS);

            // 如果鼠标在setTimeout之前已经放在的消息框中，则停止上浮
            if (isIn) {
                clearInterval(upFloat);
                divElement.stop();
            }

            // 鼠标悬浮时停止上浮和淡出效果，过后恢复
            divElement.hover(function() {
                clearInterval(upFloat);
                divElement.stop();
            },function() {
                // 这里设置元素淡出的时间应该为：间隔毫秒*剩余可以上浮空间
                divElement.fadeOut(IntervalMS * (nowTop - stopTop));
                // 继续上浮
                upFloat = setInterval(function(){
                    if (nowTop >= stopTop) {
                        divElement.css({"top": nowTop--});
                    } else {
                        // 关闭上浮
                        clearInterval(upFloat);
                        // 移除元素
                        divElement.remove();
                    }
                }, IntervalMS);
            });
        }, 1500);
    }
}

// 判断 json 结构
function isJSON(str) {
    if (typeof str == 'string') {
        try {
            let obj = JSON.parse(str);
            if (typeof obj == 'object' && obj) {
                return true;
            } else {
                return false;
            }
        } catch (e) {
            return false;
        }
    }
}

function hasToken() {
    return !isEmpty(localStorage.getItem(TOKEN_KEY))
}

// 获取 token
function getToken() {
    let token = localStorage.getItem(TOKEN_KEY)
    if (!isEmpty(token)) {
        return {
            "token": token
        }
    } else {
        return null
    }
}

function getTokenUserName() {
    let studentName = localStorage.getItem("studentName")
    if (!isEmpty(studentName)) {
        return {
            "studentName": studentName
        }
    } else {
        return null
    }
}

function getTokenUserId() {
    let studentId = localStorage.getItem("studentId")
    if (!isEmpty(studentId)) {
        return {
            "studentId": studentId
        }
    } else {
        // return null
        return {
            "studentId": "null"
        }
    }
}

// 设置 token
function setToken(token) {
    localStorage.setItem(TOKEN_KEY, token)
}

function setTokenUser(user) {
    for (var index in tokenUserAttr){
        localStorage.setItem(tokenUserAttr[index], user[tokenUserAttr[index]])
    }
}

// 移除 token
function removeToken() {
    localStorage.removeItem(TOKEN_KEY)
    removeTokenUser()
}

function removeTokenUser() {
    // localStorage.removeItem("user")
    for (var index in tokenUserAttr){
        localStorage.removeItem(tokenUserAttr[index])
    }
}

// 列表淡入
function itemListFadeIn(itemList, cssDict) {
    let itemIndex = 0, itemTimer = setInterval(function () {
        if (itemIndex < itemList.length) {
            let item = itemList.eq(itemIndex);
            item.fadeIn()
            item.css(cssDict)
            itemIndex++
        } else {
            clearInterval(itemTimer)
        }
    }, 100)
}

// 阴影动画
function shadowAnimation(selector) {
    selector.hover(
        function () {
            $(this).animate({
                boxShadow: "0 0 10px 3px rgba(120, 120, 120, 0.3)"
            }, "fast")
        },
        function () {
            $(this).animate({
                boxShadow: "0 0 10px 3px rgba(120, 120, 120, 0.1)"
            }, "fast")
        }
    )
}

// 淡入淡出动画
function fadeAnimation(parentSelector, childElement) {
    if (isEmpty(childElement)) {
        parentSelector.hover(
            function () {
                $(this).fadeIn("fast")
            },
            function () {
                $(this).fadeOut("fast")
            }
        )
    } else {
        parentSelector.hover(
            function () {
                $(childElement, this).fadeIn("fast")
            },
            function () {
                $(childElement, this).fadeOut("fast")
            }
        )
    }
}
