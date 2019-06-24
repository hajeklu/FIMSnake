package cz.uhk.fimsnake.dao;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;

import javax.annotation.Nullable;

import cz.uhk.fimsnake.activity.services.NetworkService;
import cz.uhk.fimsnake.model.user.Score;
import cz.uhk.fimsnake.model.user.User;

/**
 * Created by Luboš Hájek in 2019
 */
public class FireBase implements IDAO {

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private Context context;
    private String mac;

    protected FireBase(Context context) {
        this.context = context;
        this.mac = NetworkService.getInstance().getMacAddress(context);
    }

    @Override
    public boolean addScoreToPlayer(int value) {
        Score s = new Score();
        s.setDate(new Date());
        s.setScore(value);
        firestore.collection("snake_user").document(mac).collection("scores").add(s);
        return false;
    }

    @Override
    public void setUserScore(final Cache cache) {
        firestore.collection("snake_user").document(mac).collection("scores").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                    int value = documentSnapshot.getLong("score").intValue();
                    Date date = documentSnapshot.getDate("date");
                    Score s = new Score();
                    s.setScore(value);
                    s.setDate(date);
                    cache.addToCurrentUserScore(s);
                }
            }
        });
    }

    @Override
    public void setUser(final Cache cache) {
        firestore.collection("snake_user").document(mac).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                User u = new User();
                Object alias = documentSnapshot.get("alias");
                Object userMac = documentSnapshot.get("macAddress");
                if (alias != null && userMac != null) {
                    u.setAlias(alias.toString());
                    u.setMacAddress(userMac.toString());
                } else {
                    u.setAlias(NetworkService.getInstance().getMacAddress(context));
                    u.setMacAddress(NetworkService.getInstance().getMacAddress(context));
                }
                cache.setUser(u, context);
            }
        });
    }

    @Override
    public void addUser(User user) {
        firestore.collection("snake_user").document(mac).set(user);
    }

    @Override
    public void setScoreToCache(final Cache cache) {
        firestore.collection("snake_user").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                    final String alias = documentSnapshot.get("alias").toString();
                    documentSnapshot.getReference().collection("scores").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                                int value = documentSnapshot.getLong("score").intValue();
                                Date date = documentSnapshot.getDate("date");
                                Score s = new Score();
                                s.setUserAlias(alias);
                                s.setScore(value);
                                s.setDate(date);

                                cache.add(s);
                            }
                        }
                    });
                }
            }
        });

    }

    @Override
    public void invalidAndRestartCache(Context context) {
        Cache cache = CacheFactory.getInstance();
        cache.clear();
        setScoreToCache(cache);
        setUserScore(cache);
    }

    @Override
    public void setNewAliasToCurrentUser(String alias) {
        firestore.collection("snake_user").document(mac).update("alias", alias).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(context, "Alias was updated.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
