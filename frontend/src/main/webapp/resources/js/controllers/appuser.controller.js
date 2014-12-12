(function(){

    var app = angular.module('appuser-controller', ['appuser-service']);

    app.controller('AppUserController', function($scope, AppUserService){

        $scope.showLoginForm = true;

        $scope.testUser = function(){
            AppUserService.getRandomAppUser().success(function(data){
                // for testing purposes
                console.log(data);
                $scope.showLoginForm = false;
            });

        };

    });

})();