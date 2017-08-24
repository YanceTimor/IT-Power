/**
 * Created by Yance on 2017/8/14 0014.
 */
$.ajax({
    type: "GET",
    url: "./authority/getAuthObjectListForAdmin",
    dataType: "json",
    success: function(result) {
        console.log(result);
        $("#test").html(JSON.stringify(result));
    }
});

var htmlString="";