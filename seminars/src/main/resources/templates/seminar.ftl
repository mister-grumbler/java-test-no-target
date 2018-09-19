<div>
	<div class="card">
		<div class="card-header">
		    <h3>Seminar Create/Edit form</h3>
		</div>
	    <div class="container">
		<form ng-submit="ctrl.submit()" name="seminarForm" class="form-horizontal">
		  <input type="hidden" ng-model="ctrl.seminar.id" />
		  <div class="form-group row">
		    <label class="control-label col-sm-1" for="title">Title:</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="title"  ng-model="name" placeholder="Title of seminar">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label class="control-label col-sm-1" for="lecturer">Lecturer:</label>
		    <div class="col-sm-10"> 
		      <input type="text" class="form-control" id="lecturer" placeholder="Lecturer name">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label class="control-label col-sm-1" for="fee">Fee:</label>
		    <div class="col-sm-10"> 
		      <input type="text" class="form-control" id="fee" placeholder="Seminar fee">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label class="control-label col-sm-1" for="number">Number:</label>
		    <div class="col-sm-10"> 
		      <input type="text" class="form-control" id="number" placeholder="Seminar number">
		    </div>
		  </div>
		  <div class="form-group row"> 
		    <div class="offset-sm-1 col-sm-1">
		      <button type="button" class="btn btn-warning" ng-click="ctrl.reset()">Reset</button>
		    </div>
		    <div class="col-sm-1">
		      <button type="submit" ng-click="ctrl.create()" class="btn btn-default">Create</button>
		    </div>
		  </div>
		</form>
		</div>
	</div>
	<div class="card">
		<div class="card-header">
	    <h4>Seminars List</h4>
		</div>
	    <div class="container">
		  <table class="table table-condensed table-hover">
		    <thead>
		      <tr>
		        <th>Number</th>
		        <th>Name</th>
		        <th>Fee</th>
		        <th>Lecturer</th>
		        <th></th>
		        <th></th>
		      </tr>
		    </thead>
		    <tbody>
		      <tr ng-repeat="u in ctrl.getList()">
		        <td>{{ u.number }}</td>
		        <td>{{ u.name }}</td>
		        <td>{{ u.fee }}</td>
		        <td>{{ u.lecturer }}</td>
		        <td><button type="button" class="btn btn-info btn-sm">Edit</button></td>
		        <td><button type="button" class="btn btn-danger btn-sm">Delete</button></td>
		      </tr>
		    </tbody>
		  </table>
	    </div>
    </div>
</div>