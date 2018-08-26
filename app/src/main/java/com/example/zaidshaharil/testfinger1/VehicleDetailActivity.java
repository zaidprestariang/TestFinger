package com.example.zaidshaharil.testfinger1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class VehicleDetailActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        final Vehicle vehicle = (Vehicle) intent.getSerializableExtra("vehicle");

        TextView text_platNo = findViewById(R.id.lbl_vhc_platNo);
        text_platNo.setText(vehicle.getPlateNo());
        TextView text_gpsId = findViewById(R.id.lbl_vhc_gpsId);
        text_gpsId.setText(vehicle.getDeviceid());
        TextView text_status = findViewById(R.id.lbl_vhc_status);
        text_status.setText(vehicle.getStatus());

        Button btn_approve = findViewById(R.id.btn_vhc_approve);
        btn_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(VehicleDetailActivity.this, FingerActivity.class);
                myIntent.putExtra("vehicle", vehicle);
                myIntent.putExtra("type", "vehicle");
                myIntent.putExtra("action", "approved");
                startActivity(myIntent);
            }
        });

        Button btn_reject = findViewById(R.id.btn_vhc_reject);
        btn_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(VehicleDetailActivity.this, FingerActivity.class);
                myIntent.putExtra("vehicle", vehicle);
                myIntent.putExtra("type", "vehicle");
                myIntent.putExtra("action", "rejected");
                startActivity(myIntent);
            }
        });

        Button btn_back = findViewById(R.id.btn_vhc_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (vehicle.getStatus().equals("NEW")) {
            btn_approve.setVisibility(View.VISIBLE);
            btn_reject.setVisibility(View.VISIBLE);
        } else {
            btn_approve.setVisibility(View.INVISIBLE);
            btn_reject.setVisibility(View.INVISIBLE);
        }
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
            Intent myIntent = new Intent(VehicleDetailActivity.this, MainActivity.class);
            VehicleDetailActivity.this.startActivity(myIntent);
        } else if (id == R.id.nav_detainee) {
            Intent myIntent = new Intent(VehicleDetailActivity.this, DetaineeActivity.class);
            VehicleDetailActivity.this.startActivity(myIntent);
        } else if (id == R.id.nav_logout) {
            Intent myIntent = new Intent(VehicleDetailActivity.this, LoginActivity.class);
            VehicleDetailActivity.this.startActivity(myIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
