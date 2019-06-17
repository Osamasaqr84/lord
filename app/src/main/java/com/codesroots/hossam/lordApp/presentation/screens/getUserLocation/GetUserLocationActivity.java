package com.codesroots.hossam.lordApp.presentation.screens.getUserLocation;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.codesroots.hossam.lordApp.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class GetUserLocationActivity extends AppCompatActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private static final String TAG = "tag";
    public static final String FULL_ADDRESS = "full_address";
    public static final String USER_LAT = "user_lat";
    public static final String USER_LANG = "user_lang";
    protected Location location, myLocation;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private LocationRequest mLocationRequest;
    private double currentLatitude;
    private double currentLongitude;
    TextView search;
    GoogleMap m_map;
    ImageView mMarker;
    private LatLng curretLatLng;
    String address = "";
    Double longitude, latitude;
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    TextView address_txt;
    private RadioGroup rgMapType;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_user_location);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // Make to run your application only in portrait mode

        address_txt = findViewById(R.id.address_txt);
        rgMapType = findViewById(R.id.rg_map_type);

        search = findViewById(R.id.search);

        search.setOnClickListener(view -> {
            try {
                Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                        .build(this);
                startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
            } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                Snackbar.make(view, "Can't search for address now", Snackbar.LENGTH_LONG).show();
            }
        });

        search.setOnKeyListener((v, keyCode, event) ->
                {

                    return true;
                }
        );
        ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.usermap)).getMapAsync(this);

        if (servicesOK()) {
            buildGoogleApi();
            mGoogleApiClient.connect();
        }

        rgMapType.setOnCheckedChangeListener((group, checkedId) -> {
            if (mMap!=null) {
                int currentMapType = mMap.getMapType();
                int newMapType;
                switch (checkedId) {
                    case R.id.rb_satalite:
                        newMapType = GoogleMap.MAP_TYPE_SATELLITE;
                        break;
                    case R.id.rb_hagin:
                        newMapType = GoogleMap.MAP_TYPE_HYBRID;
                        break;
                    default:
                        newMapType = GoogleMap.MAP_TYPE_NORMAL;
                }
                if (newMapType != currentMapType) {
                    mMap.setMapType(newMapType);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    protected synchronized void buildGoogleApi() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    public boolean servicesOK() {
        int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (GooglePlayServicesUtil.isUserRecoverableError(isAvailable)) {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable, this, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "please check you opened GPS", Toast.LENGTH_LONG).show();
        }
        return false;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(false);

        LocationManager locationManager;
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                if (currentLatitude != 0d && currentLongitude != 0d) {
                    goToAddress(currentLatitude, currentLongitude);
                }
            } else {
                showAlertDialog();
            }
        }
    }

    private void showAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Warning");
        builder.setMessage("Device GPS is currently OFF. Please Turn ON.");
        builder.setNegativeButton("not now", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 111);
            }
        }).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111) {
            ((SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.usermap)).getMapAsync(this);

            if (servicesOK()) {
                buildGoogleApi();
                mGoogleApiClient.connect();
            }
        } else if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.i(TAG, "Place: " + place.getName());
                LatLng latLng = place.getLatLng();

                goToAddress(latLng.latitude, latLng.longitude);

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.i(TAG, status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }


    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();
        myLocation = location;
        curretLatLng = new LatLng(location.getLatitude(), location.getLongitude());
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //// permission
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        //// permission

        location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000);
        LocationServices
                .FusedLocationApi
                .requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        if (location != null) {
            currentLatitude = location.getLatitude();
            currentLongitude = location.getLongitude();
            myLocation = location;

            if (mMap != null)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLatitude, currentLongitude), 16.0f));
            goToAddress(currentLatitude, currentLongitude);
            curretLatLng = new LatLng(currentLatitude, currentLongitude);
        }
    }


    private void goToAddress(Double mlatitude, Double mLogitude) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mlatitude, mLogitude), 16.0f));
        mMap.setOnCameraIdleListener(() -> {
            latitude = mMap.getCameraPosition().target.latitude;
            longitude = mMap.getCameraPosition().target.longitude;
            address = getLocationAddress(latitude, longitude);
            //  address = LocationAddressHelper.getLocationAddress(getBaseContext(), cameraPosition.target.latitude, cameraPosition.target.longitude);
            if (address == null) {
                Toast.makeText(GetUserLocationActivity.this, getString(R.string.cannot_fetch_address), Toast.LENGTH_SHORT).show();
            } else {
                address_txt.setText(address);
            }
        });
    }


    public String getLocationAddress(Double Lat, Double Lng) {
        Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
        try {
            List<Address> listAddresses = geocoder.getFromLocation(Lat, Lng, 1);
            if (null != listAddresses && !listAddresses.isEmpty()) {
                StringBuilder address = new StringBuilder();
                if (listAddresses.get(0).getMaxAddressLineIndex() > 0) {
                    for (int i = 0; i < listAddresses.get(0).getMaxAddressLineIndex(); i++) {
                        address.append(listAddresses.get(0).getAddressLine(i)).append(", ");
                    }
                } else {
                    address.append(listAddresses.get(0).getAddressLine(0));
                }
                return address.toString();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void finish(View view) {


        if (!address.matches("")) {
            Intent data = new Intent();
            data.putExtra(FULL_ADDRESS, address);
            data.putExtra(USER_LAT, String.valueOf(latitude));
            data.putExtra(USER_LANG, String.valueOf(longitude));
            setResult(RESULT_OK, data);
            finish();
        }
        Snackbar.make(findViewById(R.id.usermap), getText(R.string.noaddress), Snackbar.LENGTH_LONG).show();

    }
}
