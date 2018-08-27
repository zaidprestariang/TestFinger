package com.example.zaidshaharil.testfinger1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FingerprintHandler extends FingerprintManager.AuthenticationCallback{

    private Context context;
    private Vehicle vehicle;
    private Detainee detainee;
    private String btn_action = "";
    private String intent_type = "";

    public FingerprintHandler(Context context) {
        this.context = context;
    }

    public void startAuthenticationVehicle(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject, Vehicle vehicles, String btn_actions) {
        vehicle = vehicles;
        btn_action = btn_actions;
        intent_type = "vehicle";
        //Toast.makeText(context, "Pass id = " + vehicle.getId() + ", plat no = " + vehicle.getPlateNo() + ", action = " + btn_action + ", actions = " + btn_actions, Toast.LENGTH_SHORT).show();

        CancellationSignal cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED)
            return;
        fingerprintManager.authenticate(cryptoObject, cancellationSignal,0,this,null);
    }

    public void startAuthenticationDetainee(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject, Detainee detainees, String btn_actions) {
        detainee = detainees;
        btn_action = btn_actions;
        intent_type = "detainee";
        //Toast.makeText(context, "Pass id = " + detainee.getId() + ", name = " + detainee.getName() + ", action = " + btn_action + ", actions = " + btn_actions, Toast.LENGTH_SHORT).show();

        CancellationSignal cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED)
            return;
        fingerprintManager.authenticate(cryptoObject, cancellationSignal,0,this,null);
    }

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        Toast.makeText(context, "Fingerprint Authentication Failed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        /*if (intent_type.equals("vehicle")) {
            Toast.makeText(context, "Pass id = " + vehicle.getId() + ", plat no = " + vehicle.getPlateNo() + ", action = " + btn_action, Toast.LENGTH_SHORT).show();
        } else if (intent_type.equals("detainee")) {
            Toast.makeText(context, "Pass id = " + detainee.getId() + ", name = " + detainee.getName() + ", action = " + btn_action, Toast.LENGTH_SHORT).show();
        }*/
        OkHttpClient client = new OkHttpClient();
        String url = "http://prestariang.akaunsaya.com:5000/"; // "http://219.92.29.26:5000/vehicles";

        if (intent_type.equals("vehicle")) {
            RequestBody requestBody = new FormBody.Builder()
                    .add("id", vehicle.getId())
                    .add("description", vehicle.getDescription())
                    .add("deviceid", vehicle.getDeviceid())
                    .add("plateNo", vehicle.getPlateNo())
                    .add("status", btn_action.toUpperCase())
                    .build();
            Request request = new Request.Builder().url(url+"vehicles").post(requestBody).build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Update vehicle Failed!", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Intent myIntent = new Intent(context, FingerDoneActivity.class);
                    myIntent.putExtra("action", btn_action);
                    myIntent.putExtra("type", intent_type);
                    context.startActivity(myIntent);
                }
            });
        } else if (intent_type.equals("detainee")) {
            RequestBody requestBody = new FormBody.Builder()
                    .add("id", detainee.getId())
                    .add("name", detainee.getName())
                    .add("latitude", detainee.getGeometry().get(0).getY().toString())
                    .add("longitude", detainee.getGeometry().get(0).getX().toString())
                    .add("deviceid", detainee.getDeviceid())
                    .add("picture", detainee.getPicture())
                    .add("status", btn_action.toUpperCase())
                    .build();
            Request request = new Request.Builder().url(url+"detainees").post(requestBody).build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Update detainee Failed!", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Intent myIntent = new Intent(context, FingerDoneActivity.class);
                    myIntent.putExtra("action", btn_action);
                    myIntent.putExtra("type", intent_type);
                    context.startActivity(myIntent);
                }
            });
        }

    }
}
