(function(){
	
	function formController($rootScope,$scope,$state,formFactory,$cookies,$loading){
		
		$state.go("main.form");
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