(function(){

    var app = angular.module('twitter-controller', ['twitter-service', 'filter-groups-service', 'ui.bootstrap']);

    app.controller('TwitterController', function($scope, TwitterService, FilterGroupsService){

        $scope.showNoResultsMessage = false;
        $scope.saveFilterGroupErrorMessageOnEmptyDescription = "Filter group description required";
        $scope.saveFilterGroupErrorMessageOnEmptyFilterGroup = "No filter configured for the group";

        $scope.showErrorMessage = false;

        $scope.tweets = [];
        $scope.filters = [
                          'Hashtags', // - 0
                          'References', // - 1
                          'Words',
                           'Start Date',
                           'End Date'// - 2
                          ];

        $scope.username = "johndoe";
        

        // initialize search form
        $scope.searchForm = {'username':'','filters':[]};
        // initialize new preference form
        $scope.saveFilterGroupForm = {'username':'','filters':[], 'filterGroupDescription': ''};

        for (var index=0; index<$scope.filters.length; index++)
            $scope.searchForm.filters.push('');
			
		for (var index=0; index<$scope.filters.length; index++){
            $scope.saveFilterGroupForm.filters.push('');
		}

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

        $scope.openSaveFilterGroupDialog = function(){
            $( "#saveFilterGroupDialog" ).dialog({modal:true});
        }

        $scope.saveFilterGroup = function(){
            if (!$scope.saveFilterGroupForm.filterGroupDescription) {
                $("#inputFilterGroupDescription").addClass('alert');
                $scope.showErrorMessage = true;
                $scope.saveFilterGroupErrorMessage = $scope.saveFilterGroupErrorMessageOnEmptyDescription;
                return;
            }else{
                var countFilterGroupSize = 0;
                for (var index=0; index<$scope.filters.length; index++){
                    $scope.saveFilterGroupForm.filters[index] = $scope.searchForm.filters[index];
                    if ($scope.saveFilterGroupForm.filters[index])
                        countFilterGroupSize++;
                }
                if (countFilterGroupSize == 0){
					$scope.showErrorMessage = true;
                    $scope.saveFilterGroupErrorMessage = $scope.saveFilterGroupErrorMessageOnEmptyFilterGroup;
                    return;
                }

                $scope.saveFilterGroupForm.user = $scope.username;
                $scope.saveFilterGroupForm.username = $scope.searchForm.username;
                FilterGroupsService.saveFilterGroupPreference($scope.saveFilterGroupForm)
                    .success(function(){
                         $scope.getSavedFilterGroups();
                         $( "#saveFilterGroupDialog" ).dialog('close');
                    })
                    .error(function(){
                        console.log("error saving filter group");
                    });

            }

        }

        $scope.deleteFilterGroup = function(filterGroupId) {
            FilterGroupsService.deleteFilterGroup(filterGroupId.id)
                .success(function(){
                    $scope.getSavedFilterGroups();
                })
        }

        $scope.getSavedFilterGroups = function(){
            FilterGroupsService.getFilterGroupsForUser($scope.username)
                                                .success(function(data){
                                                    $scope.userSavedFilterGroups = data;
                                                });
        }
		
		$scope.getSavedFilterGroups();
		
		$scope.userHasSavedFilters = function(){
			if ($scope.userSavedFilterGroups == null || $scope.userSavedFilterGroups.length == 0)
				return false;
			return true;
		}

		$scope.searchTweetsForFilterGroup = function(filterGroup){
		    TwitterService.getTweetsByFilterGroup(filterGroup.id)
		        .success(function(data){
		            $scope.tweets = data;
		        })
		}

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