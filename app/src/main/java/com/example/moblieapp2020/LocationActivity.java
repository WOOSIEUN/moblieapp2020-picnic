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

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.LocationSource;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.FusedLocationSource;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment)fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }
        //비동기로 콜백 메서드 호출
        mapFragment.getMapAsync(this);
        //위치 반환 구현체
        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,  @NonNull int[] grantResults) {
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

    public void setLocation(View view) throws ExecutionException, InterruptedException {
        Location userLocation = null;

        if(locationSource.isActivated()){
            userLocation = locationSource.getLastLocation();
            Log.d("userLocation","lat : " + userLocation.getLatitude() + " | lon : " + userLocation.getLongitude());
            GetDbData getData = new GetDbData();
            String result = getData.execute("FESTIVAL", "1", Double.toString(userLocation.getLatitude()), Double.toString(userLocation.getLongitude())).get();
            String[] splitedStr = result.split("#");
            int next = 0;
            while(next <= splitedStr.length) {
                Marker marker = new Marker();
                marker.setPosition(new LatLng(Double.valueOf(splitedStr[next+1]), Double.valueOf(splitedStr[next+2])));
                marker.setMap(thisNaverMap);
                next = next + 2;
            }
        } else{
            Log.d("userLocation","None");
        }
    }
}