package com.coinbkt.ngangklung;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.btnAbout)
    ImageButton btnAbout;
    @BindView(R.id.btnMainNada)
    ImageButton btnMainNada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btnAbout.setOnClickListener(this);
        btnMainNada.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        ImageButton ib = (ImageButton) v;
        switch (ib.getId()){
            case R.id.btnAbout:
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                break;
            case R.id.btnMainNada:
                startActivity(new Intent(MainActivity.this, NadaActivity.class));
                break;

        }
    }
}
