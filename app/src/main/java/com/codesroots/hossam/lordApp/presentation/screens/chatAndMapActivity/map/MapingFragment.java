package com.codesroots.hossam.lordApp.presentation.screens.chatAndMapActivity.map;

import android.arch.lifecycle.ViewModelProvider;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codesroots.hossam.lordApp.presentation.screens.home.categoryFragment.CategoryViewModelFactory;
import com.codesroots.hossam.lordApp.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;


public class MapingFragment extends Fragment implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    //private List<Overlay> mapOverlays;

    private Projection projection;

    private MapView mapView;
    private GoogleMap mMap;
    public MapingFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.livemapwithchat, container, false);

        return view;

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView =  view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
    }

    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new CategoryViewModelFactory(getActivity().getApplication());
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.mMap = googleMap;
        //mMap.addPolyline()
    }

//
//    class MyOverlay extends com.google.android.maps.Overlay {
//
//        public MyOverlay() {
//
//        }
//
//        public void draw(Canvas canvas, MapView mapv, boolean shadow) {
//            super.draw(canvas, mapv, shadow);
//
//            Paint mPaint = new Paint();
//            mPaint.setDither(true);
//            mPaint.setColor(Color.RED);
//            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//            mPaint.setStrokeJoin(Paint.Join.ROUND);
//            mPaint.setStrokeCap(Paint.Cap.ROUND);
//            mPaint.setStrokeWidth(2);
//
//            Barcode.GeoPoint gP1 = new Barcode.GeoPoint(19240000, -99120000);
//            Barcode.GeoPoint gP2 = new Barcode.GeoPoint(37423157, -122085008);
//
//            Point p1 = new Point();
//            Point p2 = new Point();
//            Path path = new Path();
//
//            Projection projection = mapv.getProjection();
//            projection.toPixels(gP1, p1);
//            projection.toPixels(gP2, p2);
//
//            path.moveTo(p2.x, p2.y);
//            path.lineTo(p1.x, p1.y);
//
//            canvas.drawPath(path, mPaint);
//        }
//    }
}
