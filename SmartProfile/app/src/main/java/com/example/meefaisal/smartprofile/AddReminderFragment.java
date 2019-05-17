package com.example.meefaisal.smartprofile;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class AddReminderFragment extends Fragment {
    EditText edittext_date,editText_time,editText_name,editText_address,editText_message;
    Calendar myCalendar;
    Button button_submit_rem;
    DatabaseHelper3 databaseHelper;
    FragmentManager fragmentManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_reminder, container, false);
        myCalendar = Calendar.getInstance();
        databaseHelper = new DatabaseHelper3(getContext());

       edittext_date= (EditText) v.findViewById(R.id.editText_date_rem);
       editText_time= (EditText) v.findViewById(R.id.editText_time_rem);
       editText_name= (EditText) v.findViewById(R.id.editText_name_rem);
       editText_address= (EditText) v.findViewById(R.id.editText_loc_rem);
       editText_message= (EditText) v.findViewById(R.id.editText_message);
       button_submit_rem = (Button) v.findViewById(R.id.submit_add_rem);

        edittext_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), AlertDialog.THEME_DEVICE_DEFAULT_DARK,date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        editText_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime();
            }
        });
        button_submit_rem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(editText_name.getText().toString())){


                    Toast.makeText(getContext(), "Please enter reminder title", Toast.LENGTH_SHORT).show();

                    return;
                }
                else if(TextUtils.isEmpty(edittext_date.getText().toString())){

                    Toast.makeText(getContext(), "Please enter reminder date", Toast.LENGTH_SHORT).show();

                    return;
                }
                else if(TextUtils.isEmpty(editText_time.getText().toString())){
                    Toast.makeText(getContext(), "Please enter reminder time", Toast.LENGTH_SHORT).show();

                    return;
                }
                else if(TextUtils.isEmpty(editText_address.getText().toString())){
                    Toast.makeText(getContext(), "Please enter  reminder address", Toast.LENGTH_SHORT).show();

                    return;
                }
                else if(TextUtils.isEmpty(editText_message.getText().toString())){

                    Toast.makeText(getContext(), "Please enter message", Toast.LENGTH_SHORT).show();

                    return;
                }
                boolean result = databaseHelper.insertDataReminder(editText_name.getText().toString(),edittext_date.getText().toString(),
                        editText_time.getText().toString(),editText_address.getText().toString(),
                        editText_message.getText().toString());
                if(result) {
                    Toast.makeText(getContext(), "Data Inserted", Toast.LENGTH_SHORT).show();
                    editText_name.setText("");
                    edittext_date.setText("");
                    editText_time.setText("");
                    editText_address.setText("");
                    editText_message.setText("");
                   fragmentManager = getFragmentManager();
                    Fragment fragment = fragmentManager.findFragmentById(R.id.frame_view);
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_view, new ManageReminderFragment());
                    fragmentTransaction.commit();
                }
                else {
                    Toast.makeText(getContext(), "Data Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });



        return v;
    }
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext_date.setText(sdf.format(myCalendar.getTime()));
    }
    private  void setTime(){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getContext(),AlertDialog.THEME_DEVICE_DEFAULT_DARK, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                editText_time.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }



}
