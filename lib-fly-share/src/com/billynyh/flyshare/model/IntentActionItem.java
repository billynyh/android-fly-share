package com.billynyh.flyshare.model;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

public class IntentActionItem extends ActionItem {

    private CharSequence mLabel;
    private Drawable mIcon;

    private Intent mIntent;
    private ActivityInfo mActivityInfo;

    public IntentActionItem(Intent baseIntent, ResolveInfo info) {
        ComponentName chosenName = new ComponentName(
            info.activityInfo.packageName, info.activityInfo.name);

        mActivityInfo = info.activityInfo;
        mIntent = new Intent(baseIntent);
        mIntent.setComponent(chosenName);
    }
    
    
    @Override
    public CharSequence getLabel() {
        return mLabel;
    }

    @Override
    public Drawable getIcon() {
        return mIcon;
    }

    @Override
    public void doAction(Context context) {
        if (mIntent != null) {
            context.startActivity(mIntent);
        }
    }


    // for delay load
    public void load(PackageManager pm) {
        mLabel = mActivityInfo.loadLabel(pm);
        mIcon = mActivityInfo.loadIcon(pm);
    }
}
