function myformatDate(d) {
    if (d === '' || d == null) {
        return "——";
    }
    return d.getFullYear() +
        "-" + ("0" + (d.getMonth() + 1)).slice(-2) +
        "-" + ("0" + d.getDate()).slice(-2) +
        " " + ("0" + d.getHours()).slice(-2) +
        ":" + ("0" + d.getMinutes()).slice(-2) +
        ":" + ("0" + d.getSeconds()).slice(-2);
}

$(document).ready(function () {
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

    $(document).on('click', '.record', function() {
        var posId = $(this).data('id');
        layer.open({
            type: 1,
            area: ['600px', '360px'],
            title: '历史归属记录',
            content: '<div style="padding:20px;"><table id="table-range"><thead><tr><th>归属网点</th><th>绑定时间</th><th>解绑时间</th></tr></thead></table></div>',
            success: function(layero, index){
                $('#table-range').bootstrapTable({
                    url: '/device/history/' + posId,
                    cache: false,
                    pagination: false,
                    sidePagination: 'server',
                    dataField: "data",
                    striped: true, //是否显示行间隔色
                    escape: true,//前端自动转义
                    columns: [
                        { field: 'location' },
                        {
                            field: 'bindtime',
                            formatter: function (value, row, index) {
                                if (value === '' || value == null) {
                                    return "——";
                                }
                                return myformatDate(new Date(parseInt(value)));
                            }
                        },
                        {
                            field: 'unbindtime',
                            formatter: function (value, row, index) {
                                if (value === '' || value == null) {
                                    return "——";
                                }
                                return myformatDate(new Date(parseInt(value)));
                            }
                        }
                    ]
                });
            }
        });
    });
});