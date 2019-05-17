package com.example.meefaisal.smartprofile;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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


public class DeleteProfileFragment extends Fragment {
    Button delete;
    EditText number;
    DatabaseHelper databaseHelper;
    TextView txdel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_delete_profile, container, false);
        databaseHelper = new DatabaseHelper(getContext());

        delete = (Button) v.findViewById(R.id.del_btn);
        txdel = (TextView) v.findViewById(R.id.text_del);
        number = (EditText) v.findViewById(R.id.editText_number_del);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(number.getText().toString())){
                    Toast.makeText(getContext(), "Please enter your number", Toast.LENGTH_SHORT).show();

                    return;
                }
                databaseHelper.deleteData(number.getText().toString());

                txdel.setText("Data Deleted");
                txdel.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "Profile Deleted Successfully", Toast.LENGTH_SHORT).show();
                FragmentManager fragmentManager = getFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.frame_view);
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_view, new ProfileFragment());
                fragmentTransaction.commit();

            }
        });
        return v;
    }
}
