<div class="alert alert-info" role="alert">
<h4 class="alert-heading">Get backup</h4>
<hr>
You may set desired name and choose to add date stamp for backup file before clicking <b>Request</b> button. The <b>.json</b> file extension will be added automaticaly.
<hr>  
	<form name="backupForm" class="form-horizontal">
	  <div class="form-group row">
	    <label class="controll-label col-sm-1" for="filename">Filename:</label>
	    <div class="col-sm-6">
	      <input type="text" class="form-control" ng-model="filename" id="filename" placeholder="seminars-backup.json">
	    </div>
        <div class="col-sm-3">
          <input class="form-check-input" type="checkbox" ng-model="dateStampOn" ng-change="dateStampOnCheck()" id="dateStampOn">
          <label class="form-check-label" for="dateStampOn">Add date stamp to filename</label>
        </div>
	    <div class="col-sm-1">
	      <a class="btn  btn-secondary" role="button" href="/api/backup/{{!filename ? 'seminars-backup.json' : filename}}">Request</a>
	    </div>
	  </div>
	</form>
</div>
<div class="alert alert-warning" role="alert">
<h4 class="alert-heading">Restore data</h4>
<hr>
You may change URL and must choose file before clicking <b>Upload</b> button.
<hr>
	<form name="restoreForm" class="form-horizontal">
	  <div class="form-group row">
	    <label class="control-label col-sm-1" for="url">URL:</label>
	    <div class="col-sm-6"> 
	      <input type="text" class="form-control" ng-model="url" id="url" placeholder="http://<hostname>:<port>/api/backup/<filename>">
	    </div>
	    <div class="custom-file col-sm-3">
	      <input type="file" class="custom-file-input" file-model="file" id="file">
	      <label class="custom-file-label" for="file">{{file.name}}</label>
	    </div>
	    <div class="col-sm-1">
	      <button ng-click="uploadFile()" class="btn btn-default" ng-disabled="!file.name">Upload</button>
	    </div>
	  </div>
	</form>
</div>
