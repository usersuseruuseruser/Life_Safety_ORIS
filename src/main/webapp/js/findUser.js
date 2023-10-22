$(document).ready(function() {
    $("#ajax-button").on("click", function() {
        var username = $("#searchUsername").val();
        if (username) {
            window.location.href = "/users/" + username;
        } else {
            alert("Please enter a username!");
        }
    });
});

