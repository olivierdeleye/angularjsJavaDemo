'use strict';

// Declare app level module which depends on filters, and services
angular.module('ngdemo', ['ngdemo.filters', 'ngdemo.services', 'ngdemo.directives', 'ngdemo.controllers']).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/leveranciers', {templateUrl: 'partials/leveranciers.html', controller: 'LeverancierCtrl'});
        $routeProvider.otherwise({redirectTo: '/leveranciers'});
    }]);
