package com.coinbkt.ngangklung.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.coinbkt.ngangklung.R;
import com.coinbkt.ngangklung.model.Nada;

public class NadaAdapter extends BaseAdapter {
    private final Context mContext;
    private final Nada[] nadas;

    public NadaAdapter(Context c, Nada[] nadas){
        this.mContext = c;
        this.nadas = nadas;
    }

    @Override
    public int getCount() {
        return nadas.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Nada nada = nadas[position];
        ImageView imageView;

        if(convertView==null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(340, 200));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
        else{
            imageView = (ImageView) convertView;
        }

        switch (position){
            case 0:
                imageView.setImageResource(nada.getIsSelected() ? R.drawable.card_do_selected : R.drawable.card_do_unselected);
                break;
            case 1:
                imageView.setImageResource(nada.getIsSelected() ? R.drawable.card_re_selected : R.drawable.card_re_unselected);
                break;
            case 2:
                imageView.setImageResource(nada.getIsSelected() ? R.drawable.card_mi_selected : R.drawable.card_mi_unselected);
                break;
            case 3:
                imageView.setImageResource(nada.getIsSelected() ? R.drawable.card_fa_selected : R.drawable.card_fa_unselected);
                break;
            case 4:
                imageView.setImageResource(nada.getIsSelected() ? R.drawable.card_sol_selected : R.drawable.card_sol_unselected);
                break;
            case 5:
                imageView.setImageResource(nada.getIsSelected() ? R.drawable.card_la_selected : R.drawable.card_la_unselected);
                break;
            case 6:
                imageView.setImageResource(nada.getIsSelected() ? R.drawable.card_si_selected : R.drawable.card_si_unselected);
                break;
            case 7:
                imageView.setImageResource(nada.getIsSelected() ? R.drawable.card_doo_selected : R.drawable.card_doo_unselected);
                break;
        }
        return imageView;
    }
}
