package com.example.meefaisal.smartprofile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MeeFaisal on 3/18/2018.
 */

public class CustomeAdapterReminder extends BaseAdapter {

    ArrayList<Reminder> list;
    Context context;

    public CustomeAdapterReminder(ArrayList<Reminder> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Reminder getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;

        if(v==null) {
            v = LayoutInflater.from(context).inflate(R.layout.reminder_list_snippet, null);
        }

        TextView name  = (TextView)v.findViewById(R.id.text_view_name_reminder);
        TextView date  = (TextView)v.findViewById(R.id.text_view_date_reminder);
        TextView time  = (TextView)v.findViewById(R.id.text_view_time_reminder);
        TextView address  = (TextView)v.findViewById(R.id.text_view_location_reminder);
        Reminder reminder = getItem(i);

        if(reminder!=null){
            name.setText(reminder.getName());
            date.setText(reminder.getDate());
            time.setText(reminder.getTime());
           address.setText(reminder.getAddress());
        }


        return v;
    }
}
