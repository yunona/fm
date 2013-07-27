$(function() {
    $('#fileItems').on('click', '.toggle', function () {

        var element = $(this);
        var tr = element.closest('tr');
        var trId = tr.attr('id');

        if (tr.hasClass('open')) {

            $.getJSON('/filemanager/subElements.ajax', {
                parent : trId
            }, function(data) {
                var len = data.length;
                for (var i = len - 1; i >= 0; i--) {
                    var html = '<tr class="close" id="' + data[i].path + '">' +
                            '<td><span class="toggle"></span>' + data[i].name + '</td>' +
                            '<td>' + data[i].extension + '</td>';

                    tr.after(html);
                }
            });

            tr.removeClass('open').addClass('close');
        } else {
            //hide children;
            tr.removeClass('close').addClass('open');
        }
    });
});