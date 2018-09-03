<form ng-submit="ctrl.submit()" name="seminarForm" class="form-horizontal">
  <input type="hidden" ng-model="ctrl.id" />
  <div class="form-group row">
    <label class="control-label col-sm-1" for="title">Title:</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="title" placeholder="Title of seminar">
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
      <button type="submit" ng-click="reset()" class="btn btn-default">Reset</button>
    </div>
    <div class="col-sm-1">
      <button type="submit" ng-click="create()" class="btn btn-default">Create</button>
    </div>
  </div>
</form>