(function(){
	
	function reportController($rootScope,$scope,reportFactory,$state,$loading){
		
		
		$loading.start("main.report");
		reportFactory.getMessages($rootScope.reportDetails.f_id).then(function(response) {
			$scope.messages=response;
		}).catch(function(){
			$loading.finish("main");
		});
		
		$scope.value=function(reportDetails){
			$scope.reportDetails=reportDetails;
			$state.go("main.report");
			$loading.finish("main");
		}; 
		
		
		
		 
		/*if($rootScope.reportDetails===undefined){
			reportFactory.getFormDetails(f_id).then(function(response){
				$rootScope.user=response.user;
				$scope.value(response.user);
			}).catch(function(){
				
			});
		}else{*/
			$scope.value($rootScope.reportDetails);
		//}
			
	};
		
		
	
	
		reportController.$inject=['$rootScope','$scope','reportFactory','$state','$loading'];
	
	angular.module('amoeba.consultant').controller("reportController",reportController);
	
})();