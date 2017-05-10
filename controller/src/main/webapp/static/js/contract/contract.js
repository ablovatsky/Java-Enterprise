class Contract {
    constructor(
        id,
        number,
        name,
        denomination,
        axCode,
        timeStart,
        timeStop,
        cost,
        plannedSurcharge,
        plannedProfit,
        contractState,
        commentFirst,
        commentSecond
    ) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.denomination = denomination;
        this.axCode = axCode;
        this.timeStart = timeStart;
        this.timeStop = timeStop;
        this.cost = cost;
        this.plannedSurcharge = plannedSurcharge;
        this.plannedProfit = plannedProfit;
        this.contractState = contractState;
        this.commentFirst = commentFirst;
        this.commentSecond = commentSecond
    }
}

let type;
let urlName;
let contractId;

$(document).ready(function() {
    let id = $("#hdnSession").data('value');
    if ((typeof id).localeCompare("number") === 0) {
        id = id.toString();
    }
    if (id.localeCompare("newContract")) {
        $.ajax({
            type: "GET",
            url : "/avectis/contracts/contract/id-" + id,
            success: function(contract){
                type = "PUT";
                urlName = "/avectis/contracts/contract/edit";
                getContractStates(contract);
            }
        });
    } else {
        getContractStates();
        type = "POST";
        urlName = "/avectis/contracts/new";
    }
});

function getContractStates(contract) {
    $.ajax({
        type: "GET",
        url : "/avectis/contracts/states" ,
        success: function(contractStates) {
            if ($.isEmptyObject(contract)) {
                fillNewContract(contractStates);
            } else {
                fillEditContract(contract, contractStates);
            }
        }
    });
}

function fillNewContract(contractStates) {
    $('.well').text("Регистрация нового договора");
    $('.btn').val("Создать");
    contractStates.forEach( function (state) {
        let id = state.id;
        let type = state.type;
        $("#state").append( $('<option value=' + id + '>' + type + '</option>'));
    });
}

function fillEditContract(contract, contractStates) {
    contractId = contract.id;
    $('.btn').val("Изменить");
    $('.well').text("Редактирование данных договора");
    $("#number").val(contract.number);
    $("#name").val(contract.name);
    $("#denomination").val(contract.denomination);
    $("#axCode").val(contract.axCode);
    $("#timeStart").val(contract.timeStart.year + "-" + addZero(contract.timeStart.monthValue));
    $("#timeStop").val(contract.timeStop.year + "-" + addZero(contract.timeStop.monthValue));
    $("#cost").val(contract.cost);
    $("#plannedSurcharge").val(contract.plannedSurcharge);
    $("#plannedProfit").val(contract.plannedProfit);
    contractStates.forEach( function (state) {
        let id = state.id;
        let type = state.type;
        $("#state").append( $('<option value=' + id + '>' + type + '</option>'));
        if (type.localeCompare(contract.contractState.type) === 0) {
            $("#state").find("[value='"+ id +"']").attr("selected", "selected");
        }
    });
    $("#commentFirst").val(contract.commentFirst);
    $("#commentSecond").val(contract.commentSecond);
}

function saveContract() {
    const
        jNumber = checkEmptyInput("number"),
        jName = checkEmptyInput("name"),
        jDenomination = checkEmptyInput("denomination"),
        jAxCode = checkEmptyInput("axCode"),
        jTimeStart = checkEmptyInput("timeStart") + "-01",
        jTimeStop = checkEmptyInput("timeStop"),
        jCost = checkEmptyInput("cost"),
        jPlannedSurcharge = checkEmptyInput("plannedSurcharge"),
        jPlannedProfit = checkEmptyInput("plannedProfit"),
        jState = checkSelect("state"),
        jCommentFirst = checkEmptyInput("commentFirst"),
        jCommentSecond = checkEmptyInput("commentSecond");


    if (jNumber && jName && jDenomination && jAxCode && jTimeStart && jTimeStop && jCost && jPlannedSurcharge && jPlannedProfit && jState && jCommentFirst && jCommentSecond) {

        let stateObj = {};
        stateObj["id"] = jState;
        stateObj["type"] = $("#state").find("[value='"+ jState +"']").text();
        let end = new Date(jTimeStop);
        end.setMonth(end.getMonth() + 1);
        end.setDate(end.getDate() - 1);
        let endTime = jTimeStop + "-" + end.getDate();
        let contract = new Contract(contractId, jNumber, jName, jDenomination, jAxCode, jTimeStart.toString(), endTime.toString(), jCost, jPlannedSurcharge, jPlannedProfit, stateObj, jCommentFirst, jCommentSecond);
        const contractJSON = JSON.stringify(contract, "", 4);
        send(contractJSON, contractId);
    }
}

function send(jsonString, contractId) {
    const token = $("meta[name='_csrf']").attr("content");
    const header = $("meta[name='_csrf_header']").attr("content");
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
                if (  contractId === undefined || contractId === null ){
                    document.location.href = "/avectis/contracts/";
                    alert("Договор добавлен успешно.");
                } else {
                    document.location.href = "/avectis/contracts/";
                    alert("Данные договора изменены.");
                }
            }
        },
        error: function(error) {
            if (error.status === 302) {
                $("#ssoIdError").text("Договор <<" + contractId + ">> уже зарегистрирован. Введите другой номер договора.");
            } else {
                if (error.status === 404) {
                    alert("Договор <<" + contractId + ">> не найден.");
                    document.location.href = "/avectis/contracts/";
                } else {
                    alert("Ошибка");
                    document.location.href = "/avectis/contracts/";
                }
            }
        }
    });
}