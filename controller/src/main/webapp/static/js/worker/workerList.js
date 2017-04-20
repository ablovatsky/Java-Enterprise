$(document).ready(function() {
    $.ajax({
        type: "GET",
        contentType : 'application/json; charset=utf-8',
        url : "/contracts/getWorkers",
        success: function(result){
            fillWorkerList(result);
        }
    });
});

function fillWorkerList(data) {
    $('.loggedinworker').text(data.map.loggedinworker);
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
    var size = data.map.workerList.myArrayList.length;
    for(var i = 0; i < size; i++ ) {
        workerTable += '<tr>';
        workerTable += '<td>';
        workerTable += data.map.workerList.myArrayList[i].map.ssoId;
        workerTable += '</td>';
        workerTable += '<td>';
        workerTable += data.map.workerList.myArrayList[i].map.firstName;
        workerTable += '</td>';
        workerTable += '<td>';
        workerTable += data.map.workerList.myArrayList[i].map.lastName;
        workerTable += '</td>';
        workerTable += '<td>';
        workerTable += data.map.workerList.myArrayList[i].map.subdivision;
        workerTable += '</td>';
        workerTable += '<td>';
        workerTable += "<a href='/contracts/edit-worker-"+data.map.workerList.myArrayList[i].map.ssoId+"' class='btn btn-success custom-width'>Редактировать</a>";
        workerTable += '</td>';
        workerTable += '<td>';
        workerTable += "<a href='/contracts/delete-worker-"+data.map.workerList.myArrayList[i].map.ssoId+"' class='btn btn-danger custom-width'>Удалить</a>";
        workerTable += '</td>';
        workerTable += '</tr>';
    }
    $('.table').append(workerTable);
}

