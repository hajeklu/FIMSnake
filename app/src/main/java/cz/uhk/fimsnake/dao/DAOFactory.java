package cz.uhk.fimsnake.dao;

import android.content.Context;

/**
 * Created by Luboš Hájek in 2019
 */
public class DAOFactory {

    private static IDAO idao;

    public static IDAO getDAO(Context context) {
        if (idao == null)
            idao = new FireBase(context);
        return idao;
    }
}
