/**
 * Created by super on 2016/10/18.
 */
/*
 swal配置参数
 参数	默认值	描述
 title	null	模态对话框的标题。它可以在参数对象的title参数中设置，也可以在swal()方法的第一个参数设置。
 text	null	模态对话框的内容。它可以在参数对象的text参数中设置，也可以在swal()方法的第二个参数设置。
 html	null	对话框中的内容作为HTML标签。如果同时提供text和html参数，插件将会优先使用text参数。
 type	null	对话框的情景类型。有5种内置的情景类型：warning，error，success，info和question。它可以在参数对象的type参数中设置，也可以在swal()方法的第三个参数设置。
 customClass	null	模态对话框的自定义class类。
 animation	true	如果设置为false，对话框将不会有动画效果。
 allowOutsideClick	true	是否允许点击对话框外部来关闭对话框。
 allowEscapeKey	true	是否允许按下Esc键来关闭对话框。
 showConfirmButton	true	是否显示“Confirm（确认）”按钮。
 showCancelButton	false	是否显示“Cancel（取消）”按钮。
 confirmButtonText	"OK"	确认按钮上的文本。
 cancelButtonText	"Cancel"	取消按钮上的文本。
 confirmButtonColor	"#3085d6"	确认按钮的颜色。必须是HEX颜色值。
 cancelButtonColor	"#aaa"	取消按钮的颜色。必须是HEX颜色值。
 confirmButtonClass	null	确认按钮的自定义class类。
 cancelButtonClass	null	取消按钮的自定义class类。
 buttonsStyling	true	为按钮添加默认的swal2样式。如果你想使用自己的按钮样式，可以将该参数设置为false。
 reverseButtons	false	如果你想反向显示按钮的位置，设置该参数为true。
 showLoaderOnConfirm	false	设置为true时，按钮被禁用，并显示一个在加载的进度条。该参数用于AJAX请求的情况。
 preConfirm	null	在确认之前执行的函数，返回一个Promise对象。
 imageUrl	null	为模态对话框自定义图片。指向一幅图片的URL地址。
 imageWidth	null	如果设置了imageUrl参数，可以为图片设置显示的宽度，单位像素。
 imageHeight	null	如果设置了imageUrl参数，可以为图片设置显示的高度，单位像素。
 imageClass	null	自定义的图片class类。
 timer	null	自动关闭对话框的定时器，单位毫秒。
 width	500	模态窗口的宽度，包括padding值（box-sizing: border-box）。
 padding	20	模态窗口的padding内边距。
 background	"#fff"	模态窗口的背景颜色。
 input	null	表单input域的类型，可以是"text", "email", "password", "textarea", "select", "radio", "checkbox" 和 "file"。
 inputPlaceholder	""	input域的占位符。
 inputValue	""	input域的初始值。
 inputOptions	{} 或 Promise	如果input的值是select或radio，你可以为它们提供选项。对象的key代表选项的值，value代表选项的文本值。
 inputAutoTrim	true	是否自动清除返回字符串前后两端的空白。
 inputValidator	null	是否对input域进行校验，返回Promise对象。
 inputClass	null	自定义input域的class类。
 */

