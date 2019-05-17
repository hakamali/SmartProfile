package com.example.meefaisal.smartprofile;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Geocoder;
import android.location.Location;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;


public class MyService extends Service implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener{
    AudioManager audio;
    Cursor cursor;
    Cursor cursor_main;
    DatabaseHelper2 databaseHelper;
    DatabaseHelper databaseHelper_main;
    DatabaseHelper3 databaseHelper3;
    int start_time_hor,start_time_min,end_time_hor,end_time_min;
   double db_lat,db_lon;
    double lat,lon;
    String place;
    GoogleApiClient googleApiClient;
Boolean isPermitted = false ;
    @Override
    public void onLocationChanged(Location location) {
        audio = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
        databaseHelper_main = new DatabaseHelper(this);
        lat = location.getLatitude();
        lon = location.getLongitude();

//        Toast.makeText(this, String.valueOf(lat), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, String.valueOf(lon), Toast.LENGTH_SHORT).show();
        cursor_main = databaseHelper_main.getData();
        databaseHelper = new DatabaseHelper2(this);
        databaseHelper3 = new DatabaseHelper3(this);

        //
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+5:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm a");
        date.setTimeZone(TimeZone.getTimeZone("GMT+5:00"));
        String localTime = date.format(currentLocalTime);
        int currentHour = cal.get(Calendar.HOUR_OF_DAY);

        int currentMinutes = cal.get(Calendar.MINUTE);
        int currentSeconds = cal.get(Calendar.SECOND);

        cursor = databaseHelper.getDataSleep();
        while (cursor.moveToNext()) {
////            Toast.makeText(this, cursor.getString(1), Toast.LENGTH_SHORT).show();
////            Toast.makeText(this, cursor.getString(2), Toast.LENGTH_SHORT).show();
////            Toast.makeText(this, cursor.getString(3), Toast.LENGTH_SHORT).show();
////            Toast.makeText(this, cursor.getString(4), Toast.LENGTH_SHORT).show();
//////
            start_time_hor = Integer.parseInt(cursor.getString(1));
            start_time_min = Integer.parseInt(cursor.getString(2));
            end_time_hor = Integer.parseInt(cursor.getString(3));
            end_time_min = Integer.parseInt(cursor.getString(4));
//            //////////////////
//        Toast.makeText(this, String.valueOf(currentHour), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, String.valueOf(currentMinutes), Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, String.valueOf(start_time_hor), Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, String.valueOf(start_time_min), Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, String.valueOf(end_time_hor), Toast.LENGTH_SHORT).show();
//
//            Toast.makeText(this, String.valueOf(end_time_min), Toast.LENGTH_SHORT).show();

            if (currentHour >= start_time_hor && currentMinutes >= start_time_min && currentHour <= end_time_hor && currentMinutes <= end_time_min) {
                audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                Toast.makeText(this, "Now Mobile In Silent", Toast.LENGTH_SHORT).show();
                break;
            } else {
//                Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
                audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            }
        }


        while (cursor_main.moveToNext()){
             db_lat = Double.parseDouble(cursor_main.getString(7));
            db_lon = Double.parseDouble(cursor_main.getString(8));
            place =  cursor_main.getString(3);
//            lat = Double.parseDouble(String.format("%.4f", lat));
//            Toast.makeText(this,  String.format("%.4f", lat), Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, String.valueOf(db_lat), Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, String.format("%.4f", lon), Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, String.valueOf(db_lon), Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, place, Toast.LENGTH_SHORT).show();
            if(place.equals("gym")||place.equals("Gym") && Double.parseDouble(String.format("%.4f", lat))==db_lat && Double.parseDouble(String.format("%.4f", lon))==db_lon){
                audio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
            }
            if( place.equals("mosque")||place.equals("Mosque")&& Double.parseDouble(String.format("%.4f", lat))==db_lat && Double.parseDouble(String.format("%.4f", lon))==db_lon){
                audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            }
            if(place.equals("Home") || place.equals("home")&& Double.parseDouble(String.format("%.4f", lat))==db_lat && Double.parseDouble(String.format("%.4f", lon))==db_lon){
            audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            }
            if(place.equals("Driving") || place.equals("driving")&& Double.parseDouble(String.format("%.4f", lat))==db_lat && Double.parseDouble(String.format("%.4f", lon))==db_lon){
                audio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
            }
            if( place.equals("office")||place.equals("Office")&& Double.parseDouble(String.format("%.4f", lat))==db_lat && Double.parseDouble(String.format("%.4f", lon))==db_lon){
                audio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
            }
            if(place.equals("University") || place.equals("university")&& Double.parseDouble(String.format("%.4f", lat))==db_lat && Double.parseDouble(String.format("%.4f", lon))==db_lon){
                audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            }

        }
        Cursor cursor3 = databaseHelper3.getDataReminder();
        while (cursor3.moveToNext()){
            Toast.makeText(this, String.valueOf(cursor3.getString(2)), Toast.LENGTH_SHORT).show();
            String dateSplit = cursor3.getString(2);
            String title = cursor3.getString(1);
                    String message = cursor3.getString(5);
            String time = cursor3.getString(3);
            String splitTime[]=time.split(":");
            String splitDate[]=dateSplit.split("/");
            String hours=splitTime[0];
            String minutes=splitTime[1];
            String month=splitDate[0];
            String day=splitDate[1];
            String year=splitDate[2];
            SimpleDateFormat format = new SimpleDateFormat("MMM dd,yyyy  ", Locale.ENGLISH);

            int splitmonth = Integer.parseInt(month);
            Calendar myCal = new GregorianCalendar();
            if(Integer.parseInt(month)<10){
                splitmonth = splitmonth%10;
            }
            int splityear = myCal.get(Calendar.YEAR)%100;

            Toast.makeText(this, String.valueOf(currentHour) +","+hours, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, String.valueOf(currentMinutes) +","+minutes, Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, String.valueOf(myCal.get(Calendar.DAY_OF_MONTH)), Toast.LENGTH_SHORT).show();
//            Toast.makeText(this,  String.valueOf( (myCal.get(Calendar.MONTH) + 1)), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, splitmonth +","+String.valueOf(myCal.get(Calendar.MONTH) + 1) +","+ day +","+ String.valueOf(myCal.get(Calendar.DAY_OF_MONTH)) +","+year +","+ String.valueOf(splityear), Toast.LENGTH_SHORT).show();
//            currentHour == Integer.parseInt(hours) && currentMinutes==Integer.parseInt(minutes) &&
            if(currentHour == Integer.parseInt(hours) && currentMinutes==Integer.parseInt(minutes) && splitmonth == (myCal.get(Calendar.MONTH) + 1) && day.equals(String.valueOf(myCal.get(Calendar.DAY_OF_MONTH))) && year.equals(String.valueOf(splityear)) ) {
                Toast.makeText(this, "hello reminder", Toast.LENGTH_SHORT).show();
                int mNotificationId = 001;
                // Build Notification , setOngoing keeps the notification always in status bar
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(getBaseContext())
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Your "+title+" Reminder")
                                .setContentText(message)
                                .setOngoing(true);

                // Create pending intent, mention the Activity which needs to be
                //triggered when user clicks on notification(StopScript.class in this case)

                PendingIntent contentIntent = PendingIntent.getActivity(getBaseContext(), 0,
                        new Intent(getBaseContext(), MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);


                mBuilder.setContentIntent(contentIntent);
                mBuilder.setAutoCancel(true);


                // Gets an instance of the NotificationManager service
                NotificationManager mNotifyMgr =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                // Builds the notification and issues it.
                mNotifyMgr.notify(mNotificationId, mBuilder.build());
            }
        }
        //

        }
//



        ///////////////////


    public MyService() {
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        googleApiClient.connect();
    }

    public void onDestroy() {
        Toast.makeText(this, "Service Stoped", Toast.LENGTH_LONG).show();
        googleApiClient.disconnect();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API).build();

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        Toast.makeText(getApplicationContext(), "Services Start", Toast.LENGTH_SHORT).show();

        Thread thread = new Thread(new TheThread(startId));
        thread.start();


        return START_STICKY;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        startLocationService();

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    public void startLocationService() {
        LocationRequest req = LocationRequest.create().setPriority(LocationRequest.PRIORITY_LOW_POWER);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
//                ActivityCompat#requestPermissions
//             here to request the missing permissions, and then overriding
//               public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                                                      int[] grantResults)
//             to handle the case where the user grants the permission. See the documentation
//             for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, req, this);
    }


    //////////////////////////////////////////////
    final class TheThread implements Runnable {
        int serviceId;

        TheThread(int serviceId) {
            this.serviceId = serviceId;
        }

        @Override
        public void run() {
            synchronized (this) {
                try {
                    if (!googleApiClient.isConnected())
                        googleApiClient.connect();
                    wait(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stopSelf(this.serviceId);
            }
        }
    }

}
