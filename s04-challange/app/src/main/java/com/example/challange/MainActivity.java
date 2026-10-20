package com.example.challange;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void addArmata(View view) {
        Intent it = new Intent(this, AddActivity.class);

        startActivityForResult(it, 45);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 45) {
            if(resultCode == RESULT_OK) {
                Armata armata = (Armata) data.getExtras().getSerializable("armata");
                Toast.makeText(this, armata.toString(), Toast.LENGTH_LONG).show();;

                TextView armata_tv = findViewById(R.id.armata_tv);
                String existing_text = armata_tv.getText().toString();
                String target_text = existing_text + (!existing_text.isEmpty() ? "\n" : "") + armata.toString();
                armata_tv.setText(target_text);

            }
        }
    }
}