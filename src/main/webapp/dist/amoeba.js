(function(){
	
	var appModule=angular.module('amoeba',['ui.bootstrap','ngRoute','ui.router','angular-input-stars','angularUtils.directives.dirPagination','ngCookies','darthwade.dwLoading','amoeba.login','amoeba.main','amoeba.profile','amoeba.form']);

	angular.element(document).ready(function() {
	    angular.bootstrap("body", ['amoeba']);
	 });
	
	appModule.config(function($stateProvider, $urlRouterProvider,$httpProvider){
		
		$httpProvider.interceptors.push([function(){
		    return {
		        request: function(config){
		            if(config.url.indexOf('partials/') > -1 || config.url.indexOf('dist/amoeba.js') > -1){
		                var separator = config.url.indexOf('?') === -1 ? '?' : '&';
		                config.url = config.url + separator + 'c=' + new Date();
		            }

		            return config;
		        }
		    };
		}]);
		
		  $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest'; 
	    $stateProvider.state('login', {
            controller:'loginController',
            templateUrl: 'partials/login/login.html'
        }).state('login.loginTemplate', {
        	 url: '/login',
            templateUrl: 'partials/login/loginTemplate.html'
        }).state('login.signupTemplate', {
        	url: '/signup',
            templateUrl: 'partials/login/signupTemplate.html'
        }).state('login.adminLoginTemplate', {
        	url:'/adminlogin',
        	templateUrl:'partials/login/adminLoginTemplate.html'
        }).state('login.forgotPassword', {
        	url: '/forgotPassword',
            templateUrl: 'partials/login/forgotPassword.html'
        }).state('main', {
            url: '/main',
            templateUrl: 'partials/main/main.html'
        }).state('main.profile', {
	            url: '/profile',
	            controller:'profileController',
	            templateUrl: 'partials/profile/profile.html'
	    }).state('main.form', {
            url: '/form',
            controller:'formController',
            templateUrl: 'partials/submitReports.html'
        }).state('main.submitedForms', {
            url: '/submitedForms',
            controller:'myFormController',
            templateUrl: 'partials/mySubmissions.html'
        }).state('main.myJobs', {
            url: '/myJobs',
            controller:'myJobsController',
            templateUrl: 'partials/myJobs.html'
        }).state('main.allUsers', {
            url: '/allUsers',
            controller:'usersController',
            templateUrl: 'partials/allUsers.html'
        }).state('main.mySubmissions', {
            url: '/mySubmissions',
            controller:'mySubmissionsController',
            templateUrl: 'partials/mySubmissions.html'
        }).state('main.viewResume', {
            url: '/viewResume',
            controller:"viewResumeController",
            templateUrl: 'partials/viewResume.html'
        }).state('main.newUser', {
            url: '/newUser',
            controller:'newUserController',
            templateUrl: 'partials/newUser.html'
        }).state('main.viewSubmission', {
            url: '/viewSubmission',
            controller:'viewSubmissionController',
            templateUrl: 'partials/viewSubmission.html'
        }).state('main.newTemplate', {
            url: '/newTemplate',
            controller:'newTemplateController',
            templateUrl: 'partials/newTemplate.html'
        }).state('main.showTemplate', {
            url: '/showTemplate',
            controller:'showTemplateController',
            templateUrl: 'partials/showTemplate.html'
        }).state('main.editTemplate', {
            url: '/editTemplate',
            controller:'editTemplateController',
            templateUrl: 'partials/editTemplate.html'
        }).state('main.postJob', {
            url: '/postJob',
            controller:'postJobController',
            templateUrl: 'partials/postJob.html'
        }).state('registrationConfirmation', {
            url: '/registrationConfirmation',
            controller:'registrationConfirmationController',
            templateUrl: 'partials/registrationConfirmation.html'
        }).state('main.applyJob', {
            url: '/applyJob',
            controller:'applyJobController',
            templateUrl: 'partials/applyJob.html'
        }).state('main.changePassword', {
            url: '/changePassword',
            controller:'changePasswordController',
            templateUrl: 'partials/changePassword.html'
        }).state('login.confirmationInstructions', {
            url: '/confirmationInstructions',
            templateUrl: 'partials/login/confirmationInstructions.html'
        });
	    
	    $urlRouterProvider.otherwise('/');
});

		appModule.run(function($state){
			$state.go("login");
		});

	
	
})();


