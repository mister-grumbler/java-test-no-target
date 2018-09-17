angular.module('seminarsApp').controller('SeminarController',
		['$scope', 'SeminarService', function($scope, service) {
	this.getList = getList;
	this.loadList = loadList;
	this.seminar = undefined;
	this.seminarsList = loadList();
	
	console.log("SeminarController is loaded.");
	return;

	function getList() {
		console.log("Returning list of seminars.");
		return this.seminarsList;
	}
	function loadList() {
		console.log("Loading list of seminars.");
		return service.getAllSeminars();
	}
	function getSeminar(id) {
		if (id == undefined) {
			id = 1;
		}
		this.seminar = service.getSeminar(id);
	}
}]);
