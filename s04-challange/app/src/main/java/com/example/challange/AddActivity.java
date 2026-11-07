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

    private EditText nrSoldatiEt;
    private EditText taraEt;
    private CheckBox inRazboiCb;
    private EditText nrTancuriEt;

    private Armata armataToReturn;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
//        setContentView(R.layout.activity_add_styled);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nrSoldatiEt = findViewById(R.id.nrSoldatiEt);
        taraEt = findViewById(R.id.taraEt);
        inRazboiCb = findViewById(R.id.inRazboiCb);
        nrTancuriEt = findViewById(R.id.nrTancuriEt);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            armataToReturn = extras.getParcelable("armata");
            position = extras.getInt("position");

            if (armataToReturn != null) {
                nrSoldatiEt.setText(String.valueOf(armataToReturn.getNrSoldati()));
                taraEt.setText(armataToReturn.getTara());
                inRazboiCb.setChecked(armataToReturn.isEsteInRazboi());
                nrTancuriEt.setText(String.valueOf(armataToReturn.getNrTancuri()));
            }
        }
    }

    public void addArmy(View view) {
        String tara = taraEt.getText().toString();
        boolean eInRazboi = inRazboiCb.isChecked();
        int nrSoldati = parseIntOrZero(nrSoldatiEt.getText().toString());
        int nrTancuri = parseIntOrZero(nrTancuriEt.getText().toString());

        Intent it = new Intent();
        if (armataToReturn == null) {
            armataToReturn = new Armata(nrSoldati, tara, eInRazboi, nrTancuri);
        } else {
            armataToReturn.setNrSoldati(nrSoldati);
            armataToReturn.setTara(tara);
            armataToReturn.setEsteInRazboi(eInRazboi);
            armataToReturn.setNrTancuri(nrTancuri);
            it.putExtra("position", position);
        }
        it.putExtra("armata", armataToReturn);

        setResult(RESULT_OK, it);
        finish();
    }

    private int parseIntOrZero(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
