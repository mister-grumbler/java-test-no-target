angular.module('seminarsApp').factory('BackupService',
	['$http', function ($http) {
	var factory = {
		uploadFileToUrl : uploadFileToUrl
	};
	return factory;

    function uploadFileToUrl(file, url) {
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
