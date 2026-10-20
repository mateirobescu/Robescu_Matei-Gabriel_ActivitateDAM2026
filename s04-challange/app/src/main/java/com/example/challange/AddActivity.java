package com.example.challange;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_add);
        setContentView(R.layout.activity_add_styled);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void addArmy(View view) {
        EditText soldati_et = findViewById(R.id.nr_soldati_et);
        EditText tara_et = findViewById(R.id.tara_et);
        CheckBox este_in_razboi_et = findViewById(R.id.in_razboi_cb);
        EditText tancuri_et = findViewById(R.id.nr_tancuri_et);
        int nrSoldati = 0;
        String tara = tara_et.getText().toString();
        boolean eInRazboi = este_in_razboi_et.isChecked();
        int nrTancuri = 0;
        try {
            nrSoldati = Integer.parseInt(soldati_et.getText().toString());
            nrTancuri = Integer.parseInt(tancuri_et.getText().toString());
        } catch (Exception e) {}

        Armata armata = new Armata(nrSoldati, tara, eInRazboi, nrTancuri);

        Intent it = new Intent();
        it.putExtra("armata", armata);

        setResult(RESULT_OK, it);
        finish();
    }
}