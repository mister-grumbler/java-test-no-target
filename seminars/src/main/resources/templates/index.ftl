<!DOCTYPE html>
 
<html lang="en" ng-app="seminarsApp">
    <head>
        <title>${title}</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="css/app.css" rel="stylesheet"/>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-route.js"></script>
    </head>
    <body>
		<nav class="navbar navbar-inverse" role="navigation">
		    <div class="navbar-header">
		        <a class="navbar-brand" href="#!seminar">Seminars manager menu</a>
		    </div>
			<ul class="nav navbar-nav">
				<li><a href="#!seminar">Seminars list</a></li>
				<li><a href="#!professor">Professors list</a></li>
				<li><a href="#!student">Students list</a></li>
				<li><a href="#!backup">Backup and Restore</a></li>
			</ul>
		</nav>
		<div ng-view></div>
        <script src="js/app/SeminarsApp.js"></script>
        <script src="js/app/BackupController.js"></script>
        <script src="js/app/SeminarController.js"></script>
        <script src="js/app/StudentController.js"></script>
        <script src="js/app/ProfessorController.js"></script>
    </body>
</html>