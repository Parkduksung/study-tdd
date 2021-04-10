package com.example.study_tdd.macaddress;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaDrm;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.provider.Settings;

import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hyosang on 2016. 7. 28..
 */
public class NetworkUtil {


    /**
     * MAC 주소 조회. Android M 부터 WifiManager 를 통한 MAC 주소 조회 불가.
     * 대체방법으로 Network interface 목록에서 wlan0 이름을 가진 장치의 MAC 을 조회하는것으로 처리되고 있었으나,
     * 가져오지 못하는 경우(단말)이 있어, API가 지원되지 않는 경우는 MAC 주소를 임의 생성한 값으로 대체하는것으로 처리할것.
     * 조회되지 않는 경우에 API에서의 반환값은 02:00:00:00:00:00 이다.
     * <p>
     * 본 메소드에서는 구분자를 제외하고 12자리로 반환한다.
     *
     * @param context
     * @return
     */
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
     * @param context
     * @return
     */

    public static String getAlternativeMacAddress(Context context) {
        String wideVineId = getWideVineId();
        String ssaId = getSSAId(context);

        String combineWideVineAndSSAId = wideVineId + ssaId;

        if (combineWideVineAndSSAId.isEmpty()) {
            return getTemporaryDateData();
        } else {
            return convertHashCode(combineWideVineAndSSAId);
        }
    }

    @SuppressLint("HardwareIds")
    private static String getSSAId(Context context) {
        try {
            String getSSAId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            return (getSSAId == null) ? "" : getSSAId;
        } catch (Exception e) {
            return "";
        }
    }

    private static String getWideVineId() {
        try {
            MediaDrm mediaDrm = new MediaDrm(new UUID(-0x121074568629b532L, -0x5c37d8232ae2de13L));
            byte[] wideVineId = mediaDrm.getPropertyByteArray(MediaDrm.PROPERTY_DEVICE_UNIQUE_ID);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return Base64.getEncoder().encodeToString(wideVineId).trim();
            } else {
                return android.util.Base64.encodeToString(wideVineId, android.util.Base64.DEFAULT).trim();
            }
        } catch (Exception e) {
            return "";
        }
    }

    private static String convertHashCode(String combineWideVineAndSSAId) {

        StringBuilder convertHashCode = new StringBuilder(String.valueOf(Math.abs(combineWideVineAndSSAId.hashCode())));

        String[] addArray = new String[]{"4", "4", "3", "0"};

        // 12자리 숫자를 만들어 주기 위한 로직.
        if (convertHashCode.length() < 12) {

            int convertHashCodeLength = convertHashCode.length();
            // 12자리 숫자가 아닌경우 빈공간에 순차적으로 배열의 값을 추가하여 12자리를 만들어준다.
            for (int i = convertHashCodeLength; i < 12; i++) {
                convertHashCode.append(addArray[(12 - i) % 4]);
            }
            return convertHashCode.toString();

        } else {

            // 12자리 숫자 초과인 경우 12자리까지 자른다.
            return convertHashCode.substring(0, 11);
        }
    }

    // wideVineId, SSAId 값이 모두 "" 인 경우, 년월일시분초 임시데이터를 만들어 저장 (IOS 방식과 동일)
    @SuppressLint("SimpleDateFormat")
    private static String getTemporaryDateData() {
        return new SimpleDateFormat("yyMMddHHmmss").format(new Date());
    }
}

