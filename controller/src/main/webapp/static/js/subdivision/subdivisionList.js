$(document).ready(function() {
    $.ajax({
        type: "GET",
        contentType : 'application/json; charset=utf-8',
        url : "/contracts/administration/getSubdivisions",
        success: function(result){
            fillSubdivisionList(result);
        }
    });
});

function fillSubdivisionList(data) {
    var subdivisionTable;
    subdivisionTable = '<thead>';
    subdivisionTable += '<tr>';
    subdivisionTable += '<th>Название</th>';
    subdivisionTable += '<th width="150"></th>';
    subdivisionTable += '<th width="150"></th>';
    subdivisionTable += '</tr>';
    subdivisionTable += '</thead>';
    var size = data.map.subdivisionList.myArrayList.length;
    for(var i = 0; i < size; i++ ) {
        subdivisionTable += '<tr>';
        subdivisionTable += "<td>";
        subdivisionTable += data.map.subdivisionList.myArrayList[i].map.name;
        subdivisionTable += '</td>';
        subdivisionTable += '<td>';
        subdivisionTable += "<a href='/contracts/administration/edit-subdivision-"+data.map.subdivisionList.myArrayList[i].map.id+"' class='btn btn-primary custom-width'>Редактировать</a>";
        subdivisionTable += '</td>';
        subdivisionTable += '<td>';
        subdivisionTable += "<a href='/contracts/administration/delete-subdivision-"+data.map.subdivisionList.myArrayList[i].map.id+"' class='btn btn-danger custom-width'>Удалить</a>";
        subdivisionTable += '</td>';
        subdivisionTable += '</tr>';
    }
    $('.table').append(subdivisionTable);
}

