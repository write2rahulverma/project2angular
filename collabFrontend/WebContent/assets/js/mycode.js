var myApp = angular.module("myApp", [ 'ngRoute', , 'ngCookies' ]);

myApp.config(function($routeProvider) {

	console.log = ('I am in templateUrl');
	$routeProvider.when("/", {
		templateUrl : "home.html"
	}).when("/contact", {
		templateUrl : "conatct.html"
	}).when("/about", {
		templateUrl : "about.html"
	}).when("/login", {
		templateUrl : "c_user/login.html"
	}).when("/register", {
		templateUrl : "c_user/register.html"
	}).when("/blog", {
		templateUrl : "c_blog/blog.html"
	}).when("/blog", {
		templateUrl : "c_blog/addBlog.html"
	}).when("/blog", {
		templateUrl : "c_blog/updateBlog.html"
	}).when("/forum", {
		templateUrl : "c_forum/forum.html"
	}).when("/forum", {
		templateUrl : "c_forum/addForum.html"
	}).when("/forum", {
		templateUrl : "c_forum/updateForum.html"
	}).when("/job", {
		templateUrl : "c_job/job.html"
	})

});

myApp.run9function($rootScope, $cookieStore)
{
	console.log('In run Function');
	console.log($rootScope.currentUser);
	if ($rootScope.currentUser == undefined) {
		$rootScope.currentUser = $cookieStore.get('userDetails');
	} else {
		console.log($rootScope.currentUser.userName);
		console.log($rootScope.currentUser.role);
	}
}

