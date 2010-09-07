package com.corner23.android.samsungmobileap;

import java.util.List;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

public class Wrapper extends Activity {

    private final static Intent sIntent = new Intent();
    
    private final static String sPackage_Samsung     = "com.sec.android.app.mobileap";
    private final static String sClassName_Samsung   = "com.sec.android.app.mobileap.TW3MobileAP";
    private final static String sAction_Samsung      = "android.intent.action.MOBILEAP_SETTINGS";
    
    /**
     * Indicates whether the specified action can be used as an intent. This
     * method queries the package manager for installed packages that can
     * respond to an intent with the specified action. If no suitable package is
     * found, this method returns false.
     *
     * @param context The application's environment.
     * @param action The Intent action to check for availability.
     *
     * @return True if an Intent with the specified action can be sent and
     *         responded to, false otherwise.
     */
    public static boolean isIntentAvailable(Context context, String action) {
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> list =
                packageManager.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        final boolean bSamsungMobileAPAvailable = isIntentAvailable(this, sAction_Samsung);
        if (bSamsungMobileAPAvailable) {
            try {
                sIntent.setAction(sAction_Samsung);
                sIntent.setClassName(sPackage_Samsung, sClassName_Samsung);
            	startActivity(sIntent);   
            } catch (ActivityNotFoundException e) {
            	e.printStackTrace();
            }
        }
        
    	finish();
    }
}