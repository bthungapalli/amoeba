(function(){
	
	var appModule=angular.module('amoeba',['ui.bootstrap','ngRoute','ui.router','angular-input-stars','angularUtils.directives.dirPagination','ngCookies','darthwade.dwLoading','amoeba.login','amoeba.main','amoeba.profile','amoeba.form','amoeba.consultant']);

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
        }).state('main.myForms', {
            url: '/myForms',
            controller:'consultantController',
            templateUrl: 'partials/showReports.html'
        }).state('main.report', {
            url: '/report',
            controller:'reportController',
            templateUrl: 'partials/editTemplate.html'
        }).state('main.activeForms', {
            url: '/activeForms',
            controller:'activeFormController',
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
