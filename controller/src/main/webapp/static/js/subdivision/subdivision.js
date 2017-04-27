
class Subdivision {
    constructor(id, name) {
        this.id = id;
        this.name = name
    }
}

let urlName;
let subdivisionId;

$(document).ready(function() {
    subdivisionId = $("#hdnSession").data('value');
    if (subdivisionId !== -1) {
        $.ajax({
            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "/contracts/administration/get-edit-subdivision-" + subdivisionId,
            success: function(result){
                urlName = "/contracts/administration/edit-subdivision";
                fillEditSubdivision(result);
            }
         });
    } else {
        urlName = "/contracts/administration/new-subdivision";
        subdivisionId = null;
        fillNewSubdivision();
    }

});

function fillNewSubdivision() {
    $('.well').text("Добавление нового подразделения");
    $('.btn').val("Создать");
}

function fillEditSubdivision(data) {
    $('.btn').val("Изменить");
    $('.well').text("Редактирование данных подразделения");
    $('#name').val(data.map.subdivision.map.name);
}

function saveWorker() {
    const jName = checkEmptyInput("name");
    if (jName) {
        let subdivision = new Subdivision(subdivisionId, jName);
        const subdivisionJson = JSON.stringify(subdivision, "", 4);
        send(subdivisionJson, jName);
    }
}

function send(jsonString, jName) {
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
                if ( subdivisionId === undefined || subdivisionId === null) {
                    document.location.href = "/contracts/administration/subdivisions";
                    alert("Подразделение " + jName + " добавлено успешно.");
                } else {
                    document.location.href = "/contracts/administration/subdivisions";
                    alert("Данные подразделения " + jName + " изменены.");
                }
            } else {
                if (msgResult.localeCompare("not unique") === 0) {
                    $("#nameError").text("Подразделение " + jName + " уже зарегистрировано. Введите дрогое название подразделения.");
                } else {
                    if (msgResult.localeCompare("not found") === 0) {
                        alert("Подразделение " + jName + "не зарегистрировано.");
                        document.location.href = "/contracts/administration/subdivisions";
                    } else {
                        if (subdivisionId === null) {
                            alert("Подразделение " + jName + " - ошибка добавления.");
                        } else {
                            alert("Подразделение " + jName + " - ошибка изменения данных.");
                        }
                    }
                }
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
