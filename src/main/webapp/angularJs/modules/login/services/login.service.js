(function(){
	
	function loginService(){
		
		this.getRoles=function(){
			var roles={
					"User":0,
					"Consultant":1
			};
			return roles;
		};
		
		this.getGender=function(){
			var gender={
					"male":"male",
					"feMale":"feMale"
			};
			return gender;
		};
		
	};
	
	loginService.$inject=[];
	
	angular.module('amoeba.login').service('loginService',loginService);
	
})();



