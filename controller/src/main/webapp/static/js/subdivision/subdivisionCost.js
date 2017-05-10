class SubdivisionCost{
    constructor(id, subdivision, cost, date) {
        this.id = id;
        this.subdivision = subdivision;
        this.cost = cost;
        this.date = date;
    }
}

let subdivisionCostList = [];
let subdivisionCostDate;
let url;
let queryType;

function showSubdivisionsCost(date) {
    subdivisionCostDate = date + "-01";
    getSubdivisionsCost(subdivisionCostDate);
}

function getSubdivisionsCost(findDate) {
    subdivisionCostList = [];
    url = null;
    queryType = null;
    $.ajax({
        type: "GET",
        url : "/avectis/administration/subdivisions/cost-" + findDate,
        success: function(subdivisionsCostIN){
            if ($.isEmptyObject(subdivisionsCostIN)) {
                url = "/avectis/administration/subdivisions/cost/new";
                queryType = "POST";
                getSubdivisions();
            } else {
                url = "/avectis/administration/subdivisions/cost/edit";
                queryType = "PUT";
                subdivisionsCostIN.forEach(function (subdivisionCost) {
                    subdivisionCostList.push( new SubdivisionCost(subdivisionCost.id, subdivisionCost.subdivision, subdivisionCost.cost, subdivisionCostDate));
                });
                fillSubdivisionCostList();
            }
        }
    });
}

function getSubdivisions() {
    $.ajax({
        type: "GET",
        url : "/avectis/administration/subdivisionLists",
        success: function(subdivisionsIN){
            subdivisionsIN.forEach(function (subdivision) {
                subdivisionCostList.push( new SubdivisionCost(null, subdivision, 0, subdivisionCostDate));
            });
            fillSubdivisionCostList();
        }
    });
}

function fillSubdivisionCostList() {
    var table = "";
    table = '<thead>';
    table += '<tr>';
    table += '<th>Название</th>';
    table += '<th>Стоимость</th>';
    table += '</tr>';
    table += '</thead>';
    let i = 0;
    subdivisionCostList.forEach( function (cost) {
        table += '<tr>';
        table += "<td>";
        table += cost.subdivision.name;
        table += '</td>';
        table += '<td>';
        table += "<input type=\"number\" id=\""+ i +"\" class=\"form-control input-sm\" value=\"" +cost.cost + "\" onchange=\"editCost(this.id, this.value)\">";
        table += "<div id=\"" + i + "Error\" class=\"has-error\"></div>";
        table += '</td>';
        table += '</tr>';
        i++;
    });
    $(".table thead").remove();
    $(".table tbody").remove();
    $('.table').append(table);
}

function editCost(id, value) {
    subdivisionCostList[id].cost = value;
}

function saveCosts() {
    const token = $("meta[name='_csrf']").attr("content");
    const header = $("meta[name='_csrf_header']").attr("content");
    const strJSON = JSON.stringify(subdivisionCostList, "", 5);
    $.ajax({
        url: url,
        type: queryType,
        contentType : 'application/json; charset=utf-8',
        data: strJSON,
        beforeSend: function(request) {
            request.setRequestHeader(header, token);
        },
        success: function(result, textStatus, status){
            if (status.status === 200) {
                if (  queryType.localeCompare("POST") === 0 ){
                    alert("Добавлено успешно.");
                } else {
                    alert("Данные изменены.");
                }
                getSubdivisionsCost(subdivisionCostDate);
            }
        },
        error: function(error) {
            alert("Ошибка!!! Обратитесь к системному администратору.")
            document.location.href = "/avectis/administration/subdivisions/cost";
        }
    });
}