package cz.uhk.fimsnake.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import cz.uhk.fimsnake.R;
import cz.uhk.fimsnake.activity.services.PreferencesService;
import cz.uhk.fimsnake.dao.Cache;
import cz.uhk.fimsnake.dao.CacheFactory;
import cz.uhk.fimsnake.dao.DAOFactory;
import cz.uhk.fimsnake.dao.IDAO;
import cz.uhk.fimsnake.model.user.User;

/**
 * Created by Luboš Hájek in 2019
 */
public class PreferencesActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private Context context;
    public final static String PRE_TEXTURES = "TEXTURES";
    public final static String PRE_NOTIFICATION = "NOTIFICATION";
    public final static boolean TEXTURES_DEFAULT = true;
    public final static boolean NOTIFICATION_DEFAULT = true;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        context = getApplicationContext();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Cache cache = CacheFactory.getInstance();

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

        Switch textures = findViewById(R.id.pref_switch_textures);

        textures.setChecked(sharedPreferences.getBoolean(PRE_TEXTURES, TEXTURES_DEFAULT));

        PreferencesService.setTextures(sharedPreferences.getBoolean(PreferencesActivity.PRE_TEXTURES, PreferencesActivity.TEXTURES_DEFAULT));


        textures.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(PRE_TEXTURES, isChecked);
                PreferencesService.setTextures(isChecked);
                editor.commit();
                Toast.makeText(context, "Textures settings was updated.", Toast.LENGTH_SHORT).show();
            }
        });


        Switch notification = findViewById(R.id.pref_switch_notification);

        notification.setChecked(sharedPreferences.getBoolean(PRE_NOTIFICATION, NOTIFICATION_DEFAULT));
        notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(PRE_NOTIFICATION, isChecked);
                editor.commit();
                Toast.makeText(context, "Notification settings was updated.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
