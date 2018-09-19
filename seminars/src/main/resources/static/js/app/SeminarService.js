angular.module('seminarsApp').factory('SeminarService',['$http', '$q', '$window', function($http, $q, $window){
	var factory = {
		loadAllSeminars : loadAllSeminars,
		getAllSeminars : getAllSeminars,
		createSeminar : createSeminar,
		getSeminar : getSeminar,
		updateSeminar : updateSeminar,
		deleteSeminar : deleteSeminar
	};
	return factory;

	function loadAllSeminars() {
		console.log("Getting list of seminars.");
		var deferred = $q.defer();
		$http.get("http://localhost:8080/api/seminar").then(
		function(response) {
			console.log("Success with " + response.data);
			if(typeof(Storage) !== "undefined") {
				$window.sessionStorage.setItem("seminarsList", angular.toJson(response.data));
			} else {
				console.log("Unable to save due to sessionStorage is not available.");
			}
			deferred.resolve(response);
		},
		function(response) {
			console.log("Failure");
			deferred.reject(response);
		});
		return deferred.promise;
	};
	function getAllSeminars() {
		console.log("Seminars list is: " + $window.sessionStorage.seminarsList);
		if(typeof(Storage) !== "undefined") {
			var lecturers = angular.fromJson($window.sessionStorage.getItem("professorsList"));
			var seminars = angular.fromJson($window.sessionStorage.getItem("seminarsList"));
			seminars.forEach(function(item){
				item.lecturer = lecturers[item.lecturer - 1].name;
			});
			return seminars;
		} else {
			console.log("The sessionStorage is not available.");
			return [];
		}
	}
	function createSeminar() {
		console.log("Creating new seminar.");
	}
	function getSeminar(id) {
		console.log("Getting seminar with id: " + id);
		$http.get("http://localhost:8080/api/seminar/" + id)
		.then(
		function(response) {
			console.log("Success");
		},
		function(response) {
			console.log("Failure");
		});
	}
	function updateSeminar(instance) {
		console.log("Updating seminar.");
	}
	function deleteSeminar(id) {
		console.log("Deleting seminar with id: " + id);
	}
}]);