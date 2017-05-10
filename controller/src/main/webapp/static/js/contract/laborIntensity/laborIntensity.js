class LaborEntensity{
    constructor(id, contract, subdivision, plannedLaborIntensity, plannedCost) {
        this.id = id;
        this.contract = contract;
        this.subdivision = subdivision;
        this.plannedLaborIntensity = plannedLaborIntensity;
        this.plannedCost = plannedCost;
    }
}

let contractId;
let type;
let urlName;
let subdivisionsIN;


$(document).ready(function(){
    contractId = $("#hdnSession").data('value');
    getLaborIntensity(contractId);
});

function getLaborIntensity(contractId) {
    $.ajax({
        type: "GET",
        url : "/avectis/contracts/laboriousness/contract-" + contractId ,
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
            subdivisionsIN = subdivisions.slice();
            fillPage(laborIntensity, subdivisions);
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
    if (!$.isEmptyObject(laborIntensity)) {
        laborIntensity.forEach(function (element) {
            $.each(memSubdivisions, function (i) {
                if (memSubdivisions[i].name === element.subdivision.name) {
                    memSubdivisions.splice(i, 1);
                    return false;
                }
            });
            table += '<tr>';
            table += '<td>';
            table += "<input type=\"text\" id='subdivision_" + element.id + "' value=" + element.subdivision.name + " class=\"form-control input-sm\" disabled>";
            table += '</td>';
            table += '<td>';
            table += "<input type=\"number\" id='plannedLaborIntensity_" + element.id + "' value=" + element.plannedLaborIntensity + " class=\"form-control input-sm\" >";
            table += '</td>';
            table += '<td>';
            table += "<input type=\"number\" id=\"plannedCost_" + element.id + "\" value=" + element.plannedCost + " class=\"form-control input-sm\" >";
            table += '</td>';
            table += '<td>';
            table += "<input type=\"submit\" class=\"btn btn-primary custom-width\" id=\"" + element.id + "\"  value=\"Редактировать\" onClick = \"editLaborIntensity(this.id)\">";
            table += '</td>';
            table += '<td>';
            table += "<input  type=\"submit\" class=\"btn del btn-danger custom-width\" id='" + element.id + "' value=\"Удалить\" onClick = \"deleteLaborIntensity(this.id)\">";
            table += '</td>';
            table += '</tr>';
        });
    }
    if (memSubdivisions.length !== 0) {
        table += '<tr>';
        table += '<td>';
        table += "<select size=\"1\" id=\"r_subdivision\" class=\"form-control input-sm\" >";
        table += '</td>';
        table += '<td>';
        table += "<input type=\"number\" id='plannedLaborIntensity_new' class=\"form-control input-sm\" >";
        table += '</td>';
        table += '<td>';
        table += "<input type=\"number\" id='plannedCost_new' class=\"form-control input-sm\" >";
        table += '</td>';
        table += '<td>';
        table += "<input  type=\"hidden\" class=\"btn del btn-danger custom-width\">";
        table += '</td>';
        table += '<td>';
        table += "<input type=\"submit\" class=\"btn btn-primary custom-width\"  value=\"Добавить\" onClick = \"addLaborIntensity()\">";
        table += '</td>';
        table += '</tr>';
    }
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

function editLaborIntensity(id) {
    const jPlannedLaborIntensity = checkEmptyInput("plannedLaborIntensity_" + id),
          jPlannedCost = checkEmptyInput("plannedCost_" + id),
          jSubdivision = checkEmptyInput("subdivision_" + id);
    if (jPlannedLaborIntensity && jPlannedCost && jSubdivision) {
        urlName = "/avectis/contracts/laboriousness/edit";
        type = "PUT";
        send(jPlannedLaborIntensity, jPlannedCost, jSubdivision, id);
    }
}

function addLaborIntensity() {
    const jPlannedLaborIntensity = checkEmptyInput("plannedLaborIntensity_new"),
          jPlannedCost = checkEmptyInput("plannedCost_new"),
          jSubdivision = checkSelect("r_subdivision");
    if (jPlannedLaborIntensity && jPlannedCost && jSubdivision) {
        let subdivision = $("#r_subdivision").find("[value='"+ jSubdivision +"']").text();
        urlName = "/avectis/contracts/laboriousness/new";
        type = "POST";
        send(jPlannedLaborIntensity, jPlannedCost, subdivision, null);
    }
}

function deleteLaborIntensity(id) {
    const token = $("meta[name='_csrf']").attr("content");
    const header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        type: "DELETE",
        url : "/avectis//contracts/laboriousness/" + id ,
        beforeSend: function(request) {
            request.setRequestHeader(header, token);
        },
        success: function(workers, textStatus, jqXHR){
            getLaborIntensity(contractId);
        }
    });
}

function send(jPlannedLaborIntensity, jPlannedCost, subdivision, id) {
    const token = $("meta[name='_csrf']").attr("content");
    const header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        type: "GET",
        url : "/avectis/contracts/contract/id-" + contractId,
        success: function(contract){
            contract.timeStart = contract.timeStart.year + "-" + addZero(contract.timeStart.monthValue) + "-" + addZero(contract.timeStart.dayOfMonth);
            contract.timeStop = contract.timeStop.year + "-" + addZero(contract.timeStop.monthValue) + "-" + addZero(contract.timeStop.dayOfMonth);
            let newSubdivision;
            subdivisionsIN.forEach(function (el) {
                if (el.name.localeCompare(subdivision) === 0) {
                    newSubdivision = el;
                }
            });
            let laborIntensity = new LaborEntensity(id, contract, newSubdivision, jPlannedLaborIntensity, jPlannedCost);
            const jsonString = JSON.stringify(laborIntensity, "", 4);
            $.ajax({
                url: urlName,
                type: type,
                contentType : 'application/json; charset=utf-8',
                data: jsonString,
                beforeSend: function(request) {
                    request.setRequestHeader(header, token);
                },
                success: function(result, textStatus, status){
                    if (status.status === 200) {
                        if (  id === undefined || id === null ){
                            getLaborIntensity(contractId);
                            alert("Добавлено успешно.");
                        } else {
                            getLaborIntensity(contractId);
                            alert("Данные изменены.");
                        }
                    }
                },
                error: function(error) {
                    if (error.status === 302) {
                        getLaborIntensity(contractId);
                        alert("Уже существует.");
                    } else {
                        if (error.status === 404) {
                            getLaborIntensity(contractId);
                            alert("Не найдено");
                        } else {
                            getLaborIntensity(contractId);
                            alert("Ошибка");
                        }
                    }
                }
            });
        }
    });

}

