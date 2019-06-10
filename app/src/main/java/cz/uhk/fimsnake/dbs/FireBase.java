package cz.uhk.fimsnake.dbs;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import cz.uhk.fimsnake.model.Players;

public class FireBase extends AppCompatActivity implements IDAO {

    

    @Override
    public boolean addScorePlayer(int data, Players player) {
        return false;
    }

    @Override
    public List<Integer> getData(Players player) {
        return null;
    }
}
