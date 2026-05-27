package com.example.mindmateai;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnCapture, btnAssist, btnPlan, btnHistory, btnPrivacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCapture = findViewById(R.id.btnCapture);
        btnAssist = findViewById(R.id.btnAssist);
        btnPlan = findViewById(R.id.btnPlan);
        btnHistory = findViewById(R.id.btnHistory);
        btnPrivacy = findViewById(R.id.btnPrivacy);

        btnCapture.setOnClickListener(v ->
                startActivity(new Intent(this, CaptureActivity.class)));

        btnAssist.setOnClickListener(v ->
                startActivity(new Intent(this, AssistActivity.class)));

        btnPlan.setOnClickListener(v ->
                startActivity(new Intent(this, PlanActivity.class)));

        btnHistory.setOnClickListener(v ->
                startActivity(new Intent(this, HistoryActivity.class)));

        btnPrivacy.setOnClickListener(v ->
                startActivity(new Intent(this, PrivacyActivity.class)));
    }
}