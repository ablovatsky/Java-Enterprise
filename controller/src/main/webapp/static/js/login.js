function showValues() {
   /* var str = $("form").serialize();
    alert(str);*/
    $.ajax({
        type: "GET",
        url : "/row/list",
        success: function(result){
            alert(result);
        }
    });

    var json_data
    var XHR = ("onload" in new XMLHttpRequest()) ? XMLHttpRequest : XDomainRequest;
    var xhr = new XHR();
    xhr.open('GET', "/row/list", true);
    xhr.onload = function() {
        json_data= JSON.parse(this.responseText);
        var a;
        a= 0 ;
    };
    xhr.onerror = function() {
        console.log( 'Ошибка ' + this.status);
    };
    xhr.send();
}




