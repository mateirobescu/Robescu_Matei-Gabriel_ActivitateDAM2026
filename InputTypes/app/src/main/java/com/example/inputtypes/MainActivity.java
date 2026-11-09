package com.example.inputtypes;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button b = findViewById(R.id.button);
        b.setOnClickListener(view -> {
            TextView tw = findViewById(R.id.textView);
            EditText et = findViewById(R.id.editText);
            RadioGroup rg = findViewById(R.id.radioGroup);
            Spinner sp = findViewById(R.id.spinner);
            RatingBar rb = findViewById(R.id.ratingBar);
            Switch sw = findViewById(R.id.switchy);

            String text = "";
            text += et.getText().toString() + "\n";
            int id = rg.getCheckedRadioButtonId();
            if(id != -1) {
                RadioButton rad = findViewById(id);
                text += rad.getText().toString() + "\n";
            }
            text += sp.getSelectedItem().toString() + "\n";
            text += rb.getRating() + "\n";
            text += String.valueOf(sw.isChecked());

            tw.setText(text);
        });
    }
}