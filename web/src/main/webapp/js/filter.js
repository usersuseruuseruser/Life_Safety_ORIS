$(document).ready(function(){
    $("#myInput").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $(".container .card").filter(function() {
            $(this).closest('.col').toggle($(this).text().toLowerCase().indexOf(value) > -1);
        });
    });
});
