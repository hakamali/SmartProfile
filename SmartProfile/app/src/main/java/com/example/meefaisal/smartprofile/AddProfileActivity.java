package com.example.meefaisal.smartprofile;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class AddProfileActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {
    Geocoder geocoder;
    List<Address> address;
    GoogleApiClient googleApiClient;
    private TextView mLevelTextView;
    Button button,button_submit;
    DatabaseHelper databaseHelper;
    EditText editText_name,editText_email,editText_gender,editText_postalcode,editText_number;
    String lat,lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);
        databaseHelper = new DatabaseHelper(this);
        mLevelTextView = ((TextView) findViewById(R.id.text_loc));
        editText_name = (EditText) findViewById(R.id.editText_name);
        editText_email = (EditText) findViewById(R.id.editText_email);
        editText_gender = (EditText) findViewById(R.id.editText_gender);
        editText_postalcode = (EditText) findViewById(R.id.editText_post_code);
        editText_number = (EditText) findViewById(R.id.editText_number);
        button_submit = (Button) findViewById(R.id.submit);
        View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout, "Welcome To New Profile", Snackbar.LENGTH_LONG)
                .setAction("CLOSE", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                .show();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API).build();
        button = (Button) findViewById(R.id.location);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mLevelTextView.setVisibility(View.VISIBLE);
            }
        });
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(editText_name.getText().toString())){


                    Toast.makeText(AddProfileActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();

                    return;
                }
                else if(TextUtils.isEmpty(editText_email.getText().toString())){

                    Toast.makeText(AddProfileActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();

                    return;
                }
                else if(TextUtils.isEmpty(editText_gender.getText().toString())){
                    Toast.makeText(AddProfileActivity.this, "Please enter desire place", Toast.LENGTH_SHORT).show();

                    return;
                }
                else if(TextUtils.isEmpty(editText_postalcode.getText().toString())){
                    Toast.makeText(AddProfileActivity.this, "Please enter your podtsl code", Toast.LENGTH_SHORT).show();

                    return;
                }
                else if(TextUtils.isEmpty(editText_number.getText().toString())){

                    Toast.makeText(AddProfileActivity.this, "Please enter your number", Toast.LENGTH_SHORT).show();

                    return;
                }
                else if (TextUtils.isEmpty(mLevelTextView.getText().toString())){
                    Toast.makeText(AddProfileActivity.this, "please add location after turn on internet", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean result = databaseHelper.insertData(editText_name.getText().toString(),editText_email.getText().toString(),
                        editText_gender.getText().toString(),editText_postalcode.getText().toString(),
                        editText_number.getText().toString(),mLevelTextView.getText().toString(),lat,lon);
                if(result) {
                    Toast.makeText(AddProfileActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();

                    editText_name.setText("");
                    editText_email.setText("");
                    editText_gender.setText("");
                    editText_postalcode.setText("");
                    editText_number.setText("");
                    mLevelTextView.setText("");
                    startActivity(new Intent(AddProfileActivity.this, MainActivity.class));

                }
                else {
                    Toast.makeText(AddProfileActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        googleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        googleApiClient.disconnect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        startLocationService();

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
            lat = String.valueOf(location.getLatitude());
           lon = String.valueOf(location.getLongitude());

        geocoder = new Geocoder(this, Locale.getDefault());
//        Toast.makeText(this, "h", Toast.LENGTH_SHORT).show();
        try {
            address = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            String adres = address.get(0).getAddressLine(0);
            String area = address.get(0).getLocality();
            String city = address.get(0).getAdminArea();
            String country = address.get(0).getCountryName();
            String postal = address.get(0).getPostalCode();
//            Toast.makeText(this, adres, Toast.LENGTH_SHORT).show();
            String fulladdress = adres + "," + area + "," + city + "," + country + "," + postal;
            mLevelTextView.setText(fulladdress);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    void startLocationService() {
        try {
            LocationRequest req = LocationRequest.create().setPriority(LocationRequest.PRIORITY_LOW_POWER);

            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, req, this);
        }catch (SecurityException ex){
            ex.printStackTrace();
        }
    }
}
