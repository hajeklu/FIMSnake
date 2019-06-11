package cz.uhk.fimsnake.model.user;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class MacAddressProvider{

    private String macAddress;
    private static MacAddressProvider provider;
    private MacAddressProvider() { }

    public static MacAddressProvider getInstance(){
        if(provider == null){
            provider = new MacAddressProvider();

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
}
