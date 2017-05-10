class Employment {
    constructor(contractNumber, workerSSOID, workedHours){
        this.contractNumber = contractNumber;
        this.workerSSOID = workerSSOID;
        this.workedHours = workedHours;
    }
}

let url;
let new_edit_url;
let contracts;
let workers;
let selectedContracts;
let employments;

$(document).ready(function() {
    let timeSheetId = $("#hdnSession").data('value');
    if ((typeof timeSheetId).localeCompare("number") === 0) {
       timeSheetId = timeSheetId.toString();
    }
    if (timeSheetId.localeCompare("newTimeSheet") === 0) {
        url = "/avectis/timesheet/newTimesheet";
        new_edit_url = "/avectis/timesheet/timesheet/new";
        getNewTimeSheet();
    } else {
        url = "/avectis/timesheet/getTimesheet-" + timeSheetId;
        new_edit_url = "/avectis/timesheet/timesheet/edit-" + timeSheetId;
        getNewTimeSheet();
    }
});

function getNewTimeSheet() {
    $.ajax({
        type: "GET",
        url : url,
        success: function(data){
            workers = data.workerSet.slice();
            contracts = data.contractNumberSet.slice();
            employments = data.employmentSet.slice();
            selectedContracts = data.selectedContractNumberSet.slice();
            fillNewTimeSheet(data);
        }
    });
}

function fillNewTimeSheet() {
    let memContracts = contracts.slice();

    /*==================================time_sheet_table===================================================*/
    $('.time_sheet_table').prop('hidden', true);
    let table = "";
    table += '<thead>';
    table += '<tr>';
    table += '<th id=\'td_c\'>Договора</th>';
    workers.forEach(function (worker) {
        table += "<th id='td_w'><p class='vertical-text'>" + worker.lastName + " " + worker.firstName + "</p></th>";
    });
    table += '<th width="130"></th>';
    table += '</tr>';
    table += '</thead>';
    if (!$.isEmptyObject(selectedContracts)) {
        selectedContracts.forEach(function (contract) {
            $.each(memContracts, function (i) {
                if (memContracts[i] === contract) {
                    memContracts.splice(i, 1);
                    return false;
                }
            });
            table += '<tr>';
            table += "<td ><input type=\"text\" id=\""+ contract + "\" value='" + contract + "' class='form-control input-sm' ></td>";
            workers.forEach(function (worker) {
                let value = 0;
                employments.forEach(function (employment) {
                   if (employment.contractNumber.localeCompare(contract) === 0 && employment.workerSSOID.localeCompare(worker.ssoId) === 0) {
                       value = employment.workedHours;
                   }
                });
                table += "<td ><input type=\"number\" id=\""+ worker.ssoId + "_" + contract + "\" value='" + value + "' class='form-control input-sm' onchange=\"changeWorkedHours(this.id, this.value)\" ></td>";
            });
            table += '<td>';
            table += "<input type=\"submit\" class=\"btn del del-time-sheet btn-danger custom-width\" id='" + contract + "' value=\"Удалить\" onClick = \"removeEmployment(this.id)\">";
            table += '</td>';
            table += '</tr>';
        });
    }
    if (memContracts.length !== 0) {
        table += '<tr>';
        table += '<td id=\'td_c\'>';
        table += "<select size=\"1\" id=\"r_contracts\" class=\"form-control input-sm\" >";
        table += '</td>';
        workers.forEach(function (worker) {
            table += "<td ><input type=\"number\" id=\""+ worker.ssoId + "_new\" class='form-control input-sm' ></td>";
        });
        table += '<td>';
        table += "<input type=\"submit\" class=\"btn btn-primary btn-primary-time-sheet custom-width\"  value=\"Добавить\" onClick = \"addEmployment()\">";
        table += '</td>';
        table += '</tr>';
    }
    $(".time_sheet_table thead").remove();
    $(".time_sheet_table tbody").remove();
    $('.time_sheet_table').append(table);

    memContracts.forEach( function (contract) {
        $("#r_contracts")
            .append( $('<option value=' + contract + '>' + contract + '</option>'));
    });
    $('.time_sheet_table').prop('hidden', false);
}

function addEmployment() {
    let contract = $("#r_contracts").val();
    selectedContracts.push(contract);
    workers.forEach(function (worker) {
        let value = $("#" +worker.ssoId + "_new").val().replace(/^\s*/, '').replace(/\s*$/, '');
        if ($.isEmptyObject(value)) {
            value = 0;
        }
        employments.push(new Employment(contract, worker.ssoId, value));
    });
    fillNewTimeSheet();
}

function removeEmployment(contractId) {
    for(let i = 0; i < employments.length; i++){
        if(employments[i].contractNumber.localeCompare(contractId) === 0){
            employments.splice(i, 1);
            i--;
        }
    }

    for(let i = 0; i < selectedContracts.length; i++){
        if(selectedContracts[i].localeCompare(contractId) === 0){
            selectedContracts.splice(i, 1);
            i--;
        }
    }
    fillNewTimeSheet();
}

function changeWorkedHours(id, value) {
    let arr = id.split('_');
    employments.forEach(function (employment) {
        if (employment.workerSSOID.localeCompare(arr[0]) === 0 && employment.contractNumber.localeCompare(arr[1]) === 0) {
            employment.workedHours = value;
        }
    })
}

function saveTimeSheet() {
    const token = $("meta[name='_csrf']").attr("content");
    const header = $("meta[name='_csrf_header']").attr("content");
    const jsonString = JSON.stringify(employments);
    $.ajax({
        url : new_edit_url,
        type: "POST",
        contentType : 'application/json; charset=utf-8',
        data: jsonString,
        beforeSend: function(request) {
            request.setRequestHeader(header, token);
        },
        success: function(){
           alert("OK");
        }
    });
}