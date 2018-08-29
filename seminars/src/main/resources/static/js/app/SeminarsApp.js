var app = angular.module('seminarsApp', [ 'ngRoute' ]);
app.config(function($routeProvider) {
	$routeProvider
	.when('/backup', {
		controller : 'BackupController',
		controllerAs : 'ctrl',
		templateUrl : 'partials/backup.ftl'
	})
	.when('/seminar', {
		controller : 'SeminarController',
		controllerAs : 'ctrl',
		templateUrl : 'partials/seminar.ftl'
	})
	.when('/professor', {
		controller : 'ProfessorController',
		controllerAs : 'ctrl',
		templateUrl : 'partials/professor.ftl'
	})
	.when('/student', {
		controller : 'StudentController',
		controllerAs : 'ctrl',
		templateUrl : 'partials/student.ftl'
	})
	.otherwise({
		redirectTo: '/seminar'
    });
});