package com.example.meefaisal.smartprofile;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BlockedNumberContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MaintainCallBlockFragment extends Fragment {

Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_maintain_call_block, container, false);
        button = (Button) v.findViewById(R.id.btn_block);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent();
                i.setComponent(new ComponentName("com.android.contacts", "com.android.contacts.DialtactsContactsEntryActivity"));

                i.addCategory("android.intent.category.LAUNCHER");
                i.addCategory("android.intent.category.DEFAULT");
                startActivity(i);
            }
        });

        return v;
    }

}
