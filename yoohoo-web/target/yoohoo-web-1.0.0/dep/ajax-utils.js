var AjaxClient = {
    get: function (args) {
        args.type = 'GET';
        this.sendAjax(args);
    },
    post: function (args) {
        args.type = 'POST';
        this.sendAjax(args);
    },
    loadIndex: 0,
    sendAjax: function (args) {
        if (this.loadIndex) {
            return;
        }
        var me = this;
        $.ajax({
            url: args.url,
            data: args.data,
            type: args.type,
            dataType: 'JSON',
            beforeSend: function (XMLHttpRequest) {
                typeof (args.before) === 'function' && args.before(XMLHttpRequest);
                me.loadIndex = layer.load(1, {shade: [0.3, '#393D49']});
            },
            success: function (data, textStatus) {
                typeof (args.success) === 'function' && args.success(data, textStatus);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                typeof (args.error) === 'function' && args.error(XMLHttpRequest, textStatus, errorThrown);
            },
            complete: function (XMLHttpRequest, textStatus) {
                typeof (args.after) === 'function' && args.after(XMLHttpRequest, textStatus);
                layer.close(me.loadIndex)
                me.loadIndex = 0;
            }
        });
    }
};

var message = {
    info: function (message) {
        layer.msg(message, {shade: [0.3, '#393D49'], icon: 1});
    },
    error: function (message) {
        layer.msg(message, {shade: [0.3, '#393D49'], icon: 2})
    },
    warn: function (message) {
        layer.msg(message, {shade: [0.3, '#393D49'], icon: 7});
    }
};