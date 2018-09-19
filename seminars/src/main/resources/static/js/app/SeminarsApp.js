var app = angular.module('seminarsApp', [ 'ngRoute' ]);
app.config(function($routeProvider) {
	$routeProvider
	.when('/summary', {
		templateUrl : 'partials/summary.ftl'
	})
	.when('/backup', {
		controller : 'BackupController',
		controllerAs : 'ctrl',
		templateUrl : 'partials/backup.ftl'
	})
	.when('/seminar', {
		controller : 'SeminarController',
		controllerAs : 'ctrl',
		templateUrl : 'partials/seminar.ftl',
		resolve : {
			seminars: function($q, SeminarService) {
				var deferred = $q.defer();
				SeminarService.loadAllSeminars().then(deferred.resolve, deferred.resolve);
                return deferred.promise;
			}
		}
	})
	.when('/professor', {
		controller : 'ProfessorController',
		controllerAs : 'ctrl',
		templateUrl : 'partials/professor.ftl',
		resolve : {
			professors: function($q, ProfessorService) {
				var deferred = $q.defer();
				ProfessorService.loadAllProfessors().then(deferred.resolve, deferred.resolve);
				return deferred.promise;
			}
		}
	})
	.when('/student', {
		controller : 'StudentController',
		controllerAs : 'ctrl',
		templateUrl : 'partials/student.ftl'
	})
	.otherwise({
		redirectTo: '/summary'
    });
});