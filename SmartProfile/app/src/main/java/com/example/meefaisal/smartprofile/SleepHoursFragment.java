package com.example.meefaisal.smartprofile;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static android.content.Context.NOTIFICATION_SERVICE;


public class SleepHoursFragment extends Fragment {

   NotificationManager mNotificationManager;
    Button button_submit_sleep;
    DatabaseHelper2 databaseHelper;
    EditText editText_start_hor,editText_start_min,editText_endi_hor,editText_endi_min;
    ImageView sleep,vibrate,ring;
    AudioManager audio;
    android.support.v4.app.FragmentManager fragmentManager;
    Fragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sleep_hours, container, false);
        databaseHelper = new DatabaseHelper2(getContext());

        editText_start_hor = (EditText) v.findViewById(R.id.editText_start_hour);
        editText_start_min = (EditText) v.findViewById(R.id.editText_start_min);
        editText_endi_hor = (EditText) v.findViewById(R.id.editText_end_hour);
        editText_endi_min = (EditText) v.findViewById(R.id.editText_end_min);
        button_submit_sleep = (Button) v.findViewById(R.id.submit_sleep);
        sleep = (ImageView) v.findViewById(R.id.silent);
        vibrate = (ImageView) v.findViewById(R.id.vibration);
        ring = (ImageView) v.findViewById(R.id.ring);
        audio = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);


   button_submit_sleep.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           databaseHelper.updateDataSleep(editText_start_hor.getText().toString(),editText_start_min.getText().toString(),editText_endi_hor.getText().toString(),editText_endi_min.getText().toString());
           Toast.makeText(getActivity(), "Time Updated Successfully", Toast.LENGTH_SHORT).show();
           editText_start_hor.setText("");
           editText_start_min.setText("");
           editText_endi_hor.setText("");
           editText_endi_min.setText("");
           fragmentManager = getFragmentManager();
           fragment = fragmentManager.findFragmentById(R.id.frame_view);
           android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
           fragmentTransaction.replace(R.id.frame_view, new ProfileFragment());
           fragmentTransaction.commit();
       }
   });
   sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                Toast.makeText(getContext(), "Your Mobile On Silent Mod Now", Toast.LENGTH_SHORT).show();
            }
        });
        vibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                Toast.makeText(getContext(), "Your Mobile IS On Vibration Now", Toast.LENGTH_SHORT).show();
            }
        });
        ring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                Toast.makeText(getContext(), "Your Mobile IS On Ring Now", Toast.LENGTH_SHORT).show();
            }
        });


        return v;
    }


}
