package com.example.mindmateai;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AssistActivity extends AppCompatActivity {

    EditText etPrompt;
    Button btnUseSavedNotes, btnExplain, btnSummarise, btnRewrite;
    TextView tvAIResponse;
    android.widget.ProgressBar pbAssist;

    MindMateAIEngine aiEngine;
    LocalStorageHelper storageHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assist);

        etPrompt = findViewById(R.id.etPrompt);
        btnUseSavedNotes = findViewById(R.id.btnUseSavedNotes);
        btnExplain = findViewById(R.id.btnExplain);
        btnSummarise = findViewById(R.id.btnSummarise);
        btnRewrite = findViewById(R.id.btnRewrite);
        tvAIResponse = findViewById(R.id.tvAIResponse);
        pbAssist = findViewById(R.id.pbAssist);

        aiEngine = new MindMateAIEngine();
        storageHelper = new LocalStorageHelper(this);

        btnUseSavedNotes.setOnClickListener(v ->
                etPrompt.setText(storageHelper.getNotes()));

        btnExplain.setOnClickListener(v -> runAI("EXPLAIN"));
        btnSummarise.setOnClickListener(v -> runAI("SUMMARISE"));
        btnRewrite.setOnClickListener(v -> runAI("REWRITE"));
    }

    private void runAI(String mode) {
        String input = etPrompt.getText().toString().trim();

        pbAssist.setVisibility(android.view.View.VISIBLE);
        tvAIResponse.setText("MindMate AI is thinking...");

        aiEngine.processPromptAsync(input, mode, new MindMateAIEngine.AIResponseCallback() {
            @Override
            public void onResponse(String response) {
                tvAIResponse.setText(response);
                pbAssist.setVisibility(android.view.View.GONE);

                storageHelper.saveHistory(
                        "Mode: " + mode + "\n\nInput:\n" + input + "\n\nOutput:\n" + response
                );
            }

            @Override
            public void onError(String error) {
                tvAIResponse.setText("Error: " + error);
                pbAssist.setVisibility(android.view.View.GONE);
            }
        });
    }
}