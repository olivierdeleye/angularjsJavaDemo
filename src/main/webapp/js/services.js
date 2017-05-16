'use strict';

/* Services */

var services = angular.module('ngdemo.services', ['ngResource']);

services.factory('LeverancierResource', function ($resource) {
    return $resource('/ngdemo/rest/leveranciers', {}, {
        query: {
            method: 'GET',
            params: {},
            isArray: true
        }
    })
});
