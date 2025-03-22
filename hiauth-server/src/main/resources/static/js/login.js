$(function () {

    const contentPath = "";
    const imgUrl = contentPath + "/auth/code/image";
    const smsUrl = contentPath + "/auth/code/sms";

    const $accountForm = $('#accountForm');
    const $telForm = $('#telForm');

    // 提示图标
    const errorIcon = '<i aria-hidden="true" class="fa fa-exclamation-triangle"></i>';
    $accountForm.validate({
        debug: false,
        errorPlacement: function (error, element) {
            error.addClass("input-invalid")
            $(element).parent().after(error);
        },
        rules: {
            account: {
                required: true,
                rangelength: [3, 10]
            },
            password: {
                required: true,
                rangelength: [3, 10]
            },
            captcha: {
                required: true,
                rangelength: [4, 6]
            }
        },
        messages: {
            account: {
                required: errorIcon + "请输入账号",
                rangelength: errorIcon + "账号长度为{0}-{1}个字符"
            },
            password: {
                required: errorIcon + "请输入密码",
                rangelength: errorIcon + "密码长度为{0}-{1}个字符"
            },
            captcha: {
                required: errorIcon + "请输入图形验证码",
                rangelength: errorIcon + "图形验证码长度为{0}-{1}个字符"
            }
        },
        // 使用div标签，包裹提示信息，而后插入DOM
        wrapper: "div",
        showErrors: function (errorMap, errorList) {
            for (var obj in errorMap) {
                $("[name='" + obj + "']").parent(".input-group").removeClass("input-group-valid").addClass("input-group-invalid")
                $("[name='" + obj + "']").addClass('is-invalid');
            }
            this.defaultShowErrors();
        },
        success: function (label) {
            const id = $(label).attr("for")
            $("#" + id).parent(".input-group").removeClass("input-group-invalid").addClass("input-group-valid")
            $("#" + id).removeClass('is-invalid').addClass("is-valid");
            $(label).parent(".input-invalid").remove();
        }
    });

    $telForm.validate({
        debug: false,
        errorPlacement: function (error, element) {
            error.addClass("input-invalid")
            $(element).parent().after(error);
        },
        rules: {
            account: {
                required: true,
                rangelength: [11, 11]
            },
            smsCode: {
                required: true,
                rangelength: [4, 6]
            },
            captcha: {
                required: true,
                rangelength: [4, 6]
            }
        },
        messages: {
            account: {
                required: errorIcon + "请输入手机号码",
                rangelength: errorIcon + "手机号码长度为{0}-{1}个字符"
            },
            smsCode: {
                required: errorIcon + "请输入短信验证码",
                rangelength: errorIcon + "短信验证码长度为{0}-{1}个字符"
            },
            captcha: {
                required: errorIcon + "请输入图形验证码",
                rangelength: errorIcon + "图形验证码长度为{0}-{1}个字符"
            }
        },
        // 使用div标签，包裹提示信息，而后插入DOM
        wrapper: "div",
        showErrors: function (errorMap, errorList) {
            for (var obj in errorMap) {
                $("[name='" + obj + "']").parent(".input-group").removeClass("input-group-valid").addClass("input-group-invalid")
                $("[name='" + obj + "']").addClass('is-invalid');
            }
            this.defaultShowErrors();
        },
        success: function (label) {
            const id = $(label).attr("for")
            $("#" + id).parent(".input-group").removeClass("input-group-invalid").addClass("input-group-valid")
            $("#" + id).removeClass('is-invalid').addClass("is-valid");
            $(label).parent(".input-invalid").remove();
        }
    });

    //获取图形验证码
    function getCaptcha(formToken) {
        const $this = $(".captcha-img");
        const url = imgUrl + "?formToken=" + formToken + "&r=" + Math.random();
        $this.attr('src', url);
    }

    //点击更新图形验证码
    $('.captcha-img').click(function () {
        const formToken = $("#formToken").val();
        getCaptcha(formToken);
    });
    getCaptcha($("#formToken").val());

    //获取短信验证码
    function getSmsCaptcha(formToken, telNo, imgCode) {
        const url = smsUrl + "?telNo=" + telNo + "&formToken=" + formToken + "&imgCode=" + imgCode + "&r=" + Math.random();
        $.get(url, function (data) {
            $("#smsCode").val(data.data);
        });
    }

    //点击获取短信验证码
    $('.sms-code-btu').click(function (e) {
        const $this = $(e.target);
        if (!$this.hasClass("disabled")) {
            $telForm.validate({
                ignore: "#smsCode",
            });
            const isValid = $telForm.valid();
            // $telForm.addClass('was-validated')
            if (isValid) {
                const formToken = $("#formToken2").val();
                const telNo = $("#telNo").val();
                const imgCode = $("#captcha1").val()
                getSmsCaptcha(formToken, telNo, imgCode);
                setTime($this, 60);
            }
            // $telForm.bootstrapValidator("addField", "smsCode", smsCodeValidators);
            $("#smsCode").val(smsCodeValidators)
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
        setTimeout(function () {
            setTime($el, countdown);
        }, 1000);
    }

});