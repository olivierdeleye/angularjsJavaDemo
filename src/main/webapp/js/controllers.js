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


app.controller('LeverancierCtrl', ['$scope', 'LeverancierResource', function ($scope, LeverancierResource) {
   
    LeverancierResource.get({}, function (LeverancierResource) {
        $scope.leveranciers = LeverancierResource.values();
    })
}]);
