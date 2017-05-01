
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

function onePointInInput(key) {
    let input = $("#" + key.currentTarget.id);
    let text = input.val();
    if (key.charCode === 44) {
        if (text.indexOf('.') === -1) {
            input.val(text + ".");
        }
    }
    if (key.charCode === 46) {
        if (text.indexOf('.') !== -1) {
            return false;
        }
    } else {
        if (key.charCode < 48 || key.charCode > 57) return false;
    }
}

function addZero(i) {
    return (i < 10)? "0" + i: i;
}