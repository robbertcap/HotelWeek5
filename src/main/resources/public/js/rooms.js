var api = "http://localhost:8080/api/room/";

function getAll() {
    $.get(api, function(rooms) {

        if (rooms == null) throw "NoData";

        table.clear();

        for (i = 0; i < rooms.length; i++) {

            var item = rooms[i];

            table.row.add([
                "<a href=\"javascript:update(" + item.id + ")\">" + item.id + "</a>",
                item.roomType
            ]);

        }

        items = rooms;

        table.draw();
    });
}


function add() {
	// Set title
    $("#title").text("New Room");

    // Hide delete button
    $("#deleteBtn").hide();

    // Clear form
    clearForm();

    // Show form
    $("#form").modal('toggle');
}

function deleteItem() {
	 var id = $("#id").val();

	 var uri =  api + id + "/";

	 // Send data
    $.ajax({
        url: uri,
        type: "DELETE"
    }).then(function() {
        $("#form").modal('toggle');
        getAll();
    });
}

function update(id) {
	// Set title
    $("#title").text("Edit Room");

    // Show delete button
    $("#deleteBtn").show();

    // Clear form
    clearForm();

    // Get item
    var item;
    for (var i = 0; i < items.length; i++) {
        if (items[i].id == id) {
            item = items[i];
            break;
        }
    };

    if (item == null) throw "NoItemFound";

    // Fill form
    $("#id").val(item.id);
    $("#roomType").val(item.roomType);

    // Show form
    $("#form").modal('toggle');
}

function save(e) {
    e.preventDefault();

    // Create obj
    var obj = {};

    obj.id = $("#id").val();
    obj.roomType = $("#roomType").val();

    send(api, obj);
}

