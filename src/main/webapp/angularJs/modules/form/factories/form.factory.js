(function(){
	
	function formFactory($q,$http,FORM_CONSTANTS){
		
		function formSubmit(formDetails){
			var defered=$q.defer();
			var body =  {"title" : formDetails.title,"reportDescription" : formDetails.reportDescription,"age": formDetails.age,"height":formDetails.height,"status":formDetails.status,"spec_Id":formDetails.spec_Id,"weight":formDetails.weight,"mainCat_Id":formDetails.mainCat_Id,"subSpec_Id":formDetails.subSpec_Id};
			$http.post(FORM_CONSTANTS.SUBMIT_FORM_URL,body).success(function(response) {
				defered.resolve(response);
			}).error(function(error) {
				defered.reject(error);
			});
			return defered.promise;
		};
		
		
		return {
			formSubmit:formSubmit
		};
	};
	
	formFactory.$inject=['$q','$http','FORM_CONSTANTS'];
	
	angular.module('amoeba.form').factory('formFactory',formFactory);
	
})();



