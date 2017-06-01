package com.st.first.customadapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.st.first.R;

import java.util.ArrayList;


public class CompanyAdapter extends ArrayAdapter<Company> {
    private Context ctx;
    private ArrayList<Company> companies;


    public CompanyAdapter(Context ctx, ArrayList<Company> companies) {
        super(ctx, R.layout.company_layout, companies);
        this.ctx = ctx;
        this.companies = companies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("First", convertView == null ? "NULL" : "Reusing view");

        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(ctx);
            convertView = inflater.inflate(R.layout.company_layout, parent, false);
        }

        Button buttonCall = (Button) convertView
                .findViewById(R.id.buttonCall);
        Button buttonVisit = (Button) convertView
                .findViewById(R.id.buttonVisit);

        final Company comp = companies.get(position);

        TextView textCompany = (TextView) convertView
                .findViewById(R.id.textCompany);
        textCompany.setText(comp.getName());

        ImageView imgLogo = (ImageView) convertView.findViewById(R.id.imgLogo);


        Resources resources = ctx.getResources();
        final int resourceId = resources.getIdentifier(comp.getLogo(), "drawable",
                ctx.getPackageName());
        imgLogo.setImageResource(resourceId);


        buttonCall.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL,
                        Uri.parse("tel:" + comp.getPhone()));
                try {
                    ctx.startActivity(intent);
                } catch (Exception ex) {
                    Log.e("DemoApp", "Unable to call " + comp.getPhone());
                }
            }

        });


        buttonVisit.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(comp.getUrl()));
                ctx.startActivity(i);
            }

        });

        return convertView;
    }

}
