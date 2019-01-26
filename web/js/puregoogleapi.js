var resultDIV = document.getElementById("resultDIV");

var infoString = document.getElementById("infoJSON").value;
var infoJSON = JSON.parse(infoString);

var empID = [];

var map;


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

//console.log(empID); 
//console.log(infoJSON);
//console.log(infoJSON[empID[0]][0].lat);

function coordinateObj(lat, lng) {
    return new google.maps.LatLng(lat, lng);
}


// polygon/area bounds
var mapPolygon = new google.maps.Polygon({
    paths: [
        new google.maps.LatLng(20.013635, 73.764568),
        new google.maps.LatLng(20.013716, 73.762858),
        new google.maps.LatLng(20.014366, 73.763212),
        new google.maps.LatLng(20.014366, 73.764730)
    ],

    strokeColor: '#000000',
    strokeOpacity: 1,
    strokeWeight: 3,
    fillColor: '#000000',
    fillOpacity: 0.6
});


// Init map
function initialize() {
    var map = new google.maps.Map(
            document.getElementById("map"), {
        center: new google.maps.LatLng(20.014365, 73.764730),
        zoom: 19,
//        draggable:false,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });

// create polygon for the given bounds
    mapPolygon.setMap(map);
    var bounds = new google.maps.LatLngBounds();
    for (var i = 0; i < mapPolygon.getPath().getLength(); i++) {
        bounds.extend(mapPolygon.getPath().getAt(i));
    }

    // Iterate through all emp's and place marker on the map
    for (var i = 0; i < empID.length; i++) {
        var latitude = infoJSON[empID[i]][0].lat;
        var longitude = infoJSON[empID[i]][0].lng;
        var empid = empID[i];

        var coords = coordinateObj(latitude, longitude);
        bounds.extend(coords);
        var marker = new google.maps.Marker({
            map: map,
            position: coords
        });
        checkInPolygon(marker, mapPolygon, empid);


    }

}

// Place Marker Overhead info and check for inside/outside polygon
function checkInPolygon(marker, polygon, empid) {
    var infowindow = new google.maps.InfoWindow();
    var html = "";
    if (google.maps.geometry.poly.containsLocation(marker.getPosition(), polygon)) {
        html = "<font color=\"green\"><b>" + empid + "</b></font>";
    } else {
        html = "<font color=\"red\"><b>" + empid + "</b></font>";

//        //directly redirect to employees profile
//        html = "<a href=" +empid+".jsp >"+empid+"</a>";
    }
    infowindow.setContent(html);
    infowindow.open(map, marker);
    google.maps.event.addListener(marker, 'click', function () {
        infowindow.open(map, marker);
    });
}

google.maps.event.addDomListener(window, "load", initialize);

