$(document).ready(function() {
    $.ajax({
        type: "GET",
        url : "/contracts/getWorkers",
        success: function(result){
            var json_data= JSON.parse(result);
            fillWorkerList(json_data);
        }
    });
});

function fillWorkerList(data) {
    $('.loggedinworker').text(data.loggedinworker);
    var workerTable;
    workerTable = '<thead>';
    workerTable += '<tr>';
    workerTable += '<th>SSO ID</th>';
    workerTable += '<th>Last Name</th>';
    workerTable += '<th>First Name</th>';
    workerTable += '<th>Subdivision</th>';
    workerTable += '<th width="150"></th>';
    workerTable += '<th width="150"></th>';
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
        workerTable += "<a href='/contracts/edit-worker-"+worker.ssoId+"' class='btn btn-success custom-width'>Редактировать</a>";
        workerTable += '</td>';
        workerTable += '<td>';
        workerTable += "<a href='/contracts/delete-worker-"+worker.ssoId+"' class='btn btn-danger custom-width'>Удалить</a>";
        workerTable += '</td>';
        workerTable += '</tr>';
    });
    $('.table').append(workerTable);
}

