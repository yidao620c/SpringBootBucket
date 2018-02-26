$(function () {
    var loading;
    jQuery.ajaxSetup({
        beforeSend: function () {
            loading = layer.load(2);
        },
        complete: function () {
            layer.close(loading);
        },
        timeout: 30000, //超时时间：30秒
        error: function
            (XMLHttpRequest, textStatus, errorThrown){
            //TODO: 处理status， http status code，超时 408
            // 注意：如果发生了错误，错误信息（第二个参数）除了得到null之外，还可能
            //是"timeout", "error", "notmodified" 和 "parsererror"
            if (textStatus === 'timeout') {
                layer.alert('请求超时，请确认电脑网络是否正常连接');
                return false;
            } else {
                layer.alert('请求出错，请联系管理员。');
                return false;
            }
        }
    });
    $("#changeBtn").on('click', function () {
        var p1 = $("#password1").val();
        var p2 = $("#password2").val();
        var p3 = $("#password3").val();

        if (p1 === '') {
            layer.msg('请输入原密码', {icon: 5});
            return false;
        }
        if (p2 === '') {
            layer.msg('请输入新密码', {icon: 5});
            return false;
        }
        if (p1 === p2) {
            layer.msg('新密码与原密码相同，请重新输入', {icon: 5});
            return false;
        }
        if (p2.length < 8 || p2.length > 20) {
            layer.msg('密码格式不正确，请重新输入8-20位的字符（支持数字、英文字母、下划线）', {icon: 5});
            return false;
        }
        if (p3 === '') {
            layer.msg('请再次输入新密码', {icon: 5});
            return false;
        }
        if (p2 !== p3) {
            layer.msg('两次输入的新密码不一致，请重新输入', {icon: 5});
            return false;
        }

        $.ajax({
            url: '/password',
            type: 'POST',
            data: {p1: p1, p2: p2},
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    layer.alert(data.msg, function (index) {
                        window.location = '/index.html';
                    });
                } else {
                    layer.alert(data.msg);
                }
            }
        });
    });
});