/**
 * Created by paradoxfm on 25.02.2016.
 */

appLog.service('ToastService', function ($timeout) {


    this.showNotify = function (notify) {
        var toast = '<div class="toast-item">' +
            '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>' +
            '<div class="toast-icon"></div><div class="toast-list">';
        if (isArray(notify)) {
            $.each(notify, function(i, v) {
                toast += createOneNotify(v);
            });
        } else {
            toast += createOneNotify(notify);
        }
        toast += '</div><div class="clearfix"></div></div>';
        var toastElm = $('#notifyStack').append(toast);

        $timeout(function () {
            toastElm.fadeOut(400, function() {
                $(this).remove();
            });
        }, 5000);
    };

    function createOneNotify(notify) {
        return '<span class="toast-message">' + notify.message + '</span>';
    }

    function isArray(obj) {
        return Object.prototype.toString.call(obj) === '[object Array]';
    }
});
