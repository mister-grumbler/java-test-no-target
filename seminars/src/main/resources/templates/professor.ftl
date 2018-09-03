<form ng-submit="ctrl.submit()" name="professorForm" class="form-horizontal">
  <input type="hidden" ng-model="ctrl.id" />
  <div class="form-group row">
    <label class="control-label col-sm-1" for="name">Name:</label>
    <div class="col-sm-10">
      <input type="text" ng-model="ctrl.name" class="form-control" id="name" placeholder="Full name">
    </div>
  </div>
  <div class="form-group row">
    <label class="control-label col-sm-1" for="address">Address:</label>
    <div class="col-sm-10">
      <input type="text" ng-model="ctrl.address" class="form-control" id="address" placeholder="Address">
    </div>
  </div>
  <div class="form-group row">
    <label class="control-label col-sm-1" for="phone">Phone:</label>
    <div class="col-sm-10">
      <input type="text" ng-model="ctrl.phone" class="form-control" id="phone" placeholder="Contact phone">
    </div>
  </div>
  <div class="form-group row">
    <label class="control-label col-sm-1" for="salary">Salary:</label>
    <div class="col-sm-10">
      <input type="text" ng-model="ctrl.salary" class="form-control" id="salary" placeholder="Salary amount">
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