function clone(obj) {
    if (null == obj || "object" != typeof obj) return obj;

    if (obj instanceof Date) {
        var copy = new Date();
        copy.setTime(obj.getTime());
        return copy;
    }

    if (obj instanceof Array) {
        var copy = [];
        var len = obj.length;

        for (var i = 0; i < len; ++i) {
            copy[i] = clone(obj[i]);
        }
        return copy;
    }

    if (obj instanceof Object) {
        var copy = {};
        for (var attr in obj) {
            if (obj.hasOwnProperty(attr)) copy[attr] = clone(obj[attr]);
        }
        return copy;
    }

    throw new Error("Unable to copy obj! Its type isn't supported.");
};

String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
      ? args[number]
      : match
    ;
  });
};

var spacedColon = " : ";

var standOutDebug = function(id) {
 return " !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + id
};

var emptyFillString = "$EMPTY$";

var replaceEmptyString =  function(str) {
    if(str == "")
        return emptyFillString;
    else
        return str;
};

function sortSelectByText(selElem,modifyValueFunc) {
    var tmpAry = new Array();
    for (var i=0;i<selElem.options.length;i++) {
        tmpAry[i] = new Array();
        var text = modifyValueFunc(selElem.options[i].text);
        tmpAry[i][0] = text;
        tmpAry[i][1] = selElem.options[i].value;
    }
    tmpAry.sort();
    while (selElem.options.length > 0) {
        selElem.options[0] = null;
    }
    for (var i=0;i<tmpAry.length;i++) {
        var op = new Option(tmpAry[i][0], tmpAry[i][1]);
        selElem.options[i] = op;
    }
    return;
};

function openDialog(message) {
    if ($('#dialog').length == 0) {
        $(document.body).append('<div id="dialog"> <div class="ui-state-highlight ui-corner-all">'+message+'</div></div>');
    } else {
        $('#dialog').html(message);
    }
    $( "#dialog" ).dialog({
        autoOpen: false,
        show: "blind",
        hide: "explode"
    });
    $( "#dialog" ).dialog("open");
}