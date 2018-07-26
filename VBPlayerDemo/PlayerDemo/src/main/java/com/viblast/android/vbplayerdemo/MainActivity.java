package com.viblast.android.vbplayerdemo;

import android.app.Activity;
import android.os.Bundle;

import com.viblast.android.ViblastConfig;
import com.viblast.android.ViblastPlayer;
import com.viblast.android.ViblastView;

public class MainActivity extends Activity {
	private ViblastPlayer viblastPlayer;
	private ViblastView viblastView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viblastView = (ViblastView)findViewById(R.id.viblast_view);
		
		final ViblastConfig vbConfig = new ViblastConfig();
		
		// start faster, using the lowest resolution:
		vbConfig.advancedConfig.put("initial-abr-index", "0");

		vbConfig.setCdnStream("https://nimble.viblast.com:8081/live/high/playlist.m3u8");
		vbConfig.advancedConfig.put("log", "error");

		vbConfig.advancedConfig.put("enable-pdn", "false");
		vbConfig.advancedConfig.put("enable-realtime-loggger", "false");
		vbConfig.advancedConfig.put("key", "200057d28abdc9fb593eb654629f2f03c14fac9c5fc0825c899bd6095ad7a8deb99804cd93c2896bb19efc36e0d4af34dbdba0de07b6909d7d0dbe314ec6bdfc");
//		if you want to see Viblast logs:
//		vbConfig.advancedConfig.put("log", "verbose");

		viblastPlayer = new ViblastPlayer(viblastView, vbConfig);
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		viblastPlayer.start();
	}

	

	@Override
	protected void onStop() {
		viblastPlayer.release();
		
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		viblastPlayer.release();
		super.onDestroy();
	}


}
