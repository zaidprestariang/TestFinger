package com.example.zaidshaharil.testfinger1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class DetaineeDetailActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detainee_detail);
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
        final Detainee detainee = (Detainee) intent.getSerializableExtra("detainee");

        TextView text_name = findViewById(R.id.lbl_dtn_name);
        text_name.setText(detainee.getName());
        TextView text_deviceId = findViewById((R.id.lbl_dtn_deviceId));
        text_deviceId.setText(detainee.getDeviceId());
        TextView text_status = findViewById(R.id.lbl_dtn_status);
        text_status.setText(detainee.getStatus());

        Button btn_approve = findViewById(R.id.btn_dtn_approve);
        btn_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(DetaineeDetailActivity.this, FingerActivity.class);
                myIntent.putExtra("detainee", detainee);
                myIntent.putExtra("type", "detainee");
                myIntent.putExtra("action", "approved");
                startActivity(myIntent);
            }
        });

        Button btn_reject = findViewById(R.id.btn_dtn_reject);
        btn_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(DetaineeDetailActivity.this, FingerActivity.class);
                myIntent.putExtra("detainee", detainee);
                myIntent.putExtra("type", "detainee");
                myIntent.putExtra("action", "rejected");
                startActivity(myIntent);
            }
        });

        Button btn_back = findViewById(R.id.btn_dtn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (detainee.getStatus().equals("NEW")) {
            btn_approve.setVisibility(View.VISIBLE);
            btn_reject.setVisibility(View.VISIBLE);
        } else {
            btn_approve.setVisibility(View.INVISIBLE);
            btn_reject.setVisibility(View.INVISIBLE);
        }

        String yourBase64String = "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";
        byte[] decodedString = Base64.decode(yourBase64String, Base64.DEFAULT);
        //SimpleDraweeView draweeView = findViewById(R.id.img_dtn_photo);
        ImageView img_photo = findViewById(R.id.img_dtn_photo);
        Bitmap bmp = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        //draweeView.setImageURI("");
        img_photo.setImageBitmap(bmp);
        //saveImage(getApplicationContext(), yourBase64String, "5b827868a6875e283acfef7f");
        //setProfileImage("5b827868a6875e283acfef7f");

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
            Intent myIntent = new Intent(DetaineeDetailActivity.this, MainActivity.class);
            DetaineeDetailActivity.this.startActivity(myIntent);
        } else if (id == R.id.nav_detainee) {
            Intent myIntent = new Intent(DetaineeDetailActivity.this, DetaineeActivity.class);
            DetaineeDetailActivity.this.startActivity(myIntent);
        } else if (id == R.id.nav_logout) {
            Intent myIntent = new Intent(DetaineeDetailActivity.this, LoginActivity.class);
            DetaineeDetailActivity.this.startActivity(myIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected File getAppFolder(Context context){
        File folder = new File(context.getFilesDir(),"photo");
        if(!folder.exists()){
            folder.mkdir();
        }

        return folder;
    }

    protected void saveImage(Context context, String base64ImageString, String filename){
        File folder = getAppFolder(getApplicationContext());
        FileOutputStream fos = null;

        try {
            if (base64ImageString != null && base64ImageString.trim() != "") {
                File dataFile = new File(folder, filename);
                fos = new FileOutputStream(dataFile);
//                FileWriter writer = new FileWriter(dataFile);
//                fos = context.openFileOutput("imageName.png", Context.MODE_PRIVATE);
                byte[] image = android.util.Base64.decode(base64ImageString, android.util.Base64.DEFAULT);
                fos.write(image);
                fos.flush();
                fos.close();
            }

        } catch (Exception e) {
            //alert.showDialog(this, "Caching Failed: " + e.getStackTrace());
        } finally {
            if (fos != null) {
                fos = null;
            }
        }
    }

    private void setProfileImage(String imagename) {
        File folder = getAppFolder(getApplicationContext());
        String filename = imagename + ".png";
        File dataFile = new File(folder, filename);

        Uri uri = Uri.fromFile(dataFile);
        SimpleDraweeView draweeView = findViewById(R.id.img_dtn_photo);
        draweeView.setImageURI(uri);
    }

}
