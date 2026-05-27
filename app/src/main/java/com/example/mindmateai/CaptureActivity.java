package com.example.mindmateai;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CaptureActivity extends AppCompatActivity {

    EditText etNotes;
    Button btnSaveNotes, btnLoadNotes, btnClearNotes;
    TextView tvStatus;
    LocalStorageHelper storageHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);

        etNotes = findViewById(R.id.etNotes);
        btnSaveNotes = findViewById(R.id.btnSaveNotes);
        btnLoadNotes = findViewById(R.id.btnLoadNotes);
        btnClearNotes = findViewById(R.id.btnClearNotes);
        tvStatus = findViewById(R.id.tvStatus);

        storageHelper = new LocalStorageHelper(this);

        btnSaveNotes.setOnClickListener(v -> {
            String notes = etNotes.getText().toString().trim();

            if (notes.isEmpty()) {
                tvStatus.setText("Please enter notes before saving.");
            } else {
                storageHelper.saveNotes(notes);
                tvStatus.setText("Notes saved locally. These notes can be used in the Assist and Plan workflow.");
            }
        });

        btnLoadNotes.setOnClickListener(v -> {
            etNotes.setText(storageHelper.getNotes());
            tvStatus.setText("Saved notes loaded from local storage.");
        });

        btnClearNotes.setOnClickListener(v -> {
            etNotes.setText("");
            tvStatus.setText("Editor cleared.");
        });
    }
}