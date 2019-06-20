package cz.uhk.fimsnake.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import cz.uhk.fimsnake.R;
import cz.uhk.fimsnake.dao.IDAO;

public class ScoreActivity extends AppCompatActivity {

    private IDAO databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

      //  databaseHelper = new DatabaseHelper(this);


        ListView p1List = findViewById(R.id.p1score);
        ListView p2List = findViewById(R.id.p2score);
/*
        List<Integer> p1Scores = databaseHelper.getData(Players.PLAYER1);
        List<Integer> p2Scores = databaseHelper.getData(Players.PLAYER2);


        View headerView = getLayoutInflater().inflate(R.layout.listview_header, null);
        p1List.addHeaderView(headerView);


        View headerViewP2 = getLayoutInflater().inflate(R.layout.listview_header_player2, null);
        p2List.addHeaderView(headerViewP2);

        ListAdapter adapterP1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, p1Scores);
        p1List.setAdapter(adapterP1);

        ListAdapter adapterP2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, p2Scores);
        p2List.setAdapter(adapterP2);*/
    }
}
