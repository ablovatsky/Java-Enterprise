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
    var table = "";
    table = '<thead>';
    table += '<tr>';
    table += '<th>Название</th>';
    table += '<th width="150"></th>';
    table += '<th width="150"></th>';
    table += '</tr>';
    table += '</thead>';
    subdivisions.forEach( function (subdivision) {
        table += '<tr>';
        table += "<td>";
        table += subdivision.name;
        table += '</td>';
        table += '<td>';
        table += "<a href='/avectis/administration//subdivisions/"+subdivision.id+"' class='btn btn-primary custom-width'>Редактировать</a>";
        table += '</td>';
        table += '<td>';
        table += "<input type=\"submit\" class=\"btn del btn-danger custom-width\" id=\""+ subdivision.id +"\" value=\"Удалить\" onClick = \"deleteSubdivision(this.id)\">";
        table += '</td>';
        table += '</tr>';
    });
    $(".table thead").remove();
    $(".table tbody").remove();
    $('.table').append(table);
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
