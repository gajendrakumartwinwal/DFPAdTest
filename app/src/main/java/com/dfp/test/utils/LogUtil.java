package com.dfp.test.utils;

import com.dfp.test.ads.AdType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Return system logs related to DFP Ad
 */
public class LogUtil {
    public static String getAdLogs() {
        StringBuilder log = null;
        try {
            Process process = Runtime.getRuntime().exec("logcat -d Ads");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            log = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    if (line.contains("Ads"))
                        log.append(line.substring(line.indexOf("Ads")) + "\n");
                } catch (Exception e) {
                }
            }
        } catch (IOException e) {
        }
        //Clear the logcat
        try {
            Runtime.getRuntime().exec("logcat -c Ads");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return log == null ? null : log.toString();
    }



    public static String getAdName(int type) {
        if (type == AdType.HEADER) {
            return "Header";
        }
        if (type == AdType.FOOTER) {
            return "Footer";
        }
        if (type == AdType.MREC) {
            return "Mrec  ";
        }
        return null;
    }
}
