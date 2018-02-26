/**
 * 获取浮层中的值
 * @param page
 */
function selectLayVal(page) {
    var iframeDocument = page.find('iframe')[0].contentWindow;
    return iframeDocument.getDatas();
}

function selectgrayIds(page) {
    var iframeDocument = page.find('.layui-layer-content').children()[0].contentWindow.document;
    var grayIds = [];
    $(iframeDocument).find("#table").find('tbody tr').each(function () {
        grayIds.push($(this).find('td:nth-child(3) a').data('id'));
    });
    return grayIds.join();
}

function selectallVals(page) {
    var iframeDocument = page.find('.layui-layer-content').children()[0].contentWindow.document;
    var allVals = [];
    $(iframeDocument).find("#table").find('tbody tr').each(function () {
        allVals.push($(this).find('td:nth-child(3) a').data('id') + "|" + $(this).find('td:nth-child(1)').text() + "|" + $(this).find('td:nth-child(2)').text());
    });
    return allVals.join();
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

    $('#file-pretty').find('input[type="file"]').prettyFile();
    $('.i-checks').iCheck({
        radioClass: 'iradio_square-green'
    });
    $('input[name="publishRange"]').on('ifClicked', function (event) {
        if (event.currentTarget.value === '1') {
            $('#grayDiv').hide();
        } else if (event.currentTarget.value === '2') {
            layer.open({
                type: 2,
                area: ['600px', '400px'],
                title: '灰度发布',
                content: '/app/gray.html',
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var grayData2 = selectLayVal(layero);
                    if (grayData2.length === 0) {
                        layer.alert('请添加需要进行灰度发布的设备');
                        return false;
                    }
                    $('#table-range').bootstrapTable('load', {
                        data: grayData2,
                        cache: false
                    });
                    var grayIds = [];
                    $.each(grayData2, function( index, value ) {
                        grayIds.push(value.id)
                    });
                    $("#grayIds").val(grayIds.join());

                    $('#grayDiv').show();
                    // 预览信息加载
                    layer.close(index);
                },
                cancel: function (index, layero) {
                    layer.close(index);
                    $('input[name="publishRange"][value="1"]').iCheck('check');
                    return false;
                }
            });
        }
    });

    $('#table-range').bootstrapTable({
        data: [],
        cache: false,
        pagination: true,
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10],                     //可供选择的每页的行数（*）
        sidePagination: 'client',
        columns: [
            { field: 'imei', width:'40%' },
            { field: 'location', width: '60%' }
        ],
        onLoadSuccess: function(data) {
        }
    });

    // ajax提交文件上传
    $("#submitBtn").on('click', function () {

        // 应用名称非空检查
        if ($("#name").val() === '') {
            layer.alert('请填写应用名称');
            return false;
        }
        // 版本说明非空检查
        if ($("#tips").val() === '') {
            layer.alert('请填写版本说明');
            return false;
        }

        // 检查设备列表是否为空
        if ($("input[name='publishRange']:checked").val() === '2' && $("#grayIds").val() === '') {
            layer.alert('请添加需要进行灰度发布的设备');
            return false;
        }

        $.ajax({
            url: '/app/publish',
            type: 'POST',
            data: new FormData($('#addVersionForm')[0]),
            dataType: "json",
            // 告诉jQuery不要去处理发送的数据
            processData: false,
            // 告诉jQuery不要去设置Content-Type请求头
            contentType: false,
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