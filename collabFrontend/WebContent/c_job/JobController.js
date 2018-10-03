myApp.controller("JobController",function($scope,$http,$location,$rootScope)
		{
	$scope.job={jobdesignation:'',jobDesc:'',location:'',salary:'',company:'',lastDateApply:''}
	$scope.jobData;
	$scope.insertJob =function(){
		console.log('inside insert job');
		$http.post('http://localhost:8081/FriendsAddaMiddleware/addJob',$scope.job)
		.then(fetchAllBlog(), function(response) {
			//$location.reload();
			console.log('Status text:' + response.statusText);
		
	});
	
		};
		function fetchAllJobs()
		{
			console.log('Fetch All Jobs');
			$http.get("http://localhost:8081/FriendsAddaMiddleware/listJob")
			.then(function(response)
			{
				$scope.jobData=response.data;
			});
		};
		
		$scope.editJob = function(jobId) {
			$http.get('http://localhost:8081/FriendsAddaMiddleware/getJob/' + jobId)
					.then(function(response) {
						console.log(response.status);
						$scope.blog = response.data;
						$location.path('/updateJob');
						console.log('Status Text' + response.statusText);
						
					});
		};

		$scope.updateJob = function(jobId){
			alert("in update job");
			$http.put('http://localhost:8081/FriendsAddaMiddleware/updateBlog/'+ blogId, $scope.blog)
			.then(fetchAllBlogs(), function(response){
				console.log('updated job'+ jobId+ ' successfully');
				 $location.path('/updatejob');
				$window.alert(jobId +" updated successfully");
			// $location.reload();
			});
			
		};
		$scope.deleteJob = function(jobId){
			 alert("in delete job");
			$http.delete('http://localhost:8081/FriendsAddaMiddleware/deleteJob/'+jobId)
			.then(fetchAllBlogs(), function(response){
				console.log('job deleted '+ jobId);
				console.log('Response Status ' + response.statusText);
			 $location.reload();
			});
		}
		
		});
		