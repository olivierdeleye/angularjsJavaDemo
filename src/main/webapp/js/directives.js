'use strict';

/* Directives */


var ngdemoApp = angular.module('ngdemo.directives', []);

    
  ngdemoApp.directive('appVersion', ['version', function(version) {
    return function(scope, elm, attrs) {
      elm.text(version);
    };
  }]);
  
  
 


