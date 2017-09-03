$(function() {

    var api = "http://localhost:8080/api/booking/";
    var table = $('#table').DataTable();

    function getAll() {
        $.get(api, function(data) {

        	if (data == null) throw "NoData";

            table.clear();

            for (i = 0; i < data.length; i++) {

            	var item = data[i];

                table.row.add([
                	item.id,
                	item.lastName,
                	item.firstName
                	]);

            }

            table.draw();
        });
    }



    getAll();

});