package com.billynyh.flyshare.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

public abstract class ActionItem {
	public abstract CharSequence getLabel();
	public abstract Drawable getIcon();
	
	public abstract void doAction(Context context);
}
