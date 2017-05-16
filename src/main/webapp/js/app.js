'use strict';

// Declare app level module which depends on filters, and services
angular.module('ngdemo', ['ngdemo.filters', 'ngdemo.services', 'ngdemo.directives', 'ngdemo.controllers']).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/leveranciers', {templateUrl: 'partials/leveranciers.html', controller: 'leverancierCtrl'});
        $routeProvider.when('/welkom', {templateUrl: 'partials/welkom.html', controller: 'welkomCtrl'});
        $routeProvider.otherwise({redirectTo: '/welkom'});
    }]);
