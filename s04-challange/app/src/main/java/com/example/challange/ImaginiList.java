package com.example.challange;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class ImaginiList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_imagini_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List<ImagineArmate> imgs = List.of(
                new ImagineArmate(
                        "https://s.iw.ro/gateway/g/ZmlsZVNvdXJjZT1odHRwJTNBJTJGJTJG/c3RvcmFnZTA4dHJhbnNjb2Rlci5yY3Mt/cmRzLnJvJTJGc3RvcmFnZSUyRjIwMjMl/MkYwNiUyRjA3JTJGMTc0Mzk2NV8xNzQz/OTY1X21pbGl0YXJpLXJvbWFuaS1mYi1t/YXBuLmpwZyZ3PTc4MCZoPTQ0MCZoYXNo/PWIzYTIwNzRmM2YyZTk5YjEyZTAwNmRhZWVkNGNkOTMx.thumb.jpg",
                        "https://ro.wikipedia.org/wiki/Armata_României",
                        "Armata Romaniei"
                ),
                new ImagineArmate(
                        "https://www.caleaeuropeana.ro/wp-content/uploads/2020/06/sua-militari-atlantic-resolve.jpg",
                        "https://ro.wikipedia.org/wiki/Armata_Statelor_Unite_ale_Americii",
                        "Armata SUA"
                ),
                new ImagineArmate(
                        "https://hotnews.ro/wp-content/uploads/2026/06/profimedia-1109490596.jpg",
                        "https://ro.wikipedia.org/wiki/Forțele_armate_ale_Italiei",
                        "Armata Italia"
                ),
                new ImagineArmate(
                        "https://rumaniamilitary.wordpress.com/wp-content/uploads/2012/07/poza-18.jpg",
                        "https://ro.wikipedia.org/wiki/Forțele_armate_ale_Franței",
                        "Armata Frantei"
                ),
                new ImagineArmate(
                        "https://hotnews.ro/wp-content/uploads/2024/04/image-2022-08-31-25763704-41-inspectorul-fortelor-armate-germane-aflat-intr-vizita-oficiala-japonia.jpg",
                        "https://ro.wikipedia.org/wiki/Armata_Imperială_Japoneză",
                        "Armata Japoniei"
                )
        );
        ListView lv = findViewById(R.id.imaginiList);

        lv.setOnItemClickListener( (parent, view, position, id) -> {
            ImagineArmate img = (ImagineArmate) parent.getAdapter().getItem(position);
            Intent it = new Intent(getApplicationContext(), WebActivity.class);
            it.putExtra("link", img.getUrlDescriere());
            startActivity(it);
        });

        ImaginiArmataAdapter adapter = new ImaginiArmataAdapter(getApplicationContext(), R.layout.imagine_armta_item, imgs);
        lv.setAdapter(adapter);


    }
}