package com.me.job.tt.ui.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.location.Address
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.me.job.R
import com.me.job.databinding.ActivityUpdateMapsBinding
import com.me.job.tt.data.remote.request.MapRequest
import android.location.Geocoder
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.me.job.tt.utils.AppConstants.Companion.MAPS_API_KEY
import kotlinx.android.synthetic.main.activity_update_maps.*
import java.io.IOException
import java.util.*


class UpdateMapsActivity : BaseActivity(),
    OnMapReadyCallback,
    GoogleMap.OnMyLocationButtonClickListener,
    ActivityCompat.OnRequestPermissionsResultCallback,
    GoogleMap.OnMyLocationClickListener,
    GoogleMap.OnMapClickListener,
    GoogleMap.OnMarkerClickListener
{

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityUpdateMapsBinding
    //private lateinit var mapRequest:MapRequest
    private lateinit var location:LatLng
    val LOCATION_PERMISSION_REQUEST_CODE=900
    lateinit var mSearch:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

         location= intent.extras.getParcelable<LatLng>("map")

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mSearch=findViewById<EditText>(R.id.et_search)
        mSearch.setOnEditorActionListener(object : TextView.OnEditorActionListener {

            override fun onEditorAction(p0: TextView?, actionId: Int, keyEvent: KeyEvent?): Boolean {

                    if(actionId==EditorInfo.IME_ACTION_SEARCH
                        || actionId==EditorInfo.IME_ACTION_DONE
                        || keyEvent?.action==KeyEvent.ACTION_DOWN
                        || keyEvent?.action==KeyEvent.KEYCODE_ENTER
                    ){

                        geoLocate()

                    }

                return false
            }

        })



        // Initialize the AutocompleteSupportFragment.
       /* val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.place_autocomplete_fragment)
                    as AutocompleteSupportFragment

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME))

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: ${place.name}, ${place.id}")
            }

            override fun onError(status: Status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: $status")
            }
        })


       
        
        /****places**/
        // Create a new token for the autocomplete session. Pass this to FindAutocompletePredictionsRequest,
        // and once again when the user makes a selection (for example when calling fetchPlace()).

        // Initialize the SDK
        Places.initialize(applicationContext, MAPS_API_KEY)

        // Create a new PlacesClient instance
        val placesClient = Places.createClient(this)

        val token = AutocompleteSessionToken.newInstance()
        var query=autocompleteFragment.view?.findViewById<EditText>(R.id.places_autocomplete_search_input)?.text
        // Create a RectangularBounds object.
        val bounds = RectangularBounds.newInstance(
            LatLng(-33.880490, 151.184363),
            LatLng(-33.858754, 151.229596)
        )
        // Use the builder to create a FindAutocompletePredictionsRequest.
        val request =
            FindAutocompletePredictionsRequest.builder()
                // Call either setLocationBias() OR setLocationRestriction().
                .setLocationBias(bounds)
                //.setLocationRestriction(bounds)
                .setOrigin(LatLng(-33.8749937, 151.2041382))
                .setCountries("AU", "NZ")
                .setTypeFilter(TypeFilter.ADDRESS)
                .setSessionToken(token)
                .setQuery(query.toString())
                .build()



        placesClient.findAutocompletePredictions(request)
            .addOnSuccessListener { response: FindAutocompletePredictionsResponse ->
                for (prediction in response.autocompletePredictions) {
                    Log.i(TAG, prediction.placeId)
                    Log.i(TAG, prediction.getPrimaryText(null).toString())
                }
            }.addOnFailureListener { exception: Exception? ->
                if (exception is ApiException) {
                    Log.e(TAG, "Place not found: " + exception.statusCode)
                }
            }




        autocompleteFragment.view?.findViewById<EditText>(R.id.places_autocomplete_search_input)?.textSize = 30.0f
        autocompleteFragment.view?.findViewById<EditText>(R.id.places_autocomplete_search_input)?.setBackgroundColor(R.color.blue)
        autocompleteFragment.setTypeFilter(TypeFilter.ADDRESS)*/
    }


    fun geoLocate(){
        var searchText= mSearch.text.toString()
        var geocoder=Geocoder(this)
        var addressList:List<Address> =listOf()
        try{
            addressList=geocoder.getFromLocationName(searchText,1)

        }catch (e:IOException){
            showPromptDialog("geolocation exception ${e.message.toString()}")
        }

        if(addressList.isNotEmpty()){

            var address:Address=addressList.get(0)
            Log.d("address",address.toString())
           var searchLocation:LatLng=LatLng(address.latitude,address.longitude)

            mMap.clear()
            mMap.addMarker(
                MarkerOptions().draggable(true)
                    .position(searchLocation).title(address.toString())
                    .icon(
                        BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
                    )
            ).showInfoWindow()
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(searchLocation, 5.0f));
        }


    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        getLocationAccess() //this is for current location
        // Add a marker in Sydney and move the camera
        mMap.setOnMapClickListener(this)
        mMap.addMarker(MarkerOptions().position(location).title("Yangon"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))

        mMap.uiSettings.isZoomControlsEnabled=true
        mMap.uiSettings.isZoomGesturesEnabled=true
        mMap.uiSettings.isCompassEnabled=true
        mMap.uiSettings.isMapToolbarEnabled=true
        mMap.uiSettings.isScrollGesturesEnabled=true
        mMap.uiSettings.isTiltGesturesEnabled=true
        mMap.uiSettings.isRotateGesturesEnabled=true

        mMap.setOnMyLocationButtonClickListener(this)
        mMap.setOnMyLocationClickListener(this)
        mMap.setOnMarkerClickListener(this)

        //this is for zoom level and to appear marker
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 10.0f));
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show()
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false
    }

    override fun onMyLocationClick(p0: Location) {
        Toast.makeText(this, "My location:\n$p0", Toast.LENGTH_LONG).show()
    }

    override fun onMapClick(p0: LatLng) {
        //previous map is clear
        mMap.clear()

        val geocoder: Geocoder
        val addresses: List<Address>
        geocoder = Geocoder(this, Locale.getDefault())

        addresses = geocoder.getFromLocation(
            p0.latitude,
            p0.longitude,
            1
        ) // Here 1 represent max location result to returned, by documents it recommended 1 to 5


        val address: String =
            addresses[0].getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
      /*  addresses[0].let {

            val city: String = it.locality
            val state: String = it.adminArea
            val country: String = addresses[0].countryName
            val postalCode: String = addresses[0].postalCode
            val knownName: String = addresses[0].featureName

        }*/



        mMap.addMarker(
            MarkerOptions().draggable(true)
                .position(p0).title(address)
                .icon(
                    BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
                )
        ).showInfoWindow()
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(p0, 5.0f));
        Toast.makeText(this, "Map Click:\n$p0", Toast.LENGTH_LONG).show()
    }


    private fun getLocationAccess(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            mMap.isMyLocationEnabled = true

        } else {
            // Permission to access the location is missing. Show rationale and request permission

            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            return
        }else{

            if(grantResults.contains(PackageManager.PERMISSION_GRANTED)){
                mMap.isMyLocationEnabled=true
            }else{
                Toast.makeText(this,"User has not granted location access permission", Toast.LENGTH_SHORT).show()
                finish()
            }
        }


    }

    override fun onMarkerClick(p0: Marker): Boolean {
        var lat:Double=p0.position.latitude
        var lng:Double=p0.position.longitude
        var location="$lat,$lng"
        Toast.makeText(this, location, Toast.LENGTH_LONG).show()
        return true
    }


}