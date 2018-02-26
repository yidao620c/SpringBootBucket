function myformatDate(d) {
    if (d === '' || d == null) {
        return "——";
    }
    var datestring = d.getFullYear() +
        "-" + ("0" + (d.getMonth() + 1)).slice(-2) +
        "-" + ("0" + d.getDate()).slice(-2) +
        " " + ("0" + d.getHours()).slice(-2) +
        ":" + ("0" + d.getMinutes()).slice(-2) +
        ":" + ("0" + d.getSeconds()).slice(-2) ;

    return datestring;
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

    $.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN']);
    laydate.render({
        elem: '#date',
        range: true
    });
    $(document).on('click', '#table .check', function () {
        var pnames = [];
        var datap = $(this).data('p');
        if (datap !== '' && datap !== null) {
            pnames = datap.split(',');
        }
        layer.open({
            type: 1,
            area: ['600px', '360px'],
            title: '查看项目',
            content: '<div class="bootstrap-table" style="padding:20px;"><table class="table" id="table-range"><thead><tr><th>归属项目</th></tr></thead><tbody></tbody></table></div>',
            success: function (layero, index) {
                for (i in pnames) {
                    var html = "<tr><td>" + pnames[i] + "</td></tr>";
                    $('#table-range tbody').append(html);
                }
            },
            cancel: function (index, layero) {
                layer.close(index);
                return false;
            }
        });
    });
    $(document).on('click', '#add', function () {
        layer.open({
            type: 1,
            area: ['700px', '470px'],
            title: '添加用户',
            content: $('#tpl-add').text(),
            btn: ['确定', '取消'],
            yes: function (index, layero) {
                var addUserForm = layero.find('.layui-layer-content').find('#addUserForm');
                // 用户名重复性检查
                var username = addUserForm.find("#username").val();
                $.ajax({
                    url: '/user/usernamechk?username=' + encodeURIComponent(username),
                    method: 'GET',
                    cache: false,
                    dataType: 'json',
                    success: function (data) {
                        if (data.success) {
                            addUserForm.submit();
                        } else {
                            layer.alert(data.msg);
                        }
                    }
                });

            },
            cancel: function (index, layero) {
                layer.close(index);
                return false;
            },
            success: function (layero, index) {
                $('.i-checks').iCheck({
                    radioClass: 'iradio_square-green'
                });
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
                var validform = $("#addUserForm").Validform({
                    tiptype: 2
                });
                // form提交时候的加载
                $('#addUserForm').submit(function (event) {
                    if (!validform.check(false)) {
                        return false;
                    }
                    // 检查项目是否有选择
                    if ($('#psAdd').val() == null || $('#psAdd').val() === '') {
                        layer.alert('至少选择一个项目');
                        return false;
                    }
                    var loading = layer.load(2);
                    return true;
                });
            }
        });
    });
    $(document).on('click', '#table .edit', function () {
        var userId = $(this).data("id");
        $.ajax({
            url: '/user/edit?id=' + userId,
            type: 'GET',
            cache: false,
            dataType: "html",
            success: function (data) {
                layer.open({
                    type: 1,
                    area: ['700px', '440px'],
                    title: '编辑用户',
                    content: data,
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
                        var userEditForm = layero.find('.layui-layer-content').find('#userEditForm');
                        userEditForm.submit();
                    },
                    cancel: function (index, layero) {
                        layer.close(index);
                        return false;
                    },
                    success: function (layero, index) {
                        $('.i-checks').iCheck({
                            radioClass: 'iradio_square-green'
                        });
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
                        var validform = $("#userEditForm").Validform({
                            tiptype: 2
                        });
                        // form提交时候的加载
                        $('#userEditForm').submit(function (event) {
                            if (!validform.check(false)) {
                                return false;
                            }
                            // 检查项目是否有选择
                            if ($('#psEdit').val() == null || $('#psEdit').val() === '') {
                                layer.alert('至少选择一个项目');
                                return false;
                            }
                            var loading = layer.load(2);
                            return true;
                        });
                    }
                });
            },
            complete: function () {
                layer.close(loading);
            }
        });
    });
    //查询按钮事件
    $('#searchBtn').on('click', function () {
        $('#table').bootstrapTable('refresh');
    });
    $(document).on('click', '#table .reset', function () {
        var resetUserName = $(this).parent('td').parent('tr').find('td:eq(0)').text();
        var resetId = $(this).data('id');
        layer.confirm('确定重置' + resetUserName + '的密码么？重置为12345678', {icon: 3, title: '重置密码'}, function () {
            $.ajax({
                url: '/user/reset/' + resetId + "/" + resetUserName,
                type: 'POST',
                dataType: "json",
                success: function (data) {
                    if (data.success) {
                        layer.alert(data.msg, function (index) {
                            window.location = '/user/index.html';
                        });
                    } else {
                        layer.alert(data.msg);
                    }
                }
            });
        });
    });
    $(document).on('click', '#table .delete', function (index) {
        var delId = $(this).data('id');
        layer.confirm('确定删除该用户吗？', {icon: 3, title: '删除用户'}, function () {
            $.ajax({
                url: '/user/delete/' + delId,
                type: 'DELETE',
                dataType: "json",
                success: function (data) {
                    if (data.success) {
                        layer.alert(data.msg, function (index) {
                            window.location = '/user/index.html';
                        });
                    } else {
                        layer.alert(data.msg);
                    }
                }
            });
        });
    });
    $('#table').bootstrapTable({
        url: '/user/list',
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
            {field: 'username', width: '10%'},
            {field: 'name', width: '10%'},
            {field: 'phone', width: '12%'},
            {
                field: 'pnames',
                width: '10%',
                formatter: function (value, row, index) {
                    return '<a class="m-r check" data-p="' + value + '" data-id="' + row.id + '" href="javascript:;">查看项目</a>';
                }
            },
            {field: 'tips', width: '15%'},
            {field: 'stateStr', width: '6%'},
            {
                field: 'createdTime',
                width: '17%',
                formatter: function (value, row, index) {
                    return myformatDate(new Date(parseInt(value)));
                }
            },
            {
                field: 'id',
                width: '20%',
                formatter: function (value, row, index) {
                    return '<a class="m-r edit" data-id="' + value + '" href="javascript:;">编辑</a><a class="reset m-r" data-id="' + value + '" href="javascript:;">重置密码</a><a class="delete m-r" data-id="' + value + '" href="javascript:;">删除</a>';
                }
            }
        ],
        queryParams: function (params) {
            return {
                //每页多少条数据
                pageSize: params.limit,
                //请求第几页
                pageNumber: params.pageNumber,
                username: $("#usernameS").val(),
                phone: $("#phoneS").val(),
                state: $("#stateS").val(),
                createdTime: $("#date").val()
            }
        },
        onLoadSuccess: function (data) {

        }
    });
});