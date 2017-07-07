(function(){
	
	function consultantFactory($http,CONSULTANT_CONSTANTS,$q,$state){
		
		

		function getForms(){
			var defered=$q.defer();
			$http.get(CONSULTANT_CONSTANTS.GET_fORM).success(function(response){
				defered.resolve(response);
			}).error(function(error){
				defered.reject(error);
			});
			return defered.promise;
		};
		
		
		
		function getFormDetails(f_id){
			var defered=$q.defer();
			$http.get(CONSULTANT_CONSTANTS.GET_USERfORM+f_id).success(function(response){
				defered.resolve(response);
			}).error(function(error){
				defered.reject(error);
			});
			return defered.promise;
		}
		
		function acceptForm(f_id){
			var defered=$q.defer();
			$http.put(CONSULTANT_CONSTANTS.ACCEPT_FORM+f_id).success(function(response){
				defered.resolve(response);
			}).error(function(error){
				defered.reject(error);
			});
			return defered.promise;
		
		}
		
		
		return {
			acceptForm:acceptForm,
			getForms:getForms,
			getFormDetails:getFormDetails
		};
	};
	
	consultantFactory.$inject=['$http','CONSULTANT_CONSTANTS','$q','$state'];
	
	angular.module('amoeba.consultant').factory('consultantFactory',consultantFactory);
	
})();



