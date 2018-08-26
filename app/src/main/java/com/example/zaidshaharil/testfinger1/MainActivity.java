package com.example.zaidshaharil.testfinger1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView ivVehicle;
    private VehicleListAdapter vehicleListAdapter;
    private List<Vehicle> mVehicleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        OkHttpClient client = new OkHttpClient();
        String url = "http://219.92.29.26:5000/vehicles";

        RequestBody requestBody1 = new FormBody.Builder()
                .add("id", "5b82527fa6875e242d0513a0")
                .add("description", "Test3")
                .add("deviceid", "GST328233")
                .add("plateNo", "FFV7890")
                .add("status", "NEW")
                .build();
        Request request1 = new Request.Builder().url(url).post(requestBody1).build();
        client.newCall(request1).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });


        Request request = new Request.Builder().url(url).build();
                //.addHeader("Authorization", "sd").addHeader("refresh_token", "ds").url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ObjectMapper objectMapper = new ObjectMapper();
                            try {
                                mVehicleList = new ArrayList<>();
                                mVehicleList = Arrays.asList(objectMapper.readValue(myResponse, Vehicle[].class));

                                ivVehicle = findViewById(R.id.listview_vehicle);
                                vehicleListAdapter = new VehicleListAdapter(getApplicationContext(), mVehicleList);
                                ivVehicle.setAdapter(vehicleListAdapter);

                                ivVehicle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        //Toast.makeText(getApplicationContext(), "Clicked id = " + view.getTag() + ", i=" + i, Toast.LENGTH_SHORT).show();
                                        Intent myIntent = new Intent(MainActivity.this, VehicleDetailActivity.class);
                                        myIntent.putExtra("vehicle", mVehicleList.get(i));
                                        MainActivity.this.startActivity(myIntent);
                                    }
                                });
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Error getting data. Please contact adminstrator.", Toast.LENGTH_LONG).show();
                }
            }
        });


        /*HttpCall httpCall = new HttpCall();
        httpCall.setMethodtype(HttpCall.GET);
        httpCall.setUrl("http://192.168.1.8:8085/topics");
        HashMap<String,String> params = new HashMap<>();
        params.put("Authorization","James Bond");
        params.put("refresh_token","dsds");
        httpCall.setParams(params);
        new HttpRequest(){
            @Override
            public void onResponse(String response) {
                super.onResponse(response);
                TextView text_name = findViewById(R.id.testView);
                text_name.setText("dsbf jsb = "+response);
            }
        }.execute(httpCall);*/

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_vehicle) {
            Intent myIntent = new Intent(MainActivity.this, MainActivity.class);
            MainActivity.this.startActivity(myIntent);
        } else if (id == R.id.nav_detainee) {
            Intent myIntent = new Intent(MainActivity.this, DetaineeActivity.class);
            MainActivity.this.startActivity(myIntent);
        } else if (id == R.id.nav_logout) {
            Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
            MainActivity.this.startActivity(myIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
