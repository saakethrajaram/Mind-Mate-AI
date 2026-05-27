package com.example.mindmateai;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PlanActivity extends AppCompatActivity {

    EditText etGoal;
    Button btnUseSavedNotesForPlan, btnCreatePlan;
    TextView tvPlan;
    android.widget.ProgressBar pbPlan;

    MindMateAIEngine aiEngine;
    LocalStorageHelper storageHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        etGoal = findViewById(R.id.etGoal);
        btnUseSavedNotesForPlan = findViewById(R.id.btnUseSavedNotesForPlan);
        btnCreatePlan = findViewById(R.id.btnCreatePlan);
        tvPlan = findViewById(R.id.tvPlan);
        pbPlan = findViewById(R.id.pbPlan);

        aiEngine = new MindMateAIEngine();
        storageHelper = new LocalStorageHelper(this);

        btnUseSavedNotesForPlan.setOnClickListener(v ->
                etGoal.setText(storageHelper.getNotes()));

        btnCreatePlan.setOnClickListener(v -> {
            String input = etGoal.getText().toString().trim();

            pbPlan.setVisibility(android.view.View.VISIBLE);
            tvPlan.setText("MindMate AI is creating your plan...");

            aiEngine.processPromptAsync(input, "PLAN", new MindMateAIEngine.AIResponseCallback() {
                @Override
                public void onResponse(String response) {
                    tvPlan.setText(response);
                    pbPlan.setVisibility(android.view.View.GONE);

                    storageHelper.saveHistory(
                            "Mode: PLAN\n\nInput:\n" + input + "\n\nOutput:\n" + response
                    );
                }

                @Override
                public void onError(String error) {
                    tvPlan.setText("Error: " + error);
                    pbPlan.setVisibility(android.view.View.GONE);
                }
            });
        });
    }
}