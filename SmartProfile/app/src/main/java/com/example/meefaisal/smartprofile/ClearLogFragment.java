package com.example.meefaisal.smartprofile;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;


public class ClearLogFragment extends Fragment {

ImageView imageView ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_clear_log, container, false);
        imageView = (ImageView) v.findViewById(R.id.img);
       imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
           return;
        }
        getActivity().getContentResolver().delete(android.provider.CallLog.Calls.CONTENT_URI, null, null);
                Toast.makeText(getActivity(), "you have successfully clear your logs", Toast.LENGTH_SHORT).show();
            }
        });
        return  v;
    }

}
