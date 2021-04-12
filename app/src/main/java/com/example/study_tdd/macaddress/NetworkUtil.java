package com.example.study_tdd.macaddress;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaDrm;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.provider.Settings;

import org.koin.java.KoinJavaComponent;

import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kotlin.Lazy;

/**
 * Created by hyosang on 2016. 7. 28..
 */
public class NetworkUtil {

    private Lazy<AlternativeMacAddress> alternativeMacAddressLazy = KoinJavaComponent.inject(AlternativeMacAddress.class);

    public static String getMacAddress(Context context) {
        if (VERSION.SDK_INT >= VERSION_CODES.M) {
            //WifiManager를 통한 MAC 조회 불가
            return getWifiMacAddress();
        }

        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        if (wifiManager == null) return "";

        String macAddress = "";

        try {

            macAddress = wifiManager.getConnectionInfo().getMacAddress();

            if (macAddress == null &&
                    !wifiManager.isWifiEnabled()) {

                wifiManager.setWifiEnabled(true);
                macAddress = wifiManager.getConnectionInfo().getMacAddress();
                wifiManager.setWifiEnabled(false);
            }

            if (macAddress != null) {
                macAddress = macAddress.toUpperCase();

                //구분자 제거하고 반환
                Pattern p = Pattern.compile("([0-9A-F]{2}).([0-9A-F]{2}).([0-9A-F]{2}).([0-9A-F]{2}).([0-9A-F]{2}).([0-9A-F]{2})");
                Matcher m = p.matcher(macAddress);
                if (m.matches()) {
                    macAddress = String.format("%s%s%s%s%s%s", m.group(1), m.group(2), m.group(3), m.group(4), m.group(5), m.group(6));
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return (macAddress == null) ? "" : macAddress;
    }

    public static String getWifiMacAddress() {
        try {
            String interfaceName = "wlan0";
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                if (!intf.getName().equalsIgnoreCase(interfaceName)) {
                    continue;
                }

                byte[] mac = intf.getHardwareAddress();
                if (mac == null) {
                    return "";
                }

                StringBuilder buf = new StringBuilder();
                for (byte aMac : mac) {
                    buf.append(String.format("%02X", aMac));
                }
                return buf.toString();
            }
        } catch (Exception exp) {

            exp.printStackTrace();
        }

        return "";
    }


    /**
     * MAC 주소 조회 불가에 대한 대처 방법.
     * 안드로이드 wiki RV Part Mac Address 획득 관련 문서 참조.
     * <p>
     * 본 메소드에서는 구분자를 제외하고 12자리로 반환한다.
     * *
     *
     * @return
     */
    public static String getAlternativeMacAddress() {
        return KoinJavaComponent.inject(AlternativeMacAddress.class).getValue().getAlternativeMacAddress();
    }

}

