package cz.uhk.fimsnake.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import cz.uhk.fimsnake.R;
import cz.uhk.fimsnake.dao.CacheFactory;
import cz.uhk.fimsnake.dao.DAOFactory;
import cz.uhk.fimsnake.dao.IDAO;
import cz.uhk.fimsnake.model.user.NetworkService;
import cz.uhk.fimsnake.model.user.User;

public class PopupActivity extends AppCompatActivity {

    private IDAO idao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));

        ImageButton close = findViewById(R.id.popup_close);
        ImageButton play = findViewById(R.id.popup_play);
        final EditText alias = findViewById(R.id.popup_alias);
        idao = DAOFactory.getDAO(getApplicationContext());

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = CacheFactory.getInstance().getUser();
                user.setAlias(alias.getText().toString());
                user.setMacAddress(NetworkService.getInstance().getMacAddress(getApplicationContext()));
                idao.addUser(user);
                finish();
            }
        });


    }
}
