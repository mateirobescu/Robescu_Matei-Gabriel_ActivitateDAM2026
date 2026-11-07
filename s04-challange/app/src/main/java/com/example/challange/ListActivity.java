package com.example.challange;

import android.app.ComponentCaller;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    private List<Armata> armate;
    private ArmataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent it = getIntent();
        armate = (List<Armata>) it.getExtras().getSerializable("armate");

//        Toast.makeText(getApplicationContext(), armate.toString(), Toast.LENGTH_LONG).show();
        ListView listView = findViewById(R.id.list_view);

        adapter = new ArmataAdapter(
                getApplicationContext(),
                R.layout.list_item,
                armate
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
//            Toast.makeText(this, armate.get(position).toString(), Toast.LENGTH_SHORT).show();
            Intent itClick = new Intent(getApplicationContext(), AddActivity.class);
            itClick.putExtra("armata", armate.get(position));
            itClick.putExtra("position", position);

            startActivityForResult(itClick, 65);
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            armate.remove(position);
            adapter.notifyDataSetChanged();
            return true;
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data, @NonNull ComponentCaller caller) {
        super.onActivityResult(requestCode, resultCode, data, caller);

        if (requestCode == 65) {
            if (resultCode == RESULT_OK) {
                Armata newArmata = (Armata) data.getExtras().getParcelable("armata");
                int position = data.getExtras().getInt("position");

                this.armate.set(position, newArmata);
                Log.e("test", newArmata.toString());
                this.adapter.notifyDataSetChanged();
            }
        }
    }
}