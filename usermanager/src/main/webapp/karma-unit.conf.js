module.exports = function(config){
	config.set({
		frameworks: ['jasmine'],
		browsers: ['Firefox'],
		files:[
		       'lib/angular.min.js',
		       'lib/angular-route.min.js',
		       'lib/angular-mocks.js',
		       'scripts/app.js',
		       'scripts/controllers/employee.js',
		       'spec/unit/*.js'
		       ]
	});
}