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

angular.module('seminarsApp').controller('BackupController',
	['$scope', 'BackupService', function($scope, service) {

	$scope.file = "no file chosen";
	$scope.url = "http://localhost:8080/api/backup";

	this.uploadFile = uploadFile;
	this.dateStampOnCheck = dateStampOnCheck;

	console.log("BackupController is loaded.");

	function uploadFile() {
        var file = $scope.file;
        var url = $scope.url;
        service.uploadFileToUrl(file, url);
    };

    function dateStampOnCheck() {
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