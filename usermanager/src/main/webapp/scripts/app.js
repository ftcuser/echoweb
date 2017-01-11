var myApp = angular.module("EchoWebAppModule", ["ngRoute", "ngAnimate", "ngSanitize", "mgcrea.ngStrap", "datatables"]);

myApp.config(['$routeProvider', function($routeProvider){	
	$routeProvider
		.when("/home", {
			templateUrl: "views/home.html",
			controller: "homeController"
		})
		.when("/employee", {
			templateUrl: "views/employee.html",
			controller: "employeeController"
		})	
		.when("/grids", {
			templateUrl: "views/grids.html",
			controller: "gridsController"
		})
		.when("/form", {
			templateUrl: "views/form.html",
			controller: "formController"
		})
		.otherwise ({
			redirectTo: "/employee"
		});
}]);

