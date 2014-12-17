(function(){

    var app = angular.module('twitter-controller', ['twitter-service']);

    app.controller('TwitterController', function($scope, TwitterService){

        $scope.showNoResultsMessage = false;
        $scope.tweets = [];
        $scope.filters = [
                          'Hashtags', // - 0
                          'References', // - 1
                          'Words' // - 2
                          ];

        // initialize search form
        $scope.searchForm = {'username':'','filters':[]};
        for (var index=0; index<$scope.filters.length; index++)
            $scope.searchForm.filters.push('');

        // show no filter at page load
        $scope.filterShows = [];
        for (var index=0; index<$scope.filters.length; index++){
            $scope.filterShows.push(false);
        }

        $scope.testUser = function(){
            TwitterService.getAllTweets().success(function(data){
                $scope.tweets = data;
            });

        };

        $scope.addFilterInput = function(filterIndex){
            if ($scope.filterShows[filterIndex])
                // reset input field on filter removal
                $scope.searchForm.filters[filterIndex] = '';
            $scope.filterShows[filterIndex] = !$scope.filterShows[filterIndex];
//            window.scrollTo(0,document.body.scrollHeight);
        };

        $scope.searchTweets = function(){
            TwitterService.getSearchedTweets($scope.searchForm).success(function(data){
                $scope.tweets = data;
                if ($scope.tweets.length==0){
                    $scope.showNoResultsMessage = true;
                }
                else
                    $scope.showNoResultsMessage = false;
            });
        };

    });

})();