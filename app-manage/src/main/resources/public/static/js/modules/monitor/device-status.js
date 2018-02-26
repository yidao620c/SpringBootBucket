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

    // 项目下拉框选择查询
    $('#projectSelect').on('change', function() {
        var projectid = $(this).val();
        $.ajax({
            url: '/monitor/reload?projectId=' + projectid,
            method: 'GET',
            data : {},
            dataType: 'json',
            success: function (data) {
                if (data.success) {
                    $("#posCountArticle").text(data.data.posCount);
                    $("#locationCountArticle").text(data.data.locationCount);
                }
            }
        });
        $('#table').bootstrapTable('refresh');
    });
    // $(document).on('click', '#table .detail', function() {
    //     var id = $(this).data('id');
    //     location.href = 'deviceStatusDetail.html'
    // });
    $('#table').bootstrapTable({
        url: '/monitor/list',
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
            { field: 'location' },
            { field: 'projectName' },
            { field: 'posCount' },
            {
                field: 'location',
                formatter: function(value, row, index) {
                    return '<a class="m-r detail" data-id="'+ value +'" href="/monitor/detail.html?location=' + value + '">查看详情</a>';
                }
            }
        ],
        queryParams: function(params) {
            return {
                //每页多少条数据
                pageSize: params.limit,
                //请求第几页
                pageNumber: params.pageNumber,
                projectId: $("#projectSelect").val(),
                location: $("#locationS").val()
            }
        },
        onLoadSuccess: function(data) {

        }
    });
    //查询按钮事件
    $('#searchBtn').on('click', function () {
        $('#table').bootstrapTable('refresh');
    });
});