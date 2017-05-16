'use strict';

/* Controllers */


var app = angular.module('ngdemo.controllers', []);


// Clear browser cache (in development mode)
//
// http://stackoverflow.com/questions/14718826/angularjs-disable-partial-caching-on-dev-machine
app.run(function ($rootScope, $templateCache) {
    $rootScope.$on('$viewContentLoaded', function () {
        $templateCache.removeAll();
    });
});


app.controller('leverancierCtrl', ['$scope', 'LeverancierResource', function ($scope, LeverancierResource) {
   
   $scope.leveranciers = LeverancierResource.get();
   
}]);

app.controller('welkomCtrl', ['$scope', function ($scope) {
   
}]);