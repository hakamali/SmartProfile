package com.example.meefaisal.smartprofile;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ReminderDetailActivity extends AppCompatActivity {
 DatabaseHelper3 databaseHelper;
 Cursor cursor;
 TextView textView_name,textView_date,textView_time,textView_address,textView_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_detail);
        databaseHelper = new DatabaseHelper3(this);
        textView_name = (TextView) findViewById(R.id.title_rem);
        textView_date = (TextView) findViewById(R.id.date_rem);
        textView_time = (TextView) findViewById(R.id.time_rem);
        textView_address = (TextView) findViewById(R.id.address_rem);
        textView_message = (TextView) findViewById(R.id.message_rem);
        int id = Integer.parseInt(getIntent().getStringExtra("id"));

//        Toast.makeText(this,  getIntent().getStringExtra("id"), Toast.LENGTH_SHORT).show();
        Cursor cursor = databaseHelper.getDataReminder2(id);
        while (cursor.moveToNext()){
            textView_name.setText( cursor.getString(1));
            textView_date.setText( cursor.getString(2));
            textView_time.setText( cursor.getString(3));
            textView_address.setText( cursor.getString(4));
            textView_message.setText( cursor.getString(5));
//            Toast.makeText(this, cursor.getString(1), Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, cursor.getString(2), Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, cursor.getString(3), Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, cursor.getString(4), Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, cursor.getString(5), Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, cursor.getString(6), Toast.LENGTH_SHORT).show();
//            name.setText(cursor.getString(1));
//            sell_price.setText(cursor.getString(3));
//            purchase_price.setText(cursor.getString(4));

        }
    }
}
