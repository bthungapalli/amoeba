(function() {

	function roleService() {
		this.roleAuthorities = function(role) {
			var roleAuthorities =  {
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