var urlName;

$(document).ready(function() {
    var ssoId= $("#hdnSession").data('value');
    if (ssoId.localeCompare("newWorker")) {
        $.ajax({
            type: "GET",
            url : "/contracts/get-edit-worker-" + ssoId,
            success: function(result){
                var json_data= JSON.parse(result);
                urlName = "";
                fillEditWorker(json_data);
            }
         });
    } else {
        $.ajax({
            type: "GET",
            url : "/contracts/getNewWorker",
            success: function(result){
                var json_data= JSON.parse(result);
                urlName = "/contracts/newworker";
                fillNewWorker(json_data);
            }
        });
    }

});

function fillNewWorker(data) {
    $('.loggedinworker').text(data.loggedinworker);
    $('.well').text("Регистрация нового рабочего");
    $('.btn').val("Создать");

    data.roles.forEach(function ( role ) {
        $("#workerProfiles").append( $('<option value=' + role.id + '>' + role.type + '</option>'));
    });
    data.subdivision.forEach(function ( subdivision ) {
        $("#subdivision").append( $('<option value=' + subdivision.id + '>' + subdivision.name + '</option>'));
    });
}

function fillEditWorker(data) {
    $('.loggedinworker').text(data.loggedinworker);
    $('.btn').val("Изменить");
    $('.well').text("Редактирование данных рабочего");
    $('#firstName').val(data.worker.firstName);
    $('#lastName').val(data.worker.lastName);
    $('#patronymic').val(data.worker.patronymic);
    $('#ssoId').val(data.worker.ssoId).prop('disabled', true);
    $('#password').val(data.worker.password);
    $('#email').val(data.worker.email);

    data.roles.forEach(function ( role ) {
        $("#workerProfiles").append( $('<option value=' + role.id + '>' + role.type + '</option>'));
        for (var i = 0; i < data.worker.workerProfiles.length; i++) {
            if (role.type.localeCompare(data.worker.workerProfiles[i]) === 0) {
                $("#workerProfiles").find("[value='"+ role.id +"']").attr("selected", "selected");
            }
        }
    });

    data.subdivision.forEach(function ( subdivision ) {
        $("#subdivision").append( $('<option value=' + subdivision.id + '>' + subdivision.name + '</option>'));
        if (subdivision.name.localeCompare(data.worker.subdivision) === 0) {
            $("#subdivision").find("[value='"+ subdivision.id +"']").attr("selected", "selected");
        }
    });
}

function saveWorker() {
    var json = "";

    var jFirstName      = checkEmptyInput("firstName"),
        jLastName       = checkEmptyInput("lastName"),
        jPatronymic     = checkEmptyInput("patronymic"),
        jSsoId          = checkEmptyInput("ssoId"),
        jPassword       = checkEmptyInput("password"),
        jEmail          = checkEmptyInput("email"),
        jWorkerProfiles = checkSelect("workerProfiles"),
        jSubdivision    = checkSelect("subdivision");

    if (jFirstName && jLastName && jPatronymic && jSsoId && jPassword && jEmail && jWorkerProfiles && jSubdivision) {
        json += "{ \"firstName\": \"" + jFirstName + " \", ";
        json += "\"lastName\": \"" + jLastName + " \", ";
        json += "\"patronymic\": \"" + jPatronymic + " \", ";
        json += "\"ssoId\": \"" + jSsoId + " \", ";
        json += "\"password\": \"" + jPassword + " \", ";
        json += "\"email\": \"" + jEmail + " \", ";
        json += "\"workerProfiles\": [";
        var profiles = "";
        for (var i = 0; i < jWorkerProfiles.length; i++) {
            if (i === 0) {
                profiles += "{\"id\": " + jWorkerProfiles[i] + ", \"type\": \"" + $("#workerProfiles").find("[value='"+ jWorkerProfiles[i] +"']").text() + "\"}";
            } else {
                profiles += ", {\"id\": " + jWorkerProfiles[i] + ", \"type\": \"" + $("#workerProfiles").find("[value='"+ jWorkerProfiles[i] +"']").text() + "\"}";
            }
        }
        json += profiles;
        json += "], ";
        json += "\"subdivision\": [";
        json += "{\"id\": " + jSubdivision + ", \"name\": \"" + $("#subdivision").find("[value='"+ jSubdivision +"']").text() + "\"}";
        json += "]\"}";
        alert(json);
    }
    send(json);
}

function send(jsonString) {
    $.ajax({
        url: "/contracts/newworker",
        type: "POST",
        data: jsonString,
        success: function(resposeJsonObject){
            alert(resposeJsonObject);
        }
    });
}

function checkEmptyInput(id) {
    var value = $("#" + id).val().replace(/^\s*/,'').replace(/\s*$/,'');
    $("#" + id).val(value);
    if ($.isEmptyObject(value)) {
        $("#" + id + "Error").text("Данное поле обязательно для заполнения!");
        return false;
    } else {
        $("#" + id + "Error").text("");
        return value;
    }
}

function checkSelect(id) {
    var selectId = $("#" + id).val();
    if (!selectId) {
        $("#" + id + "Error").text("Данное поле обязательно для заполнения!");
        return false;
    } else {
        $("#" + id + "Error").text("");
        return selectId;
    }

}