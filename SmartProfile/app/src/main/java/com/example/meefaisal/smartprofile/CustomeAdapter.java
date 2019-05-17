package com.example.meefaisal.smartprofile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MeeFaisal on 3/18/2018.
 */

public class CustomeAdapter extends BaseAdapter {

    ArrayList<User> list;
    Context context;

    public CustomeAdapter (ArrayList<User> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public User getItem(int i) {
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
            v = LayoutInflater.from(context).inflate(R.layout.user_list_snippet, null);
        }

        TextView name  = (TextView)v.findViewById(R.id.text_view_name_list_snippet);
        TextView address  = (TextView)v.findViewById(R.id.text_view_address_list_snippet);
        User user = getItem(i);

        if(user!=null){
            name.setText(user.getName());
           address.setText(user.getAddress());
        }


        return v;
    }
}
