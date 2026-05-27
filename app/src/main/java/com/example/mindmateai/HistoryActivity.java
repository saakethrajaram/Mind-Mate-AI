package com.example.mindmateai;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HistoryActivity extends AppCompatActivity {

    TextView tvHistory;
    Button btnClearHistory;
    LocalStorageHelper storageHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        tvHistory = findViewById(R.id.tvHistory);
        btnClearHistory = findViewById(R.id.btnClearHistory);

        storageHelper = new LocalStorageHelper(this);

        tvHistory.setText(storageHelper.getHistory());

        btnClearHistory.setOnClickListener(v -> {
            storageHelper.clearHistory();
            tvHistory.setText("History cleared.");
        });
    }
}