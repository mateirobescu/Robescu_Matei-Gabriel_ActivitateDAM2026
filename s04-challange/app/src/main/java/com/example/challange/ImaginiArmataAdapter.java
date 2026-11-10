package com.example.challange;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ImaginiArmataAdapter extends BaseAdapter {

    private final Context ctx;
    private final int resourceLayout;

    private final List<ImagineArmate> items;

    public ImaginiArmataAdapter(Context ctx, int resourceLayout, List<ImagineArmate> items) {
        this.ctx = ctx;
        this.resourceLayout = resourceLayout;
        this.items = items;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(ctx).inflate(this.resourceLayout, parent, false);

        ImageView iv = v.findViewById(R.id.imageV);
        TextView tv = v.findViewById(R.id.textV);
        ImagineArmate img = (ImagineArmate) this.getItem(position);
        tv.setText(img.getDescriere());

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(() -> {
            HttpURLConnection conn = null;
            try {
                URL url = new URL(img.getUrlImagine());
                conn = (HttpURLConnection) url.openConnection();
                InputStream is = conn.getInputStream();
                Bitmap bm = BitmapFactory.decodeStream(is);

                if(bm != null) {
                    handler.post(() -> {
                        iv.setImageBitmap(bm);
                    });
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return v;
    }
}
