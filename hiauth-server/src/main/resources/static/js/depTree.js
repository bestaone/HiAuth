(function($){

    // 定义默认选项
    const defaults = {
        corpId: null,
        nodeOnClickCallback: function (id) {}
    };

    // 创建jQuery插件
    $.fn.devTree = function(options){

        // 合并选项
        const opt = $.extend({}, defaults, options);
        $this = $(this);

        function depTreeNodeClick(e) {
            const $e = $(e.target);
            const pid = $e.attr("data-pid");
            $(".bd-toc nav a.active").removeClass("active");
            $e.addClass("active");
            options.nodeOnClickCallback(pid);
        }

        function buildSubNode(pe, deps, pid){
            const ul = $("<ul></ul>");
            let hasSub = false;
            deps.forEach(function (dep) {
                if(dep.pid===pid){
                    const li = $("<li><a class='rounded' href='#' data-pid='" + dep.id + "'>" + dep.name + "</a></li>");
                    li.click(function (e) {
                        e.stopPropagation();
                        depTreeNodeClick(e);
                    })
                    buildSubNode(li, deps, dep.id);
                    ul.append(li);
                    hasSub = true;
                }
            });
            if(pid && hasSub){
                pe.prepend("<div class='fa fa-caret-down' aria-hidden='true'></div>");
            }
            pe.append(ul);
        }

        function depTree() {
            $.ajax({
                type: "POST",
                url: "/" + opt.corpId + "/orgMgr/listDep",
                contentType: 'application/json',
                success: function (result) {
                    $this.empty();
                    buildSubNode($this, result.data, undefined);
                    console.log(result.data);
                }
            });
        }
        depTree();
        return this;
    };

})(jQuery);