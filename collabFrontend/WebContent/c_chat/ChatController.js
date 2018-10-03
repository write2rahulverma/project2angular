myApp.controller('ChatController', function($scope, $rootScope, chatService) {
	
	$scope.message=[];
	$scope.message="";
	$scope.max=250;

	$scope.addMessage=function()
	{
		console.log=('Add Message Called');
		chatService.send($rootScope.currentUser.name+":" +$scope.message);
		$scope.message="";
	};
	chatService.receive.then(null,null,function(message)
	{
		console.log("Add Message Called");
		$scope.message.push(message);
	});		
	
});

