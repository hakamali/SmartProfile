package com.example.meefaisal.smartprofile;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class NewUpdateReminderFragment extends Fragment {
 DatabaseHelper3 databaseHelper;
 Cursor cursor;
    ListView listView;
    ArrayList<Reminder> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v =  inflater.inflate(R.layout.fragment_update_reminder, container, false);
        databaseHelper = new DatabaseHelper3(getContext());
        listView = (ListView) v.findViewById(R.id.list_update_reminder);

        cursor = databaseHelper.getDataReminder();
        while (cursor.moveToNext()){
//            Toast.makeText(getActivity(), cursor.getString(1), Toast.LENGTH_SHORT).show();
//            Toast.makeText(getActivity(), cursor.getString(2), Toast.LENGTH_SHORT).show();
//            Toast.makeText(getActivity(), cursor.getString(3), Toast.LENGTH_SHORT).show();
//            Toast.makeText(getActivity(), cursor.getString(4), Toast.LENGTH_SHORT).show();
//            Toast.makeText(getActivity(), cursor.getString(5), Toast.LENGTH_SHORT).show();
            String name = cursor.getString(1);
            String address = cursor.getString(4);
            String date = cursor.getString(2);
            String time = cursor.getString(3);
            String message = cursor.getString(5);
            list.add(new Reminder(name,date,time,address,message));

        }
        CustomeAdapterReminder customeAdapterReminder = new CustomeAdapterReminder(list,getContext());
        listView.setAdapter(customeAdapterReminder);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(),  String.valueOf(id), Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getActivity(),MapsActivity.class)); if(cursor!=null){
                System.out.println("position===>"+position);
                if(cursor.moveToFirst()){
                    cursor.moveToPosition(position);
                    String cardid =    (cursor.getString(0));
                    Intent i = new Intent(getContext(),UpdateReminderActivity.class);
                    i.putExtra("id",cardid);
                    startActivity(i);
//                    Toast.makeText(getActivity(), cardid, Toast.LENGTH_SHORT).show();

                }
            }



        });
       return v;
    }

}
