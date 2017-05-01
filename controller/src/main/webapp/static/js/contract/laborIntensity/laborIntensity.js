$(document).ready(function(){
    let contractNumber = $("#hdnSession").data('value');
    getLaborIntensity(contractNumber)
});

function getLaborIntensity(contractNumber) {
    $.ajax({
        type: "GET",
        url : "/avectis/contracts/laboriousness/contract-" + contractNumber ,
        success: function(laborIntensity) {
            getSubdivisions(laborIntensity);
        }
    });
}

function getSubdivisions(laborIntensity) {
    $.ajax({
        type: "GET",
        url : "/avectis/administration/subdivisionLists" ,
        success: function(subdivisions) {
            fillPage(laborIntensity,subdivisions);
        }
    });
}

function fillPage(laborIntensity, subdivisions) {
    let memSubdivisions = subdivisions;
    $('.table-labor-intensity').prop('hidden', true);
    let table = "";
    table += '<thead>';
    table += '<tr>';
    table += '<th>Подразделение</th>';
    table += '<th>Трудоемкость</th>';
    table += '<th>Себестоемость</th>';
    table += '<th width="140"></th>';
    table += '<th width="140"></th>';
    table += '</tr>';
    table += '</thead>';
    laborIntensity.forEach(function(element) {
        $.each(memSubdivisions, function(i){
            if(memSubdivisions[i].name === element.subdivision.name) {
                memSubdivisions.splice(i,1);
                return false;
            }
        });
        table += '<tr>';
        table += '<td>';
        table += "<input type=\"text\" value="+ element.subdivision.name +" class=\"form-control input-sm\" disabled>";
        table += '</td>';
        table += '<td>';
        table += "<input type=\"number\" id='plannedLaborIntensity' value="+ element.plannedLaborIntensity +" class=\"form-control input-sm\" >";
        table += '</td>';
        table += '<td>';
        table += "<input type=\"number\" id='plannedCost' value="+ element.plannedCost +" class=\"form-control input-sm\" >";
        table += '</td>';
        table += '<td>';
        table += "<input type=\"submit\" class=\"btn btn-primary custom-width\" id='" + element.id + "'  value=\"Редактировать\" onClick = \"editLaborIntensity()\">";
        table += '</td>';
        table += '<td>';
        table += "<input  type=\"submit\" class=\"btn del btn-danger custom-width\" id='" + element.id + "' value=\"Удалить\" onClick = \"deleteLaborIntensity()\">";
        table += '</td>';
        table += '</tr>';
    });
    table += '<tr>';
    table += '<td>';
    table += "<select size=\"1\" id=\"r_subdivision\" class=\"form-control input-sm\" >";
    table += '</td>';
    table += '<td>';
    table += "<input type=\"text\" class=\"form-control input-sm\" >";
    table += '</td>';
    table += '<td>';
    table += "<input type=\"text\" class=\"form-control input-sm\" >";
    table += '</td>';
    table += '<td>';
    table += "<input  type=\"hidden\" class=\"btn del btn-danger custom-width\">";
    table += '</td>';
    table += '<td>';
    table += "<input type=\"submit\" class=\"btn btn-primary custom-width\"  value=\"Добавить\" onClick = \"addLaborIntensity()\">";
    table += '</td>';
    table += '</tr>';
    $(".table-labor-intensity thead").remove();
    $(".table-labor-intensity tbody").remove();
    $('.table-labor-intensity').append(table);
    $('#r_subdivision').html('');
    memSubdivisions.forEach( function (subdivision) {
        $("#r_subdivision")
            .append( $('<option value=' + subdivision.id + '>' + subdivision.name + '</option>'));
    });
    $('.table-labor-intensity').prop('hidden', false);
}