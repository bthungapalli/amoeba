(function(){
	
	function reportFactory($http,CONSULTANT_CONSTANTS,$q,$state){
		
		function getFormDetails(f_id){
			var defered=$q.defer();
			$http.get(CONSULTANT_CONSTANTS.GET_USERfORM+f_id).success(function(response){
				defered.resolve(response);
			}).error(function(error){
				defered.reject(error);
			});
			return defered.promise;
		}
		
	
		
		return {
			getFormDetails:getFormDetails
		};
	};
	
	reportFactory.$inject=['$http','CONSULTANT_CONSTANTS','$q','$state'];
	
	angular.module('amoeba.consultant').factory('reportFactory',reportFactory);
	
})();



