### Overview

Viblast PDN Android SDK extends your peer-assisted video delivery to the part of your audience using Android devices. Viblast PDN allows you to stream to web and mobile audiences up to 70% more efficiently.

Visit Viblast PDN's [web page](http://viblast.com/pdn/)

### Project setup
Let's assume you have an existing Android Studio project or you've just created one.

- Download the aar `viblast-<version>-release.aar` file
- In Android studio: New > New Module > Import JAR/AAR Package
- In Android studio: Select your module > Right Click > Open Module Settings (F4) > Dependencies > + > Module Dependency > viblast-<version>-release
- Add dependency for ExoPlayer in `build.gradle`:
```
dependencies {
    compile 'com.google.android.exoplayer:exoplayer:r2.4.3'
}
```

### Basic usage example

Viblast requires Internet access in order to work, so make sure you have
requested the appropriate permissions in your `AndroidManifest.xml` file

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android" >
	...
	<uses-permission android:name="android.permission.INTERNET" />
	...
</manifest>
```

Create a `ViblastView` inside your layout

```xml
...
<com.viblast.android.ViblastView
		android:id="@+id/viblast_view"
		android:layout_width="match_parent"
		android:layout_height="wrap_content"
		android:layout_gravity="center" />
...
```

Naturally, you can specify the parameters most suitable for your
application's interface. The code above is just an example.

The only thing you need is a ViblastPlayer instance. A good spot to
create it is in the Activity's onCreate method.

```java
public class MainActivity extends Activity {
	... // as usual
    private ViblastPlayer viblastPlayer;
    private ViblastView viblastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		... // as usual

		viblastView = (ViblastView)findViewById(R.id.viblast_view);

		final ViblastConfig vbConfig = new ViblastConfig();
		vbConfig.setCdnStream("your-cdn-stream");
		vbConfig.advancedConfig.put("enable-pdn", "true");
		vbConfig.advancedConfig.put("enable-realtime-loggger", "true");
		vbConfig.advancedConfig.put("realtime-logger-server", "wss://cs.viblast.com/rt");
		vbConfig.advancedConfig.put("key", "200057d28abdc9fb593eb654629f2f03c14fac9c5fc0825c899bd6095ad7a8de79ad770b4e99ec1581285ecb2cac1d6d");

		viblastPlayer = new ViblastPlayer(viblastView, vbConfig);
    }
```

Start Viblast on Activity start

```java
@Override
protected void onStart() {
	super.onStart();
	viblastPlayer.start();
}
```

Don't forget to stop and release Viblast when the Activity stops

```java
@Override
protected void onStop() {
	viblastPlayer.release();
	super.onStop();
}
```

That's about it. Your video playback through Viblast should begin after
you start your Activity.

### Licensing

To remove the Viblast logo you should get a license key and set it in ```vbConfig```:
```java
vbConfig.advancedConfig.put("key", "YOURKEY");
```

### Checking playback state
There are five playback states:
 - IDLE
  - ```The player is neither prepared or being prepared.```
 - BUFFERING
  - ```The player is prepared but not able to immediately play from the current position.
		This state typically occurs when more data needs to be buffered for playback to start.```
 - PLAYING
  - ```The player is playing.```
 - ENDED
  - ```The player has finished playing the media.```

There are two options for checking playback state:
 * check current playback state
  * Simply call ```viblastPlayer.getPlaybackState()```:
  ```java
  viblastPlayer = new ViblastPlayer(viblastView, vbConfig);
  playbackState = viblastPlayer.getPlaybackState();
  ```

 * listen for playback state changes
  * Add listener to ```viblastPlayer```:
  ```java
	viblastPlayer = new ViblastPlayer(viblastView, vbConfig);
	viblastPlayer.addListener(new Listener() {
		@Override
		public void onPlaybackStateChanged(ViblastPlayerState state) {
			// state contains new playback state
		}
		// ...
	});
  ```

### ExoPlayer

The current version of Viblast is built on top of ExoPlayer version *2.4.3*. It must be specified in `build.gradle`:
```
dependencies {
    compile 'com.google.android.exoplayer:exoplayer:r2.4.3'
}
```
