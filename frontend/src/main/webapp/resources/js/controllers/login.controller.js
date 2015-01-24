(function(){

    var app = angular.module('login-controller', ['login-service','ngCookies']);

    app.controller('LoginController', function($scope, $rootScope, $window, $cookies, LoginService){

        $scope.loginError = undefined;
        $scope.registerError = undefined;
        $scope.registerSuccess = undefined;

        $scope.showLoginForm = true;

        $scope.user = {'username':'','password':''};

        $scope.loginUser = function(){
            LoginService.getAppUser($scope.user)
                    .success(function(data){
                        $cookies.currentUsername = $scope.user.username;
                        $cookies.currentRole = $scope.user.role;
                        $window.location.replace(ROOT_CONTEXT);
                    })
                    .error(function(data){
                        $scope.loginError = data;
                    });

        };

        $scope.registerUser = function(){
            LoginService.registerAppUser($scope.user)
                    .success(function(data){
                        $scope.registerError = undefined;
                        $scope.registerSuccess = 'Successfully registered!';
                    })
                    .error(function(data){
                        if (data.indexOf('Error: User already exists!')>0)
                            $scope.registerError = 'Error: User already exists!';
                        else
                            $scope.registerError = 'User could not be created! Please retry.';
                    });

        };

        $scope.setLoginView = function(){
            $scope.showLoginForm = true;
            $scope.loginError = undefined;
            $scope.user = {'username':'','password':''};
        };

        $scope.setRegisterView = function(){
            $scope.showLoginForm = false;
            $scope.registerError = undefined;
            $scope.registerSuccess = undefined;
            $scope.user = {'username':'','password':''};
        };

    });

})();