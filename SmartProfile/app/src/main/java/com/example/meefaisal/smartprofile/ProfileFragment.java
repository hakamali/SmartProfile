package com.example.meefaisal.smartprofile;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class ProfileFragment extends Fragment {

    DatabaseHelper databaseHelper;
    DatabaseHelper2 databaseHelper2;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<User> list = new ArrayList<>();
    Cursor cursor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_profile, container, false);
        //////////////////
        Timer timer = new Timer();
        TimerTask hourlyTask = new TimerTask() {
            @Override
            public void run () {
//                 your code here...
                Intent intent = new Intent(getContext(), MyService.class);
                getContext().startService(intent);
            }
        };

// schedule the task to run starting now and then every hour...
        timer.schedule (hourlyTask, 0l, 1000*60);   // 1000*10*60 every 10 minut
        //////////////
        databaseHelper = new DatabaseHelper(getContext());

        FloatingActionButton fab = v.findViewById(R.id.fab);
        listView = (ListView) v.findViewById(R.id.list_profile);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(getActivity(), AddProfileActivity.class);
                getActivity().startActivity(myIntent);
            }
        });
        cursor = databaseHelper.getData();
        while (cursor.moveToNext()){
//            Toast.makeText(getActivity(), cursor.getString(0), Toast.LENGTH_SHORT).show();
//            Toast.makeText(getActivity(), cursor.getString(1), Toast.LENGTH_SHORT).show();
//            Toast.makeText(getActivity(), cursor.getString(3), Toast.LENGTH_SHORT).show();
//            Toast.makeText(getActivity(), cursor.getString(4), Toast.LENGTH_SHORT).show();
//            Toast.makeText(getActivity(), cursor.getString(5), Toast.LENGTH_SHORT).show();
//            Toast.makeText(getActivity(), cursor.getString(6), Toast.LENGTH_SHORT).show();
            String name = cursor.getString(1);
            String address = cursor.getString(6);
        list.add(new User(name ,address));

        }
        CustomeAdapter customeAdapter = new CustomeAdapter(list,getContext());
        listView.setAdapter(customeAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(),  String.valueOf(id), Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getActivity(),MapsActivity.class)); if(cursor!=null){
                System.out.println("position===>"+position);
                if(cursor.moveToFirst()){
                    cursor.moveToPosition(position);
                    String cardid =    (cursor.getString(0));
                    Intent i = new Intent(getContext(),FullProfileActivity.class);
                    i.putExtra("id",cardid);
                    startActivity(i);
//                    Toast.makeText(getActivity(), cardid, Toast.LENGTH_SHORT).show();

                }
            }



        });

        return v;
    }

}
