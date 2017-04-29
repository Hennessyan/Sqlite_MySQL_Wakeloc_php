package com.llu17.youngq.sqlite_gps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;


public class MainActivity extends AppCompatActivity  implements SharedPreferences.OnSharedPreferenceChangeListener{


//    private final static String tag = "Gps_SQLite";
    private String sampling_rate;
    static TextView upload_state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        sampling_rate = sharedPreferences.getString(getResources().getString(R.string.sr_key_all),"1000");
        Log.e("-----ALL SR-----",""+sampling_rate);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        //////////
        /*===check sqlite data using "chrome://inspect"===*/
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        //////////
        upload_state = (TextView)findViewById(R.id.Upload_State);
        upload_state.setVisibility(android.view.View.GONE);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.INTERNET}, 10);
//            Toast.makeText(this, "Check_Permission", Toast.LENGTH_SHORT).show();
        }


        int v = 0;
        try {
            v = getPackageManager().getPackageInfo("com.google.android.gms", 0 ).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Log.e("version:-------",""+ v);

    }
    /*Sampling rate menu*/
    //Add the menu to the menu bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sampling_rate_menu, menu);
        return true;
    }
    //When the "Settings" menu item is pressed, open SettingsActivity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.sr_key_all))) {
            sampling_rate = sharedPreferences.getString(key, "1000");
            Log.e("-----ALL SR-----","changed: "+sampling_rate);
        }
    }
    public void startService(View view) {
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
        Log.e("unregister","success");

        Toast.makeText(this, "Starting the service", Toast.LENGTH_SHORT).show();
        startService(new Intent(getBaseContext(), CollectorService.class));
        startService(new Intent(getBaseContext(), Activity_Tracker.class));
    }

    // Method to stop the service
    public void stopService(View view) {
        Toast.makeText(this, "Stopping the service", Toast.LENGTH_SHORT).show();
        stopService(new Intent(getBaseContext(), CollectorService.class));
        stopService(new Intent(getBaseContext(), Activity_Tracker.class));
        stopService(new Intent(getBaseContext(), HandleActivity.class));

    }

    public void uploadService(View view){
        Toast.makeText(this, "Begin to upload data", Toast.LENGTH_SHORT).show();
        startService(new Intent(getBaseContext(), UploadService.class));
    }

    public void breakService(View view){
        Toast.makeText(this, "Break the connection with MySQL", Toast.LENGTH_SHORT).show();
        stopService(new Intent(getBaseContext(), UploadService.class));
    }
}
