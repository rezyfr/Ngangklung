package com.coinbkt.ngangklung;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class ListAboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        RecyclerView mRecyclerView = findViewById(R.id.rv_about);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(ListAboutActivity.this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        List<About> aboutList = new ArrayList<>();
        aboutList.add(new About("Pengertian Alat Musik Angklung Asal Sunda","http://dunia-kesenian.blogspot.com/2014/11/pengertian-alat-musik-angklung-asal-sunda.html"));
        aboutList.add(new About("Sejarah Alat Musik Angklung","https://sejarahlengkap.com/indonesia/sejarah-alat-musik-angklung"));
        aboutList.add(new About("Angklung Wikipedia","https://id.m.wikipedia.org/wiki/Angklung"));
        aboutList.add(new About("Angklung, Alat Musik Indonesia yang Sudah Mendunia","https://student.cnnindonesia.com/edukasi/20180221104520-445-277661/angklung-alat-musik-indonesia-yang-sudah-mendunia/"));

        AboutAdapter myAdapter = new AboutAdapter(ListAboutActivity.this, aboutList);
        mRecyclerView.setAdapter(myAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
