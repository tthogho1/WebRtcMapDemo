
class GoogleMap{
	
	
	constructor(googlemap,map){
		this.GOOGLE_MAP = googlemap;
		this.hotels=[];
		this.map = map;
		this.places = new google.maps.places.PlacesService(googlemap);
		this.MARKER_PATH ="https://developers.google.com/maps/documentation/javascript/images/marker_green";
	}
	
	dropMarker(map) {
  		return function () {
    		map.setMap(this.GOOGLE_MAP);
  		};
	}
	
	showInfoWindow() {
		const marker = this;
		places.getDetails(
			{ placeId: marker.placeResult.place_id },
	    		(place, status) => {
	      			if (status !== google.maps.places.PlacesServiceStatus.OK) {
	        			return;
	      			}
	      		infoWindow.open(this.map, marker);
	      		buildIWContent(place);
	    	}
		);
	}
	
	buildIWContent(place) {
		document.getElementById("iw-icon").innerHTML =
	    	'<img class="hotelIcon" ' + 'src="' + place.icon + '"/>';

	  	document.getElementById("iw-url").innerHTML =
	    	"<b><a href=\"javascript:OpenWebComWindow('" + place.url + "');\">" + place.url + "</a></b>" ;

		document.getElementById("iw-address").textContent = place.vicinity;

		if (place.formatted_phone_number) {
			document.getElementById("iw-phone-row").style.display = "";
	    	document.getElementById("iw-phone").textContent =
	    	place.formatted_phone_number;
	  	} else {
	    	document.getElementById("iw-phone-row").style.display = "none";
	  	}

		if (place.rating) {
	    	let ratingHtml = "";

			for (let i = 0; i < 5; i++) {
				if (place.rating < i + 0.5) {
	        		ratingHtml += "&#10025;";
	      		} else {
	        		ratingHtml += "&#10029;";
				}
				document.getElementById("iw-rating-row").style.display = "";
				document.getElementById("iw-rating").innerHTML = ratingHtml;
			}
		} else {
	    	document.getElementById("iw-rating-row").style.display = "none";
	  	}

	  // The regexp isolates the first part of the URL (domain plus subdomain)
	  // to give a short URL for displaying in the info window.
		if (place.website) {
	    	let fullUrl = place.website;
	    	let website = String(hostnameRegexp.exec(place.website));

			if (!website) {
				website = "http://" + place.website + "/";
				fullUrl = website;
			}
			document.getElementById("iw-website-row").style.display = "";
			document.getElementById("iw-website").textContent = website;
		} else {
			document.getElementById("iw-website-row").style.display = "none";
		}
	}
	
	searchHotel(){
  		const search = {
    		bounds: this.GOOGLE_MAP.getBounds(),
    		types: ["lodging"],
  		};
  
  
  		this.places.nearbySearch(search, (results, status, pagination) => {
    		if (status !== google.maps.places.PlacesServiceStatus.OK || !results) {
    			return;    
    		}

			this.hotels.forEach(element => {
				element.setMap(null);
			});
		
			this.hotels=[];
			for (let i = 0; i < results.length; i++) {
	        	const markerLetter = String.fromCharCode("A".charCodeAt(0) + (i % 26));
		        const markerIcon = this.MARKER_PATH + markerLetter + ".png";
	
	        	this.hotels[i] = new google.maps.Marker({
	          		position: results[i].geometry.location,
	          		animation: google.maps.Animation.DROP,
	         		icon: markerIcon,
	        	});
		        // If the user clicks a hotel marker, show the details of that hotel
		        // in an info window.
	        	this.hotels[i].placeResult = results[i];
    	    	google.maps.event.addListener(this.hotels[i], "click", this.showInfoWindow);
        		setTimeout(this.dropMarker(this.hotels[i]), i * 400);
        		//addResult(results[i], i);
    		}
 		});
	}	

}