(function(){
	
	angular.module('amoeba.login',[]);
})();

(function(){
	
	angular.module('amoeba.main',[]);
})();

(function(){
	
	angular.module('amoeba.profile',[]);
})();

(function(){
	
	angular.module('amoeba.form',[]);
})();

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

(function(){
	
	function loginController($rootScope,$scope,$state,loginService,loginFactory,$cookies,$loading){
		
		$state.go("login.loginTemplate");
		$scope.rememberMe=false;
		
		$scope.assignState=function(state){
			$rootScope.activeState=state;
		};
		
		$scope.rememberMe1=function(){
			$scope.rememberMe=!$scope.rememberMe;
		};
		
		$scope.assignState('login.loginTemplate');
		
		$scope.resetUserDetails=function() {
			$scope.userDetails = {
				"firstName":"",
				"lastName":"",
				"phone":"",
				"emailId" : "",
				"password" : "",
				"confirmPassword" : "",
				"role" : 0,
				"gender":"",
				"address":""
			};
		};
		
		$scope.resetMessages=function() {
			
			$scope.loginMessageDetails = {
				"errorMessage" : {
					"login" : "",
					"signup_emailId" : "",
					"signup_confirmPassword":""
				},
				"successMessage" : {
					"login" : "",
					"signup_emailId" : "",
					"signup_confirmPassword":""
				}
			};
		};
		
     $scope.checkForRememberMe=function() {
    	 var emailId=$cookies.get("emailId");
 		if(emailId!==undefined){
 			$scope.userDetails.emailId=emailId;
 			$scope.rememberMe=true;
 		}
		
		};
		 
		$scope.resetUserDetails();
		$scope.resetMessages();
		$scope.checkForRememberMe();
		
		$scope.roles=loginService.getRoles();
		$scope.gender=loginService.getGender();
		
		
		$scope.checkemailIdAvailable=function(){
			$scope.loginMessageDetails.errorMessage.signup_emailId="";
			if($scope.userDetails.emailId!==""){
				loginFactory.checkemailIdAvailable($scope.userDetails.emailId).then(function(response){
					if(response[0]==='alreadyExist'){
						$scope.loginMessageDetails.errorMessage.signup_emailId="emailId Id already exist.";
					}else{
						$scope.resetMessages();
					}
				}).catch(function(error){
					
	            });
			}
			
		};
		
		$scope.checkConfirmPassword=function(){
			$scope.loginMessageDetails.errorMessage.signup_confirmPassword="";
			if($scope.userDetails.password!==$scope.userDetails.confirmPassword){
				$scope.loginMessageDetails.errorMessage.signup_confirmPassword="Password and Confirm Password din't match";
				$loading.finish('login');
				return false;
			}
			return true;
		};
		
		$scope.login=function(){
			$loading.start('login');
			$scope.resetMessages();
			loginFactory.submitLogin($scope.userDetails).then(function(response){
				if(response.user===undefined){
					$scope.loginMessageDetails.errorMessage.login=response[0];
				}else{
					if($scope.rememberMe){
						$cookies.put("emailId", $scope.userDetails.emailId);
					}else{
						$cookies.remove("emailId");
					}
					$rootScope.user=response.user;
					$state.go("main");
				}
				 $loading.finish('login');
			}).catch(function(error){
				$scope.loginMessageDetails.errorMessage.login="Either emailId or Password is incorrect ";
				$loading.finish('login');
            });
		};
		
		$scope.adminLogin=function(){
			$loading.start('login');
			$scope.resetMessages();
			loginFactory.submitLogin($scope.userDetails).then(function(response){
				if(response.user===undefined){
					$scope.loginMessageDetails.errorMessage.login=response[0];
				}else{
					if($scope.rememberMe){
						$cookies.put("emailId", $scope.userDetails.emailId);
					}else{
						$cookies.remove("emailId");
					}
					$rootScope.user=response.user;
					$state.go("main");
				}
				 $loading.finish('login');
			}).catch(function(error){
				$scope.loginMessageDetails.errorMessage.login="Either emailId or Password is incorrect ";
				$loading.finish('login');
            });
		};
		
		$scope.signup=function(){
			$loading.start('login');
			if($scope.checkConfirmPassword()){
				$scope.resetMessages();
				loginFactory.signup($scope.userDetails).then(function(response){
					$scope.loginMessageDetails.successMessage.signup_emailId=response.success;
					$scope.resetUserDetails();
					$loading.finish('login');
				}).catch(function(error){
					$scope.loginMessageDetails.errorMessage.signup_emailId="Something went wrong  please contact administrator";
					$loading.finish('login');
	            });
			}
		};
		
		$scope.defaultLogins=function(emailId,password) {
			$scope.userDetails.emailId=emailId;
			$scope.userDetails.password=password;
			$scope.login();			
		};
		
		
		$scope.forgotPassword=function(){
			$loading.start('login');
				loginFactory.forgotPassword($scope.userDetails).then(function(response){
					$scope.loginMessageDetails.successMessage.signup_emailId=response.success;
					$scope.resetUserDetails();
					$loading.finish('login');
				}).catch(function(error){
					$scope.loginMessageDetails.errorMessage.signup_emailId="Something went wrong  please contact administrator";
					$loading.finish('login');
	            });
		};
		
		$scope.confirmationInstructions=function(){
			$loading.start('login');
				loginFactory.confirmationInstructions($scope.userDetails).then(function(response){
					$scope.loginMessageDetails.successMessage.signup_emailId=response.success;
					$scope.resetUserDetails();
					$loading.finish('login');
				}).catch(function(error){
					$scope.loginMessageDetails.errorMessage.signup_emailId="Something went wrong  please contact administrator";
					$loading.finish('login');
	            });
		};
	};
	 
	loginController.$inject=['$rootScope','$scope','$state','loginService','loginFactory','$cookies','$loading'];
	
	angular.module('amoeba.login').controller("loginController",loginController);
	
})();

