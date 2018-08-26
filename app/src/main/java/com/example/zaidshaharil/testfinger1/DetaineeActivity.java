package com.example.zaidshaharil.testfinger1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DetaineeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView ivDetainee;
    private DetaineeListAdapter detaineeListAdapter;
    private List<Detainee> mDetaineeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detainee);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ivDetainee = findViewById(R.id.listview_detainee);
        mDetaineeList = new ArrayList<>();

        mDetaineeList.add(new Detainee(1, "Samad bin Ishak", "FNE32223", "Approved"));
        mDetaineeList.add(new Detainee(2, "Tarik bin Razali", "VBR83729", "Pending"));
        mDetaineeList.add(new Detainee(3, "Muhammad Nabil bin Sulaiman", "NIR43983", "Rejected"));
        mDetaineeList.add(new Detainee(4, "Ahmad Tarmizi bin Yusuf", "KFR43822", "Approved"));
        mDetaineeList.add(new Detainee(5, "Norhaliza binti Idris", "GWJ23254", "Pending"));
        mDetaineeList.add(new Detainee(6, "Yap Wai Loon", "FHR214343", "Pending"));
        mDetaineeList.add(new Detainee(7, "Suriati binti Banos", "PUN22394", "Pending"));
        mDetaineeList.add(new Detainee(8, "Muhammad Amin bin Muhammad Razak", "QWW32234", "Pending"));

        detaineeListAdapter = new DetaineeListAdapter(getApplicationContext(), mDetaineeList);
        ivDetainee.setAdapter(detaineeListAdapter);

        ivDetainee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent myIntent = new Intent(DetaineeActivity.this, DetaineeDetailActivity.class);
                myIntent.putExtra("detainee", mDetaineeList.get(i));
                DetaineeActivity.this.startActivity(myIntent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
            Intent myIntent = new Intent(DetaineeActivity.this, MainActivity.class);
            DetaineeActivity.this.startActivity(myIntent);
        } else if (id == R.id.nav_detainee) {
            Intent myIntent = new Intent(DetaineeActivity.this, DetaineeActivity.class);
            DetaineeActivity.this.startActivity(myIntent);
        } else if (id == R.id.nav_logout) {
            Intent myIntent = new Intent(DetaineeActivity.this, LoginActivity.class);
            DetaineeActivity.this.startActivity(myIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
