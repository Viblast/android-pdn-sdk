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

		vbConfig.setCdnStream("http://cdn3.viblast.com/streams/hls/airshow/playlist.m3u8");
		
		vbConfig.advancedConfig.put("enable-pdn", "true");
		vbConfig.advancedConfig.put("enable-realtime-loggger", "true");
		vbConfig.advancedConfig.put("realtime-logger-server", "wss://cs.viblast.com/rt");
		vbConfig.advancedConfig.put("key", "200057d28abdc9fb593eb654629f2f03c14fac9c5fc0825c899bd6095ad7a8de5f83d52297310d340876edc852425419de9630208b8476a4307ad1082f80f033c8ffb5d5100fdf126e25ff7c77759cf1");
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


}
