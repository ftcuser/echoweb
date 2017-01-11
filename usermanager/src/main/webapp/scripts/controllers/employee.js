var controllers = {};

controllers.homeController = function($scope){
	$scope.message = "Hello,  This is the home page!";
	$scope.modal = {
		title: 'Title', 
		content: 'Hello Modal<br />This is a multiline message!'
	};
	$scope.selectedIcon = "";
	$scope.selectedIcons = ["Globe","Heart"];
	$scope.icons = [{"value":"Gear","label":"<i class=\"fa fa-gear\"></i> Gear"},{"value":"Globe","label":"<i class=\"fa fa-globe\"></i> Globe"},{"value":"Heart","label":"<i class=\"fa fa-heart\"></i> Heart"},{"value":"Camera","label":"<i class=\"fa fa-camera\"></i> Camera"}];
};

controllers.employeeController = function($scope, $http, UserService) {
	
	  $scope.editMode = false;
	  $scope.actionLabel = "Add";
	  
	  $http.get("servlet/listusers")
		.then(function(response){
			$scope.users = response.data;
		});
	 
	  	//bind JavaScript function
	  	$scope.editUser = function(email) {
			console.log(email);
			$scope.actionLabel = "Update";
			$scope.user = UserService.getUserByEmail(email, $scope.users);
			$scope.editMode = true;
		};
		
		$scope.addUser = function() {
			$scope.user = {};
			$scope.user.firstName = '';
			$scope.user.lastName = '';
			$scope.user.email = '';
			$scope.editMode = true;
			$scope.actionLabel = "Add";
		};
		
		$scope.updateUser = function() {
			 $scope.editMode = false;
			 $http.post("servlet/adduser", UserService.getUserDataObj($scope.user))
			 	.then(function(success){
			 		$scope.users = success.data;
			 	});
		};
		
		$scope.deleteUser = function(email) {
			var user = UserService.getUserByEmail(email, $scope.users);			
			$http.post("servlet/deleteuser", UserService.getUserDataObj(user))
				.then(function(success){
					$scope.users = success.data;
				});
			$scope.editMode = false;
		};
};

controllers.gridsController = function($scope) {

};
controllers.formController = function($scope) {

};

myApp.controller(controllers);