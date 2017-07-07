(function(){
	
	function myActiveFormFactory($http,FORM_CONSTANTS,$q,$state){
		
		

		function getMyForms(){
			var defered=$q.defer();
			$http.get(FORM_CONSTANTS.GET_ACTIVE_fORM).success(function(response){
				defered.resolve(response);
			}).error(function(error){
				defered.reject(error);
			});
			return defered.promise;
		};
		
		
		return {
			getMyForms:getMyForms
		};
	};
	
	myActiveFormFactory.$inject=['$http','FORM_CONSTANTS','$q','$state'];
	
	angular.module('amoeba.form').factory('myActiveFormFactory',myActiveFormFactory);
	
})();



