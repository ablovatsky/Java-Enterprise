$(document).ready(function() {
    getSubdivisions();
});

function getSubdivisions() {
    $.ajax({
        type: "GET",
        url : "/avectis/administration//subdivisionLists",
        success: function(subdivisions){
            fillSubdivisionList(subdivisions);
        }
    });
}

function fillSubdivisionList(subdivisions) {
    var subdivisionTable = "";
    subdivisionTable = '<thead>';
    subdivisionTable += '<tr>';
    subdivisionTable += '<th>Название</th>';
    subdivisionTable += '<th width="150"></th>';
    subdivisionTable += '<th width="150"></th>';
    subdivisionTable += '</tr>';
    subdivisionTable += '</thead>';
    subdivisions.forEach( function (subdivision) {
        subdivisionTable += '<tr>';
        subdivisionTable += "<td>";
        subdivisionTable += subdivision.name;
        subdivisionTable += '</td>';
        subdivisionTable += '<td>';
        subdivisionTable += "<a href='/avectis/administration//subdivisions/"+subdivision.id+"' class='btn btn-primary custom-width'>Редактировать</a>";
        subdivisionTable += '</td>';
        subdivisionTable += '<td>';
        subdivisionTable += "<input type=\"submit\" class=\"btn del btn-danger custom-width\" id=\""+ subdivision.id +"\" value=\"Удалить\" onClick = \"deleteSubdivision(this.id)\">";
        subdivisionTable += '</td>';
        subdivisionTable += '</tr>';
    });
    $(".table thead").remove();
    $(".table tbody").remove();
    $('.table').append(subdivisionTable);
}

function deleteSubdivision(id) {
    const token = $("meta[name='_csrf']").attr("content");
    const header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        type: "DELETE",
        url : "/avectis/administration/subdivisions/" + id ,
        beforeSend: function(request) {
            request.setRequestHeader(header, token);
        },
        success: function(workers, textStatus, jqXHR){
            getSubdivisions();
        }
    });
}
