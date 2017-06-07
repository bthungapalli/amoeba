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