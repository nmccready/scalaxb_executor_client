var registerValidation = function(){
    ko.validation.rules['uniqueCheck'] = {
            async: true,
            validator: function ( val, param, callback ) {
                if(val != null || param == null){
                    if(param.viewModel == null || param.url == null)
                        return callback(true);
                    if(param.viewModel.toJS == null)
                        return callback(true);
                    var defaults = {
                        url: param.url,
                        data: ko.toJSON(param.viewModel.toJS()),
                        type: param.method || 'POST',
                        dataType: param.dataType || 'json',
                        contentType: param.contentType || 'application/json; charset=utf-8',
                        success: function(val){
                            callback(val);
                            if(param.viewModel.handleCallback != null)
                                param.viewModel.handleCallback(val);
                        }
                    };
                    var options =  $.extend(defaults,{})
                    $.ajax( options );
                }
                else{
                    return callback(true);
                }
            }
    };
    ko.validation.registerExtenders();
};