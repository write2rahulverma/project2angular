myApp.controller("ForumController", function($scope, $http, $location,$rootScope, $window) {
	$scope.forum = {"forumId":0,"forumName" : '',"forumContent" : '',"createdDate" : '',"userName" : '',"status" : ''}
	$rootScope.forum = {"forumId":0,"forumName" : '',"forumContent" : '',"createdDate" : '',"userName" : '',"status" : ''}
	$scope.forumData;

	$scope.insertForum = function() {
		console.log('Entered into the insertForum method');
		$scope.forum.userName=$rootScope.currentUser.name;
		$http.post("http://localhost:8081/FriendsAddaMiddleware/addForum",$scope.forum)
				.then(fetchAllForums(), function(response) {
						$scope.forumData=response.Data;
						console.log('Status text:' + response.statusText);
						 $window.alert("Data inserted successfully");
				});
	};
	function fetchAllForums() {
		console.log('Into Fetch All Forums');
		$http.get("http://localhost:8081/FriendsAddaMiddleware/listForums").then(
				function(response) {
					console.log('Status text:' + response.statusText);
					$scope.forumData = response.data;
					console.log(response.data);
				});
	};
	$scope.editForum = function(forumId) {
		console.log('In editForum method');
		$http.get('http://localhost:8081/FriendsAddaMiddleware/getForum/' + forumId)
				.then( function(response) {
					console.log('In edit forum');
					console.log(response.data);
					$scope.forum = response.data;
					console.log('Status Text' + response.statusText);
					$location.path('/updateForum');
				});
	};

	$scope.updateForum = function(forumId){
		console.log('Entered into the updateForum method');
		console.log(forumId);
		$http.put('http://localhost:8081/FriendsAddaMiddleware/updateForum/'+ forumId, $scope.forum)
		.then(fetchAllForums(), function(response){
			console.log('updated forum'+ forumId+ ' successfully');
			console.log(forumId +" updated successfully");
			 $location.path('/updateForum');
			$window.alert('Forum updated successfully...');
			
		});
		
	};
	$scope.deleteForum = function(forumId){
		console.log('Entered into the deleteForum method');
		$http.delete('http://localhost:8081/FriendsAddaMiddleware/deleteForum/'+forumId)
		.then(fetchAllForums(), function(response){
			console.log('Forum deleted '+ forumId);
			console.log('Response Status ' + response.statusText);
			fetchAllForums();
			$window.alert('Forum deleted successfully..');
		});
	};

	fetchAllForums();
});