var global_data;
var global_page = 0;
var global_sum;
var page_sum = 1;
var size = 20;
var count;
var check;
var page_array = [10, 20, 30, 50, 100];
var global_url = 'https://epec418.cyparty.com'
//加载页码
var loadPage = (function () {
    //加载下一页
    function pageNext() {
        global_page = $($('.page_current')[0]).text() - 1;
        $('.user_manage_container_li').remove();
        global_page++;
        findMessage();
    }

    //加载上一页
    function pagePrev() {
        global_page = $($('.page_current')[0]).text() - 1;
        $('.user_manage_container_li').remove();
        global_page--;
        findMessage();
    }

    //控制上一页下一页按钮
    function pageButton() {
        console.log("当前" + global_page);
        if (global_page == 0) {
            console.log("page_sum总页数" + page_sum);
            if (global_page == page_sum - 1) {
                $('.page_prev').hide();
                $('.page_next').hide();
                $('.page_input_number').hide();
                $('.page_go').hide();
            } else {
                $('.page_input_number').show();
                $('.page_go').show();
                $('.page_prev').hide();
                $('.page_next').show();
            }
        } else if (global_page == page_sum - 1) {
            console.log("page_sum总页数2" + page_sum);
            $('.page_input_number').show();
            $('.page_go').show();
            $('.page_prev').show();
            $('.page_next').hide();
        } else {
            $('.page_input_number').show();
            $('.page_go').show();
            $('.page_prev').show();
            $('.page_next').show();
        }
    }

    //页码检查
    function checkPageTo() {
        if ($($('.page_input_number')[0]).val() == "") {
            global_page = $($('.page_input_number')[1]).val() - 1;
        } else {
            global_page = $($('.page_input_number')[0]).val() - 1;
        }
        if (global_page > page_sum - 1 || global_page < 0) {
            global_page = $('.page_current').text() - 1;
            $('.page_input_number').val("");
            swal("输入的页码有误");
        } else if ($($('.page_input_number')[0]).val() == "" && $($('.page_input_number')[1]).val() == "") {
            global_page = $('.page_current').text() - 1;
            $('.page_input_number').val("");
            swal("输入的页码为空");
        } else if (isNaN($('.page_input_number').val())) {
            global_page = $('.page_current').text() - 1;
            $('.page_input_number').val("");
            swal("输入的页码格式不正确");
        } else if ($('.page_input_number').val() == $($('.page_current')[0]).text() || $('.page_input_number').val() == $($('.page_current')[1]).text()) {
            return false;
        } else {
            $('.page_input_number').val("");
            findMessage();
        }

    }

    //判断用户权限
    function checkUserPrivilege(count, listStyleFunction) {
        $('.right_menu_li_all').show();
        global_sum = count;
        if (global_sum == 0) {
            $('.page_box').hide();
            $('.not_find_message').show();
        } else {
            $('.page_box').show();
            $('.not_find_message').hide();
        }
        $('.sub_message').text("总数" + "(" + global_sum + ")");
        //统计分页总数配置
        page_sum = Math.ceil(global_sum / size);
        if ((global_page + 1) > page_sum) {
            global_page = 0;
        }
        $('.page_content').text(page_sum);
        listStyleFunction();
        pageButton();
        $('.page_current').text(global_page + 1);
    }

    return {
        pageNext: pageNext,
        pagePrev: pagePrev,
        checkPageTo: checkPageTo,
        checkUserPrivilege: checkUserPrivilege
    }
}());
//对分页控制
var pageSet = (function () {

    //显示页码设置
    function showPageSetDrop(obj) {
        $(obj).children('.page_set_ul').toggle();
    }

    //隐藏页码
    function hidePageSet(obj) {
        $(obj).children('.page_set_ul').hide();
    }

    //获取页码信息
    function getPageNumber(obj) {
        global_page = 0;
        size = $(obj).children('.page_set_number').text();
        $('.show_page').text(size);
        findMessage();
    }

    //添加页码选择数据
    function setPageNumber() {
        for (var i = 0; i < page_array.length; i++) {
            $('.page_set_ul').append('<li onclick="pageSet.getPageNumber(this)">' +
                '<span class="page_set_style">每页</span>' +
                '<span class="page_set_number">' + page_array[i] + '</span>' +
                '<span>条</span>' +
                '</li>')
        }
    }

    return {
        showPageSetDrop: showPageSetDrop,
        hidePageSet: hidePageSet,
        getPageNumber: getPageNumber,
        setPageNumber: setPageNumber
    }
}());

