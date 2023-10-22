$(document).on("click", "#ajax-button", function() {
    console.log("Button clicked!");
    const username = $("#searchUsername").val();
    $.get("/searchUser?username=" + username, function(user) {
    if (user) {
    let userInfo = `<h1>${user.name}</h1>Email: ${user.email}<br>Self Info: ${user.selfInfo}<br>`;

    if (user.profilePictureUrl) {
        userInfo += `<img src="${user.profilePictureUrl}" alt="${user.name}'s profile picture"><br>`;
    } else {
        userInfo += 'Здесь будет фото вашего профиля<br>';
    }
        $("#searchResults").html(userInfo);
    }
    });
});

