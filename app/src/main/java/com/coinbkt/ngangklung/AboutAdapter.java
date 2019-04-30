package com.coinbkt.ngangklung;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AboutAdapter extends RecyclerView.Adapter< AboutViewHolder > {

    private Context mContext;
    private List<About> aboutList;

    AboutAdapter(Context mContext, List<About> aboutList) {
        this.mContext = mContext;
        this.aboutList = aboutList;
    }

    @Override
    public AboutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_about_layout, parent, false);
        return new AboutViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final AboutViewHolder holder, int position) {
        holder.mTitle.setText(aboutList.get(position).getAboutListTitle());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AboutActivity.class);
                intent.putExtra("link", aboutList.get(position).aboutListLink);
                mContext.startActivity(intent);
            }
        });
        
    }

    @Override
    public int getItemCount() {
        return aboutList.size();
    }
}

class AboutViewHolder extends RecyclerView.ViewHolder {

    TextView mTitle;
    CardView mCardView;

    AboutViewHolder(View itemView) {
        super(itemView);
        
        mTitle = itemView.findViewById(R.id.judulAbout);
        mCardView = itemView.findViewById(R.id.cardView);

    }
}