angular.module('seminarsApp').factory('ProfessorService', ['$http', '$q', '$window', function ($http, $q, $window) {
	var factory = {
			loadAllProfessors: loadAllProfessors,
			getAllProfessors: getAllProfessors,
			createProfessor: createProfessor,
			deleteProfessor: deleteProfessor
	};
	return factory;

	function loadAllProfessors() {
		console.log("Getting list of professors")
		var deferred = $q.defer();
		$http.get("http://localhost:8080/api/professor").then(
		function(response) {
			console.log("Success with" + response.data);
			if (typeof (Storage) !== undefined) {
				$window.sessionStorage.setItem("professorsList", angular.toJson(response.data));
			} else {
				console.log("Unable to save due to sessionStorage is not available.");
			}
			deferred.resolve(response);
		}, function(response) {
			console.log("Failed");
			deferred.reject(response);
		});
		return deferred.promise;
	}
	function getAllProfessors() {
		console.log("Getting list of professors");
		if (typeof(Storage) !== undefined) {
			return angulat.fromJson($window.sessionStorage.getItem("professorsList"));
		} else {
			console.log("The sessionStorage is not available.")
			return [];
		}
	}
	function getSeminar(id) {
		console.log("Getting record for professor with Id: " + id);
		var deferred = $q.defer();
		$http.get("http://localhost:8080/api/professor/" + id).then(
			function(response) {
				console.log("Success with " + response.data);
				return response.data;
			},
			function(response) {
				console.log("Failed");
			});
		return deferred.promise;
	}
	function createProfessor() {
		console.log("Creating new record for professor.");
	}
	function deleteProfessor(id) {
		console.log("Deleting record for professor with Id: " + id);
	}
}
]);