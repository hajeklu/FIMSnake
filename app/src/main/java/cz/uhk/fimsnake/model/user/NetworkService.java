package cz.uhk.fimsnake.model.user;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.InetAddress;

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

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }
}
