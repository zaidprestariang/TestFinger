package com.example.zaidshaharil.testfinger1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

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
        vehicle = new Vehicle(vehicles.getId(), vehicles.getPlateNo(), vehicles.getDeviceid(), "", vehicles.getStatus());
        btn_action = btn_actions;
        intent_type = "vehicle";
        Toast.makeText(context, "Pass id = " + vehicle.getId() + ", plat no = " + vehicle.getPlateNo() + ", action = " + btn_action + ", actions = " + btn_actions, Toast.LENGTH_SHORT).show();

        CancellationSignal cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED)
            return;
        fingerprintManager.authenticate(cryptoObject, cancellationSignal,0,this,null);
    }

    public void startAuthenticationDetainee(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject, Detainee detainees, String btn_actions) {
        detainee = new Detainee(detainees.getId(), detainees.getName(), detainees.getDeviceId(), detainees.getStatus());
        btn_action = btn_actions;
        intent_type = "detainee";
        Toast.makeText(context, "Pass id = " + detainee.getId() + ", name = " + detainee.getName() + ", action = " + btn_action + ", actions = " + btn_actions, Toast.LENGTH_SHORT).show();

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
        if (intent_type.equals("vehicle")) {
            Toast.makeText(context, "Pass id = " + vehicle.getId() + ", plat no = " + vehicle.getPlateNo() + ", action = " + btn_action, Toast.LENGTH_SHORT).show();
        } else if (intent_type.equals("detainee")) {
            Toast.makeText(context, "Pass id = " + detainee.getId() + ", name = " + detainee.getName() + ", action = " + btn_action, Toast.LENGTH_SHORT).show();
        }
        Intent myIntent = new Intent(context, FingerDoneActivity.class);
        myIntent.putExtra("action", btn_action);
        myIntent.putExtra("type", intent_type);
        context.startActivity(myIntent);
    }
}
