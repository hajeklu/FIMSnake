package cz.uhk.fimsnake.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import cz.uhk.fimsnake.R;
import cz.uhk.fimsnake.dao.Cache;
import cz.uhk.fimsnake.dao.CacheFactory;
import cz.uhk.fimsnake.dao.DAOFactory;
import cz.uhk.fimsnake.dao.IDAO;
import cz.uhk.fimsnake.model.user.User;

public class PreferencesActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private Cache cache;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        context = getApplicationContext();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        cache = CacheFactory.getInstance();

        User user = cache.getUser();
        final EditText editText = findViewById(R.id.pref_alias_editable);
        editText.setText(user.getAlias());


        TextView textView = findViewById(R.id.mac);
        textView.setText(user.getMacAddress());

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                IDAO idao = DAOFactory.getDAO(context);
                idao.setNewAliasToCurrentUser(v.getText().toString());
                return false;
            }
        });

    }
}
