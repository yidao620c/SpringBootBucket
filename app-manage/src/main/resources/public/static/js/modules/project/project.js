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
    var account = $(".list-item").length;
    function checkEmpty() {
        if(account === 1) {
            $('.project-list').addClass('empty');
        } else {
            $('.project-list').removeClass('empty');
        }
    }
    $(document).on('click', '.x-close', function() {
        var projId = $(this).data("id");
        layer.confirm('确定删除此项目吗？', { title: '删除项目' }, function() {
            $.ajax({
                url: '/project/delete?id=' + projId,
                type: 'DELETE',
                success: function(result) {
                    if (result.success) {
                        layer.alert(result.msg, function (index) {
                            window.location = '/project/index';
                        });
                    } else {
                        layer.alert(result.msg);
                    }
                }
            });
        });
    });
    var openIndex;
    $('.add').click(function() {
        openIndex = layer.open({
            type: 1,
            area: ['600px', '360px'],
            title: '添加项目',
            content: $('#tpl').text(),
            success: function(layero, index){
                $('#upload').diyUpload({
                    url: '/project/upload',
                    success:function( data ) {
                        if (data.success) {
                            $("#iconHidden").val(data.data.filename);
                        }
                    },
                    error:function( err ) {
                        console.info( err );
                    },
                    finished: function () {
                        console.log("all finished.............");
                    },
                    chunked:true,
                    // 分片大小
                    chunkSize: 512 * 1024,
                    //最大上传的文件数量
                    fileNumLimit: 1,
                    // 总文件大小(单位字节)
                    // fileSizeLimit: 300 * 1024,
                    // 单个文件大小(单位字节)
                    fileSingleSizeLimit:300 * 1024,
                    accept: {extensions:"jpg,jpeg,bmp,png"} //图片格式
                });
                // 取消动作
                $("#cancelBtn").on('click', function () {
                    layer.close(openIndex);
                });
                // form提交时候的加载
                $('#proejctForm').submit(function(event) {
                    var proName = $("#projectName").val();
                    if (proName === '') {
                        layer.msg('请输入项目名称', {icon: 5});
                        return false;
                    }
                    if (proName.length > 60) {
                        layer.msg('项目名称不得超过60位字符', {icon: 5});
                        return false;
                    }
                    var applicationId = $("#applicationId").val();
                    if (applicationId === '') {
                        layer.msg('请输入Application ID', {icon: 5});
                        return false;
                    }
                    if (applicationId.length > 64) {
                        layer.msg('Application ID不得超过64位', {icon: 5});
                        return false;
                    }
                    var loading = layer.load(2);
                    return true;
                });
            }
        });
    });
    checkEmpty();
});