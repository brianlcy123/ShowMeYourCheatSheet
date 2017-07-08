$(function () { 
    $("#register").click(function() {
        console.log("Regrister clicked");
        var userid = $.now();
        var user = {
            "username": "user"+userid,
            "password": ""
        }
        $.ajax({
            url: '/users',
            type: 'POST',
            dataType : "json",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(user),
            success: function(result) {
                console.log(result.username);
                console.log(result.id);
            }
        });
    });
});