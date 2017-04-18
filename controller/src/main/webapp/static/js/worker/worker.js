$(document).ready(function() {
    /*$.ajax({
        type: "GET",
        url : "/contracts/getWorkers",
        success: function(result){
            var json_data= JSON.parse(result);
            filWorkerList(json_data);
        }
    });*/
    var sessionValue= $("#hdnSession").data('value');
    var value = '@Request.RequestContext.HttpContext.Session["someKey"]';
    var urlPost;
    var url = document.URL;
    if(url.indexOf('new-worker') + 1) {
       urlPost = ""
    } else if(url.indexOf('edit-worker') + 1) {
        alert("edit-worker");
    }

});