angular.module('seminarsApp').directive('fileModel',
	['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
           var model = $parse(attrs.fileModel);
           var modelSetter = model.assign;
           
           element.bind('change', function(){
              scope.$apply(function(){
                 modelSetter(scope, element[0].files[0]);
              });
           });
        }
    };
}]);
angular.module('seminarsApp').service('backupService',
	['$http', function ($http) {
    this.uploadFileToUrl = function(file, url){
       console.log("Sending " + file.name);
       var fd = new FormData();
       fd.append('file', file);
    
       $http.post(url, fd, {
          transformRequest: angular.identity,
          headers: {'Content-Type': 'application/json'}
       }).then(
    
       function(response){
    	   console.log('Success');
       },
    
       function(response){
    	   console.log('Failure with ' + response.status);
       });
    };
}]);
angular.module('seminarsApp').controller('BackupController',
	['$scope', 'backupService', function($scope, backupService) {
	$scope.file = "no file chosen";
	$scope.url = "http://localhost:8080/api/backup";
	$scope.uploadFile = function() {
        var file = $scope.file;
        var url = $scope.url;
        
        backupService.uploadFileToUrl(file, url);
    };
    $scope.dateStampOnCheck = function() {
		if ($scope.filename == undefined) {
			$scope.filename = "seminars-backup";
		}
		var filename = $scope.filename.slice(0, ($scope.filename.lastIndexOf(".") - 1 >>> 0) + 1);
		var d = new Date();
		var datestamp = "-" + d.getDate() + "-" + d.getMonth() + "-" + d.getFullYear();
		if ($scope.dateStampOn) {
			filename = filename + datestamp;
		} else {
			filename = filename.slice(0, filename.lastIndexOf(datestamp));
		}
		$scope.filename = filename + ".json";
	};
}]);