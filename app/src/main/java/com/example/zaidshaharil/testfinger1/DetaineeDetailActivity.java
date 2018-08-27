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

//import com.facebook.drawee.view.SimpleDraweeView;

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
        text_deviceId.setText(detainee.getDeviceid());
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

        ImageView img_photo = findViewById(R.id.img_dtn_photo);
        img_photo.setImageBitmap(null);
        img_photo.destroyDrawingCache();

        if (detainee.getPicture() != null) {
            byte[] decodedString = Base64.decode(detainee.getPicture(), Base64.DEFAULT);
            Bitmap bmp = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            img_photo.setImageBitmap(bmp);
        }
        //SimpleDraweeView draweeView = findViewById(R.id.img_dtn_photo);
        //draweeView.setImageURI("");
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
        //SimpleDraweeView draweeView = findViewById(R.id.img_dtn_photo);
        //draweeView.setImageURI(uri);
    }

}
