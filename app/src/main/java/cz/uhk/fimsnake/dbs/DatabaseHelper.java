package cz.uhk.fimsnake.dbs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;

import java.util.ArrayList;
import java.util.List;

import cz.uhk.fimsnake.model.user.Players;
import cz.uhk.fimsnake.model.user.User;

public class DatabaseHelper extends SQLiteOpenHelper implements IDAO {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_PLAYER1 = "player1_table";
    private static final String TABLE_PLAYER2 = "player2_table";
    private static final String COL0 = "ID";
    private static final String COL1 = "value";

    public DatabaseHelper(Context context) {
        super(context, "db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createtable = "CREATE TABLE " + TABLE_PLAYER1 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL1 + " INTEGER)";
        db.execSQL(createtable);

        createtable = "CREATE TABLE " + TABLE_PLAYER2 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL1 + " INTEGER)";
        db.execSQL(createtable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP IF TABLE EXISTS " + TABLE_PLAYER1;
        db.execSQL(sql);

        sql = "DROP IF TABLE EXISTS " + TABLE_PLAYER2;
        db.execSQL(sql);

        onCreate(db);
    }

    @Override
    public boolean addScorePlayer(int data, Players player) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, data);
        long result = -1;
        if (player == Players.PLAYER1) {
            Log.d(TAG, "add data " + data + " to " + TABLE_PLAYER1);
            result = database.insert(TABLE_PLAYER1, null, contentValues);
        } else if (player == Players.PLAYER2) {
            Log.d(TAG, "add data " + data + " to " + TABLE_PLAYER2);
            result = database.insert(TABLE_PLAYER2, null, contentValues);
        }
        return result != -1;
    }

    @Override
    public void getData(OnCompleteListener listener) {

    }

/*
    @Override
    public void getData(Players player) {
        SQLiteDatabase database = this.getReadableDatabase();

        String query = "SELECT * FROM ";

        if (player == Players.PLAYER1) {

            query += TABLE_PLAYER1;

        } else if (player == Players.PLAYER2) {

            query += TABLE_PLAYER2;

        }

        query += " ORDER BY "+ COL1 + " DESC";
        Cursor cursor = database.rawQuery(query, null);

        List<Integer> scores = new ArrayList<>();

        while (cursor.moveToNext()){
            scores.add(cursor.getInt(1));
        }

        return scores;

    }
*/


    @Override
    public void setUser(String mac) {
    }

    @Override
    public void addUser(User user) {

    }


}
