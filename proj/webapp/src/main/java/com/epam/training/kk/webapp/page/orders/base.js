function initMap() {
  // Create a map object and specify the DOM element for display.
	 function initialize() {
		    var mapCanvas = document.getElementById('map');
		    var mapOptions = {
		      center: new google.maps.LatLng(53.6666667, 23.8333333),
		      zoom: 8,
		      mapTypeId: google.maps.MapTypeId.ROADMAP
		    }
		    var map = new google.maps.Map(mapCanvas, mapOptions);
		  }
	 google.maps.event.addDomListener(window, 'load', initialize);
}