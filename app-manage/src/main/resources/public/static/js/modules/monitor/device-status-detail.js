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
    $(document).on('click', '#table .detail', function() {
        var id = $(this).data('id');
        location.href = 'deviceStatusDetail.html'
    });
    $('#table').bootstrapTable({
        url: '/monitor/detailList',
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
            { field: 'imei' },
            { field: 'series' },
            {
                field: 'reportTime',
                formatter: function (value, row, index) {
                    if (value === '' || value == null) {
                        return "——";
                    }
                    return myformatDate(new Date(parseInt(value)));
                }
            },
            { field: 'reportLocation' },
            { field: 'onlineStateStr' }
        ],
        queryParams: function(params) {
            return {
                //每页多少条数据
                pageSize: params.limit,
                //请求第几页
                pageNumber: params.pageNumber,
                projectId: $("#projectIdS").val(),
                location: $("#locationS").text(),
                imei: $("#imeiS").val(),
                onlineState: $("#onlineStateS").val()
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