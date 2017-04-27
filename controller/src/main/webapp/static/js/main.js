$(document).ready(function() {
    $.ajax({
        type: "GET",
        url : "/contracts/loggedinworker",
        success: function(result){
            $('.loggedinworker').text(result);
        }
    });
})
