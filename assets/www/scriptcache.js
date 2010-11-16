var ScriptCache = (function() {
  function ScriptCache() {
    this.cache = {};
    
    var scriptCache = this;
    $('[data-role=page]').live('pageshow', function(event) {
      var pageName = event.target.id;

      // pageName might have some ? parameters tagged on. If so, remove the argument
      var questionIndex = pageName.indexOf('?');
      if (questionIndex != -1) {
        pageName = pageName.substr(0, questionIndex);
      }
      if (pageName == '') {
        return;
      }
      var jsName = pageName.replace(/\.[^\.]*$/,'') + '.js';
      scriptCache.load(jsName);
    });
  };
  
  ScriptCache.prototype.load = function(src) {
    var cache = this.cache;
    if (cache[src] != undefined) {
      // the script has already been loaded. don't load it again!
      return;
    }
    $.ajax({
      dataType: 'script',
      async: false,
      cache: true,
      url: src,
      success: function() {
        cache[src] = true;
      }
    });
  };
  
  ScriptCache.prototype.onPageLoad = function(page, callback) {
    $('[data-role=page]').live('pageshow', function(event) {
      if (event.target.id.indexOf(page) == 0) {
        callback();
      }
    });
  };
  
  return ScriptCache;
})();

// var scriptCache = {
//   cache: {},
//   
//   load: function(src) {
//     var cache = this.cache;
//     if (cache[src] != undefined) {
//       // the script has already been loaded. don't load it again!
//       return;
//     }
//     $.ajax({
//       dataType: 'script',
//       async: false,
//       cache: true,
//       url: src,
//       success: function() {
//         cache[src] = true;
//       }
//     });
//   },
//   
//   onPageLoad: function(page, callback) {
//     $('[data-role=page]').live('pageshow', function(event) {
//       if (event.target.id.indexOf(page) == 0) {
//         callback();
//       }
//     });
//   },
// 
//   initialize: function() {
// 
//     $('[data-role=page]').live('pageshow', function(event) {
//       var pageName = event.target.id;
// 
//       // pageName might have some ? parameters tagged on. If so, remove the argument
//       var questionIndex = pageName.indexOf('?');
//       if (questionIndex != -1) {
//         pageName = pageName.substr(0, questionIndex);
//       }
//       if (pageName == '') {
//         return;
//       }
//       var jsName = pageName.replace(/\.[^\.]*$/,'') + '.js';
//       scriptCache.load(jsName);
//     });
//   }
// };