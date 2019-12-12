package org.likesyou.bensalcie.bongodoo;

import android.app.Application;

import org.likesyou.bensalcie.bongodoo.utils.FontLoader;

public class GameApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		FontLoader.loadFonts(this);

	}
}
