<!DOCTYPE html>
<html lang="en" ng-app="seminarsApp">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <!--link href="css/seminarsApp.css" rel="stylesheet"/-->
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-route.js"></script>
    <title>${title}</title>
  </head>
    <body>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		    <div class="navbar-header">
		        <a class="navbar-brand" href="#!summary">Seminars manager menu</a>
		    </div>
			<ul class="nav navbar-nav navbar-inverse">
				<li class="nav-item"><a class="nav-link" href="#!seminar">Seminars list</a></li>
				<li class="nav-item"><a class="nav-link" href="#!professor">Professors list</a></li>
				<li class="nav-item"><a class="nav-link" href="#!student">Students list</a></li>
				<li class="nav-item"><a class="nav-link" href="#!backup">Backup and Restore</a></li>
			</ul>
		</nav>
		<div ng-view></div>
        <script src="js/app/SeminarsApp.js"></script>
        <script src="js/app/BackupService.js"></script>
        <script src="js/app/BackupController.js"></script>
        <script src="js/app/SeminarService.js"></script>
        <script src="js/app/SeminarController.js"></script>
        <script src="js/app/StudentController.js"></script>
        <script src="js/app/ProfessorService.js"></script>
        <script src="js/app/ProfessorController.js"></script>
    </body>
</html>