(function(){
	
	function profileFactory($q,PROFILE_CONSTANTS){
		
		function updateProfile(profileDetails){
			var defered=$q.defer();
			 var payload = new FormData();
			 payload.append('firstName', profileDetails.firstName);
			 payload.append('lastName', profileDetails.lastName);
			 payload.append('phone', profileDetails.number);
			 payload.append('Address', profileDetails.address);
			 payload.append('Gender', profileDetails.gender);
			 if(profileDetails.role===1){
				 payload.append('email', profileDetails.email);
			 }
			 
			 if(profileDetails.profileImage!==null){
				 payload.append('profileImage', profileDetails.profileImage);
			 }
				 
            
			 $.ajax({
					type : 'POST',
					url : PROFILE_CONSTANTS.PROFILE_UPDATE_URL,
					data : payload,
					contentType : false,
					processData : false,
					success : function(response) {
						 defered.resolve(response);
					},
					error : function(xhr, status) {
						 defered.reject("error");
					}
		
				});
			return defered.promise;
		};
		
		
		return {
			updateProfile:updateProfile
		};
	};
	
	profileFactory.$inject=['$q','PROFILE_CONSTANTS'];
	
	angular.module('amoeba.profile').factory('profileFactory',profileFactory);
	
})();



