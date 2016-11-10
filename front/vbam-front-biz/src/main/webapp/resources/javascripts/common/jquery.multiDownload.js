/* 传参为数组 例如$([url,url]).multiDownload();*/
(function($) {
 
    var methods = {
        _download: function(options) {
            var triggerDelay = (options && options.delay) || 100;
            var removeDelay = (options && options.removeDelay) || 1000;
              this.each(function(index, item) {
                    methods._createIFrame(item, index * triggerDelay, removeDelay);
                });
        },
        _createIFrame: function(url, triggerDelay, removeDelay) {
            //动态添加iframe，设置src，然后删除
            setTimeout(function() {
                var frame = $('<iframe style="display: none;" class="multi-download"></iframe>');
                frame.attr('src', url);
                $(document.body).after(frame);
                setTimeout(function() {
                    frame.remove();
                }, removeDelay);
            }, triggerDelay);
        }
    };
 
    $.fn.multiDownload = function(options) {
        methods._download.apply(this, arguments);
    };
 
})(jQuery);