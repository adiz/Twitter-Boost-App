(function(){

    var app = angular.module('appuser-service', [])
                     .factory('AppUserService', function($http){

        return{

            getRandomAppUser: function(){
                return $http.get(ROOT_CONTEXT + '/users/test');
            }

        }

    });

})();