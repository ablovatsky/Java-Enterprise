
class Subdivision {
    constructor(id, name) {
        this.id = id;
        this.name = name
    }
}

let urlName;
let subdivisionId;
let type;

$(document).ready(function() {
    subdivisionId = $("#hdnSession").data('value');
    if (subdivisionId !== -1) {
        $.ajax({
            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "/avectis/administration/subdivisions/subdivision/" + subdivisionId,
            success: function(result){
                urlName = "/avectis/administration/subdivisions/subdivision/edit";
                type = "PUT";
                fillEditSubdivision(result);
            }
         });
    } else {
        urlName = "/avectis/administration/subdivisions/subdivision/new";
        type = "POST";
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
    $('#name').val(data.name);
}

function addSubdivision() {
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
        type: type,
        contentType : 'application/json; charset=utf-8',
        data: jsonString,
        beforeSend: function(request) {
            request.setRequestHeader(header, token);
        },
        success: function(result, textStatus, status){
            if (status.status === 200) {
                if (  jName === undefined || jName === null ){
                    document.location.href = "/avectis/administration/subdivisions";
                    alert("Подразделение <<" + jName + ">> добавлено успешно.");
                } else {
                    document.location.href = "/avectis/administration/subdivisions";
                    alert("Данные Подразделения <<" + jName + ">> изменены.");
                }
            }
        },
        error: function(error) {
            if (error.status === 302) {
                $("#ssoIdError").text("Подразделение <<" + jName + ">> уже зарегистрировано. Введите дрогое имя подразделения.");
            } else {
                if (error.status === 404) {
                    alert("Подразделение <<" + jName + ">> не найдено.");
                    document.location.href = "/avectis/administration/subdivisions";
                } else {
                    alert("Ошибка");
                    document.location.href = "/avectis/administration/subdivisions";
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
