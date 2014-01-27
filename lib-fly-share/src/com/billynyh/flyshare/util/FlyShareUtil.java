package com.billynyh.flyshare.util;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.billynyh.flyshare.model.IntentActionItem;

public class FlyShareUtil {
    
    public static List<IntentActionItem> fromIntentActivities(PackageManager pm, Intent intent, int flags) {
        List<ResolveInfo> infos = pm.queryIntentActivities(intent, flags);
        List<IntentActionItem> results = new ArrayList<IntentActionItem>();

        for (ResolveInfo info : infos) {
            results.add(fromResolveInfo(intent, info));
        }

        return results;
    }

    public static IntentActionItem fromResolveInfo(Intent baseIntent, ResolveInfo info) {
        IntentActionItem item = new IntentActionItem(baseIntent, info);
        return item;
    }
}
