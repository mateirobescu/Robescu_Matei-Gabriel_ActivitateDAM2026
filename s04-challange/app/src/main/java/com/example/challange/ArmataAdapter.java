package com.example.challange;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ArmataAdapter extends BaseAdapter {
    private final Context ctx;
    private final int resourceLayout;
    private final List<Armata> armate;

    public ArmataAdapter(Context ctx, int resourceLayout, List<Armata> armate) {
        this.ctx = ctx;
        this.resourceLayout = resourceLayout;
        this.armate = armate;
    }

    @Override
    public int getCount() {
        return armate.size();
    }

    @Override
    public Object getItem(int position) {
        return armate.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(ctx).inflate(resourceLayout, parent, false);

        TextView taraTv = v.findViewById(R.id.taraTv);
        TextView soldatiTv = v.findViewById(R.id.soldatiTv);
        TextView tancuriTv = v.findViewById(R.id.tancuriTv);
        TextView razboiTv = v.findViewById(R.id.razboiTv);

        Armata armata = (Armata) getItem(position);
        taraTv.setText(armata.getTara());
        soldatiTv.setText(String.valueOf(armata.getNrSoldati()));
        tancuriTv.setText(String.valueOf(armata.getNrTancuri()));
        razboiTv.setText(String.valueOf(armata.isEsteInRazboi()));

        return v;
    }
}
