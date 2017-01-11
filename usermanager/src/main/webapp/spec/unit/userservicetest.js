describe('User Service Unit Test', function(){

	var users = [
	             {
					"email" : "joe1@g.com",
					"firstName" : "joe1",
					"lastName" : "doe1",
				 },
	             {
					"email" : "joe2@g.com",
					"firstName" : "joe2",
					"lastName" : "doe2",
				 },
	             {
					"email" : "joe3@g.com",
					"firstName" : "joe3",
					"lastName" : "doe3",
				 }					 
				];
	var email = 'joe1@g.com';
	
	it('Test Get User Function', function(){		
		var $injector = angular.injector([ 'EchoWebAppModule' ]);
        var UserService = $injector.get( 'UserService' );
		var guser = UserService.getuserByEmail(email, users);
		
		expect(guser.firstName).toBe('joe1');
	});
	
});