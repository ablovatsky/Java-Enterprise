let itemsOnPage = 10;
let pageNumber = 1;
let items = 1;
let url = "/contracts/administration/workers-" + pageNumber + "-" + itemsOnPage;
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
    if ( subdivisionId === 0) {
        pageNumber = 1;
        reloadWorkers();
    } else {
        getWorkersBySubdivisionId(subdivisionId);
    }
}

function findByLastName(lastName) {
    $("#l_subdivision").find("[value='0']").attr("selected", "selected");
    if ( $.isEmptyObject(lastName)) {
        pageNumber = 1;
        reloadWorkers();
    } else {
        getWorkersByLastName(lastName);
    }
}

function fillWorkerList(workers, subdivisions) {
    $('.table').prop('hidden', true);
    let subdivisionTable = "";
    $("#l_subdivision").append( $('<option value=\'0\'>Все подразделения</option>'));
    subdivisions.forEach( function (subdivision) {
        $("#l_subdivision")
            .append( $('<option value=' + subdivision.id + '>' + subdivision.name + '</option>'));
    });
    subdivisionTable += '<thead>';
    subdivisionTable += '<tr>';
    subdivisionTable += '<th>SSO ID</th>';
    subdivisionTable += '<th>Фамилия</th>';
    subdivisionTable += '<th>Имя</th>';
    subdivisionTable += '<th>Подразделение</th>';
    subdivisionTable += '<th width="150"></th>';
    subdivisionTable += '<th width="150"></th>';
    subdivisionTable += '</tr>';
    subdivisionTable += '</thead>';
    workers.forEach( function (worker) {
        subdivisionTable += '<tr>';
        subdivisionTable += '<td>';
        subdivisionTable += worker.ssoId;
        subdivisionTable += '</td>';
        subdivisionTable += '<td>';
        subdivisionTable += worker.lastName;
        subdivisionTable += '</td>';
        subdivisionTable += '<td>';
        subdivisionTable += worker.firstName;
        subdivisionTable += '</td>';
        subdivisionTable += '<td>';
        subdivisionTable += worker.subdivision;
        subdivisionTable += '</td>';
        subdivisionTable += '<td>';
        subdivisionTable += "<a href='/contracts/administration/workers/"+worker.ssoId+"' class='btn btn-primary custom-width'>Редактировать</a>";
        subdivisionTable += '</td>';
        subdivisionTable += '<td>';
        subdivisionTable += "<input type=\"submit\" class=\"btn del btn-danger custom-width\" id=\""+ worker.ssoId +"\" value=\"Удалить\" onClick = \"deleteWorker(this.id)\">";
        subdivisionTable += '</td>';
        subdivisionTable += '</tr>';
    });

    $(".table thead").remove();
    $(".table tbody").remove();
    $('.table').append(subdivisionTable);
    $('.table').prop('hidden', false);
    $('#light-pagination').prop('hidden', false);

}

function reloadWorkers() {
    url = "/contracts/administration/workers-" + pageNumber + "-" + itemsOnPage;
    getWorkers();
}

function getWorkersBySubdivisionId(subdivisionId) {
    url = "/contracts/administration//workers/subdivision/" + subdivisionId + "-" + pageNumber + "-" + itemsOnPage;
    getWorkers();
}

function getWorkersByLastName(lastName) {
    url = "/contracts/administration//workers/lastname/" + lastName + "-" + pageNumber + "-" + itemsOnPage;
    getWorkers();
}

function deleteWorker(ssoId) {
    const token = $("meta[name='_csrf']").attr("content");
    const header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        type: "DELETE",
        url : "/contracts/administration/workers/" + ssoId ,
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
        url : "/contracts/administration/subdivisionLists" ,
        success: function(subdivisions) {
            fillWorkerList(workers, subdivisions);
        }
    });
}