(function(){
	
	function registrationConfirmationController($scope,$state,loginFactory,$location,$loading){
		        $loading.start('register');
				loginFactory.registrationConfirmation($location.search().token).then(function(response){
					if(response.success!==undefined){
						$scope.success=response.success;
					}
					$loading.finish('register');
				}).catch(function(error){
					$scope.error=error.failed;
					$loading.finish('register');
	            });
	};
	
	
	
	registrationConfirmationController.$inject=['$scope','$state','loginFactory','$location','$loading'];
	
	angular.module('amoeba.login').controller("registrationConfirmationController",registrationConfirmationController);
	
})();

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





(function(){
	
	angular.module('amoeba.main').constant("MAIN_CONSTANTS",{
		"LOGOUT_URL":"/amoeba/logout",
		"CHECK_USER_URL":"/amoeba/checkUser",
		"CHANGE_PASSWORD_URL":"/amoeba/changePassword"
	});
	
})();

(function(){
	
	function changePasswordController($rootScope,$scope,$state,roleService,mainFactory,$loading){
		$loading.start("main");
		$scope.changePassword={
				"password":"",
				"confirmPassword":""
		};
		$scope.error="";
		$scope.success="";
		
		$scope.changePassword=function(){ 
			$scope.error="";
			$scope.success="";
			if($scope.changePassword.password===$scope.changePassword.confirmPassword){
				
				mainFactory.changePassword($scope.changePassword.password).then(function(response){
					$scope.success="Password changed successfully";
				}).catch(function(error){
					$scope.error="Something went wrong";
				});
			}else{
				$scope.error="Passwords did not match";
			}
		};
		$loading.finish("main");
	};
	
	changePasswordController.$inject=['$rootScope','$scope','$state','roleService','mainFactory','$loading'];
	
	angular.module('amoeba.login').controller("changePasswordController",changePasswordController);
	
})();

(function(){
	
	function mainController($rootScope,$scope,$state,roleService,mainFactory,$loading){
		$loading.start("main");
		$scope.currentView=".profile";
		$scope.value=function(userDetails){
			$scope.userDetails=userDetails;
			$state.go("main.profile");
			$scope.authorities=roleService.roleAuthorities($scope.userDetails.role);
			$loading.finish("main");
		}; 
		
		if($rootScope.user===undefined){
			mainFactory.checkUser().then(function(response){
				$rootScope.user=response.user;
				$scope.value(response.user);
			}).catch(function(){
				
			});
		}else{
			$scope.value($rootScope.user);
		}
		
		$scope.logout=function(){
			$loading.start("main");
			mainFactory.logout();
		};
		
		$scope.setSideBarActive=function(view){
			$scope.currentView=view;
		};
		
		$scope.$on('sideBarViewEvent', function(event, data) {
			$scope.currentView=data;
		});
		
	};
	
	mainController.$inject=['$rootScope','$scope','$state','roleService','mainFactory','$loading'];
	
	angular.module('amoeba.login').controller("mainController",mainController);
	
})();

