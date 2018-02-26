var tableData = [];

// 移除一行
function removeRow(r) {
    var dataid = $(r).data('id');
    $('#table').bootstrapTable('remove', {field: 'id', values: [dataid]});
}

$(function() {
    var config = {
        '.chosen-select': {},
        '.chosen-select-deselect': {
            allow_single_deselect: true
        },
        '.chosen-select-no-single': {
            disable_search_threshold: 10
        },
        '.chosen-select-no-results': {
            no_results_text: 'Oops, nothing found!'
        },
        '.chosen-select-width': {
            width: "95%"
        }
    };
    for (var selector in config) {
        $(selector).chosen(config[selector]);
    }
    var loading;
    jQuery.ajaxSetup({
        beforeSend: function () {
            loading = layer.load(2);
        },
        complete: function () {
            layer.close(loading);
        },
        timeout: 300000, //超时时间：300秒
        error: function (XMLHttpRequest, textStatus, errorThrown) {
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
    $('#table').bootstrapTable({
        // url: '/app/listlo?locations=0',
        // dataField: "data",
        data: tableData,
        cache: false,
        pagination: true,
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10],                     //可供选择的每页的行数（*）
        sidePagination: 'client',
        columns: [
            { field: 'imei', width:'35%' },
            { field: 'location', width: '50%' },
            {
                field: 'id',
                width: '15%',
                formatter: function(value, row, index) {
                    return '<a data-id="' + value + '" href="javascript:void(0);" onclick="removeRow(this);">移除</a>';
                }
            }
        ],
        onLoadSuccess: function(data) {
        }
    });

    // 多选框的选择事件
    $("#locationSelect").on("change", function () {
        var locVal = $(this).val();
        if (locVal == null || locVal === '') {
            tableData.length = 0;
            $('#table').bootstrapTable('load', {
                data: tableData,
                cache: false
            });
        }
        $.ajax({
            url: '/app/listlo?locations=' + locVal,
            method: "GET",
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    tableData = data.data;
                } else {
                    tableData = [];
                }
                $('#table').bootstrapTable('load', {
                    data: tableData,
                    cache: false
                });
            }
        });
    });

    // 点击添加事件
    $("#addBtn").on('click', function () {
        var imeiInputVal = $('#imeiInput').val();

        if (imeiInputVal === '') {
            layer.alert('请输入POS设备的IMEI');
            return false;
        }

        // 重复判断
        var duplicated = false;
        $('#table').find('tbody tr td:nth-child(1)').each(function () {
            if ($(this).text() === imeiInputVal) {
                duplicated = true;
                return false;
            }
        });
        if (duplicated) {
            layer.alert('该设备已存在列表中，请勿重复添加');
            return false;
        }

        $.ajax({
            url: '/app/imei?imei=' + imeiInputVal,
            method: "GET",
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    $('tr.no-records-found').remove();
                    $('#table').bootstrapTable('prepend', {id: data.data.id, imei: data.data.imei, location: data.data.location});
                } else {
                    layer.alert('没有查询到POS信息');
                }
            }
        });
    });

});