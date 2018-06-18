function prikaziLokaciju() {
	var geocoder = new google.maps.Geocoder();
	kodirajAdresu(geocoder,address);
}

function kodirajAdresu(geocoder,address) 
{
  geocoder.geocode( {address:address}, function(results, status) 
  {
    if (status == google.maps.GeocoderStatus.OK) 
    {
    	var mapOptions = {
    		    center: results[0].geometry.location,
    		    zoom: 20,
    		}
    		        
    		var map = new google.maps.Map(document.getElementById('map'), mapOptions);

    		var marker = new google.maps.Marker({
    		position: results[0].geometry.location,
    		map: map,
    		title: 'Lokacija institucije.'
    	});
    } else {
      alert('Geocode was not successful for the following reason: ' + status);
   }
  });
}

