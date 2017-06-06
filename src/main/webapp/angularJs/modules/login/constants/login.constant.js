(function(){
	
	angular.module('amoeba.login').constant("LOGIN_CONSTANTS",{
		"LOGIN_URL":"/amoeba/login",
		"SIGNUP_URL":"/amoeba/registration",
		"CHECK_EMAIL_AVAILABLE":"/amoeba/emailValidation?email=",
		"REGISTRATION_CONFIRMATION_URL":"/amoeba/registration/registrationConfirmation?token=",
		"FORGOT_PASSWORD_URL":"/amoeba/forgotPassword",
		"CONFIRMATION_INSTRUCTIONS_URL":"/amoeba/updateToken?email="
	});
	
})();