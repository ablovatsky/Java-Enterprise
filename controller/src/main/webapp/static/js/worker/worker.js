
class Worker {
    constructor(id, ssoId, password, firstName, lastName, patronymic, email, subdivision, workerProfiles ) {
        this.id = id;
        this.ssoId = ssoId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.subdivision = subdivision;
        this.workerProfiles = workerProfiles
    }
}

let urlName;
let workerId;

$(document).ready(function() {
    let ssoId = $("#hdnSession").data('value');
    if ((typeof ssoId).localeCompare("number") === 0) {
        ssoId = ssoId.toString();
    }
    if (ssoId.localeCompare("newWorker")) {
        $.ajax({
            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "/contracts/get-edit-worker-" + ssoId,
            success: function(result){
                urlName = "/contracts/edit-worker-" + ssoId;
                fillEditWorker(result);
            }
         });
    } else {
        $.ajax({
            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "/contracts/get-new-worker",
            success: function(result){
                urlName = "/contracts/new-worker";
                fillNewWorker(result);
            }
        });
    }

});

function fillNewWorker(data) {
    $('.loggedinworker').text(data.map.loggedinworker);
    $('.well').text("Регистрация нового рабочего");
    $('.btn').val("Создать");

    var i = 0;
    var rolesSize = data.map.roles.myArrayList.length;
    var subdivisionsSize = data.map.roles.myArrayList.length;
    for(i = 0; i < rolesSize; i++) {
        $("#workerProfiles").append( $('<option value=' + data.map.roles.myArrayList[i].map.id + '>' + data.map.roles.myArrayList[i].map.type + '</option>'));
    }
    for(i = 0; i < subdivisionsSize; i++) {
        $("#subdivision").append( $('<option value=' + data.map.subdivision.myArrayList[i].map.id + '>' + data.map.subdivision.myArrayList[i].map.name + '</option>'));
    }
}

function fillEditWorker(data) {
    $('.loggedinworker').text(data.map.loggedinworker);
    $('.btn').val("Изменить");
    $('.well').text("Редактирование данных рабочего");
    workerId = data.map.worker.map.id;
    $('#firstName').val(data.map.worker.map.firstName);
    $('#lastName').val(data.map.worker.map.lastName);
    $('#patronymic').val(data.map.worker.map.patronymic);
    $('#ssoId').val(data.map.worker.map.ssoId).prop('disabled', true);
    $('#password').val(data.map.worker.map.password);
    $('#email').val(data.map.worker.map.email);

    var roles = data.map.roles.myArrayList;
    var workerRoles = data.map.worker.map.workerProfiles.myArrayList;

    var subdivisions = data.map.subdivision.myArrayList;
    var workerSubdivision = data.map.worker.map.subdivision;


    for(var i = 0; i < roles.length; i++) {
        var id = roles[i].map.id;
        var type = roles[i].map.type;
        $("#workerProfiles").append( $('<option value=' + id + '>' + type + '</option>'));
        for (var y = 0; y < workerRoles.length; y++) {
            if (type.localeCompare(workerRoles[y]) === 0) {
                $("#workerProfiles").find("[value='"+ id +"']").attr("selected", "selected");
            }
        }
    }
    for(var i = 0; i < subdivisions.length; i++) {
        $("#subdivision").append( $('<option value=' + subdivisions[i].map.id + '>' + subdivisions[i].map.name + '</option>'));
        if (subdivisions[i].map.name.localeCompare(workerSubdivision) === 0) {
            $("#subdivision").find("[value='"+ subdivisions[i].map.id +"']").attr("selected", "selected");
        }
    }
}

function saveWorker() {
    var jFirstName      = checkEmptyInput("firstName"),
        jLastName       = checkEmptyInput("lastName"),
        jPatronymic     = checkEmptyInput("patronymic"),
        jSsoId          = checkEmptyInput("ssoId"),
        jPassword       = checkEmptyInput("password"),
        jEmail          = checkEmptyInput("email"),
        jWorkerProfiles = checkSelect("workerProfiles"),
        jSubdivision    = checkSelect("subdivision");

    if ( jFirstName && jLastName && jPatronymic && jSsoId && jPassword && jEmail && jWorkerProfiles && jSubdivision) {
        let subdivisionObj = {};
        subdivisionObj["id"] = jSubdivision;
        subdivisionObj["name"] = $("#subdivision").find("[value='"+ jSubdivision +"']").text();
        let workerProfilesMass = [];
        for (let i = 0; i < jWorkerProfiles.length; i++) {
            let workerProfilesObj = {};
            workerProfilesObj["id"] = jWorkerProfiles[i];
            workerProfilesObj["type"] = $("#workerProfiles").find("[value='"+ jWorkerProfiles[i] +"']").text();
            workerProfilesMass.push(workerProfilesObj);
        }
        let myWorker = new Worker(workerId, jSsoId, jPassword, jFirstName, jLastName, jPatronymic, jEmail, subdivisionObj, workerProfilesMass);
        const workerJson = JSON.stringify(myWorker, "", 4);
        send(workerJson);
    }

}

function send(jsonString) {
    const token = $("meta[name='_csrf']").attr("content");
    const header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        url: urlName,
        type: "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        data: jsonString,
        beforeSend: function(request) {
            request.setRequestHeader(header, token);
        },
        success: function(result){
            let msgResult = result.map.state;
            if (msgResult.localeCompare("ok") === 0) {
                document.location.href = "/contracts/list";
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert("some error");
        }
    });
}

function checkEmptyInput(id) {
    const value = $("#" + id).val().replace(/^\s*/, '').replace(/\s*$/, '');
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
    let selectId = $("#" + id).val();
    if (!selectId) {
        $("#" + id + "Error").text("Данное поле обязательно для заполнения!");
        return false;
    } else {
        $("#" + id + "Error").text("");
        return selectId;
    }

}
