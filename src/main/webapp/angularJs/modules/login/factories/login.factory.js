(function(){
	
	function loginFactory($q,$http,LOGIN_CONSTANTS){
		
		function checkEmailAvailable(email){
			var defered=$q.defer();
			$http.get(LOGIN_CONSTANTS.CHECK_EMAIL_AVAILABLE+email).success(function(response) {
				defered.resolve(response);
			}).error(function(error) {
				defered.reject(error);
			});
			return defered.promise;
		};
		
		function submitLogin(loginDetails){
			var defered=$q.defer();
			var headers =  {authorization : "Basic "+ btoa(loginDetails.emailId + ":" + loginDetails.password)};
			$http.get(LOGIN_CONSTANTS.LOGIN_URL, {headers : headers}).success(function(response) {
				defered.resolve(response);
			}).error(function(error) {
				defered.reject(error);
			});
			return defered.promise;
		};
		
		
		function signup(loginDetails){
			var defered=$q.defer();
			var body =  {"email" : loginDetails.emailId,"password": loginDetails.password,"role":loginDetails.role,"firstName":loginDetails.firstName,"lastName":loginDetails.lastName,"number":loginDetails.phone,"gender":loginDetails.gender,"address":loginDetails.address};
			$http.post(LOGIN_CONSTANTS.SIGNUP_URL,body).success(function(response) {
				defered.resolve(response);
			}).error(function(error) {
				defered.reject(error);
			});
			return defered.promise;
		};
		
		function registrationConfirmation(token){
			var defered=$q.defer();
			$http.get(LOGIN_CONSTANTS.REGISTRATION_CONFIRMATION_URL+token).success(function(response) {
				defered.resolve(response);
			}).error(function(error) {
				defered.reject(error);
			});
			return defered.promise;
		};
		
		function forgotPassword(loginDetails){
			var defered=$q.defer();
			var body =  {"email" : loginDetails.emailId};
			$http.post(LOGIN_CONSTANTS.FORGOT_PASSWORD_URL,body).success(function(response) {
				defered.resolve(response);
			}).error(function(error) {
				defered.reject(error);
			});
			return defered.promise;
		};
		
		function confirmationInstructions(loginDetails){
			var defered=$q.defer();
			$http.post(LOGIN_CONSTANTS.CONFIRMATION_INSTRUCTIONS_URL+loginDetails.email).success(function(response) {
				defered.resolve(response);
			}).error(function(error) {
				defered.reject(error);
			});
			return defered.promise;
		};
		
		return {
			checkEmailAvailable:checkEmailAvailable,
			submitLogin:submitLogin,
			signup:signup,
			registrationConfirmation:registrationConfirmation,
			forgotPassword:forgotPassword,
			confirmationInstructions:confirmationInstructions
		};
	};
	
	loginFactory.$inject=['$q','$http','LOGIN_CONSTANTS'];
	
	angular.module('amoeba.login').factory('loginFactory',loginFactory);
	
})();



