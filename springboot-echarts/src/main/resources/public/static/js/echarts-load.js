var system = require('system');
var page = require('webpage').create();

// 获取第二个参数(即请求地址url).
var url = system.args[1];
console.log('url:' + url);

// 显示控制台日志.
page.onConsoleMessage = function (msg, lineNum, sourceId) {
    console.log('console message ----> ' + msg);
};
page.onLoadStarted = function () {
    console.log("load started");
};

page.onLoadFinished = function () {
    console.log("load finished");
};
page.onUrlChanged = function () {
    console.log("onUrlChanged");
};

//打开给定url的页面.
var start = new Date().getTime();

page.open(url, function (status) {
    if (status === 'success') {
        console.log('echarts页面加载完成,加载耗时:' + (new Date().getTime() - start) + ' ms');
        // // 处理页面
        // var pic_url = page.evaluate(function() {
        //     // DOM操作
        //     return document.getElementById('cp_image').getAttribute('src');
        // });
        // 由于echarts动画效果，延迟500毫秒确保图片渲染完毕再调用下载图片方法.
        // setTimeout(function () {
        //     page.evaluate(function () {
        //         console.log("调用了echarts的下载图片功能.");
        //     });
        // }, 500);
    } else {
        console.log("页面加载失败 Page failed to load!");
    }
});