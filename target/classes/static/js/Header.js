$(document).ready(function () {
    console.log("Header.js")
    $("#search").on("keypress",function (event) {
        var inputValue=$("#search").val()
        if (event.keyCode === 13) {
            location.href="/ida/all/findallbyanywords?words="+inputValue
        }
    })
})