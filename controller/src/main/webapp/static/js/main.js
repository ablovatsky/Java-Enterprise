$(document).ready(function() {
    $.ajax({
        type: "GET",
        url : "/avectis/loggedinworker",
        success: function(result){
            $('.loggedinworker').text(result);
        }
    });
})
