myApp.controller("UserController",function($scope,$http,$location,$rootScope,$cookieStore){
    
	$scope.user={"name":'',"password":'',"email":'',"address":'',"phone":'',"role":'',"isOnline":''};
	
	   $scope.register=function(){
		   console.log("Registration function");
		   $http.post('',$scope.user)
		   .then(function(response){
			   console.log('Status text:' +response.statusText);
			   alert('Registe successfully...!!');
		 });
 };
     $rootScope.login=function(){
    	 console.log("Logging Function");
    	 $http.post("",$scope.user)
    	 .then(function(response){
    	       console.log(response.status);
    	       $scope.user=response.data;
    	       $rootScope.currentUser=response.data;
    	       $cookieStore.put('userDetails',response.data);
    	       console.log($rootScope.currentUser.role);
    	       if($rootScope.currentUser.role=='Admin'){
    	    	   console.log("Admin");
    	       }
    	       if($rootScope.currentUser.role=='User'){
    	    	   console.log("User");
    	       }
    		 $location.path("/");
    	 });
     };
    	 
     $rootScope.logout=function(){
    	 console.log('Logout function');
    	 delete $rootScope.currentUser;
    	 $cookiesStore.remove('userDetails');
    	 $location.path("/");
     }
 
});