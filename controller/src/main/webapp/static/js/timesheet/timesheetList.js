let itemsOnPage = 10;
let pageNumber = 1;
let items = 1;
let url = "/avectis/timesheet/timesheets/" + pageNumber + "-" + itemsOnPage;
let stateSelIndex = 0;
$(document).ready(function() {
    getTimeSheets();
});

function createPagination() {
    $(function() {
        $('#light-pagination').pagination({
            items: items,
            itemsOnPage: itemsOnPage,
            currentPage: pageNumber,
            cssStyle: 'light-theme'
        });
    });
    $('#light-pagination').prop('hidden', true);
}

function changePage(newPageNumber) {
    pageNumber = newPageNumber;
    let selState = parseInt($("select#l_states").val());
    stateSelIndex = selState;
    let setContractNumber = $('#l_contractNumber').val();
    if ( selState === 0  || $.isEmptyObject(selState)) {
        if ($.isEmptyObject(setContractNumber)) {
            reloadContracts();
        } else {
            getContractsByNumber(setContractNumber);
        }
    } else {
        getContractsByState(selState);
    }
}

function changeItemsOnPage(countItems) {
    pageNumber = 1;
    itemsOnPage = countItems;
    let selState = parseInt($("select#l_states").val());
    stateSelIndex = selState;
    let setContractNumber = $('#l_contractNumber').val();
    if ( selState === 0  || $.isEmptyObject(selState)) {
        if ($.isEmptyObject(setContractNumber)) {
            reloadContracts();
        } else {
            getContractsByNumber(setContractNumber);
        }
    } else {
        getContractsByState(selState);
    }
    createPagination();
}

function findByState(state) {
    stateSelIndex = state;
    $('#l_contractNumber').val("");
    state = parseInt(state);
    if ( state === 0) {
        pageNumber = 1;
        reloadContracts();
    } else {
        getContractsByState(state);
    }
}

function findByContractNumber(contractNumber) {
    $("#l_states").find("[value='0']").attr("selected", "selected");
    stateSelIndex = 0;
    if ( $.isEmptyObject(contractNumber)) {
        pageNumber = 1;
        reloadContracts();
    } else {
        getContractsByNumber(contractNumber);
    }
}

function fillTimeSheetList(timeSheets) {
    $('.table').prop('hidden', true);
    let table = "";
    table += '<thead>';
    table += '<tr>';
    table += '<th>Дата</th>';
    table += '<th>Состояние</th>';
    table += '<th>Подразделение</th>';
    table += '<th width="140"></th>';
    table += '</tr>';
    table += '</thead>';
    timeSheets.forEach( function (timesheet) {
        table += '<tr>';
        table += '<td>';
        table += addZero(timesheet.date.monthValue) + " - " + timesheet.date.year;
        table += '</td>';
        table += '<td>';
        if (timesheet.state === true) {
            table += "Открыт";
        } else {
            table += "Закрыт";
        }
        table += '</td>';
        table += '<td>';
        table += timesheet.subdivision;
        table += '</td>';
        table += '<td>';
        table += "<a href='/avectis/timesheet/"+timesheet.id+"' class='btn btn-primary custom-width'>Редактировать</a>";
        table += '</td>';
        table += '</tr>';
    });

    $(".table thead").remove();
    $(".table tbody").remove();
    $('.table').append(table);
    $('.table').prop('hidden', false);
    $('#light-pagination').prop('hidden', false);

}

function reloadContracts() {
    url = "/avectis/contracts/" + pageNumber + "-" + itemsOnPage;
    getContracts();
}

function getContractsByState(state) {
    url = "/avectis/contracts/state/" + state + "-" + pageNumber + "-" + itemsOnPage;
    getContracts();
}

function getContractsByNumber(number) {
    url = "/avectis/contracts/number/" + number + "-" + pageNumber + "-" + itemsOnPage;
    getContracts();
}

function deleteContract(number) {
    const token = $("meta[name='_csrf']").attr("content");
    const header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        type: "DELETE",
        url : "/avectis/contracts/contract/" + number ,
        beforeSend: function(request) {
            request.setRequestHeader(header, token);
        },
        success: function(workers, textStatus, jqXHR){
            reloadContracts();
        }
    });
}

function getTimeSheets() {
    $.ajax({
        type: "GET",
        url : url ,
        success: function(timeSheets, textStatus, jqXHR){
            const newItems = parseInt(jqXHR.getResponseHeader("timeSheetCount"));
            if (items !== newItems) {
                items = newItems;
                createPagination();
            }
            if (pageNumber === 1) {
                createPagination();
            }
            fillTimeSheetList(timeSheets);
        }
    });
}
