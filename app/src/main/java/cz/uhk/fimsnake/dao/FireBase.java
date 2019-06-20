package cz.uhk.fimsnake.dao;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;

import javax.annotation.Nullable;

import cz.uhk.fimsnake.model.user.NetworkService;
import cz.uhk.fimsnake.model.user.Score;
import cz.uhk.fimsnake.model.user.User;

public class FireBase implements IDAO {

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private Context context;
    private String mac;

    public FireBase(Context context) {
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
                u.setAlias(documentSnapshot.get("alias").toString());
                u.setMacAddress(documentSnapshot.get("macAddress").toString());
                cache.setUser(u, context);
            }
        });
    }

    @Override
    public void addUser(User user) {
        firestore.collection("snake_user").add(user);
    }

    @Override
    public void setScoreToCache(final Cache cache) {
        cache.clear();
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
}
