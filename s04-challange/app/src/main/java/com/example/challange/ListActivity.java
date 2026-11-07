package com.example.challange;

import android.app.ComponentCaller;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private static final int REQUEST_EDIT = 65;

    private ArrayList<Armata> armate;
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

        armate = getIntent().getParcelableArrayListExtra("armate");

        ListView listView = findViewById(R.id.listView);
        adapter = new ArmataAdapter(getApplicationContext(), R.layout.list_item, armate);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent it = new Intent(getApplicationContext(), AddActivity.class);
            it.putExtra("armata", armate.get(position));
            it.putExtra("position", position);
            startActivityForResult(it, REQUEST_EDIT);
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

        if (requestCode == REQUEST_EDIT && resultCode == RESULT_OK) {
            Armata newArmata = data.getExtras().getParcelable("armata");
            int position = data.getExtras().getInt("position");

            armate.set(position, newArmata);
            adapter.notifyDataSetChanged();
        }
    }
}
