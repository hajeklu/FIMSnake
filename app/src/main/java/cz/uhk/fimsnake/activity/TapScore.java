package cz.uhk.fimsnake.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import cz.uhk.fimsnake.R;
import cz.uhk.fimsnake.activity.ui.main.RecyclerViewAdapter;
import cz.uhk.fimsnake.activity.ui.main.SectionsPagerAdapter;
import cz.uhk.fimsnake.dbs.Cache;
import cz.uhk.fimsnake.dbs.MemoryCache;

public class TapScore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_score);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


    }
}