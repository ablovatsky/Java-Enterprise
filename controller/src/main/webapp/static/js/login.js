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
}




