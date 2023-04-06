AJS.toInit(function () {
    console.log("loading...");

    $('#btn-save').click(function(event) {
      event.preventDefault();

      var project = $('#project').val();
      var issue = $('#issue').val();
      
       console.log("project : #project");

      $.ajax({
        type: 'POST',
        dataType: 'json',
        contentType: "application/json;",
        context : document.body,
        url: AJS.params.baseURL + "/rest/training-service/1.0/form-data/create",
        data: JSON.stringify({ project: project, issue: issue }),
        success: function(data) {
          console.log('Success');
        },
        error: function(xhr, status, error) {
          console.log('Failure');
        }
      });
    });

});