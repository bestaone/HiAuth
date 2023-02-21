
var contentPath = "";

$(function(){

    var imgUrl = contentPath + "/code/image";
    var smsUrl = contentPath + "/code/sms";

    var $accountForm = $('#accountForm');
    var $telForm = $('#telForm');

    //账号登录表单校验
    $accountForm.bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                message: '用户名验证失败',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 3,
                        max: 15,
                        message: '用户名长度为3-15个字符'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    }
                }
            },
            yzm: {
                validators: {
                    notEmpty: {
                        message: '图形验证码不能为空'
                    }
                }
            }
        }
    });
    //获取图形验证码
    function getImgYzm(formToken) {
        var $this = $(".yzm-img");
        var url = imgUrl + "?formToken=" + formToken + "&r=" + Math.random();
        $this.attr('src', url);
    }
    //点击更新图形验证码
    $('.yzm-img').click(function () {
        var formToken = $("#formToken1").val();
        getImgYzm(formToken);
    });
    getImgYzm($("#formToken1").val());

    //短信验证码字段校验
    var smsCodeValidators = {
        validators: {
            notEmpty: {
                message: '短信验证码不能为空'
            }
        }
    };
    //手机号登录表单校验
    $telForm.bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            telNo: {
                message: '手机号码验证失败',
                validators: {
                    notEmpty: {
                        message: '手机号码不能为空'
                    },
                    regexp: {
                        regexp: /^1\d{10}$/,
                        message: '请输入一个正确的手机号码'
                    }
                }
            },
            yzm: {
                validators: {
                    notEmpty: {
                        message: '图形验证码不能为空'
                    }
                }
            }
            ,
            smsCode: smsCodeValidators
        }
    });
    //获取短信验证码
    function getSmsYzm(formToken, telNo, imaCode) {
        var url = smsUrl + "?telNo=" + telNo + "&formToken=" + formToken + "&imgCode=" + imaCode + "&r=" + Math.random();
        $.get(url, function(data){
            $("#smsCode").val(data.data);
        });
    }
    //点击获取短信验证码
    $('.sms-code-btu').click(function (e) {
        var $this = $(e.target);
        if(!$this.hasClass("disabled")){
            $telForm.bootstrapValidator("removeField", "smsCode");
            $telForm.data("bootstrapValidator").validate();
            var isValid = $telForm.data("bootstrapValidator").isValid();
            if(isValid){
                var formToken = $("#formToken2").val();
                var telNo = $("#telNo").val();
                var imaCode = $("#yzm1").val()
                getSmsYzm(formToken, telNo, imaCode);
                setTime($this, 60);
            }
            $telForm.bootstrapValidator("addField", "smsCode", smsCodeValidators);
        }
    });

    //发送完短信后倒计时数秒，如果需要支持页面刷新，可以使用cooke
    function setTime($el, countdown) {
        if (countdown == 0) {
            $el.removeClass("disabled");
            $el.text("获取验证码");
            return false;
        } else {
            $el.addClass("disabled");
            $el.text("重新发送(" + countdown + ")");
            countdown--;
        }
        setTimeout(function() {
            setTime($el, countdown);
        },1000);
    }

});