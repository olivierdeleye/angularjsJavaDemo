'use strict';

// Declare app level module which depends on filters, and services
var app = angular.module('ngdemo', ['ngRoute', 'ngAnimate', 'ngTouch', 'ui.grid', 'ui.grid.selection', 'ui.bootstrap', 'internationalPhoneNumber', 'angularModalService', 'ngdemo.filters', 'ngdemo.services', 'ngdemo.directives', 'ngdemo.controllers']).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/leveranciers', {templateUrl: 'partials/leveranciers.html', controller: 'leverancierCtrl'});
        $routeProvider.when('/home', {templateUrl: 'partials/welkom.html', controller: 'welkomCtrl'});
        $routeProvider.otherwise({redirectTo: '/home'});
        
        
    }]);
