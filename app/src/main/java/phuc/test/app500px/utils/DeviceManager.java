/*
 * Copyright (c) 2015, Xenero | Complete IT Solutions. All rights reserved.
 */

package phuc.test.app500px.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.graphics.Point;
import android.location.Location;
import android.telephony.TelephonyManager;
import android.util.Patterns;
import android.view.Display;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.regex.Pattern;

public class DeviceManager {

    public static final String TAG = "DeviceManager";
    public static final String ANDROID = "Android";

    public static String getDeviceId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = telephonyManager.getDeviceId();
        SmartLog.error(DeviceManager.class, deviceId);
        return deviceId;
    }

    public static String getDeviceName(Context context) {
        String deviceName = DeviceManager.ANDROID + "-" + android.os.Build.MANUFACTURER + "-" + android.os.Build.MODEL;
        SmartLog.error(DeviceManager.class, deviceName);
        return deviceName;
    }

    @SuppressWarnings("deprecation")
    public static int getScreenWidth(Context context) {
        int width;
        if (android.os.Build.VERSION.SDK_INT >= 13) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            width = size.x;
        } else {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            width = display.getWidth();  // deprecated
        }
        return width;
    }

    @SuppressWarnings("deprecation")
    public static int getScreenHeight(Context context) {
        int height;
        if (android.os.Build.VERSION.SDK_INT >= 13) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            height = size.y;
        } else {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            height = display.getHeight();  // deprecated
        }
        return height;
    }

    /**
     * Get primary email on phone
     *
     * @return The primary email on the phone
     */
    public static String getEmail(Context context) {
        Pattern emailPattern = Patterns.EMAIL_ADDRESS;
        Account[] accounts = AccountManager.get(context).getAccounts();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                String possibleEmail = account.name;
                if (possibleEmail.length() > 0) {
                    return possibleEmail;
                }
            }
        }
        return null;
    }

    /**
     * Get phone number if possible
     *
     * @param context The context where we want to get phone number
     * @return The phone number
     */
    public static String getPhoneNumber(Context context) {
        TelephonyManager tMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String phoneNumber = tMgr.getLine1Number();
        SmartLog.error(DeviceManager.class, phoneNumber);
        return phoneNumber;
    }

    public static Location getPhoneLocation() {
        Location location = null;
        return location;
    }

    /**
     * Hide soft keyboard
     * <p></p>
     *
     * @param context
     * @param edt
     */
    public static void hideSoftKeyboard(Context context, EditText edt) {
        if (edt != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edt.getWindowToken(), 0);
        }
    }
}
