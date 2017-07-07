(function(){
	
	function activeFormController($rootScope,$scope,myActiveFormFactory,$state,$loading){
		

		/*$scope.postJob=function(){
			myJobsService.editJob=null; 
			$scope.$emit('sideBarViewEvent', ".submitedForms");
			$state.go('main.submitedForms');
		};
		*/

		$loading.start('main');
		
		myActiveFormFactory.getMyForms().then(function(response) {
				$scope.myformDetails=response;
				$loading.finish("main");
			}).catch(function(){
				$loading.finish("main");
			});
			
		
		
	};
		
		
	
	
	activeFormController.$inject=['$rootScope','$scope','myActiveFormFactory','$state','$loading'];
	
	angular.module('amoeba.form').controller("activeFormController",activeFormController);
	
})();