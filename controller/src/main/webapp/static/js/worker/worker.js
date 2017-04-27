
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

let type;
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
            url : "/contracts/administration/workers/worker/" + ssoId,
            success: function(worker){
                type = "PUT";
                urlName = "/contracts/administration/workers/worker/edit";
                getSubdivisions(worker);
            }
        });
    } else {
        getSubdivisions();
        type = "POST";
        urlName = "/contracts/administration/workers/worker/new";
    }
});

function fillNewWorker(subdivisions, profiles) {
    $('.well').text("Регистрация нового рабочего");
    $('.btn').val("Создать");
    profiles.forEach( function (profile) {
        let id = profile.id;
        let type = profile.type;
        $("#workerProfiles").append( $('<option value=' + id + '>' + type + '</option>'));
    });

    subdivisions.forEach( function (subdivision) {
        let id = subdivision.id;
        let name = subdivision.name;
        $("#subdivision").append( $('<option value=' + id + '>' + name + '</option>'));
    });
}

function fillEditWorker(worker, subdivisions, profiles) {
    $('.btn').val("Изменить");
    $('.well').text("Редактирование данных рабочего");
    workerId = worker.id;
    $('#firstName').val(worker.firstName);
    $('#lastName').val(worker.lastName);
    $('#patronymic').val(worker.patronymic);
    $('#ssoId').val(worker.ssoId).prop('disabled', true);
    $('#password').val(worker.password);
    $('#email').val(worker.email);

    profiles.forEach( function (profile) {
        let id = profile.id;
        let type = profile.type;
        $("#workerProfiles").append( $('<option value=' + id + '>' + type + '</option>'));
        worker.workerProfiles.forEach( function (workerProfile) {
           if (workerProfile.localeCompare(type) === 0) {
               $("#workerProfiles").find("[value='"+ id +"']").attr("selected", "selected");
           }
        });
    });

    subdivisions.forEach( function (subdivision) {
        let id = subdivision.id;
        let name = subdivision.name;
        $("#subdivision").append( $('<option value=' + id + '>' + name + '</option>'));
        if (worker.subdivision.localeCompare(name) === 0) {
            $("#subdivision").find("[value='"+ id +"']").attr("selected", "selected");
        }
    });

}

function saveWorker() {
    const jFirstName = checkEmptyInput("firstName"),
        jLastName = checkEmptyInput("lastName"),
        jPatronymic = checkEmptyInput("patronymic"),
        jSsoId = checkEmptyInput("ssoId"),
        jPassword = checkEmptyInput("password"),
        jEmail = checkEmptyInput("email"),
        jWorkerProfiles = checkSelect("workerProfiles"),
        jSubdivision = checkSelect("subdivision");

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
        send(workerJson, jSsoId);
    }

}

function send(jsonString, jSsoId) {
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
                if (  workerId === undefined || workerId === null ){
                    document.location.href = "/contracts/administration/workers";
                    alert("Работник <<" + jSsoId + ">> добавлен успешно.");
                } else {
                    document.location.href = "/contracts/administration/workers";
                    alert("Данные работника <<" + jSsoId + ">> изменены.");
                }
            }
        },
        error: function(error) {
            if (error.status === 302) {
                $("#ssoIdError").text("Работник <<" + jSsoId + ">> уже зарегистрирован. Введите дрогое SSO ID.");
            } else {
                if (error.status === 404) {
                    alert("Работник <<" + jSsoId + ">> не найден.");
                    document.location.href = "/contracts/administration/workers";
                } else {
                    alert("Ошибка");
                    document.location.href = "/contracts/administration/workers";
                }
            }
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


function getSubdivisions(worker) {
    $.ajax({
        type: "GET",
        url : "/contracts/administration/subdivisionLists" ,
        success: function(subdivisions) {
            getProfiles(worker, subdivisions)
        }
    });
}

function getProfiles(worker, subdivisions) {
    $.ajax({
        type: "GET",
        url : "/contracts/administration/profiles" ,
        success: function(profiles) {
            if ($.isEmptyObject(worker)) {
                fillNewWorker(subdivisions, profiles);
            } else {
                fillEditWorker(worker, subdivisions, profiles);
            }
        }
    });
}