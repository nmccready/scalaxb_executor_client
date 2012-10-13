
 var RotationHelper = function(){
      var self = this;

      var count = 0;
      var doRun = true;

      self.rotate = function(selectedElement) {
        //selectedElement.style.MozTransform = 'scale(0.5) rotate('+count+'deg)';
        selectedElement.style.WebkitTransform = 'scale(0.5) rotate('+count+'deg)';
        if (count==360) {
            count = 0
        }
        count+=45;
        window.setTimeout( function(){
                if(doRun)
                    self.rotate(selectedElement);
            },
            100);
      }

      self.stop = function(){ doRun = false;}
};