package cz.uhk.fimsnake.model.user;

/**
 * Created by Luboš Hájek in 2019
 */
public class User {

    private String macAddress;
    private String alias;

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

    @Override
    public String toString() {
        return alias + " " + macAddress;
    }
}
