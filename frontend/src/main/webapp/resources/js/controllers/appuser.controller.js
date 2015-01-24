(function(){

    var app = angular.module('appuser-controller', ['appuser-service','ngCookies']);

    app.controller('AppUserController', function($scope, $rootScope, $window, $cookies, AppUserService){

        $scope.user = {'username':$cookies.currentUsername, 'password':$cookies.currentRole};

        $scope.logout = function(){
            AppUserService.logoutUser().success(function(data){
                $window.location.replace(ROOT_CONTEXT + '/login');
            });
        }

    });

})();