(function(){
	
	function mainFactory($rootScope,$http,MAIN_CONSTANTS,$state,$q){
		
		function logout(){
			$http.get(MAIN_CONSTANTS.LOGOUT_URL).then(function(){
				$rootScope.user=null;
				$state.go("login");
			});
		}
		
		function checkUser(){
			var defered=$q.defer();
			$http.get(MAIN_CONSTANTS.CHECK_USER_URL).success(function(response){
				defered.resolve(response);
			}).error(function(error){
				$state.go("login");
				defered.reject(error);
			});
			return defered.promise;
		}
		
		function changePassword(password){
			var defered=$q.defer();
			var data={"password":password};
			$http.post(MAIN_CONSTANTS.CHANGE_PASSWORD_URL,data).success(function(response){
				defered.resolve(response);
			}).error(function(error){
				defered.reject(error);
			});
			return defered.promise;
		}
		
		return {
		logout:logout,
		checkUser:checkUser,
		changePassword:changePassword
		};
	};
	
	mainFactory.$inject=['$rootScope','$http','MAIN_CONSTANTS','$state','$q'];
	
	angular.module('amoeba.main').factory('mainFactory',mainFactory);
	
})();





angular.module('amoeba.main')
    .filter('toTrustHtml', ['$sce', function($sce){
        return function(text) {
            return $sce.trustAsHtml(text);
        };
    }]);

angular.module('amoeba.main')
.filter("trustUrl", ['$sce', function ($sce) {
    return function (recordingUrl) {
        return $sce.trustAsResourceUrl(recordingUrl);
    };
}]);

(function() {

	function roleService() {
		this.roleAuthorities = function(role) {
			var roleAuthorities = {
				"0" : {
					"":["glyphicon glyphicon-user","User"],
					".form":["glyphicon glyphicon-modal-window","SubmitQuery"],
					".submitedForms":["glyphicon glyphicon-share","submitedForms"]
				} ,
				"1" : {
					"":["glyphicon glyphicon-user","Consultant"],
					".myForms":["glyphicon glyphicon-pencil","myForms"],
					".activeForms":["glyphicon glyphicon-screenshot","activeForms"]
					
				}
				
			};

			return roleAuthorities[role];
		};

	};

	roleService.$inject = [];

	angular.module('amoeba.main').service('roleService', roleService);

})();


(function(){
	
	angular.module('amoeba.profile').constant("PROFILE_CONSTANTS",{
		"PROFILE_UPDATE_URL":"/amoeba/updateProfile"
	});
	
})();

(function(){
	
	function profileController($scope,profileFactory,$loading){
		
		$scope.viewProfile=true;
		if($scope.userDetails!==undefined){
			$scope.profileDetails=angular.copy($scope.userDetails);
			//$scope.profileDetails.jobType=($scope.profileDetails.jobType).toString();
		}
		
		$scope.editProfile=function(){
			$scope.viewProfile=!$scope.viewProfile;
			$loading.finish("main");
		};
		
		$scope.changeToInt=function(value){
			return parseInt(value);
		};
		
		$scope.updateProfile=function(){
			$loading.start("main");
			profileFactory.updateProfile($scope.profileDetails).then(function(response){
				var updatedUserDetails=response.user;
				if(updatedUserDetails.imagePath!==null){
					$scope.profileDetails.imagePath=updatedUserDetails.imagePath;
					$scope.profileDetails.profieImageBytes=updatedUserDetails.profieImageBytes;
				}
				angular.extend($scope.userDetails, $scope.profileDetails);
				$scope.editProfile();
			}).catch(function(){
				$loading.finish("main");
			});
		};
		$loading.finish("main");
	};
	
	profileController.$inject=['$scope','profileFactory','$loading'];
	
	angular.module('amoeba.profile').controller("profileController",profileController);
	
})();

