package com.example.challange;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ADD = 45;

    private final ArrayList<Armata> armate = new ArrayList<>();

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

        Button listaBtn = findViewById(R.id.listaBtn);
        listaBtn.setOnClickListener(view -> {
            Intent it = new Intent(getApplicationContext(), ListActivity.class);
            it.putParcelableArrayListExtra("armate", armate);
            startActivity(it);
        });

        armate.add(new Armata(30000, "Romania", false, 200));
    }

    public void addArmata(View view) {
        Intent it = new Intent(this, AddActivity.class);
        startActivityForResult(it, REQUEST_ADD);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ADD && resultCode == RESULT_OK) {
            Armata armata = data.getExtras().getParcelable("armata");
            Toast.makeText(this, armata.toString(), Toast.LENGTH_LONG).show();
            armate.add(armata);
        }
    }
}
