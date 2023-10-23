$(document).ready(function(){
$("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $(".user-content .card").filter(function() {
        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
});
});