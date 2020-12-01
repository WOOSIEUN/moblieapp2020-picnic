package com.example.moblieapp2020;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.LocationSource;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.util.FusedLocationSource;
import com.naver.maps.map.util.MarkerIcons;
import com.naver.maps.map.widget.LocationButtonView;

import java.util.concurrent.ExecutionException;

public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private MapView mapView;
    private FusedLocationSource locationSource;
    private LocationManager locationManager;
    private NaverMap thisNaverMap;
    private String[] PERMISSION = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private Marker marker;
    private String[] splitedStr;
    private int next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }
        //비동기로 콜백 메서드 호출
        mapFragment.getMapAsync(this);
        //위치 반환 구현체
        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated()) { // 권한 거부됨
                thisNaverMap.setLocationTrackingMode(LocationTrackingMode.Face);
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        //위치 권한 확인하기
        ActivityCompat.requestPermissions(this, PERMISSION, LOCATION_PERMISSION_REQUEST_CODE);

        thisNaverMap = naverMap;
        thisNaverMap.setLocationSource(locationSource);

        UiSettings uiSettings = thisNaverMap.getUiSettings();
        uiSettings.setLocationButtonEnabled(false);
        LocationButtonView locationButtonView = findViewById(R.id.location);
        locationButtonView.setMap(thisNaverMap);
    }

    public void searchFestival(View view) {
        if (locationSource.isActivated()) {
            new Thread() {
                public void run() {
                    Location userLocation = locationSource.getLastLocation();
                    Log.d("userLocation", "lat : " + userLocation.getLatitude() + " | lon : " + userLocation.getLongitude());
                    GetDbData getData = new GetDbData();
                    String result = null;
                    try {
                        result = getData.execute("FESTIVAL", "1", Double.toString(userLocation.getLatitude()), Double.toString(userLocation.getLongitude()), "0").get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    splitedStr = result.split("#");
                    Log.d("Marker", "splitedStr length : " + splitedStr.length);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            next = 0;
                            while (next + 3 <= splitedStr.length) {
                                marker = new Marker();
                                marker.setIcon(MarkerIcons.PINK);
                                marker.setCaptionText(splitedStr[next + 1]);
                                marker.setPosition(new LatLng(Double.parseDouble(splitedStr[next + 2]), Double.parseDouble(splitedStr[next + 3])));
                                marker.setMap(thisNaverMap);
                                next = next + 4;
                            }
                        }
                    });
                }
            }.start();
        } else {
            Log.d("userLocation", "None");
        }
    }

    public void searchTour(View view) {
        if (locationSource.isActivated()) {
            new Thread() {
                public void run() {
                    Location userLocation = locationSource.getLastLocation();
                    Log.d("userLocation", "lat : " + userLocation.getLatitude() + " | lon : " + userLocation.getLongitude());
                    GetDbData getData = new GetDbData();
                    String result = null;
                    try {
                        result = getData.execute("TOUR", "1", Double.toString(userLocation.getLatitude()), Double.toString(userLocation.getLongitude()), "0").get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    splitedStr = result.split("#");
                    Log.d("Marker", "splitedStr length : " + splitedStr.length);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            next = 0;
                            while (next + 3 <= splitedStr.length) {
                                marker = new Marker();
                                marker.setIcon(MarkerIcons.LIGHTBLUE);
                                marker.setCaptionText(splitedStr[next + 1]);
                                marker.setPosition(new LatLng(Double.parseDouble(splitedStr[next + 2]), Double.parseDouble(splitedStr[next + 3])));
                                marker.setMap(thisNaverMap);
                                next = next + 4;
                            }
                        }
                    });
                }
            }.start();
        } else {
            Log.d("userLocation", "None");
        }
    }

    public void searchHeritage(View view) {
        if (locationSource.isActivated()) {
            new Thread() {
                public void run() {
                    Location userLocation = locationSource.getLastLocation();
                    Log.d("userLocation", "lat : " + userLocation.getLatitude() + " | lon : " + userLocation.getLongitude());
                    GetDbData getData = new GetDbData();
                    String result = null;
                    try {
                        result = getData.execute("HERITAGE", "1", Double.toString(userLocation.getLatitude()), Double.toString(userLocation.getLongitude()), "0").get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    splitedStr = result.split("#");
                    Log.d("Marker", "splitedStr length : " + splitedStr.length);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            next = 0;
                            while (next + 3 <= splitedStr.length) {
                                marker = new Marker();
                                marker.setIcon(MarkerIcons.YELLOW);
                                marker.setCaptionText(splitedStr[next + 1]);
                                marker.setPosition(new LatLng(Double.parseDouble(splitedStr[next + 2]), Double.parseDouble(splitedStr[next + 3])));
                                marker.setMap(thisNaverMap);
                                next = next + 4;
                            }
                        }
                    });
                }
            }.start();
        } else {
            Log.d("userLocation", "None");
        }
    }
}