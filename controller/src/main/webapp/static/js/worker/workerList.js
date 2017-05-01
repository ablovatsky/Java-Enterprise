let itemsOnPage = 10;
let pageNumber = 1;
let items = 1;
let url = "/avectis/administration/workers-" + pageNumber + "-" + itemsOnPage;
let subdivisionSelIndex = 0;
$(document).ready(function() {
    getWorkers();
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
    let selSubdivision = parseInt($("select#subdivision").val());
    subdivisionSelIndex = selSubdivision;
    let setLastName = $('#l_lastName').val();
    if ( selSubdivision === 0  || $.isEmptyObject(selSubdivision)) {
        if ($.isEmptyObject(setLastName)) {
            reloadWorkers();
        } else {
            getWorkersByLastName(setLastName);
        }
    } else {
        getWorkersBySubdivisionId(selSubdivision);
    }
}

function changeItemsOnPage(countItems) {
    pageNumber = 1;
    itemsOnPage = countItems;
    let selSubdivision = parseInt($("select#l_subdivision").val());
    subdivisionSelIndex = selSubdivision;
    let setLastName = $('#l_lastName').val();
    if ( selSubdivision === 0 ) {
        if ($.isEmptyObject(setLastName)) {
            reloadWorkers();
        } else {
            getWorkersByLastName(setLastName);
        }
    } else {
        getWorkersBySubdivisionId(selSubdivision);
    }
    createPagination();
}

function findBySubdivision(subdivisionId) {
    $('#l_lastName').val("");
    subdivisionId = parseInt(subdivisionId);
    subdivisionSelIndex = subdivisionId;
    if ( subdivisionId === 0) {
        pageNumber = 1;
        reloadWorkers();
    } else {
        getWorkersBySubdivisionId(subdivisionId);
    }
}

function findByLastName(lastName) {
    $("#l_subdivision").find("[value='0']").attr("selected", "selected");
    subdivisionSelIndex = 0;
    if ( $.isEmptyObject(lastName)) {
        pageNumber = 1;
        reloadWorkers();
    } else {
        getWorkersByLastName(lastName);
    }
}

function fillWorkerList(workers, subdivisions) {
    $('.table').prop('hidden', true);
    let table = "";
    $('#l_subdivision').html('');
    $("#l_subdivision").append( $('<option value=\'0\'>Все подразделения</option>'));
    subdivisions.forEach( function (subdivision) {
        $("#l_subdivision")
            .append( $('<option value=' + subdivision.id + '>' + subdivision.name + '</option>'));
    });
    $("#l_subdivision").find("[value='" + subdivisionSelIndex + "']").attr("selected", "selected");
    table += '<thead>';
    table += '<tr>';
    table += '<th>SSO ID</th>';
    table += '<th>Фамилия</th>';
    table += '<th>Имя</th>';
    table += '<th>Подразделение</th>';
    table += '<th width="150"></th>';
    table += '<th width="150"></th>';
    table += '</tr>';
    table += '</thead>';
    workers.forEach( function (worker) {
        table += '<tr>';
        table += '<td>';
        table += worker.ssoId;
        table += '</td>';
        table += '<td>';
        table += worker.lastName;
        table += '</td>';
        table += '<td>';
        table += worker.firstName;
        table += '</td>';
        table += '<td>';
        table += worker.subdivision;
        table += '</td>';
        table += '<td>';
        table += "<a href='/avectis/administration/workers/"+worker.ssoId+"' class='btn btn-primary custom-width'>Редактировать</a>";
        table += '</td>';
        table += '<td>';
        table += "<input type=\"submit\" class=\"btn del btn-danger custom-width\" id=\""+ worker.ssoId +"\" value=\"Удалить\" onClick = \"deleteWorker(this.id)\">";
        table += '</td>';
        table += '</tr>';
    });

    $(".table thead").remove();
    $(".table tbody").remove();
    $('.table').append(table);
    $('.table').prop('hidden', false);
    $('#light-pagination').prop('hidden', false);

}

function reloadWorkers() {
    url = "/avectis/administration/workers-" + pageNumber + "-" + itemsOnPage;
    getWorkers();
}

function getWorkersBySubdivisionId(subdivisionId) {
    url = "/avectis/administration//workers/subdivision/" + subdivisionId + "-" + pageNumber + "-" + itemsOnPage;
    getWorkers();
}

function getWorkersByLastName(lastName) {
    url = "/avectis/administration//workers/lastname/" + lastName + "-" + pageNumber + "-" + itemsOnPage;
    getWorkers();
}

function deleteWorker(ssoId) {
    const token = $("meta[name='_csrf']").attr("content");
    const header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        type: "DELETE",
        url : "/avectis/administration/workers/" + ssoId ,
        beforeSend: function(request) {
            request.setRequestHeader(header, token);
        },
        success: function(workers, textStatus, jqXHR){
            reloadWorkers();
        }
    });
}

function getWorkers() {
    $.ajax({
        type: "GET",
        url : url ,
        success: function(workers, textStatus, jqXHR){
            const newItems = parseInt(jqXHR.getResponseHeader("workerCount"));
            if (items !== newItems) {
                items = newItems;
                createPagination();
            }
            getSubdivisions(workers);
            if (pageNumber === 1) {
                createPagination();
            }
        }
    });
}

function getSubdivisions(workers) {
    $.ajax({
        type: "GET",
        url : "/avectis/administration/subdivisionLists" ,
        success: function(subdivisions) {
            fillWorkerList(workers, subdivisions);
        }
    });
}