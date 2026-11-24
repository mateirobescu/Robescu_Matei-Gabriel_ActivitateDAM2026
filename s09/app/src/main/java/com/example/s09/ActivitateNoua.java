package com.example.s09;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ActivitateNoua extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_activitate_noua);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String apiKey = "KEY";

        Button cautaBtn = findViewById(R.id.cauta_btn);
        EditText orasEt = findViewById(R.id.oras_et);
        Spinner zileSpinner = findViewById(R.id.unu_cinci_sp);
        TextView afisaj = findViewById(R.id.afisaj);

        cautaBtn.setOnClickListener(view -> {
            Executor executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler();
            executor.execute(() -> {
                String link = "https://dataservice.accuweather.com/locations/v1/cities/search?q=" + orasEt.getText().toString();
                try {
                    URL url = new URL(link);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setRequestProperty("Authorization", "Bearer " + apiKey);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    String body = sb.toString();

                    JSONArray vector = new JSONArray(body);
                    if (vector.length() == 0) {
                        handler.post(() -> {
                            Toast.makeText(getApplicationContext(), "Nu exista orasul!", Toast.LENGTH_SHORT).show();
                        });
                        return;
                    }

                    JSONObject object = vector.getJSONObject(0);
                    String cheieOras = object.getString("Key");

                    link = "https://dataservice.accuweather.com/forecasts/v1/daily/" + zileSpinner.getSelectedItem().toString()
                            + "day/" + cheieOras + "?metric=true";

                    url = new URL(link);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setRequestProperty("Authorization", "Bearer " + apiKey);
                    reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    sb = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    body = sb.toString();

                    StringBuilder afis = new StringBuilder();
                    JSONObject objectMain = new JSONObject(body);
                    JSONArray days = objectMain.getJSONArray("DailyForecasts");

                    for (int i = 0; i < days.length(); i++) {
                        JSONObject day = days.getJSONObject(i);
                        String date = day.getString("Date");
                        JSONObject temp = day.getJSONObject("Temperature");
                        JSONObject min = temp.getJSONObject("Minimum");
                        int minVal = min.getInt("Value");
                        String minUnit = min.getString("Unit");

                        JSONObject max = temp.getJSONObject("Maximum");
                        int maxVal = max.getInt("Value");
                        String maxUnit = max.getString("Unit");

                        afis.append(date).append("\n").
                                append("Min: ").append(minVal).append(minUnit).append("\n").
                                append("Max: ").append(maxVal).append(maxUnit).append("\n\n");
                    }

                    handler.post(() -> {
                        afisaj.setText(afis.toString());
                    });

                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            });
        });

    }
}