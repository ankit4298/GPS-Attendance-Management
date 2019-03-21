(function () {

    var circle;

    function initialize() {
        var mapOptions = {
            center: new google.maps.LatLng(20.014570, 73.764073),
            zoom: 13
        };


        var map = new google.maps.Map(document.getElementById('map-canvas'),
                mapOptions);

        var drawingManager = new google.maps.drawing.DrawingManager({
            drawingMode: google.maps.drawing.OverlayType.MARKER,
            drawingControl: true,
            drawingControlOptions: {
                position: google.maps.ControlPosition.TOP_CENTER,
                drawingModes: [
                    google.maps.drawing.OverlayType.CIRCLE]
            },
            circleOptions: {
                fillColor: '#AA0000',
                // fillOpacity: 1,
                strokeWeight: 5,
                // clickable: false,
                // editable: true,
                zIndex: 1
            }
        });
        drawingManager.setMap(map);
        google.maps.event.addListener(drawingManager, 'circlecomplete', onCircleComplete);
    }

    function onCircleComplete(shape) {
        if (shape == null || (!(shape instanceof google.maps.Circle)))
            return;

        if (circle != null) {
            circle.setMap(null);
            circle = null;
        }

        circle = shape;

        document.getElementById("lat").value = circle.getCenter().lat();
        document.getElementById("lng").value = circle.getCenter().lng();
        document.getElementById("rad").value = circle.getRadius();

    }

    google.maps.event.addDomListener(window, 'load', initialize);
})();
