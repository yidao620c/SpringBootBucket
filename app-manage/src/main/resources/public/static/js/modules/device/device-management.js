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

    $(document).on('click', '#table .edit', function() {
        var id = $(this).data('id');
        var stateStr = $(this).parent('td').parent('tr').find('td:eq(4)').text();
        var html = '<div style="padding:20px; text-align:center;">'+
            '<select id="staSelect" class="form-control m-b">'+
            '   <option value="1">正常</option>'+
            '   <option value="2">故障</option>'+
            '   <option value="3">维修中(返厂)</option>'+
            '   <option value="4">已禁用(丢失)</option>'+
            '   <option value="5">已停用(回收)</option>'+
            '</select></div>';
        layer.open({
            type: 1,
            area: ['350px', '240px'],
            title: '编辑机具状态',
            btn: ['确定', '取消'],
            content: html,
            success: function(layero, index){
                $('#staSelect').find('option').each(function(){
                    if ($(this).text() === stateStr) {
                        $(this).attr('selected', 'selected');
                        return false;
                    }
                    return true;
                });
            },
            yes: function (index) {
                $.ajax({
                    url: '/device/state/' + id + "/" + $("#staSelect").val(),
                    type: 'POST',
                    dataType: "json",
                    success: function (data) {
                        if (data.success) {
                            layer.close(index);
                            layer.alert(data.msg, function (index) {
                                window.location = '/device/index.html';
                            });
                        } else {
                            layer.alert(data.msg);
                        }
                    }
                });
            }
        });
    });
    //查询按钮事件
    $('#searchBtn').on('click', function () {
        $('#table').bootstrapTable('refresh');
    });
    // 接触绑定事件
    $(document).on('click', '#table .delete', function() {
        var loc = $(this).parent('td').parent('tr').find('td:eq(3)').text();
        var id = $(this).data('id');
        layer.confirm('确定解除与' + loc + '的绑定？', { title: '解除绑定' }, function() {
            $.ajax({
                url: '/device/unbind/' + id,
                type: 'POST',
                dataType: "json",
                success: function (data) {
                    if (data.success) {
                        layer.alert(data.msg, function (index) {
                            window.location = '/device/index.html';
                        });
                    } else {
                        layer.alert(data.msg);
                    }
                }
            });
        });
    });
    $(document).on('click', '#table .detail', function() {
        var id = $(this).data('id');
        location.href = '/device/detail.html?id=' + id;
    });
    $('#table').bootstrapTable({
        url: '/device/list',
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
            { field: 'imei', width: '12%' },
            { field: 'series', width: '10%' },
            {
                field: 'jointime',
                width: '12%',
                formatter: function (value, row, index) {
                    return myformatDate(new Date(parseInt(value)));
                }
            },
            { field: 'location',  width: '15%'},
            { field: 'posStateStr', width: '7%'},
            { field: 'projectName', width: '10%'},
            { field: 'tips', width: '16%'},
            {
                field: 'id',
                width: '18%',
                formatter: function(value, row, index) {
                    if (row.location == null || row.location === '') {
                        return '<a class="m-r detail" data-id="'+ value +'" href="javascript:;">查看详情</a>'+'<a class="edit m-r" data-id="'+ value +'" href="javascript:;">编辑状态</a>';
                    } else {
                        return '<a class="m-r detail" data-id="'+ value +'" href="javascript:;">查看详情</a>'+'<a class="edit m-r" data-id="'+ value +'" href="javascript:;">编辑状态</a><a class="delete m-r" data-id="'+ value +'" href="javascript:;">解除绑定</a>';
                    }
                }
            }
        ],
        queryParams: function (params) {
            return {
                //每页多少条数据
                pageSize: params.limit,
                //请求第几页
                pageNumber: params.pageNumber,
                imei: $("#imeiS").val(),
                location: $("#locationS").val(),
                projectId: $("#projectIdS").val()
            }
        }
    });
});