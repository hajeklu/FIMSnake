package cz.uhk.fimsnake.dbs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.uhk.fimsnake.model.user.Players;
import cz.uhk.fimsnake.model.user.User;

public class FireBase implements IDAO {

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private Context context;

    public FireBase(Context context) {
        this.context = context;
    }

    @Override
    public boolean addScorePlayer(int data, Players player) {
        Map<String, Object> put_data = new HashMap<>();
        put_data.put("date", Calendar.getInstance().getTime());
        put_data.put("value", data);
        put_data.put("user_id", User.getUser().getMacAddress());
        firestore.collection("score").add(data);
        return true;
    }

    @Override
    public List<Integer> getData(Players player) {
        return null;
    }

    @Override
    public void setUser(String mac) {
        firestore.collection("users").whereEqualTo("mac_address", mac).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<DocumentSnapshot> result = task.getResult().getDocuments();
                if (result.size() > 0) {
                    User u = new User();
                    u.setAlias(result.get(0).get("alias").toString());
                    u.setMacAddress(result.get(0).get("mac_address").toString());
                    System.out.println(u);
                    User.setUser(u, context);
                }
            }
        });
    }

    @Override
    public void addUser(User user) {
        Map<String, Object> data = new HashMap<>();
        data.put("alias", user.getAlias());
        data.put("mac_address", user.getMacAddress());
        firestore.collection("users").add(data);
    }
}
