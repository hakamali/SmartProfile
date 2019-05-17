package com.example.meefaisal.smartprofile;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ImageButton logo;
    DrawerLayout drawerLayout;
    boolean doubleBackToExitPressedOnce = false;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.frame_view);

        if(fragment == null) {
            ProfileFragment profileFragment = new ProfileFragment();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.frame_view, profileFragment);
            fragmentTransaction.commit();

        }

        logo = (ImageButton) findViewById(R.id.toogle);
        navigationView = (NavigationView) findViewById(R.id.navigation_View);
//        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()){

                    case R.id.id_profile:
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        Fragment fragment = fragmentManager.findFragmentById(R.id.frame_view);


                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.frame_view, new ProfileFragment());
                            fragmentTransaction.commit();
                            drawerLayout.closeDrawer(Gravity.LEFT);
                            item.setChecked(true);


                        break;
//                    case R.id.id_block:
//                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                            fragmentTransaction.replace(R.id.frame_view, new CallBlockFragment());
//                            fragmentTransaction.commit();
//                            drawerLayout.closeDrawer(Gravity.LEFT);
//                            item.setChecked(true);
//
//                        break;
                    case  R.id.id_maintain_call_block:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_view, new MaintainCallBlockFragment());
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        item.setChecked(true);

                        break;
                    case  R.id.id_reminder:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_view, new AddReminderFragment());
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        item.setChecked(true);

                        break;
                    case  R.id.id_manage_reminder:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_view, new ManageReminderFragment());
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        item.setChecked(true);

                        break;
                    case  R.id.id_sleep_hour:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_view, new SleepHoursFragment());
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        item.setChecked(true);

                        break;
                    case  R.id.id_sleep_hour_add:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_view, new AddSleepingHoursFragment());
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        item.setChecked(true);

                        break;

                    case  R.id.id_clear_log:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_view, new ClearLogFragment());
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        item.setChecked(true);

                        break;
                    case  R.id.id_current_loc:
                        Intent i = new Intent(MainActivity.this,MapsActivity.class);
                        startActivity(i);
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        item.setChecked(true);

                        break;
                    case  R.id.id_update:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_view, new UpdateProfileFragment());
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        item.setChecked(true);

                        break;
                    case  R.id.id_del:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_view, new DeleteProfileFragment());
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        item.setChecked(true);

                        break;
                    case  R.id.id_delete_reminder:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_view, new UpdateReminderFragment());
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        item.setChecked(true);

                        break;
                    case  R.id.id_update_reminder:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_view, new NewUpdateReminderFragment());
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        item.setChecked(true);

                        break;
                    case R.id.id_about:
                        //your code here
                        startActivity(new Intent(MainActivity.this,AboutActivity.class));
                        break;

                }
                return true;
            }
        });


    }





    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {



                return super.onOptionsItemSelected(item);

    }
}
