(function(){
	
	function myFormFactory($http,FORM_CONSTANTS,$q){
		
		

		function getForm(){
			var defered=$q.defer();
			$http.get(FORM_CONSTANTS.GET_FORMS).success(function(response){
				defered.resolve(response);
				
			}).error(function(error){
				defered.reject(error);
			});
			return defered.promise;
		};
		
		
		return {
			getForm:getForm
		};
	};
	
	myFormFactory.$inject=['$http','FORM_CONSTANTS','$q'];
	
	angular.module('amoeba.form').factory('myFormFactory',myFormFactory);
	
})();



