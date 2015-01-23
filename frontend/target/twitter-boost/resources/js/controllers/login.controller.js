(function(){

    var app = angular.module('login-controller', ['login-service','ngCookies']);

    app.controller('LoginController', function($scope, $rootScope, $window, $cookies, LoginService){

        $scope.error = undefined;

        $scope.loginUser = function(){
            LoginService.getAppUser($scope.user)
                    .success(function(data){
                        $cookies.currentUsername = $scope.user.username;
                        $cookies.currentRole = $scope.user.role;
                        $window.location.replace(ROOT_CONTEXT);
                    })
                    .error(function(data){
                        $scope.error = data;
                    });

        };

    });

})();