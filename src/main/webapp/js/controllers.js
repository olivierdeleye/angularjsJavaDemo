'use strict';

/* Controllers */


var app = angular.module('ngdemo.controllers', []);


// Clear browser cache (in development mode)
//
// http://stackoverflow.com/questions/14718826/angularjs-disable-partial-caching-on-dev-machine
//app.run(function ($rootScope, $templateCache) {
//    $rootScope.$on('$viewContentLoaded', function () {
//        $templateCache.removeAll();
//    });
//});


app.controller('leverancierCtrl', ['$scope', 'LeverancierResource', function ($scope, LeverancierResource) {
   
              
   $scope.leveranciers = [];
   
//   $scope.gridOptions = { data: 'leveranciers',
//              rowHeight: 25,
//              multiSelect:false,
//              enableRowSelection: true,
//              columnDefs: [
//               {
//                   field: "leverancierNr",
//                   displayName: "Nummer"
//                      
//                },
//               {
//                 field: "lev_naam",
//                 displayName: "Naam",
//                 width: '25%',
//                 height: '50px'                
//               },
//               {
//                 field: "straat",
//                 displayName: "Straat",
//                 width: '25%',
//                 height: '50px'
//               },
//               {
//                 field: "huisNr",
//                 displayName: "HuisNr",
//                 width: '25%',
//                 height: '50px'
//               },
//               {
//                 field: "postcode",
//                 displayName: "Postcode",
//                 width: '25%',
//                 height: '50px'
//               },
//               {
//                 field: "gemeente",
//                 displayName: "Gemeente",
//                 width: '25%',
//                 height: '50px'
//               },
//               {
//                 field: "telefoon",
//                 displayName: "Telefoon",
//                 width: '25%',
//                 height: '50px'
//               }
//
//               ]};
           
    $scope.promise = LeverancierResource.query().$promise.then(function(result){
             $scope.leveranciers = angular.copy(result);
             $scope.gridOptions = $scope.leveranciers;
             
        });
     
//         $scope.myData = [{name: "Moroni", age: 50},
//                     {name: "Tiancum", age: 43},
//                     {name: "Jacob", age: 27},
//                     {name: "Nephi", age: 29},
//                     {name: "Enos", age: 34}];

   
}]);


app.controller('welkomCtrl', ['$scope', function ($scope) {
   
}]);