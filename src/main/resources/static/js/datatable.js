$(function() {
    $.ajaxPrefilter(function( settings, original, xhr ) {
        if (['post','put','delete'].includes(settings.type.toLowerCase())
            && !settings.crossDomain) {
                xhr.setRequestHeader("X-XSRF-TOKEN", getCookie('XSRF-TOKEN'));
        }
    });

    function getCookie(name) {
        var cookieValue = null;
        if (document.cookie && document.cookie != '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = jQuery.trim(cookies[i]);
                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                    break;
                }
            }
        }
        return cookieValue;
    }

    $("#cakesTable").dataTable({
        "columns": [
            { "data": "id" },
            { "data": "title" },
            { "data": "description" },
            {
                "data": "image",
                "render": function(data, type, row) {
                    return '<img src="'+data+'" />';
                }
            }
        ],
    });

    function refreshCakeTable() {
        $.ajax({
              type: 'GET',
              url: "http://localhost:8282/cakes",
              contentType: "text/plain",
              dataType: 'json',
              success: function (data) {
                myJsonData = data;
                populateDataTable(myJsonData);
              },
              error: function (e) {
                console.log("There was an error with your request...");
                console.log("error: " + JSON.stringify(e));
              }
        });
    }

    function populateDataTable(data) {
        console.log("populating data table...");
        $("#cakesTable").DataTable().clear();
        $.each( data._embedded.cakeDtoList, function( i, cake ) {
          $('#cakesTable').DataTable().row.add(cake).draw();
        });
    }

    refreshCakeTable();

    $('#refreshCakesButton').click(function(){
        refreshCakeTable();
    });

    $('#addCakeForm').submit(function (evt) {
        evt.preventDefault();
        var json = objectifyForm($('#addCakeForm').serializeArray());
        console.log(json);
        $.ajax({
            url: 'http://localhost:8282/cakes',
            data: JSON.stringify(json),
            dataType : 'json',
            contentType: "application/json",
            type: 'POST',
             success : function(result) {
                 console.log(result);
             },
             error: function(xhr, resp, text) {
                 console.log(xhr, resp, text);
             }
        });
    });

    function objectifyForm(formArray) {
        var returnArray = {};
        for (var i = 0; i < formArray.length; i++){
            returnArray[formArray[i]['name']] = formArray[i]['value'];
        }
        return returnArray;
    }
});