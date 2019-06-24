package cz.uhk.fimsnake.activity.services;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * Created by Luboš Hájek in 2019
 */
public class NetworkService {

    private String macAddress;
    private static NetworkService provider;
    private NetworkService() { }

    public static NetworkService getInstance(){
        if(provider == null){
            provider = new NetworkService();

        }
        return provider;
    }

    public String getMacAddress(Context context) {
        if(macAddress == null) {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wInfo = wifiManager.getConnectionInfo();
            macAddress = wInfo.getMacAddress();
        }
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public boolean isInternetAvailable(Context context) {
        try {
            ConnectivityManager cm =
                    (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        } catch (Exception e) {
            return false;
        }
    }
}
