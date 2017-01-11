module.exports = function(config){
	config.set({
		frameworks: ['jasmine'],
		browsers: ['Firefox'],
		files:[
			   'https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.1/angular.js',
			   'https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.1/angular-route.js',
			    'https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.1/angular-mocks.js',
		       'scripts/app.js',
		       'scripts/services/userwebservice.js',
		       'scripts/controllers/employee.js',
		       'spec/unit/*.js'
		       ]
	});
}