//ajax加载画面
function loading() {
    $('.hover_all').show();
    $('.loading_box').show();
}
function closeLoading() {
    $('.hover_all').hide();
    $('.loading_box').hide();
}
//检测是否含有数据
function checkMessageIsNull() {
    //判断是否存在数据
    if ($('.list_li').length == 0) {
        $('.not_find_message').show();
    }
}
//添加下滑
function addManagerStyle(slideTitle) {
    $('.slide_container').animate({"top": "22%", "opacity": "1"}, 300);
    $('.slide_container_top').find('.slide_container_top_title').text(slideTitle);
    $('.submit_select_sure').text('保存');
    $('.input_style').val('');
}
function removeManagerStyle() {
    $('.slide_container').animate({"top": "-220%", "opacity": "0"}, 300);
}
//ajax操作
var operation = (function () {
    //异步传输
    var operation_ajax_async = function (url,type, array, callback) {
        $.ajax({
            type: type,
            url: url,
            data: array,
            dataType: "json",
            beforeSend: loading,//执行ajax前执行loading函数.直到success
            success: function (data) {
                closeLoading();
                global_data = data;
                if (global_data.status == true) {
                    // console.log("操作成功");
                    callback();
                } else {
                    swal(data.message);
                }
            },
            error: function () {
                swal(data.message);
            }
        })
    };
    //同步传输
    var operation_ajax_synchro = function (url, type,array, callback) {
        $.ajax({
            type: type,
            url: url,
            data: array,
            async: false,
            dataType: "json",
            beforeSend: loading,//执行ajax前执行loading函数.直到success
            success: function (data) {
                closeLoading();
                global_data = data;
                if (global_data.status == true) {
                    // console.log("交互成功");
                    callback();
                } else {
                    swal(data.message);
                }
            },
            error: function () {
                swal(data.message);
            }
        })

    };
    //异步提交，不结束loading
    var operation_ajax_loading = function (url, array, callback) {
        $.ajax({
            type: "POST",
            url: url,
            data: array,
            dataType: "json",
            beforeSend: loading,//执行ajax前执行loading函数.直到success
            success: function (data) {
                global_data = data;
                if (global_data.status == true) {
                    // console.log("交互成功");
                    callback();
                } else {
                    swal(data.message);
                }
            },
            error: function () {
                console.log("交互失败");
                callback();
            }
        })
    };
    //formData表单提交
    var operation_ajax_FormData = function (url, data, callback) {
        //var json = JSON.stringify(array_img);
        //var data = new FormData($('#upload_form')[0]);
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            processData: false,
            contentType: false,
            async: true,
            beforeSend: loading,//执行ajax前执行loading函数.直到success
            success: function (data) {
                global_data = data;
                closeLoading();
                if (global_data.status == true) {
                    // console.log("交互成功");
                    callback();
                } else {
                    swal(data.message);
                }
            },
            error: function () {
                console.log("交互失败");
                swal(data.message);
            }
        })
    };

    //添加和删除修改
    var swal_ajax_manager = function (url, array, callback) {
        $.ajax({
            type: "POST",
            url: url,
            data: array,
            dataType: "json",
            success: function (data) {
                if (data.status == true) {
                    global_data = data.result;
                    swal(data.message, "", "success");
                    callback();
                } else {
                    swal(data.message, "", "error");
                }
            },
            error: function (data) {
                swal(data.message);
            }
        })

    };
    //添加和删除
    var swal_ajax_find = function (url, array, callback) {
        $.ajax({
            type: "POST",
            url: url,
            data: array,
            dataType: "json",
            beforeSend: loading,//执行ajax前执行loading函数.直到success
            success: function (data) {
                closeLoading();
                if (data.status == true) {
                    global_data = data.result;
                    callback();
                } else {
                    swal(data.message, "", "error");
                }
            },
            error: function (data) {
                swal(data.message);
            }
        })
    };
    //异步传输
    var operation_ajax_toastr = function (url, array, callback) {
        $.ajax({
            type: "POST",
            url: url,
            data: array,
            dataType: "json",
            success: function (data) {
                global_data = data;
                if (global_data.status == true) {
                    // console.log("操作成功");
                    callback();
                } else {
                    toastr.warning(data.message);
                }
            },
            error: function (data) {
                toastr.error(data.message);
            }
        })

    };
    return {
        operation_ajax_async: operation_ajax_async,
        operation_ajax_synchro: operation_ajax_synchro,
        operation_ajax_loading: operation_ajax_loading,
        operation_ajax_FormData: operation_ajax_FormData,
        swal_ajax_manager: swal_ajax_manager,
        operation_ajax_toastr: operation_ajax_toastr
    }

})();
//分页加载下拉数据
pageSet.setPageNumber();

function showTips(tipMessage) {
    $('.fixed_tips').remove();

    $('.container_content ').append('<div class="fixed_tips">' +
        '<span class="fixed_tips_span"></span>' +
        '</div>');

    $('.fixed_tips').fadeIn().children('.fixed_tips_span').text(tipMessage);

    setTimeout(function () {
        $('.fixed_tips').hide();
    }, 2000)
}

// getFormatDate("Wed Mar 08 2017 00:00:00 GMT+0800 (中国标准时间)","：","-")  时间格式处理
function getFormatDate(date_time, seperator1, seperator2) {
    var date = date_time;
    console.log(date);
    if (date_time == "") {
        date = new Date();
    }

    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var hours = date.getHours();
    var minutes = date.getMinutes();
    var seconds = date.getSeconds();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    if (hours >= 0 && hours <= 9) {
        hours = "0" + hours;
    }
    if (minutes >= 0 && minutes <= 9) {
        minutes = "0" + minutes;
    }
    if (seconds >= 0 && seconds <= 9) {
        seconds = "0" + seconds;
    }
    var currentDate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + hours + seperator2 + minutes
        + seperator2 + seconds;

    return currentDate;
}

function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

//设置cookie
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires+"; path=../";
}

//清除cookie
function clearCookie(name) {
    setCookie(name, "", -1);
}