(function(){

    var app = angular.module('filter-groups-service', [])
                     .factory('FilterGroupsService', function($http){

        return{ saveFilterGroupPreference: function(saveFilterGroupForm){
                return $http.post(ROOT_CONTEXT + '/filterGroups/saveFilterGroup', saveFilterGroupForm);
            }, getFilterGroupsForUser: function(userName){
                return $http.post(ROOT_CONTEXT + '/filterGroups/getAllForUser/' + userName );
            }, deleteFilterGroup: function(filterGroupId){
                return $http.post(ROOT_CONTEXT + '/filterGroups/delete/' + filterGroupId );
            }

        }

    });

})();