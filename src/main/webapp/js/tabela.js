$(document).ready(function () {
    $("#clearsearch").click(function () {
        $("#clearsearch").fadeOut(300);
        $("#search").val("");
        $("#tablecontent tbody tr td").removeClass('success');
        $("#tablecontent tbody tr").removeClass('hide');
    });
    $("#search").keyup(function () {
        var result = $(this).val().replace(/ +?/g, "").toLowerCase();
        if (result != null && result != "") {
            $("#clearsearch").fadeIn(300);
            $("#tablecontent tbody tr").addClass('hide');
            $("#tablecontent tbody tr").find('td').each(function () {
                var tbresult = $(this).text().replace(/ +?/g, "").toLowerCase();
                if (tbresult.indexOf(result) !== -1) {
                    $(this).closest('tr').removeClass('hide');
                    $(this).addClass('success');
                } else {
                    $(this).removeClass('success');
                }
            });
        } else {
            $("#clearsearch").fadeOut(300);
            $("#tablecontent tbody tr").removeClass('hide');
            $("#tablecontent tbody tr td").removeClass('success');
        }
    });
});