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
angular.module('seminarsApp').service('fileUpload',
	['$http', function ($http) {
    this.uploadFileToUrl = function(file, url){
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
    }
}]);
angular.module('seminarsApp').controller('BackupController',
	['$scope', 'fileUpload', function($scope, fileUpload) {
	$scope.file = "no file chosen";
	$scope.url = "http://localhost:8080/api/backup";
	$scope.uploadFile = function() {
        var file = $scope.file;
        var url = $scope.url;
        
        fileUpload.uploadFileToUrl(file, url);
    };
}]);