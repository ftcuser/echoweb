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

controllers.employeeController = function($scope, $http) {
	
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
			for (i = 0; i < $scope.users.length; i++) {
			   if($scope.users[i].email == email){
				   $scope.user = $scope.users[i];
				   $scope.editMode = true;
				   break;
			   }
			}
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
			 var data = $.param({
		        json: JSON.stringify($scope.user)
		      });
			 var dataObj = {
						firstName : $scope.user.firstName,
						lastName : $scope.user.lastName,
						email : $scope.user.email
				};	
			 	console.log(dataObj);
			   $http.post("servlet/adduser", dataObj).success(function(data, status) {
		            $scope.users = data;
		       })
		};
		
		$scope.deleteUser = function(email) {
			for (i = 0; i < $scope.users.length; i++) {
				   if($scope.users[i].email == email){					   
					  
					    var dataObj = {
						   firstName : $scope.users[i].firstName,
						   lastName : $scope.users[i].lastName,
						   email : $scope.users[i].email
					   };	
					   console.log(dataObj);
					   $http.post("servlet/deleteuser", dataObj).success(function(data, status) {
						   $scope.users = data;
					   })
					  
					   break;
				   }
				}
			
			$scope.editMode = false;
		};
};

controllers.gridsController = function($scope) {

};
controllers.formController = function($scope) {

};

myApp.controller(controllers);