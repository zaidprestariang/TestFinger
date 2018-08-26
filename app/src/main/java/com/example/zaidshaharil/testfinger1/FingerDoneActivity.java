package com.example.zaidshaharil.testfinger1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FingerDoneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_done);

        Intent intent = getIntent();
        String btn_action = intent.getExtras().getString("action");
        final String intent_type = intent.getExtras().getString("type");

        TextView text_result = findViewById(R.id.lbl_fgd_result);
        text_result.setText("The application has been successfully " + btn_action);

        Button btn_done = (Button)findViewById(R.id.btn_fgr_done);

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intent_type.equals("vehicle")) {
                    startActivity(new Intent(FingerDoneActivity.this, MainActivity.class));
                } else if (intent_type.equals("detainee")) {
                    startActivity(new Intent(FingerDoneActivity.this, DetaineeActivity.class));
                }
            }
        });

    }

}
