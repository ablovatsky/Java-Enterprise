$(document).ready(function() {
    $.ajax({
        type: "GET",
        url : "/row/getWorkers",
        success: function(result){
            var json_data= JSON.parse(result);
            filWorkerList(json_data);
        }
    });
});

function filWorkerList(data) {
    $('.loginedworker').text(data.loggedinworker)

    var workerTable;

    workerTable += '<thead>';
    workerTable += '<tr>';
    workerTable += '<th>SSO ID</th>';
    workerTable += '<th>Last Name</th>';
    workerTable += '<th>First Name</th>';
    workerTable += '<th>Subdivision</th>';
    workerTable += '<th width="100"></th>';
    workerTable += '<th width="100"></th>';
    workerTable += '</tr>';
    workerTable += '</thead>';
    data.workerList.forEach(function ( worker ) {
        workerTable += '<tr>';
        workerTable += '<td>';
        workerTable += worker.ssoId;
        workerTable += '</td>';
        workerTable += '<td>';
        workerTable += worker.firstName;
        workerTable += '</td>';
        workerTable += '<td>';
        workerTable += worker.lastName;
        workerTable += '</td>';
        workerTable += '<td>';
        workerTable += worker.subdivision;
        workerTable += '</td>';
        workerTable += '<td>';
        workerTable += "<a href='/row/edit-worker-"+worker.ssoId+"' class='btn btn-success custom-width'>Ред-ть</a>";
        workerTable += '</td>';
        workerTable += '<td>';
        workerTable += "<a href='/row/delete-worker-"+worker.ssoId+"' class='btn btn-danger custom-width'>Удалить</a>";
        workerTable += '</td>';
        workerTable += '</tr>';
    });
    $('.table').append(workerTable);


}

function addWorker() {
    
}

function validateWorker() {
    
}
