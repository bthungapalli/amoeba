(function(){
	
	function myFormFactory($http,FORM_CONSTANTS,$q,$state){
		
		

		function getForms(userId){
			var defered=$q.defer();
			$http.get(FORM_CONSTANTS.GET_MY_FORMS+userId).success(function(response){
				defered.resolve(response);
			}).error(function(error){
				defered.reject(error);
			});
			return defered.promise;
		};
		
		
		
		function getFormDetails(f_id){
			var defered=$q.defer();
			$http.get(FORM_CONSTANTS.GET_fORM+f_id).success(function(response){
				defered.resolve(response);
			}).error(function(error){
				defered.reject(error);
			});
			return defered.promise;
		}
		return {
			getForms:getForms,
			getFormDetails:getFormDetails
		};
	};
	
	myFormFactory.$inject=['$http','FORM_CONSTANTS','$q','$state'];
	
	angular.module('amoeba.form').factory('myFormFactory',myFormFactory);
	
})();



