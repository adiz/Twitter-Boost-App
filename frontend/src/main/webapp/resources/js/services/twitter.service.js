(function(){

    var app = angular.module('twitter-service', [])
                     .factory('TwitterService', function($http){

        return{

            getAllTweets: function(){
                return $http.get(ROOT_CONTEXT + '/twitter/all');
            },

            getSearchedTweets: function(searchForm){
                return $http.post(ROOT_CONTEXT + '/twitter/searchByFormFilters', searchForm);
            },

            getTweetsByFilterGroup: function(filterGroupId){
                            return $http.post(ROOT_CONTEXT + '/twitter/searchByFilterGroup/' + filterGroupId);
                        },


        }

    });

})();