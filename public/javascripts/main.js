var start = function() {
   $(document).ready(function(){
       var searchInit = false;
       $("#videoFeedTabs").tabs({
           select: function(event, ui){
               var tabNumber = ui.index;
               var tabName = $(ui.tab).text();
               if(tabNumber = 2)
                    if(!searchInit){
                        initializeSearchModel();
                        searchInit = true;
                    }
           }
       });
       registerValidation();
       initializeMainViewModel();
   });
};

var startWithExisting = function(existingVideoFeed){
    if(existingVideoFeed == null)
        start();
    else{
        var vfResult = VideoFeedSearchResultViewModel.fromJson(JSON.parse(existingVideoFeed));
        $(document).ready(function(){
               var searchInit = false;
               $("#videoFeedTabs").tabs({
                   select: function(event, ui){
                       var tabNumber = ui.index;
                       var tabName = $(ui.tab).text();
                       if(tabNumber = 2)
                            if(!searchInit){
                                initializeSearchModel();
                                searchInit = true;
                            }
                   }
               });
               registerValidation();
               initializeMainViewModel();
               //convert result to a mainVM so that we have something to bind to with existing values!
               //setting after initialize, ko validation does not work if set before initialize
               mainVM.fillFromScalaForm(vfResult);
        });
    }
};

var applyJQueryTheme = function(){
    $('button').addClass("ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only");
    $('button > span').addClass("ui-button-text");
    $('input[type="submit"]').addClass("ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only");
    $('div.sliderHeader').addClass("ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all");
    $('a.slider').addClass("ui-slider-handle ui-state-default ui-corner-all");
};

var mainVM = null;
var initializeMainViewModel = function () {
    if(mainVM == null)
        mainVM = new videoFeedFormViewModel();
	ko.applyBindingsWithValidation(mainVM);
	applyJQueryTheme();
};

var initializeEncoderTypeFormViewModel = function () {
	ko.applyBindingsWithValidation(new EncoderTypeFormViewModel(),$("#encodertype_form")[0]);
};

var searchVM = null;
var initializeSearchModel = function () {
    if(searchVM == null){
        searchVM = new VideoFeedSearchViewModel();
        $('#tab2').html(initializeSearchModel().html());
        ko.applyBindingsWithValidation(searchVM,$("#tab2")[0]);
        applyJQueryTheme();
        $('#dialogSearchWarn').dialog({
              autoOpen: false,
              buttons: { "Ok": function() { $(this).dialog("close"); } },
              title: "Search Fields Invalid",
              closeOnEscape: true
        });
        //override the click of this element so it does not go to a new page
        $('#videoFeedResults tr').click(function() {
            var href = $(this).find("a").attr("href");
            if(href){
                window.location = href;
            }
        });
    }
	return searchVM;
};

var setSubForm = function (subHtmlCallback, replacingHtml, url, formViewModelFactory, onSubmitCallback, subformElementName) {
    $.get(url, function (html) {
        var returnedHtml = html;
        subHtmlCallback(replacingHtml);//erase b4 add, detaches old VM
        subHtmlCallback(returnedHtml);
        var formViewModel = formViewModelFactory();
        formViewModel.onSubmit(function (returnedEntity) {
            onSubmitCallback(returnedEntity);
            subHtmlCallback(replacingHtml);
        });
        ko.applyBindingsWithValidation(formViewModel, $(subformElementName)[0]);
        applyJQueryTheme();
    });
};