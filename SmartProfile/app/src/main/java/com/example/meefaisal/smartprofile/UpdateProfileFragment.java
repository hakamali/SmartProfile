package com.example.meefaisal.smartprofile;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class UpdateProfileFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener{
    Geocoder geocoder;
    List<Address> address;
    GoogleApiClient googleApiClient;
    private TextView mLevelTextView;
    Button button,button_submit;
    DatabaseHelper databaseHelper;
    EditText editText_name,editText_email,editText_gender,editText_postalcode,editText_number;
    String lat,lon;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_update_profile, container, false);
        mLevelTextView = ((TextView) v.findViewById(R.id.text_loc_update));
        databaseHelper = new DatabaseHelper(getContext());
        editText_name = (EditText) v.findViewById(R.id.editText_name_update);
        editText_email = (EditText) v.findViewById(R.id.editText_email_update);
        editText_gender = (EditText) v.findViewById(R.id.editText_gender_update);
        editText_postalcode = (EditText) v.findViewById(R.id.editText_post_code_update);
        editText_number = (EditText) v.findViewById(R.id.editText_number_update);
        button_submit = (Button) v.findViewById(R.id.submit_update);
        button = (Button) v.findViewById(R.id.location_update);
        googleApiClient = new GoogleApiClient.Builder(getContext())
//                .enableAutoManage(getActivity(),this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API).build();
//        mLevelTextView.setVisibility(View.VISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mLevelTextView.setVisibility(View.VISIBLE);
            }
        });
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(TextUtils.isEmpty(editText_number.getText().toString())){

                    Toast.makeText(getContext(), "Please enter your number", Toast.LENGTH_SHORT).show();

                    return;
                }
                else if(TextUtils.isEmpty(editText_name.getText().toString())){


                    Toast.makeText(getContext(), "Please enter your name", Toast.LENGTH_SHORT).show();

                    return;
                }
                else if(TextUtils.isEmpty(editText_email.getText().toString())){

                    Toast.makeText(getContext(), "Please enter your email", Toast.LENGTH_SHORT).show();

                    return;
                }
                else if(TextUtils.isEmpty(editText_gender.getText().toString())){
                    Toast.makeText(getContext(), "Please enter desire place", Toast.LENGTH_SHORT).show();

                    return;
                }
                else if(TextUtils.isEmpty(editText_postalcode.getText().toString())){
                    Toast.makeText(getContext(), "Please enter your podtsl code", Toast.LENGTH_SHORT).show();

                    return;
                }

                else if (TextUtils.isEmpty(mLevelTextView.getText().toString())){
                    Toast.makeText(getContext(), "please add location after turn on internet", Toast.LENGTH_SHORT).show();
                    return;
                }
                databaseHelper.updateData(editText_name.getText().toString(),editText_email.getText().toString(),
                        editText_gender.getText().toString(),editText_postalcode.getText().toString(),
                        editText_number.getText().toString(),mLevelTextView.getText().toString(),lat,lon);
                Toast.makeText(getActivity(), "SProfile Updated Successfully", Toast.LENGTH_SHORT).show();
                FragmentManager fragmentManager = getFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.frame_view);
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_view, new ProfileFragment());
                fragmentTransaction.commit();
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        googleApiClient.connect();
    }

    @Override
    public void onPause() {
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
        geocoder = new Geocoder(getContext(), Locale.getDefault());
//        Toast.makeText(getContext(), "h", Toast.LENGTH_SHORT).show();
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
