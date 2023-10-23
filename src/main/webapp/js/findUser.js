$(document).ready(function() {
    $("#ajax-button").on("click", function () {
        var username = $("#searchUsername").val();
        if (username !== "") {
            $.get("/searchUser?username=" + username, function (data) {
                if (data.error) {
                    alert(data.error);
                } else {
                    window.location.href = "/users/" + username;
                }
            });
        } else {
            alert("Please enter a username!");
        }
    });
});

