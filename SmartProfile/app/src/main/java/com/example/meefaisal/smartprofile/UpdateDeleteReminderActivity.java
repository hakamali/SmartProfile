package com.example.meefaisal.smartprofile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UpdateDeleteReminderActivity extends AppCompatActivity {

    Button button_delete;
    DatabaseHelper3 databaseHelper;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_reminder);
        databaseHelper = new DatabaseHelper3(this);
//        button_update = (Button) findViewById(R.id.update_reminder);
        button_delete = (Button) findViewById(R.id.delete_reminder);

         id = Integer.parseInt(getIntent().getStringExtra("id"));

//        Toast.makeText(this,  getIntent().getStringExtra("id"), Toast.LENGTH_SHORT).show();
//        button_update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(UpdateDeleteReminderActivity.this, id, Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(UpdateDeleteReminderActivity.this,ReminderDetailActivity.class);
//                    i.putExtra("id",id);
//                    startActivity(i);
//            }
//        });
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.deleteDataReminder(id);
                Toast.makeText(UpdateDeleteReminderActivity.this, "Reminder Deleted Successfully", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
