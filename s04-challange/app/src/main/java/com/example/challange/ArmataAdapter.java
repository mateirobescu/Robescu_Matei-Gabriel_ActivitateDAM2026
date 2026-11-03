package com.example.challange;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ArmataAdapter extends BaseAdapter {
    private List<Armata> armatas = null;
    private Context ctx;
    private int resourceLayout;

    public ArmataAdapter(Context ctx, int resourceLayout, List<Armata> armatas) {
        this.armatas = armatas;
        this.ctx = ctx;
        this.resourceLayout = resourceLayout;
    }

    @Override
    public int getCount() {
        return armatas.size();
    }

    @Override
    public Object getItem(int position) {
        return armatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this.ctx);
        View v = inflater.inflate(resourceLayout, parent, false);

        TextView tara_tv = v.findViewById(R.id.tara_tv);
        TextView soldati_tv = v.findViewById(R.id.soldati_tv);
        TextView tancuri_tv = v.findViewById(R.id.tancuri_tv);
        TextView razboi_tv = v.findViewById(R.id.razboi_tv);

        Armata armata = (Armata) this.getItem(position);
        tara_tv.setText(armata.getTara());
        soldati_tv.setText(String.valueOf(armata.getNrSoldati()));
        tancuri_tv.setText(String.valueOf(armata.getNrTancuri()));
        razboi_tv.setText(String.valueOf(armata.isEsteInRazboi()));

        return v;
    }
}
