package com.example.meefaisal.smartprofile;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class FullProfileActivity extends AppCompatActivity {

   DatabaseHelper databaseHelper;
   TextView txt_name,txtname2,txt_email,txt_gender,txt_postal,txt_num,txt_loc;
   Button button_map,button_call,button_sms;
    String number;
    double lat,lon;
    String address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_profile);
        databaseHelper = new DatabaseHelper(this);
        txt_name = (TextView) findViewById(R.id.name);
        txtname2 = (TextView) findViewById(R.id.name2);
        txt_email = (TextView) findViewById(R.id.email);
        txt_gender = (TextView) findViewById(R.id.gender);
        txt_postal = (TextView) findViewById(R.id.post);
        txt_num = (TextView) findViewById(R.id.num);
        txt_loc = (TextView) findViewById(R.id.loc);
        button_map = (Button) findViewById(R.id.profile_map);
        button_call = (Button) findViewById(R.id.call_profile);
        button_sms = (Button) findViewById(R.id.sms_profile);
        int id = Integer.parseInt(getIntent().getStringExtra("id"));

//        Toast.makeText(this,  getIntent().getStringExtra("id"), Toast.LENGTH_SHORT).show();
        Cursor cursor = databaseHelper.getData2(id);
        while (cursor.moveToNext()){
            txt_name.setText( cursor.getString(1));
            txtname2.setText( cursor.getString(1));
            txt_email.setText( cursor.getString(2));
            txt_gender.setText( cursor.getString(3));
            txt_postal.setText( cursor.getString(4));
            txt_num.setText( cursor.getString(5));
            txt_loc.setText( cursor.getString(6));
           number = cursor.getString(5);
           lat = cursor.getDouble(7);
           lon = cursor.getDouble(8);
           address = cursor.getString(6);

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
        button_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+number));
                startActivity(i);
            }
        });
        button_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FullProfileActivity.this,ProfileMapsActivity.class);
                i.putExtra("lat",String.valueOf(lat));
                i.putExtra("lon",String.valueOf(lon));
                startActivity(i);
            }
        });
        button_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SmsManager smsManager = SmsManager.getDefault();
//                smsManager.sendTextMessage("03337572712",null,"hello",null,null);
                Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("sms:"));
                i.putExtra("sms_body","Location : "+ address + " \n Cordinates : " + lat +" , "+lon);
                startActivity(i);
            }
        });

    }
}
