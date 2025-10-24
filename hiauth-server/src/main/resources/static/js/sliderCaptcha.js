(function ($) {
    // 滑块验证码插件
    $.fn.sliderCaptcha = function (options) {

        if (typeof options === 'string') {
            const methodName = options;
            const args = Array.prototype.slice.call(arguments, 1);

            // 如果是 reset 方法
            if (methodName === 'reset') {
                return this.each(function() {
                    const $sliderTrack = $(this);
                    const sliderInstance = $sliderTrack.data('sliderCaptcha');
                    if (sliderInstance && typeof sliderInstance.reset === 'function') {
                        sliderInstance.reset.apply(sliderInstance, args);
                    }
                });
            }
        }

        // 默认配置
        const defaults = {
            token: null,
            successCallback: null,
            errorCallback: null
        };

        // 合并配置
        const settings = $.extend({}, defaults, options);

        // 遍历每个元素
        return this.each(function () {
            const $sliderTrack = $(this);
            const $sliderThumb = $sliderTrack.find('.slider-thumb');
            const $thumbTrack = $sliderTrack.find('.slider-track');
            const $sliderTitle = $sliderTrack.find('.slider-title');
            const $captchaInput = $sliderTrack.find('[name="captcha"]');

            let isDragging = false;
            let isVerified = false;  // 添加验证状态变量
            let startX = 0;
            let endX = 0;
            let startTime = 0;
            let endTime = 0;
            let startLeft = 0;

            // 重置方法
            function reset() {
                $sliderThumb.animate({left: 0}, 300);
                $thumbTrack.animate({width: 0}, 300);
                $sliderTrack.removeClass('success');
                $sliderThumb.removeClass('success');
                $sliderTitle.removeClass('success');
                $sliderTitle.text('按住滑块，拖动到最右边');
                $sliderThumb.html('<img src="../static/img/chevron-right-light.png" alt="">');
                isVerified = false;
                $captchaInput.val('');
            }

            // 将 reset 方法暴露给元素
            $sliderTrack.data('sliderCaptcha', {
                reset: reset
            });

            // 鼠标按下事件
            $sliderThumb.on('mousedown', function (e) {
                // 如果已经验证成功，则不再允许拖动
                if (isVerified) return;
                isDragging = true;
                startX = e.clientX;
                startTime = new Date().getTime();
                startLeft = parseInt($sliderThumb.css('left')) || 0;
                e.preventDefault();
            });

            // 鼠标移动事件
            $(document).on('mousemove', function (e) {
                if (!isDragging) return;

                const diffX = e.clientX - startX;
                const newLeft = startLeft + diffX;

                // 限制滑块移动范围
                const maxLeft = $sliderTrack.width() - $sliderThumb.width();
                const boundedLeft = Math.max(0, Math.min(newLeft, maxLeft));

                $sliderThumb.css('left', boundedLeft);
                $thumbTrack.css('width', boundedLeft);
            });

            // 鼠标释放事件
            $(document).on('mouseup', function (e) {
                if (!isDragging) return;
                isDragging = false;
                endX = e.clientX;
                endTime = new Date().getTime();

                // 检查是否拖动到最右边
                const maxLeft = $sliderTrack.width() - $sliderThumb.width();
                const currentLeft = parseInt($sliderThumb.css('left')) || 0;

                // 如果拖动到最右边，验证成功
                if (currentLeft >= maxLeft - 5) { // 允许5px的误差

                    // 构建数据对象
                    const data = {
                        token: settings.token,
                        startX: startX,
                        endX: endX,
                        startTime: startTime,
                        endTime: endTime
                    };

                    // 执行成功回调
                    if (typeof settings.successCallback === 'function') {
                        settings.successCallback.call($sliderTrack, data);
                    }

                    // 调用后台接口
                    $.ajax({
                        url: '/auth/code/captcha',
                        type: 'POST',
                        data: JSON.stringify(data),
                        contentType: 'application/json',
                        success: function(response) {
                            if(response.code === 10000){
                                $sliderThumb.css('left', maxLeft);
                                $sliderTrack.addClass('success');
                                $sliderThumb.addClass('success');
                                $sliderTitle.addClass('success');
                                $sliderTitle.text('验证成功');
                                // 修改滑块内容为绿色对号
                                $sliderThumb.html('<img src="../static/img/chevron-success.png" alt="">');
                                // 设置验证状态为已验证
                                isVerified = true;
                                $captchaInput.val(response.data)
                            } else {
                                // 验证失败时重置
                                reset();
                            }
                        },
                        error: function() {
                            // 请求失败时重置
                            reset();
                        }
                    });

                } else {
                    // 如果没有拖动到最右边，回到初始位置
                    $sliderThumb.animate({left: 0}, 300);
                    $thumbTrack.animate({width: 0}, 300);

                    // 执行错误回调
                    if (typeof settings.errorCallback === 'function') {
                        settings.errorCallback.call($sliderTrack);
                    }
                }
            });
        });
    };
})(jQuery);