(function(){
	angular.module('amoeba.profile').directive('fileModel', ['$parse', function ($parse) {
        return {
           restrict: 'A',
           link: function(scope, element, attrs) {
              var model = $parse(attrs.fileModel);
              var modelSetter = model.assign;
              
              element.bind('change', function(){
                 scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                 });
              });
           }
        };
     }]);
})();

(function(){
	
	function profileFactory($q,PROFILE_CONSTANTS){
		
		function updateProfile(profileDetails){
			var defered=$q.defer();
			 var payload = new FormData();
			 payload.append('firstName', profileDetails.firstName);
			 payload.append('lastName', profileDetails.lastName);
			 payload.append('phone', profileDetails.phone);
			 payload.append('location', profileDetails.location);
			 payload.append('currentJobTitle', profileDetails.currentJobTitle);
			 payload.append('currentEmployer', profileDetails.currentEmployer);
			 if(profileDetails.role===0){
				 payload.append('middleName', profileDetails.middleName);
				 payload.append('experience', profileDetails.experience);
				 payload.append('currentSalary', profileDetails.currentSalary);
				 payload.append('primarySkills', profileDetails.primarySkills);
				 payload.append('secondarySkills', profileDetails.secondarySkills);
				 payload.append('prefredLocations', profileDetails.prefredLocations);
				 payload.append('workAuthorization', profileDetails.workAuthorization);
				 payload.append('jobType', profileDetails.jobType);
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





(function() {

	function profileService() {
		
	};

	profileService.$inject = [];

	angular.module('amoeba.profile').service('profileService', profileService);

})();

(function(){
	
	angular.module('amoeba.form').constant("FORM_CONSTANTS",{
		
		"SUBMIT_FORM_URL":"/amoeba/formSub",
		
	});

})();

(function(){
	
	function formController($rootScope,$scope,$state,formFactory,$cookies,$loading){
		
		$state.go("main.form");
		$scope.rememberMe=false;
		
		$scope.assignState=function(state){
			$rootScope.activeState=state;
		};
		
		$scope.rememberMe1=function(){
			$scope.rememberMe=!$scope.rememberMe;
		};
		
		$scope.assignState('main.form');
		
		$scope.resetFormDetails=function() {
			$scope.formDetails = {
					"title":"",
					"reportDescription" : "",
					"age" : "",
					"height" : "",
					"status" : 0,
					"spec_Id" :""  ,
					"weight" :"",
					"mainCat_Id" :"",
					"subSpec_Id" :""
					
			};
		};
		
		$scope.resetMessages=function() {
			
			$scope.formSubmitMessageDetails = {
				"errorMessage" : {
					"submit" : ""
				},
				"successMessage" : {
					"submit" : ""
				}
			};
		};
		
		
		$scope.resetFormDetails();
		$scope.resetMessages();
		
		$scope.formSubmit=function(){
			$loading.start('main');
				formFactory.formSubmit($scope.formDetails).then(function(response){
					$scope.formSubmitMessageDetails.successMessage.submit=response.success;
					$scope.resetFormDetails();
					$loading.finish('main');
				}).catch(function(error){
					$scope.formSubmitMessageDetails.errorMessage.submit="Something went wrong please contact administrator";
					$loading.finish('main');
	            });
			
		};
	};
	 
	formController.$inject=['$rootScope','$scope','$state','formFactory','$cookies','$loading'];
	
	angular.module('amoeba.form').controller("formController",formController);
	
})();

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
(function(){
	
	function myFormController($scope,myFormFactory,$state,$loading){
		
		/*$scope.postJob=function(){
			myJobsService.editJob=null;
			$scope.$emit('sideBarViewEvent', ".submitedForms");
			$state.go('main.submitedForms');
		};
		*/
		$loading.start('main');
			formFactory.getForms().then(function(response) {
				/*if(response.form === undefined){
					$scope.formMessageDetails.errorMessage.submit="you didn't submited any Query ";
				}*/
				$scope.formDetails=response;
				console.log(response);
				$loading.finish("main");
			}).catch(function(){
				$loading.finish("main");
			});
			
		};
		
		
	
	
	myFormController.$inject=['$scope','myFormFactory','$state','$loading'];
	
	angular.module('amoeba.form').controller("myFormController",myFormController);
	
})();
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











