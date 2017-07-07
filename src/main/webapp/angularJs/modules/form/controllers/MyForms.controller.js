(function(){
	
	function myFormController($rootScope,$scope,myFormFactory,$state,$loading){
		

		/*$scope.postJob=function(){
			myJobsService.editJob=null; 
			$scope.$emit('sideBarViewEvent', ".submitedForms");
			$state.go('main.submitedForms');
		};
		*/

		$loading.start('main');
		
		myFormFactory.getForms($scope.userDetails.userId).then(function(response) {
				$scope.myformDetails=response;
				$loading.finish("main");
			}).catch(function(){
				$loading.finish("main");
			});
			
		$scope.getFormDetails=function(f_id,index){
			
			if(	$scope.myformDetails[index].formDetails===undefined){
				myFormFactory.getFormDetails(f_id).then(function(response){
					$scope.myformDetails[index].formDetails={};
					$scope.myformDetails[index].formDetails=response;
					$scope.myformDetails[index].showDescription=true;
					$loading.finish("main");
				}).catch(function(){
					$loading.finish("main");
				});
			}else{
				$scope.myformDetails[index].showDescription=!$scope.myformDetails[index].showDescription;
			}
		};
		
		
	};
		
		
	
	
	myFormController.$inject=['$rootScope','$scope','myFormFactory','$state','$loading'];
	
	angular.module('amoeba.form').controller("myFormController",myFormController);
	
})();