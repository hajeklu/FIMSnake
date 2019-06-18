package cz.uhk.fimsnake.dbs;

import android.content.Context;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

import cz.uhk.fimsnake.model.user.NetworkService;
import cz.uhk.fimsnake.model.user.Players;
import cz.uhk.fimsnake.model.user.Score;
import cz.uhk.fimsnake.model.user.User;

public class FireBase implements IDAO {

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private Context context;

    public FireBase(Context context) {
        this.context = context;
    }

    @Override
    public boolean addScoreToPlayer(int value) {
        final String mac = NetworkService.getInstance().getMacAddress(context);
        firestore.collection("snake_user").whereEqualTo("macAddress", mac).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                
            }
        });
        return false;
    }

    @Override
    public void getData(OnCompleteListener listener) {

    }

    @Override
    public void setUser(String mac) {

    }

    @Override
    public void addUser(User user) {
        firestore.collection("snake_user").add(user);
    }

    @Override
    public void setScoreToCache(Cache cache) {

    }
/*
    @Override
    public boolean addScoreToPlayer(int data, Players player) {
        Map<String, Object> put_data = new HashMap<>();
        put_data.put("date", Calendar.getInstance().getTime());
        put_data.put("value", data);
        put_data.put("user_id", User.getUser().getMacAddress());
        firestore.collection("score").add(put_data);
        return true;
    }

    @Override
    public void getData(OnCompleteListener listener) {
        firestore.collection("score").get().addOnCompleteListener(listener);
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

    @Override
    public void setScoreToCache(final Cache cache) {
        firestore.collection("score").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<DocumentSnapshot> result = task.getResult().getDocuments();
                List<Score> scores = new ArrayList<>();
                for (int i = 0; i<result.size();i++){
                    DocumentSnapshot snapshot = result.get(i);
                    Score s = new Score();
                    Timestamp timestamp = (Timestamp) snapshot.get("date");
                    s.setDate(timestamp.toDate());
                    s.setScore(((Long) snapshot.get("value")).intValue());
                    System.out.println(snapshot.get("user_id"));


                    scores.add(s);
                }
                cache.resetAllScore(scores);
            }
        });
    }
    */


}
