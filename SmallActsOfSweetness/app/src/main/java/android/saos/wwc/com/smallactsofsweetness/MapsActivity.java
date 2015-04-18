package android.saos.wwc.com.smallactsofsweetness;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements View.OnClickListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    public static Context context;
    private double myCurrentLat;
    private double myCurrentLong;
    //Debug
    private static final String TAG = "MapsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_maps);

        //get current location
        GPSTracker gps = new GPSTracker(this);
        myCurrentLat = gps.getLatitude();
        myCurrentLong = gps.getLongitude();

        setUpMapIfNeeded();

        RadioButton radioBakeries = (RadioButton)findViewById(R.id.radioBakeries);
        RadioButton radioIcecream = (RadioButton)findViewById(R.id.radioIcecream);
        RadioButton radioCoffee = (RadioButton)findViewById(R.id.radioCoffee);

        radioBakeries.setOnClickListener(this);
        radioIcecream.setOnClickListener(this);
        radioCoffee.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Log.v(TAG, "A button is clicked");

        switch(v.getId()){
            case R.id.radioBakeries:
                mMap.clear();
                addBakeryMarkers();
                break;
            case R.id.radioIcecream:
                mMap.clear();
                addIcecreamMarkers();
                break;
            case R.id.radioCoffee:
                mMap.clear();
                addCoffeeMarkers();
                break;
            default:
                setUpMapIfNeeded();
                break;
        }
    }

    void addBakeryMarkers(){
        //Log.v(TAG, "Inside bakeries");
        mMap.addMarker(new MarkerOptions().position(new LatLng(myCurrentLat, myCurrentLong)).title("Me").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.addMarker(new MarkerOptions().position(new LatLng((myCurrentLat + 0.001), (myCurrentLong + 0.001))).title("Speciality Cafe"));
        mMap.addMarker(new MarkerOptions().position(new LatLng((myCurrentLat - 0.001), (myCurrentLong - 0.001))).title("Cake Expressions"));
        mMap.addMarker(new MarkerOptions().position(new LatLng((myCurrentLat - 0.002), (myCurrentLong - 0.002))).title("Sultan Bakery"));
    }

    void addIcecreamMarkers(){
        mMap.addMarker(new MarkerOptions().position(new LatLng(myCurrentLat, myCurrentLong)).title("Me").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(myCurrentLat + 0.004, myCurrentLong + 0.004)).title("Mission City Creamery"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(myCurrentLat - 0.004, myCurrentLong - 0.004)).title("Nirvanaah"));
        mMap.addMarker(new MarkerOptions().position(new LatLng((myCurrentLat + 0.006), (myCurrentLong + 0.006))).title("Treatbot Factory"));
        mMap.addMarker(new MarkerOptions().position(new LatLng((myCurrentLat - 0.006), (myCurrentLong - 0.006))).title("CREAM"));
        mMap.addMarker(new MarkerOptions().position(new LatLng((myCurrentLat - 0.007), (myCurrentLong - 0.003))).title("Jimbo's"));
    }

    void addCoffeeMarkers(){
        mMap.addMarker(new MarkerOptions().position(new LatLng(myCurrentLat, myCurrentLong)).title("Me").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(myCurrentLat + 0.003, myCurrentLong + 0.003)).title("Cafe Sun"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(myCurrentLat - 0.004, myCurrentLong - 0.001)).title("Starbucks"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(myCurrentLat + 0.001, myCurrentLong - 0.001)).title("Speciality's Cafe"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(myCurrentLat - 0.009, myCurrentLong - 0.009)).title("Coffee Chai"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(myCurrentLat + 0.001, myCurrentLong - 0.003)).title("Barefoot Coffee Roasters"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(myCurrentLat + 0.008, myCurrentLong + 0.008)).title("The Grind Coffee House"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(myCurrentLat - 0.002, myCurrentLong - 0.002)).title("Barefoot Coffee"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(myCurrentLat - 0.001, myCurrentLong + 0.002)).title("Cosmic Coffee"));
    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        //mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));

        //add marker to current location
        mMap.addMarker(new MarkerOptions().position(new LatLng(myCurrentLat, myCurrentLong)).title("Me").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

//        mMap.addMarker(new MarkerOptions().position(new LatLng((myCurrentLat + 0.001), (myCurrentLong + 0.001))).title("MarkerB1"));
//        mMap.addMarker(new MarkerOptions().position(new LatLng((myCurrentLat - 0.001), (myCurrentLong - 0.001))).title("MarkerB2"));


        //zoom in on your current location
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(myCurrentLat, myCurrentLong), 13));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(myCurrentLat, myCurrentLong))      // Sets the center of the map to location user
                .zoom(15)                   // Sets the zoom
                .bearing(90)                // Sets the orientation of the camera to east
                .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }
}
