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

var entityMap = {
    '&': '&amp;',
    '<': '&lt;',
    '>': '&gt;',
    '"': '&quot;',
    "'": "'",
    '/': '/',
    '`': '`',
    '=': '='
};

function escapeHtml (string) {
    return String(string).replace(/[&<>"'`=\/]/g, function (s) {
        return entityMap[s];
    });
}

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
    $.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN']);
    laydate.render({
        elem: '#publishtimeRangeS',
        range: true
    });
    $(document).on('click', '#table .detail', function() {
        var data = $(this).data('tips');
        layer.alert(escapeHtml(data), { title: '版本说明' });
    });
    $(document).on('click', '#table .publish', function() {
        var appId = $(this).data('id');
        layer.confirm('是否将应用版本对所有执收单位发布？', { title: '调整发布范围' }, function() {
            $.ajax({
                url: '/app/publishall/' + appId,
                method: "POST",
                dataType: "json",
                success: function (data) {
                    if (data.success) {
                        layer.alert(data.msg, function (index) {
                            window.location = '/app/index.html';
                        });
                    } else {
                        layer.alert(data.msg);
                    }
                }
            });
        });
    });
    $(document).on('click', '#table .range', function() {
        var appId = $(this).data('id');
        layer.open({
            type: 1,
            area: ['600px', '360px'],
            title: '灰度发布设备列表',
            content: '<div style="padding:20px;"><table id="table-range"><thead><tr><th>设备IMEI码</th><th>网点名称</th></tr></thead></table></div>',
            success: function(layero, index) {
                $('#table-range').bootstrapTable({
                    url: '/app/graylist?id=' + appId,
                    cache: false,
                    pagination: true,
                    pageSize: 10,
                    pageNumber: 1,
                    pageList:[10],//分页步进值
                    queryParamsType: 'limit',  //查询参数组织方式
                    sidePagination: 'client',
                    dataField: "data",
                    escape: true, //前端自动转义
                    columns: [ { field: 'imei' }, { field: 'location' }]
                });
            }
        });
    });
    $('#table').bootstrapTable({
        url: '/app/list',
        cache: false,
        pageSize: 10,
        pageNumber: 1, //初始化加载第一页，默认第一页
        pagination: true,
        queryParamsType: 'limit',  //查询参数组织方式
        sidePagination: 'server',
        dataField: "data",
        striped: true, //是否显示行间隔色
        escape: true,//前端自动转义
        columns: [
            { field: 'id', width: '5%'},
            { field: 'name', width: '12%' },
            { field: 'version', width: '7%' },
            { field: 'projectName', width: '12%' },
            {
                field: 'tips',
                width: '7%',
                formatter: function(value, row, index) {
                    return '<a class="detail" data-tips="'+ value +'" href="javascript:void(0);">查看详情</a>';
                }
            },
            {
                field: 'publishtime',
                width: '13%',
                formatter: function (value, row, index) {
                    return myformatDate(new Date(parseInt(value)));
                }
            },
            {
                field: 'publishRange',
                width: '7%',
                formatter: function(value, row, index) {
                    if(value === 1) {
                        return '全网发布';
                    } else {
                        return '<a class="range" data-id="'+ row.id +'" href="javascript:void(0);">灰度发布</a>';
                    }
                }
            },
            { field: 'operatorUsername', width: '10%' },
            { field: 'operatorName', width: '10%' },
            {
                field: 'downloadUrl',
                formatter: function(value, row, index) {
                    if(row.publishRange === 1) {
                        return '<a data-id="'+ row.id +'" href="' + value + '">下载</a>';
                    } else if(row.publishRange === 2) {
                        return '<a class="m-r" data-id="'+ row.id +'" href="' + value + '">下载</a>'+'<a class="publish" data-id="'+ row.id +'" href="javascript:void(0);">调整为全部发布</a>';
                    }
                }
            }
        ],
        queryParams: function(params) {
            return {
                //每页多少条数据
                pageSize: params.limit,
                //请求第几页
                pageNumber: params.pageNumber,
                appName: $("#appNameS").val(),
                appVersion: $("#versionS").val(),
                projectId: $("#projectIdS").val(),
                publishRange: $("#publishRangeS").val(),
                publishtimeRange: $("#publishtimeRangeS").val()
            }
        },
        onLoadSuccess: function(data) {

        }
    });

    //查询按钮事件
    $('#searchBtn').on('click', function () {
        $('#table').bootstrapTable('refresh');
    });

    var versionData = JSON.parse($("#appVersionMap").val());
    $('#appNameS').change(function() {
       var val = $(this).val();
        $('#versionS').empty().append('<option value="">版本号</option>');
       if(val !== '') {
           for(i in versionData[val]) {
               $('#versionS').append('<option value="'+ versionData[val][i] +'">'+ versionData[val][i] +'</option>')
           }
       }
    });

});