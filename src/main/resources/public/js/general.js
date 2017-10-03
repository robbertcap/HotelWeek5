var table = $('#table').DataTable();
var items = [];

function clearForm() {
    $("input").each(function() {
        $(this).val("");
    });
}

function send(api,obj) {
    var uri = api;
    var method = "POST";

    // Is this an update?
    if (id == null) {
        uri = api + obj.id + "/";
        method = "PUT"
    }

    // Send data
    $.ajax({
        url: uri,
        type: method,
        data: JSON.stringify(obj),
        contentType: "application/json; charset=utf-8"
    }).then(function() {
        $("#form").modal('toggle');
        getAll();
    });}

$("#addBtn").click(add);
$("#saveBtn").click(save);
$("#deleteBtn").click(deleteItem);
getAll();