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


app.controller('leverancierCtrl', ['$scope', '$log', 'LeverancierService' ,'ModalService', function ($scope, $log, LeverancierService, ModalService) {
        
   $scope.leverancier = {};
   var init = function(){
       $scope.formData = {
       lev_naam : "",
       leverancierNr: "",
       straat: "",
       huisNr: "",
       bus: "",
       gemeente: "",
       postcode: "",
       telefoon: "",
       fax: "",
       groepNaam: "",
       contact: "",
       opmerking: ""
    };
   };
   
   init();
   
   $scope.gridOptions = { 
        enableRowSelection: true,
        selectionRowHeaderWidth: 35,
        rowHeight: 35,
        multiSelect: false,
        enableGridMenu: true
    };
    
    
    var gridCols= [
               {
                 
                field: "leverancierNr",
                displayName: "Nummer",
                width: '15%'            
                },
               {
                 field: "lev_naam",
                 displayName: "Naam",
                 width: '20%'
                              
               },
                {
                 field: "groep.groepNaam",
                 displayName: "Groep",
                 width: '10%'
                              
               },
               {
                 field: "straat",
                 displayName: "Straat",
                 width: '15%'
               },
               {
                 field: "huisNr",
                 displayName: "HuisNr",
                 width: '8%'
               },
               {
                 field: "postcode",
                 displayName: "Postcode",
                 width: '8%'
               },
               {
                 field: "gemeente",
                 displayName: "Gemeente",
                 width: '8%'
               },
               {
                 field: "telefoon",
                 displayName: "Telefoon"
                
               }
     ];
 
    $scope.listLeveranciers = function(){
        $scope.promise = LeverancierService.query().$promise.then(function(results){
            $scope.gridOptions.columnDefs = gridCols;
            $scope.gridOptions.data = results;
        });
    };
    
    $scope.listLeveranciers();

     $scope.gridOptions.onRegisterApi = function(gridApi){
      //set gridApi on scope
      $scope.gridApi = gridApi;
      
      gridApi.selection.on.rowSelectionChanged($scope,function(row){
        var msg = 'row selected ' + row.isSelected;
        $scope.selectedRow= {};
        $scope.selectedRow = $scope.gridApi.selection.getSelectedRows();
        $log.log(msg);
        $scope.showModal();
        $log.log($scope.selectedRow[0].postcode);
      });
      
    

      gridApi.selection.on.rowSelectionChangedBatch($scope,function(rows){
        var msg = 'rows changed ' + rows.length;
        $log.log(msg);
      });
    };
    
    $scope.showModal = function() {

            ModalService.showModal({
              templateUrl: "modals/leverancierModal.html",
              controller: "leverancierModalCtrl",
              preClose: (modal) => { modal.element.modal('hide'); },
              inputs: {
                title: "Details leverancier",
                leverancier : $scope.selectedRow[0]
              }
            }).then(function(modal) {
              modal.element.modal();
              modal.close.then(function(result) {
                if (result) {        
                  $scope.listLeveranciers();
                  console.log('leverancier vanuit modal.close.then function');
                } 
              });
            });
    };
    
    $scope.saveNewLeverancier = function(){
        $scope.promiseLeverancier = LeverancierService.save({"leverancier": $scope.formData}).$promise.then(function(response){
             if(response){
                 $scope.response = response.data;
                 $scope.listLeveranciers();
                 init();
                 
             }
        });     
    };
    
}]);


app.controller('leverancierModalCtrl', ['$scope', '$element', 'title', 'leverancier', 'close', 'LeverancierService',
  function($scope, $element, title, leverancier, close, LeverancierService) {

        $scope.leverancierCopy = angular.copy(leverancier);
        $scope.title = title;

        $scope.opslaan = function(){
            
            //UPDATE LEVERANCIER HIER
            $scope.promiseLeverancier = LeverancierService.update({"leverancier": $scope.leverancierCopy}).$promise.then(function(response){
                $scope.response = response;
                console.log("http response: " + response);
            });
        };
        
        $scope.wissen = function(){
            //UPDATE LEVERANCIER HIER
            $scope.promiseLeverancier = LeverancierService.delete({"leverancierId": $scope.leverancierCopy.leverancierNr}).$promise.then(function(response){
                $scope.response = response;
                console.log("http response: " + response);
                $scope.close();
            });
        };
        
        //  This close function doesn't need to use jQuery or bootstrap, because
        //  the button has the 'data-dismiss' attribute.
        $scope.close = function() {
                close({
                    leverancier: $scope.leverancierCopy
      
          }, 500); // close, but give 500ms for bootstrap to animate
        };

        //  This cancel function must use the bootstrap, 'modal' function because
        //  the doesn't have the 'data-dismiss' attribute.
        $scope.cancel = function() {

          //  Manually hide the modal.
          $element.modal('hide');

          //  Now call close, returning control to the caller.
          close({
          
          }, 500); // close, but give 500ms for bootstrap to animate
        };

}]);


app.controller('welkomCtrl', ['$scope', function ($scope) {
   
}]);