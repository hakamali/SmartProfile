package com.example.meefaisal.smartprofile;

import android.app.NotificationManager;
import android.content.Context;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class AddSleepingHoursFragment extends Fragment {
    NotificationManager mNotificationManager;
    Button button_submit_sleep;
    DatabaseHelper2 databaseHelper;
    EditText editText_start_hour,editText_start_min,editText_endi_hour,editText_endi_min;
    ImageView sleep,vibrate,ring;
    AudioManager audio;

    android.support.v4.app.FragmentManager fragmentManager;
    Fragment fragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_sleeping_hours, container, false);
        databaseHelper = new DatabaseHelper2(getContext());

        editText_start_hour = (EditText) v.findViewById(R.id.editText_start_hour_add);
        editText_endi_hour = (EditText) v.findViewById(R.id.editText_end_hour_add);
        editText_endi_min = (EditText) v.findViewById(R.id.editText_end_min_add);
        editText_start_min = (EditText) v.findViewById(R.id.editText_start_min_add);
        button_submit_sleep = (Button) v.findViewById(R.id.submit_sleep_add);
        sleep = (ImageView) v.findViewById(R.id.silent_add);
        vibrate = (ImageView) v.findViewById(R.id.vibration_add);
        ring = (ImageView) v.findViewById(R.id.ring_add);
        audio = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);


        button_submit_sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.insertDataSleep(editText_start_hour.getText().toString(),editText_start_min.getText().toString(),editText_endi_hour.getText().toString(),editText_endi_min.getText().toString());
                Toast.makeText(getActivity(), "Time Add Successfully", Toast.LENGTH_SHORT).show();
                editText_start_hour.setText("");
                editText_endi_hour.setText("");
                editText_start_min.setText("");
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
