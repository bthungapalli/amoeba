(function(){
	
	function consultantController($rootScope,$scope,consultantFactory,$state,$loading){
		
		$loading.start('main');
		
		consultantFactory.getForms().then(function(response) {
				$scope.myformDetails=response;
				$loading.finish("main");
			}).catch(function(){
				$loading.finish("main");
			});
		
		
		$scope.getFormDetails=function(f_id,index){
			
			if(	$scope.myformDetails[index].formDetails===undefined){
				consultantFactory.getFormDetails(f_id).then(function(response){
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
		
		$scope.acceptForm=function(f_id){
			consultantFactory.acceptForm(f_id).then(function(response){
				$rootScope.reportDetails=response;
				$state.go("main.report")
			}).catch(function(error){
				$scope.getMessage="Something went wrong please contact administrator";
				$loading.finish("main");
            });
		}
		
		};
		
		
	
	
	consultantController.$inject=['$rootScope','$scope','consultantFactory','$state','$loading'];
	
	angular.module('amoeba.consultant').controller("consultantController",consultantController);
	
})();