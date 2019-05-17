package com.example.meefaisal.smartprofile;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UpdateReminderActivity extends AppCompatActivity {
    int id;
    EditText edittext_date,editText_time,editText_name,editText_address,editText_message;
    Calendar myCalendar;
    Button button_submit_rem;
    DatabaseHelper3 databaseHelper;
    FragmentManager fragmentManager;
    android.app.Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_reminder);
       id = Integer.parseInt(getIntent().getStringExtra("id"));

//        Toast.makeText(this,  getIntent().getStringExtra("id"), Toast.LENGTH_SHORT).show();
        myCalendar = Calendar.getInstance();
        databaseHelper = new DatabaseHelper3(this);

        edittext_date= (EditText) findViewById(R.id.editText_date_rem_update);
        editText_time= (EditText) findViewById(R.id.editText_time_rem_update);
        editText_name= (EditText) findViewById(R.id.editText_name_rem_update);
        editText_address= (EditText) findViewById(R.id.editText_loc_rem_update);
        editText_message= (EditText) findViewById(R.id.editText_message_update);
        button_submit_rem = (Button) findViewById(R.id.submit_add_rem_update);

        edittext_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(UpdateReminderActivity.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK, date, myCalendar
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


                    Toast.makeText(UpdateReminderActivity.this, "Please enter reminder title", Toast.LENGTH_SHORT).show();

                    return;
                }
                else if(TextUtils.isEmpty(edittext_date.getText().toString())){

                    Toast.makeText(UpdateReminderActivity.this, "Please enter reminder date", Toast.LENGTH_SHORT).show();

                    return;
                }
                else if(TextUtils.isEmpty(editText_time.getText().toString())){
                    Toast.makeText(UpdateReminderActivity.this, "Please enter reminder time", Toast.LENGTH_SHORT).show();

                    return;
                }
                else if(TextUtils.isEmpty(editText_address.getText().toString())){
                    Toast.makeText(UpdateReminderActivity.this, "Please enter  reminder address", Toast.LENGTH_SHORT).show();

                    return;
                }
                else if(TextUtils.isEmpty(editText_message.getText().toString())){

                    Toast.makeText(UpdateReminderActivity.this, "Please enter message", Toast.LENGTH_SHORT).show();

                    return;
                }
                databaseHelper.updateDataReminder(editText_name.getText().toString(),edittext_date.getText().toString(),
                        editText_time.getText().toString(),editText_address.getText().toString(),
                        editText_message.getText().toString(),id);
                Toast.makeText(UpdateReminderActivity.this, "Reminder Update Successfully", Toast.LENGTH_SHORT).show();
                editText_name.setText("");
                edittext_date.setText("");
                editText_time.setText("");
                editText_address.setText("");
                editText_message.setText("");
                startActivity( new Intent(UpdateReminderActivity.this,MainActivity.class));

            }
        });


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
        mTimePicker = new TimePickerDialog(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK,new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                editText_time.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

}
