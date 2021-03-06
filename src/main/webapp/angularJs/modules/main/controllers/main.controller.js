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