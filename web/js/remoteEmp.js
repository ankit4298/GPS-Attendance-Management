var resultDIV = document.getElementById("resultDIV");
var infoString = document.getElementById("infoJSON").value;
var infoJSON = JSON.parse(infoString);
var map;

var empID = [];

for (var x in infoJSON) {
    empID.push(x);
}

// sort emp ids in sequence, it will auto call respective lat lng when calling JSON
empID = empID.sort(function (a, b) {
    if (a < b)
        return -1;
    else if (a > b)
        return 1;
    return 0;
});


var coordsString = document.getElementById("remoteCoords").value;
var coordsJSON = JSON.parse(coordsString);

var locID = [];

for (var x in coordsJSON) {
    locID.push(x);
}

// sort loc ids in sequence, it will auto call respective lat lng when calling JSON
locID = locID.sort(function (a, b) {
    if (a < b)
        return -1;
    else if (a > b)
        return 1;
    return 0;
});

function coordinateObj(lat, lng) {
    return new google.maps.LatLng(lat, lng);
}

var mapView = google.maps.MapTypeId.ROADMAP;
// Init map
function initialize() {


    var map = new google.maps.Map(
            document.getElementById("map"), {
        center: new google.maps.LatLng(20.014570, 73.764073),
        zoom: 14,
        mapTypeId: mapView
    });

    // Iterate through all emp's and place marker on the map
    for (var i = 0; i < empID.length; i++) {
        var latitude = infoJSON[empID[i]][0].lat;
        var longitude = infoJSON[empID[i]][0].lng;
        var empid = empID[i];

        var coords = coordinateObj(latitude, longitude);

        var marker = new google.maps.Marker({
            map: map,
            position: coords
        });
        placeMarker(marker, empid);
    }

    for (var i = 0; i < locID.length; i++) {

        var site_name = coordsJSON[locID[i]][0].site_name;
        var latitude = coordsJSON[locID[i]][0].latitude;
        var longitude = coordsJSON[locID[i]][0].longitude;
        var radius = coordsJSON[locID[i]][0].radius;
        var locationID = locID[i];

        // create google api compitible coordinate object
        var centerCoords = coordinateObj(latitude, longitude);

        // Create marker 
        var marker = new google.maps.Marker({
            map: map,
            position: centerCoords
        });

        // Add circle overlay and bind to marker
        var circle = new google.maps.Circle({
            map: map,
            radius: radius, // 10 miles in metres
            fillColor: '#AA0000'
        });
        circle.bindTo('center', marker, 'position');
        marker.setMap(null);
    }




}

// Place Marker Overhead info and check for inside/outside polygon
function placeMarker(marker, empid) {
    var infowindow = new google.maps.InfoWindow();
    var html = "<a href=\"profile.jsp?eid=" + empid + "\"><font color=\"blue\"><b>" + empid + "</b></font></a>";

    infowindow.setContent(html);
    infowindow.open(map, marker);
    google.maps.event.addListener(marker, 'click', function () {
        infowindow.open(map, marker);
    });
}

google.maps.event.addDomListener(window, "load", initialize);

function focusthere(){
    
    
    
}