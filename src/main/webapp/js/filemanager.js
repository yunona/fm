(function ($) {
    $.fn.fileManager = function (settings) {
        var outerDiv = $(this);

        function addDirectory(item, parent) {
            var isOpened = item.subItems.length > 0;
            var cls = isOpened ? 'close' : 'open';
            var imgCls = item.extension.length != 0 ? item.extension : "folder";
            var div = $('<div class="' + cls + '" rel="' + item.name + '" style="margin-left:25px"></span>' + item.name + '</div>').appendTo(parent);
            $(div).wrapInner('<span class="file-text"></span>').prepend('<span class="file-icon"></span>').addClass(imgCls).addClass('file-icon');
            $(div).prepend('<span class="toggle ' + cls + '"></span>');
            return div;
        }

        function addFile(item, parent) {
            var div = $('<div class="file-element" style="margin-left:25px">' + item.name + '</div>').appendTo(parent);
            var cls = item.extension;
            if (cls.length == 0 && item.directory) cls = "folder";
            $(div).wrapInner('<span class="file-text"></span>').prepend('<span class="file-icon"></span>').addClass(cls).addClass('file-icon');
            return div;
        }

        $(function() {
            $.post(settings.url + "/initialElements.ajax", {root : settings.rootDirectory })
                    .done(function(data) {

                var f = function (parent, items) {

                    $.each(items, function(i, item) {
                        if (item.expandable) {
                            var div = addDirectory(item, parent);

                            var isOpened = item.subItems.length > 0;
                            if (isOpened) {
                                f(div, item.subItems);
                            }
                        } else {
                            addFile(item, parent);
                        }
                    });
                }
                f(outerDiv, data);
            });
        });

        $(function() {
            $(outerDiv).on('click', '.toggle', function () {

                var spanElement = $(this);
                var divElement = spanElement.parent();
                var value = divElement.attr('rel');
                if (value.match(/rar$/)) {
                    alert("This type of archive can't be opened");
                    return;
                }

                var parents = divElement.parents('div');
                var path = settings.rootDirectory;
                if (parents.length > 1) {
                    for (var i = parents.length - 2; i >= 0; i--) {
                        var p = parents[i];
                        path += $(parents[i]).attr('rel') + "\\";
                    }
                    path += value;
                } else {
                    path += value + "\\";
                }

                if (spanElement.hasClass('open')) {

                    $.post(settings.url + "/subElements.ajax", {parent : path })
                            .done(function(data) {
                        $.each(data, function(i, item) {
                            if (item.expandable) {
                                addDirectory(item, divElement);
                            } else {
                                addFile(item, divElement);
                            }
                        });
                    });

                    divElement.removeClass('open').addClass('close');
                    spanElement.removeClass('open').addClass('close');
                } else {
                    $.post(settings.url + "/closeElements.ajax", {parent : path });
                    divElement.children('div').remove();
                    divElement.removeClass('close').addClass('open');
                    spanElement.removeClass('close').addClass('open');
                }
            });
        });
    };
})(jQuery);