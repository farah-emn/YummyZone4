package com.example.yummyzone.activites;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.example.yummyzone.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;
import java.util.Locale;


public class MapActivity extends AppCompatActivity {

    SupportMapFragment smf;
    FusedLocationProviderClient client;
    Button loc;
    TextView tl;
    Button bt_continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        loc=findViewById(R.id.Confirm_Location);
        bt_continue = findViewById(R.id.map_bt_continue);
        bt_continue.setEnabled(false);
        getSupportActionBar().hide();


        smf = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        client = LocationServices.getFusedLocationProviderClient(this);

        bt_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getmylocation();
                bt_continue.setEnabled(true);
            }
        });
        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

    }

    private void showDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog);
//        EditText et_city = dialog.findViewById(R.id.dialog_et_city);
//        EditText et_street = dialog.findViewById(R.id.dialog_et_street);
//        EditText et_district = dialog.findViewById(R.id.dialog_et_district);
//        TextView tv = dialog.findViewById(R.id.dialig_tv);
        Button bt_save = dialog.findViewById(R.id.dialog_bt_save);
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations= R.style.dialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

//        String city, street, district;
//        city = et_city.getText().toString();
//        street = et_street.getText().toString();
//        district = et_district.getText().toString();


        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = ((EditText)dialog.findViewById(R.id.dialog_et_city)).getText().toString();
                String street = ((EditText)dialog.findViewById(R.id.dialog_et_street)).getText().toString();
                String district = ((EditText)dialog.findViewById(R.id.dialog_et_district)).getText().toString();
                TextView tv = dialog.findViewById(R.id.dialig_tv);
                if (city == null || street == null || district == null){
                    tv.setText("please enter all field");

                }
                else {
                    String price = getIntent().getStringExtra("total");
                    String id = getIntent().getStringExtra("delivery_fee");
                    Intent intent = new Intent(MapActivity.this, orderActivity.class);
                    intent.putExtra("total", price.toString());
                    intent.putExtra("delivery_fee", id.toString());
                    startActivity(intent);
                }
            }
        });
    }

    @SuppressLint("MissingPermission")
    private void pi() {

        try {
            LocationManager manager= (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            // LocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,MainActivity4.this);
            Location location=manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null){

                Geocoder geocoder = new Geocoder(MapActivity.this, Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                String address = addresses.get(0).getAddressLine(0);
                tl.setText(address);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void getmylocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                smf.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
                        MarkerOptions markerOptions=new MarkerOptions().position(latLng).title("You are here...!!");
                        googleMap.addMarker(markerOptions).showInfoWindow();
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));

                    }
                });
            }
        });
    }
}