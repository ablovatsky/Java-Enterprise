let itemsOnPage = 10;
let pageNumber = 1;
let items = 1;
let url = "/avectis/contracts/" + pageNumber + "-" + itemsOnPage;
let stateSelIndex = 0;
$(document).ready(function() {
    getContracts();
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

function fillContractList(contracts, states) {
    $('.table').prop('hidden', true);
    let table = "";
    $('#l_states').html('');
    $("#l_states").append( $('<option value=\'0\'>Все состояния</option>'));
    states.forEach( function (state) {
        $("#l_states")
            .append( $('<option value=' + state.id + '>' + state.type + '</option>'));
    });
    $("#l_states").find("[value='" + stateSelIndex + "']").attr("selected", "selected");
    table += '<thead>';
    table += '<tr>';
    table += '<th>Номер</th>';
    table += '<th>Название</th>';
    table += '<th>АХ код</th>';
    table += '<th>Начало</th>';
    table += '<th>Окончание</th>';
    table += '<th>Состояние</th>';
    table += '<th width="140"></th>';
    table += '<th width="140"></th>';
    table += '<th width="140"></th>';
    table += '</tr>';
    table += '</thead>';
    contracts.forEach( function (contract) {
        table += '<tr>';
        table += '<td>';
        table += contract.number;
        table += '</td>';
        table += '<td>';
        table += contract.name;
        table += '</td>';
        table += '<td>';
        table += contract.axCode;
        table += '</td>';
        table += '<td>';
        table += addZero(contract.timeStart.monthValue) + " - " + contract.timeStart.year;
        table += '</td>';
        table += '<td>';
        table += addZero(contract.timeStop.monthValue) + " - " + contract.timeStop.year;
        table += '</td>';
        table += '<td>';
        table += contract.contractState.type;
        table += '</td>';
        table += '<td>';
        table += "<a href='/avectis/contracts/laboriousness/"+contract.id+"' class='btn btn-primary custom-width'>Трудоемкость</a>";
        table += '</td>';
        table += '<td>';
        table += "<a href='/avectis/contracts/contract/"+contract.id+"' class='btn btn-primary custom-width'>Редактировать</a>";
        table += '</td>';
        table += '<td>';
        table += "<input type=\"submit\" class=\"btn del btn-danger custom-width\" id=\""+ contract.id +"\" value=\"Удалить\" onClick = \"deleteContract(this.id)\">";
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

function getContracts() {
    $.ajax({
        type: "GET",
        url : url ,
        success: function(contracts, textStatus, jqXHR){
            const newItems = parseInt(jqXHR.getResponseHeader("contractCount"));
            if (items !== newItems) {
                items = newItems;
                createPagination();
            }
            getContractStates(contracts);
            if (pageNumber === 1) {
                createPagination();
            }
        }
    });
}

function getContractStates(contracts) {
    $.ajax({
        type: "GET",
        url : "/avectis/contracts/states" ,
        success: function(states) {
            fillContractList(contracts, states);
        }
    });
}