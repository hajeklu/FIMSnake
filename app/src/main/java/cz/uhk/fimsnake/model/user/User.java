package cz.uhk.fimsnake.model.user;


import android.content.Context;

import cz.uhk.fimsnake.dbs.FireBase;
import cz.uhk.fimsnake.dbs.IDAO;

public class User {

    private String macAddress;
    private String alias;
    private static User user;

    public User(String macAddress, String alias) {
        this.macAddress = macAddress;
        this.alias = alias;
    }

    public User() {
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        User.user = user;
    }

    @Override
    public String toString() {
        return alias + " " + macAddress;
    }

    @Override
    public int hashCode() {
        return alias.length() * macAddress.length();
    }
}
