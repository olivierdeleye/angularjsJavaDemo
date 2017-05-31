'use strict';

/* Services */

var services = angular.module('ngdemo.services', ['ngResource'])


.factory('LeverancierService', ['$resource', function ($resource) {

    return $resource('/ngdemo/rest/leveranciers', {}, 
    {
        save: { method: 'POST', url: '/ngdemo/rest/leveranciers/save'}
    }
    );
   
  }]);
